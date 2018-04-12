import java.util.Iterator;
import java.util.Map;

import com.butone.codeinf.model.CodeDef;
import com.butone.codeinf.util.CodeGenerator;
import com.butone.extend.CacheManager;
import com.butone.extend.ModelPathHelper;
import com.butone.sequencecode.SequenceCodeImpl;
import com.butone.utils.ModelExtUtils;
import com.justep.exception.BusinessException;
import com.justep.model.Activity;
import com.justep.model.Concept;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Process;
import com.justep.model.Relation;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.util.Utils;

public class SequenceCode {

	/**
	 * 预览通用编码
	 * @param codeGuid
	 * @param concept
	 * @param nodeValues
	 * @return
	 */
	public static Map<String, String> previewSequenceCode(String codeGuid, String concept, Map<String, String> nodeValues) {
		CodeDef def = CacheManager.getCodeDef(codeGuid);
		if (def == null) {
			throw new BusinessException("编号为" + codeGuid + "的通用编码定义未生成");
		}
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		return generator.previewCodeValue(def, concept, nodeValues);
	}

	/**
	 * 生成通用编码
	 * @param codeGuid
	 * @param concept
	 * @param nodeValues
	 * @param relation
	 * @param idValue
	 * @return
	 */
	public static String makeSequenceCode(String codeGuid, String concept, Map<String, String> nodeValues, String relation, String idValue) {
		CodeDef def = CacheManager.getCodeDef(codeGuid);
		if (def == null) {
			throw new BusinessException("未生成" + concept + "." + relation + "的编码定义");
		}
		return makeSequenceCode(def, concept, nodeValues, relation, idValue);
	}

	private static String makeSequenceCode(CodeDef codeDef, String concept, Map<String, String> nodeValues, String relation, final String idValue) {
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		String ret = generator.makeCodeValue(codeDef, nodeValues, concept, relation, idValue);
		return ret;
	}

	private static void releaseCodeValue(String userTable, String userField, String userKeyValues) {
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		generator.releaseCodeValue(userTable, userField, userKeyValues);
	}

	/**
	 * codeDef.paramNodes.length==0不会发起预览操作，所以CreateAfter之后 进行auto处理
	 */
	public static void makeSequenceCodeValueAfterCreateAction() {
		String conceptName = (String) ContextHelper.getActionContext().getParameter("concept");
		Process process = ContextHelper.getActionContext().getProcess();
		Model model = ModelUtils.getModel(ModelPathHelper.getProcessOntology(process));
		Concept concept = model.getUseableConcept(conceptName);
		Activity activity = ContextHelper.getActionContext().getActivity();
		String codeFields = ModelExtUtils.getActivityCodeFields(activity);
		if (Utils.isEmptyString(codeFields))
			return;

		Table table = (Table) ContextHelper.getActionContext().getParameter("table");
		Map<String, String> codeDefines = ModelExtUtils.getProcessCodeDefines(process);

		String[] fields = codeFields.split(",");
		for (String field : fields) {
			String[] args = field.split("\\.");
			if (!conceptName.equals(args[0]))
				continue;
			if (!"auto".equals(args[2]))
				continue;

			String fieldName = args[1];
			String codeGuid = null;
			if (codeDefines != null)
				codeGuid = codeDefines.get(field);
			if (codeGuid == null) {
				Relation r = concept.getRelation(fieldName);
				if (r != null) {
					codeGuid = ModelExtUtils.getCodeDef(r);
				}
			}
			if (codeGuid == null) {
				throw new BusinessException("字段" + field + "编码定义未生成。（原始表需生成工作表资源，引用表需生成流程模型）");
			}
			CodeDef def = CacheManager.getCodeDef(codeGuid);
			if (def == null) {
				throw new BusinessException("未生成" + conceptName + "." + fieldName + "的编码定义");
			}

			Iterator<Row> i = table.iterator();
			while (i.hasNext()) {
				Row row = i.next();
				// 编码字段值改变
				String value = previewSequenceCode(codeGuid, conceptName, null).get("sequenceValue");
				row.setValue(fieldName, value);
			}
		}

	}

