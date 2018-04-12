import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.util.Base64;
import com.butone.flowbiz.FileImportExoprtRuntime;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.model.FileImportPlugin;
import com.butone.utils.StringUtils;
import com.butone.x5Impl.BusinessServerImpl;
import com.butone.xml.JaxbUtils;
import com.justep.exception.BaseRuntimeException;
import com.justep.exception.BusinessException;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.message.BusinessMessages;
import com.justep.model.Activity;
import com.justep.model.BusinessActivity;
import com.justep.model.Config;
import com.justep.model.ModelUtils;
import com.justep.model.Process;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Table;
import com.justep.system.transform.ParameterTransform;
import com.justep.util.Utils;

public class FileImportExport {
	/**
	 * 获得文件解析器
	 * 
	 * @param fileType
	 * @return
	 */
	private static String getFileParserClass(String fileType) {
		Config config = (Config) ModelUtils.getModelObjectByFullName("/base/system/fileImpExp/logic/code/fileParser", Config.TYPE);
		for (String name : config.getNames()) {
			if (name.equals(fileType))
				return config.getItem(name).getValue();
		}
		throw new BusinessException("不支持" + fileType + "类型的文件处理");
	}

	@SuppressWarnings("rawtypes")
	private static Map transMapParam(String content) throws UnsupportedEncodingException {
		content = new String(Base64.decodeFast(content), "utf-8");
		String xml = "<root xmlns:xbiz=\"http://www.justep.com/xbiz\">" + content + "</root>";
		SAXReader reader = new SAXReader();
		reader.setEncoding("utf-8");
		Document doc;
		try {
			doc = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
		} catch (Exception e) {
			throw new BusinessException("解析Map xml格式失败");
		}
		Element paramElement = (Element) doc.getRootElement().elements().get(0);
		return transMapParam(paramElement);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map transMapParam(Element paramElement) {
		HashMap ret = new HashMap();
		Iterator<Element> localIterator = paramElement.elementIterator();
		while (localIterator.hasNext()) {
			Element localElement = (Element) localIterator.next();
			String str = localElement.attributeValue("key");
			if (Utils.isEmptyString(str))
				throw BusinessException.create(BusinessMessages.class, "JUSTEP150076", new Object[] { paramElement.asXML() });
			List<Element> localList = localElement.elements();
			if ((Utils.isNotNull(localList)) && (localList.size() > 0)) {
				if (localList.size() > 1)
					throw BusinessException.create(BusinessMessages.class, "JUSTEP150077", new Object[] { paramElement.asXML() });
				ret.put(str, ParameterTransform.transParam(localList.get(0), null, null));
			} else {
				ret.put(str, null);
			}
		}
		return ret;
	}

	/**
	 * 文件导入
	 * 
	 * @param input
	 * @param url
	 * @param bizRecId
	 * @param variants
	 * @param filters
	 * @param returnData
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Table> externalFileImport(InputStream input, String targetProcess, String targetActivity, String url, String bizRecId, Object variants, Object filters,
			String returnData, String fileName) throws UnsupportedEncodingException {
		ActionContext actionContext = ContextHelper.getActionContext();
		File file = new File(FileSystemWrapper.instance().getRealPath(url));
		if (!file.exists()) {
			new RuntimeException("文件导入的配置文件不存在:" + url);
		}
		FileImportPlugin plugin = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			plugin = (FileImportPlugin) JaxbUtils.unMarshal(in, "utf-8", FileImportPlugin.class);
			String logic = plugin.getMappingLogic();
			if (Utils.isNotEmptyString(logic) && !logic.startsWith("<")) {
				logic = new String(StringUtils.base64Decode(logic.getBytes("utf-8")), "utf-8");
				plugin.setMappingLogic(logic);
			}

		} catch (Exception e) {
			throw new BusinessException("加载文件导入配置失败:" + url, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		Process bizProcess = null;
		Activity bizActivity = null;
		if (Utils.isEmptyString(targetProcess) && Utils.isEmptyString(targetActivity)) {
			bizProcess = actionContext.getProcess();
			bizActivity = actionContext.getActivity();
		} else {
			bizProcess = ModelUtils.getProcess(targetProcess);
			bizActivity = bizProcess.getActivity(targetActivity);
		}
		Utils.check(bizProcess != null && bizActivity != null, "参数错误，无法获取目标业务及环节");

		ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
		boolean releaseContext = context == null;
		if (releaseContext)
			context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);
		if (!(bizActivity instanceof BusinessActivity)) {
			if (filters != null) {
				if (filters instanceof Map) {
					context.addBizDataFilters((Map<String, String>) filters);
				} else if (filters instanceof String) {
					context.addBizDataFilters((Map<String, String>) transMapParam((String) filters));
				}
			}
		}

		try {
			FileImportExoprtRuntime runtime = new FileImportExoprtRuntime(context);
			if (Utils.isNotEmptyString(fileName)) {
				fileName = new String(Base64.decodeFast(fileName), "utf-8");
			}

			Map<String, Object> variantMap = null;
			if (variants != null) {
				if (variants instanceof Map) {
					variantMap = ((Map<String, Object>) variants);
				} else if (variants instanceof String) {
					variantMap = (Map<String, Object>) transMapParam((String) variants);
				}
			}
			if (variantMap == null) {
				variantMap = new HashMap<String, Object>();
			}
			// 导入文件名传递给计算组件
			variantMap.put("_importFileName", fileName);
			runtime.setVariants(variantMap);

			runtime.setParserClassName(getFileParserClass(plugin.getFileType()));
			runtime.setPlugin(plugin);
			Map<String, Table> ret = runtime.importData(input);
			if ("true".equalsIgnoreCase(returnData)) {
				return ret;
			}
		} catch (Exception e) {
			if (e instanceof BaseRuntimeException)
				throw (BaseRuntimeException) e;
			else
				throw new BusinessException("导入文件失败:" + e.getMessage(), e);
		} finally {
			if (releaseContext)
				ProcessLogicPluginContext.removeLogicPluginContext(context, true);
		}
		return null;
	}

	public static Map<String, Table> virtualFileImport(String targetProcess, String targetActivity, String url, String bizRecId, Map<String, Object> variants, Map<String, String> filters,
			Boolean returnData) throws FileNotFoundException, UnsupportedEncodingException {
		return externalFileImport(null, targetProcess, targetActivity, url, bizRecId, variants, filters, returnData == null ? "false" : returnData.toString(), null);
	}

	/**
	 * 异步文件导出
	 * 
	 * @author tangkejie
	 * 
	 */
	static class AsyncExportFile implements Runnable {
		ProcessLogicPluginContext context;
		Process bizProcess;
		Activity bizActivity;
		String bizRecId;
		Map<String, String> filters;
		Map<String, Object> variants;
		FileImportPlugin plugin;
		File exportFile;

		public AsyncExportFile(Process bizProcess, Activity bizActivity, String bizRecId, FileImportPlugin plugin, Map<String, String> filters, Map<String, Object> variants, File exportFile) {
			this.bizActivity = bizActivity;
			this.bizProcess = bizProcess;
			this.bizRecId = bizRecId;
			this.exportFile = exportFile;
			this.filters = filters;
			this.plugin = plugin;
			this.variants = variants;
		}

		public AsyncExportFile(ProcessLogicPluginContext context, FileImportPlugin plugin, Map<String, String> filters, Map<String, Object> variants, File exportFile) {
			this.context = context;
			this.exportFile = exportFile;
			this.filters = filters;
			this.plugin = plugin;
			this.variants = variants;
		}

		public void run() {
			BusinessServerImpl.createRequestContext2(null, null, null, null, null);
			try {
				this.execute();
			} finally {
				BusinessServerImpl.removeRequestContext();
			}
		}

		private void execute() {
			ProcessLogicPluginContext context = this.context;
			if (context == null)
				context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);

			boolean releaseContext = context == null;
			if (releaseContext)
				context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);
			if (filters != null)
				context.addBizDataFilters(filters);
			try {
				FileImportExoprtRuntime runtime = new FileImportExoprtRuntime(context);
				runtime.setParserClassName(getFileParserClass(plugin.getFileType()));
				runtime.setVariants(variants);
				runtime.setPlugin(plugin);
				runtime.exportData(exportFile);
			} catch (Exception e) {
				if (e instanceof BaseRuntimeException)
					throw (BaseRuntimeException) e;
				else
					throw new BusinessException("导入文件失败:" + e.getMessage(), e);
			} finally {
				if (releaseContext)
					ProcessLogicPluginContext.removeLogicPluginContext(context, true);
			}
		}
	}

	/**
	 * 外部文件导出为临时文件,返回临时文件的文件名
	 * 
	 * @param url
	 * @param bizRecId
	 * @param variants
	 * @param filters
	 * @return
	 */
	public static String exportExternalFile(String targetProcess, String targetActivity, String url, String bizRecId, Map<String, Object> variants, Map<String, String> filters) {
		ActionContext actionContext = ContextHelper.getActionContext();
		File file = new File(FileSystemWrapper.instance().getRealPath(url));
		if (!file.exists()) {
			new RuntimeException("文件导入的配置文件不存在:" + url);
		}
		FileImportPlugin plugin = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			plugin = (FileImportPlugin) JaxbUtils.unMarshal(in, "utf-8", FileImportPlugin.class);
			String logic = plugin.getMappingLogic();
			if (Utils.isNotEmptyString(logic) && !logic.startsWith("<")) {
				logic = new String(StringUtils.base64Decode(logic.getBytes("utf-8")), "utf-8");
				plugin.setMappingLogic(logic);
			}
		} catch (Exception e) {
			throw new BusinessException("加载文件导入配置失败:" + url, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		Process bizProcess = null;
		Activity bizActivity = null;
		if (Utils.isEmptyString(targetProcess) && Utils.isEmptyString(targetActivity)) {
			bizProcess = actionContext.getProcess();
			bizActivity = actionContext.getActivity();
		} else {
			bizProcess = ModelUtils.getProcess(targetProcess);
			bizActivity = bizProcess.getActivity(targetActivity);
		}
		Utils.check(bizProcess != null && bizActivity != null, "参数错误，无法获取目标业务及环节");
		File exprotFile = FileImportExoprtRuntime.newTempFile();

		if (Boolean.TRUE.equals(plugin.getAsync())) {
			// 异步线程执行
			AsyncExportFile export = new AsyncExportFile(bizProcess, bizActivity, bizRecId, plugin, filters, variants, exprotFile);
			new Thread(export).start();
		} else {
			// 同步执行
			ProcessLogicPluginContext context = ProcessLogicPluginContext.findLogicPluginContext(bizActivity, bizRecId);
			boolean releaseContext = context == null;
			if (releaseContext)
				context = ProcessLogicPluginContext.createLogicPluginContext(bizProcess, bizActivity, bizRecId);
			try {
				AsyncExportFile export = new AsyncExportFile(context, plugin, filters, variants, exprotFile);
				export.execute();
			} catch (Exception e) {
				if (e instanceof BaseRuntimeException)
					throw (BaseRuntimeException) e;
				else
					throw new BusinessException("导入文件失败:" + e.getMessage(), e);
			} finally {
				if (releaseContext)
					ProcessLogicPluginContext.removeLogicPluginContext(context, true);
			}

		}
		return exprotFile.getName();
	}

	private static long DeleteDownloadTempFileTime = -1;
	private static long Interval = 3;

	// 删除几天前的excel导出文件
	private static void deleteDownloadTempFile() {
		long t = Interval * 24 * 60 * 60 * 1000;
		long ct = System.currentTimeMillis();
		Date d = new Date();
		if (ct - DeleteDownloadTempFileTime >= t) {
			DeleteDownloadTempFileTime = ct;
			File tempDir = new File(FileImportExoprtRuntime.getTempDir());
			for (File f : tempDir.listFiles()) {
				if (f.isFile() && f.getName().endsWith(FileImportExoprtRuntime.getTempExtName()) && d.getTime() - t >= f.lastModified())
					try {
						f.delete();// 删除失败,忽略异常
					} catch (Exception e) {
					}
			}
		}
	}

	public static Object downloadExternalFile(String fileName) {
		// 删除目录下的临时文件
		deleteDownloadTempFile();
		FileInputStream fis = null;
		File tmpFile = new File(FileImportExoprtRuntime.getTempDir() + "/" + fileName);
		try {
			fis = new FileInputStream(tmpFile);
		} catch (FileNotFoundException e) {
			throw new BusinessException("文件不存在");
		}
		return fis;
	}

}