import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.extend.TaskUtils;
import com.butone.flowbiz.FlowBizConsts;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;

public class Widgets {
 

	public static String queryDbsxTask() {
		return "[{name:'未阅件','count':'32','icon':'myIcon-wy','url':'/UI/GZMOI/official/process/receiveDoc/businessDocActivity.w'},{name:'未阅件','count':'32','icon':'myIcon-wy','url':'/UI/GZMOI/official/process/receiveDoc/businessDocActivity.w'}]";
	}

	public static String queryAjtjTask() {
		//TODO  动态提取数据,构造数据data
		//String data = "[{'name':'办文总数','count':'23','icon':'ico1','url':'/UI/GZMOI/official/process/receiveDoc/businessDocActivity.w'},
		//{'name':'在办案卷','count':'23','icon':'ico2','url':''},{'name':'已办案卷','count':'23','icon':'ico3','url':''},
		//{'name':'新增案卷','count':'23','icon':'ico4','url':''},{'name':'正常案卷','count':'23', 'icon':'ico5','url':''},
		//{'name':'预警案卷',	'count':'23','icon':'ico6','url':''},{'name':'黄牌案卷','count':'23','icon':'ico7','url':''},
		//{'name':'红牌案卷','count':'23','icon':'ico8','url':''}]";
		//return data;
		Iterator<Row> rows = getBizInfoTable().iterator() ;
		JSONArray array = new JSONArray();
		
		while(rows.hasNext()){
			Row row = (Row)rows.next();
			JSONObject object = new JSONObject();
			object.put("count", row.getValue("CNT").toString());
			object.put("name", row.getValue("BIZTYPE").toString());
			object.put("icon", row.getValue("ICO").toString());
			object.put("url", "/UI/SA/task/taskCenter/mainActivity.w");
			array.add(object);
		}
 
		return array.toString();	
	}

	public static String queryAjtbTask() {

		Iterator<Row> rows = getBizInfoTable().iterator() ;
		JSONArray array = new JSONArray();
		
		while(rows.hasNext()){
			Row row = (Row)rows.next();
			JSONObject object = new JSONObject();
			if(Integer.parseInt(row.getValue("CNT").toString())==0) continue;
			object.put("value", row.getValue("CNT").toString());
			object.put("name", row.getValue("BIZTYPE").toString());
			array.add(object);
		}

		//新增案卷：   受理时间为当日的    已办案卷： fStatusName ='已办结'   已办案卷：  fStatusName ='办理中'    
		//红牌 小于等于0    黄牌 大于0小于等于1    黄牌 ：  小于3天是黄牌      预警是小于等于5天 
		//return "[{value:310, name:'新增案卷'},{value:310, name:'已办案卷'},{value:310, name:'在办案卷'},{value:310, name:'办文总数'}]";
		return array.toString();

	}
	
	private static Table  getBizInfoTable(){
		StringBuffer sql = new StringBuffer();
		String condition = TaskUtils.getExecutorCondition("b", ContextHelper.getPerson().getPersonMembers(), true);
		sql.append("with task as ( select fStatusName, freceivetime, (case when a.flimitdate is null then 99999999 else (select count(*)-1 from b_workdaysmang m where m.fisworkday='是' and fDate between trunc(sysdate) and trunc(a.flimitdate)) end) as days from b_bizRec a ")
		.append(" left Join sa_task b on a.fFlowid = b.Sflowid where (b.sStatusID='tesReady' or b.sStatusID='tesExecuting') and ").append(condition).append(")");
		sql.append(" select count(*) cnt , '新增案卷' bizType, 'ico4' ico from task task where to_char(task.freceivetime,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')")
		.append(" union All select count(*) cnt , '在办案卷' bizType ,'ico2' ico from task task where task.fStatusName ='办理中'")  
		.append(" union All select count(*) cnt, '办结案卷' bizType ,'ico3' ico from task task where task.fStatusName in ('办结','作废办结')") 
		.append(" union All select count(*) cnt, '预警案卷' bizType ,'ico3' ico from task task where task.fStatusName ='办理中' and task.days>1 and task.days <=5")
		.append(" union All select count(*) cnt, '黄牌案卷' bizType ,'ico3' ico from task task where task.fStatusName ='办理中' and task.days>0 And task.days <=1")
		.append(" union All select count(*) cnt, '红牌案卷' bizType ,'ico3' ico from task task where task.fStatusName ='办理中' and task.days<=0");
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put(DatabaseProduct.DEFAULT.name(), sql.toString());
		return SQL.select(sqlmap, null, FlowBizConsts.DATA_MODEL_SYSTEM);
	}
	 
}