	/**
	 * 保存前，生成通用编码。手动编码不处理。
	 * @see BizProcedureExt
	 */
	public static void makeSequenceCodeValueBeforeSaveAction() {
		String conceptName = (String) ContextHelper.getActionContext().getParameter("concept");
		Process process = ContextHelper.getActionContext().getProcess();
		Model model = ModelUtils.getModel(ModelPathHelper.getProcessOntology(process));
		Concept concept = model.getUseableConcept(conceptName);
		Activity activity = ContextHelper.getActionContext().getActivity();
		String codeFields = ModelExtUtils.getActivityCodeFields(activity);
		if (Utils.isEmptyString(codeFields))
			return;

		Table table = (Table) ContextHelper.getActionContext().getParameter("table");
		Map<String, String> codeDefines = ModelExtUtils.getProcessCodeDefines(process);

		String[] fields = codeFields.split(",");
		for (String field : fields) {
			String[] args = field.split("\\.");
			if (!conceptName.equals(args[0]))
				continue;
			String fieldName = args[1];
			// 手动编码不处理
			if ("manual".equals(args[2]))
				continue;
			String codeGuid = null;
			if (codeDefines != null)
				codeGuid = codeDefines.get(field);
			if (codeGuid == null) {
				Relation r = concept.getRelation(fieldName);
				if (r != null) {
					codeGuid = ModelExtUtils.getCodeDef(r);
				}
			}
			if (codeGuid == null) {
				throw new BusinessException("字段" + field + "编码定义未生成。（原始表需生成工作表资源，引用表需生成流程模型）");
			}

			CodeDef def = CacheManager.getCodeDef(codeGuid);
			if (def == null) {
				throw new BusinessException("未生成" + conceptName + "." + fieldName + "的编码定义");
			}

			@SuppressWarnings("unchecked")
			Map<String, Object> variants = (Map<String, Object>) ContextHelper.getActionContext().getParameter("variants");
			// 序列编码节点值，当前表的所有编码字段 Map<FieldName,Map<rowid,Map<ParamName,ParamValue>>>
			@SuppressWarnings("unchecked")
			Map<String, Object> sequenceNodeValues = variants == null ? null : (Map<String, Object>) variants.get("sequenceNodeValues");

			// 指定字段的多行的nodeValues Map<rowid,Map<ParamName,ParamValue>>
			@SuppressWarnings("unchecked")
			Map<String, Object> multiRowNodeValues = (Map<String, Object>) (sequenceNodeValues != null ? sequenceNodeValues.get(fieldName) : null);

			Iterator<Row> i = table.iterator();
			while (i.hasNext()) {
				Row row = i.next();
				if (row.getState() == ModifyState.EDIT && Utils.isEmptyString(row.getString(fieldName))
						&& Utils.isNotEmptyString((String) row.getOldValue(fieldName))) {
					// 如果编码字段清空，释放编码
					releaseCodeValue(conceptName, fieldName, row.getString(conceptName));
				} else {
					// 新增、修改时，如果编码字段值改变，产生编码值
					String newValue = row.getString(fieldName);
					boolean b = ModifyState.NEW.equals(row.getState()) || ModifyState.EDIT.equals(row.getState()) && newValue != null
							&& !newValue.equals(row.getOldValue(fieldName));
					if (b) {
						String idValue = row.getString(conceptName);
						@SuppressWarnings("unchecked")
						Map<String, String> nodeValues = (Map<String, String>) (multiRowNodeValues != null ? multiRowNodeValues.get(idValue) : null);
						String value = makeSequenceCode(codeGuid, conceptName, nodeValues, fieldName, idValue);
						row.setValue(fieldName, value);
					}
				}

			}

		}
	}

	public static Map<String, Object> queryUnusedSequenceCodeValues(String codeGuid, String concept, Map<String, String> nodeValues) {
		CodeDef def = CacheManager.getCodeDef(codeGuid);
		if (def == null) {
			throw new BusinessException("编号为" + codeGuid + "的通用编码定义未生成");
		}
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		return generator.queryUnusedCodeValues(def, concept, nodeValues);
	}

	public static void lockUnusedSequenceCodeValue(String codeGuid, String idValue, String concept, String groupValue, String codeValue,
			String relation) {
		CodeDef def = CacheManager.getCodeDef(codeGuid);
		if (def == null) {
			throw new BusinessException("编号为" + codeGuid + "的通用编码定义未生成");
		}
		CodeGenerator generator = new CodeGenerator();
		generator.setSequenceCode(new SequenceCodeImpl());
		generator.lockUnusedCodeValue(def, groupValue, codeValue, concept, relation, idValue);
	}

	public static void releaseUsedSequenceCodeValue(String concept, String relation, String idValue) {
		new SequenceCodeImpl().releaseCodeValue(concept, relation, idValue);
	}
}