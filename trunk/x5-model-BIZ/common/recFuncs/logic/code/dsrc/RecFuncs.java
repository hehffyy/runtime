import java.util.*;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.BizData;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableUtils;
import com.justep.system.opm.PersonMember;

public class RecFuncs {

	public static Map<String, String> getAllDepts() {
		Map<String, String> depts = new HashMap<String, String>();
		List<PersonMember> list = ContextHelper.getPersonMembers();
		for (PersonMember m : list) {
			if(m.getDept() ==null)
				continue;
			String id = m.getDept().getID();
			String name = m.getDept().getName();
			if (depts.containsKey(id))
				continue;
			if(name.equals("厅机关处室"))
				continue;
			depts.put(id, name);
		}
		return depts;
	}

	public static Table queryDeptRec(String concept, String idColumn, String select, String from, String condition, List range, String filter,
			Boolean distinct, Integer offset, Integer limit, String columns, String orderBy, String aggregate, String aggregateColumns,
			Map variables, String dataModel, String fnModel) {
		select =select + ",(select  SQL.wm_concat(concat(t1.sActivityName,':',SQL.nvl(t1.sExecutorPersonName,'无')))  from SA_Task t1  where  t1.sData1 = B_BizRec AND t1.sActivityName is not null and t1.sExecutorFID is not null and t1.sStatusID in ('tesExecuting', 'tesPaused', 'tesReady')  ) as fBlr";
		columns = columns+",fBlr";
		StringBuffer  filters = new StringBuffer();
		String fDeptID = (String)variables.get("fDeptID");
		String fBizName = (String)variables.get("fBizName");
		String fKind = (String)variables.get("fKind");
		String fCustomFilter = (String)variables.get("fCustomFilter");
		
		//在办过滤条件
		if(fKind.startsWith("fIn")||fKind.startsWith("fOut"))
			filters.append(" not exists (select 1 from B_BJJLB bj where bj.fBizRecId = B_BizRec) and B_BizRec.fStatus in ('bsProcessing') ");
		else
			filters.append(" not exists (select 1 from B_BJJLB bj where bj.fBizRecId = B_BizRec) and B_BizRec.fStatus in ('bsProcessing','bsSuspended') ");
		//任务状态过滤条件
		String taskStatus="";
		if(fKind.startsWith("fIn"))
			taskStatus = "t.sStatusID in ('tesExecuting','tesPaused','tesReady')";
		else if (fKind.startsWith("fOut"))
			taskStatus = "not (t.sStatusID in ('tesExecuting','tesPaused','tesReady','tesCanceled'))";
		
		if(taskStatus.equals(""))
			filters.append(" and exists (select 1 from SA_Task t where t.sData1=B_BizRec and t.sStatusID <> 'tesCanceled' and t.sExecutorDeptID='"+fDeptID+"') ");
		else {
			filters.append(" and exists (select 1 from SA_Task t where t.sData1=B_BizRec and t.sStatusID <> 'tesCanceled' and "+taskStatus+" and t.sExecutorDeptID='"+fDeptID+"') ");
			if(fKind.startsWith("fOut"))
				filters.append(" and not exists (select 1 from SA_Task t where t.sData1=B_BizRec and t.sStatusID in ('tesExecuting','tesPaused','tesReady') and  t.sExecutorDeptID='"+fDeptID+"') ");
		}
		//挂起过滤条件
		String hangFilter="";
		if(fKind.equalsIgnoreCase("fApprizeCount"))
			hangFilter = " exists (select 1 from B_AJGQJLB g where g.fBizRecId=B_BizRec and g.fJSSJ is null and g.fGQLX='skApprize')";
		else if (fKind.equalsIgnoreCase("fSpecialProcedureCount"))
			hangFilter = " exists (select 1 from B_AJGQJLB g where g.fBizRecId=B_BizRec and g.fJSSJ is null and g.fGQLX='skSpecialProcedure')";
		else if (fKind.equalsIgnoreCase("fQtGqCount"))
			hangFilter = " exists (select 1 from B_AJGQJLB g where g.fBizRecId=B_BizRec and g.fJSSJ is null and g.fGQLX<>'skApprize' and g.fGQLX<>'skSpecialProcedure')";
		if(!hangFilter.equals(""))
			filters.append(" and ").append(hangFilter);
		//红黄牌过滤条件
		String limitFilter="1=1";
		if(fKind.contains("Zc"))
			 limitFilter = " B_BizRec.fRemainingDays>5 or B_BizRec.fRemainingDays is null";
		else if(fKind.contains("Yj"))
			 limitFilter = " B_BizRec.fRemainingDays<=5 and B_BizRec.fRemainingDays>=2";
		else if (fKind.contains("Yellow"))
			limitFilter = " B_BizRec.fRemainingDays<2 and B_BizRec.fRemainingDays>=0";
		else if (fKind.contains("Red"))
			limitFilter = " B_BizRec.fRemainingDays<0";
		if(!limitFilter.equals(""))
			filters.append(" and ").append("("+limitFilter+")");
		//业务名称过滤
		if(fBizName!=null && !fBizName.equals("") && !fBizName.equals("汇总"))
			filters.append(" and ").append(" B_BizRec.fBizName='"+fBizName+"'");
		//自定义过滤条件
		if(fCustomFilter!=null && !fCustomFilter.equals(""))
			filters.append(" and ").append("( fBizNo like '%"+fCustomFilter+"%' ").append("or fRecTitle like '%"+fCustomFilter+"%') ");
		filter = filters.toString();
		return BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
	}

	public static Table queryV_DeptRecSum(List range,String concept,String select,String from,String aggregate,String dataModel,String fnModel,String condition,Boolean distinct,String idColumn,String filter,Integer limit,Integer offset,String columns,String orderBy,String aggregateColumns,Map variables){
		Table table = BizData.query(concept, idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate,
				aggregateColumns, variables, dataModel, fnModel);
		Iterator<Row> iter = table.iterator();
		Table resultTable = TableUtils.createTable("V_DeptRecSum", "/common/recFuncs/data");
		Row totalRow = resultTable.appendRow();
		totalRow.setString("fBizName", "汇总");
		while (iter.hasNext()) {
			Row row = iter.next();
			Row newRow=resultTable.appendRow();
			for (int i = 0; i < table.getMetaData().getColumnCount(); i++) {
				String column = table.getMetaData().getColumnName(i);
				newRow.setValue(column, row.getValue(column));
				if(table.getMetaData().getColumnMetaData(column).getType().equalsIgnoreCase("integer")){
					Integer value = totalRow.getInteger(column);
					if(value==null)
						value=0;
					totalRow.setInteger(column,value+row.getInteger(column));
				}
			}
		}
		return resultTable;
	}
}