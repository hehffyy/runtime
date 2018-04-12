import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.spi.FlowControlUtils;
import com.butone.utils.ContextUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Transaction;
import com.justep.system.opm.OrgUnit;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessUtils;
import com.justep.system.util.CommonUtils;

public class InnerBz {

	public static void bjgz(String suspendInfo,String bizRecId,String isSuspend,String reason) throws Exception {
		Transaction trans = ContextHelper.getTransaction();
		trans.begin();
		try {
			String deptid= ContextHelper.getPersonMember().getDept().getID();
			Object taskId = SysUtils.queryFldValue("select SID from SA_task where sData1=? and sexecutordeptid=? and sStatusID in ('tesExecuting','tesReady') and sKindID='tkTask'",
					bizRecId,deptid);
			if(taskId==null)
				throw new Exception("案卷已经挂起!");
			if (isSuspend.equals("1")){
				ProcessControl control = ProcessUtils.suspendProcessQuery(taskId.toString());
				JSONObject json = JSONObject.parseObject(suspendInfo);
				control.getExts().put("suspendInfo", json);
				ProcessUtils.suspendProcess(taskId.toString(), control);
			}
			
			//更新补正信息
			SysUtils.executeSql("update B_BzInfo set FDoState='已提交',fReason=?,FStartTime=sysDate where FBizRecID=?",reason, bizRecId);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} 
	}

	public static void bjsl(String suspendInfo,String bizRecId) throws Exception {
		Object taskId = SysUtils.queryFldValue("select FTaskID from b_Ajgqjlb where FBizRecID=? and FJSSJ is null ", bizRecId);
		if (taskId == null)
			throw new Exception("案卷已经补正受理！");
		Transaction trans = ContextHelper.getTransaction();
		trans.begin();
		try {
			JSONObject json = JSONObject.parseObject(suspendInfo);
			ContextHelper.getRequestContext().put(FlowBizConsts.RequestContext_BizRecSuspendInfo, json);
			ProcessUtils.resumeProcess(taskId.toString());
			
			//更新补正信息状态
			SysUtils.executeSql("update B_BzInfo set FDoState='补正受理',FState='已完成', " + "FFinishTime=sysdate where FBizRecID=? and FState='未完成'",
					bizRecId);
			//新增附件
			//SysUtils.executeSql("insert into ", params)；
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw new Exception(e.getMessage());
		}
	}

	public static String startHs(String bizrecId,String hsProcess,String hsActivity){
		List<OrgUnit> executors = new ArrayList<OrgUnit>();
		executors.add(ContextHelper.getPersonMember());
		String hsBizRecId=CommonUtils.createGUID();
		SysUtils.executeSql("insert into B_BIZRECRELATION(fBizRecId,fParentID,fKind) values(?,?,?)", hsBizRecId, bizrecId, "补正会审");
		FlowControlUtils.startFlow(hsBizRecId, hsProcess, hsActivity, executors);
		//更新补正信息状态
		SysUtils.executeSql("update B_BzInfo set FDoState='会审中', " + "FFinishTime=sysdate where FBizRecID=? and FState='未完成'",
				bizrecId);
		return hsBizRecId;
	}

	public static void secondBz(String bizRecId,String reason){
		//查询当前补正信息
		String sql = "select * from B_BzInfo where FBizRecID=? and FState='未完成'";
		Map<String,Object> vals=SysUtils.queryFldsValue(sql, bizRecId);
		if(vals==null){
			throw new BusinessException("案卷状态异常");
		}
		
		//插入二次补正信息
		sql = "insert into B_BzInfo(FBZID ,VERSION,FBIZRECID,FDEPTID,FPERSONID,FPERSONNAME,FTASKID,FSTARTTIME,FREASON, FSTATE,FKIND,FDOSTATE,FSFGQ)"
			+" select sys_guid(),0,FBIZRECID,FDEPTID,FPERSONID,FPERSONNAME,FTASKID,sysdate,?,'未完成','二次补正','已提交','0' from B_BzInfo where FBZID=?";
		
		//更新补正信息状态
		SysUtils.executeSql("update B_BzInfo set FState='已完成', " + "FFinishTime=sysdate where FBzID=?",
				vals.get("FBZID"));
		//新增二次补正
		SysUtils.executeSql(sql,reason,vals.get("FBZID"));
	}
}