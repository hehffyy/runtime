import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONObject;
import com.butone.flowbiz.FinishKind;
import com.butone.flowbiz.FlowBizConsts;
import com.butone.flowbiz.SuspendKind;
import com.butone.spi.JsonUtilsUtils;
import com.butone.utils.SysUtils;
import com.butone.workdate.WorkDayUtils;
import com.justep.exception.BusinessException;
import com.justep.system.action.ActionUtils;
import com.justep.system.context.ActionContext;
import com.justep.system.context.ContextHelper;
import com.justep.system.context.RequestContext;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.Row;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.process.ProcessControl;
import com.justep.system.process.ProcessUtils;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class ExchangeFn {
	// private static int cLimitDay = 5;
	// 网上受理状态
	private static String cNet_Wsl = "未受理";
	private static String cNet_Ystg = "预审通过";
	// private static String cNet_Bysl = "不予受理";
	private static String cNet_sl = "办理中";
	// private static String cNet_Bj = "办结";
	// private static String cNet_Xsyjscbtg = "形式要件审查不通过";
	private static String cNet_Bzgz = "补正告知";
	private static String cNet_Bzsl = "补正受理";

	// 交换任务分类
	private static String cExKind_Twz = "厅网站";
	private static String cExKind_Chpt = "测绘平台";
	private static String cExKind_Sx = "省信";
	private static String cExKind_tysbpt = "申办受理平台";
	private static String cExKind_Nbxt = "内部系统";
	// 省信状态
	private static String cSx_Wsysl = "网上预受理";
	private static String cSx_Sb = "申办";
	private static String cSx_Sl = "受理";
	private static String cSx_Cb = "承办";
	private static String cSx_Sh = "审核";
	private static String cSx_Pz = "批准";
	private static String cSx_Bzgz = "补正告知";
	private static String cSx_Bzsl = "补正受理";
	private static String cSx_Tbcxsq = "特别程序申请";
	private static String cSx_Tbcxjg = "特别程序结果";
	private static String cSx_Bj = "办结";

	// 产生交换任务
	private static void genExTask(String bizRecId, String taskId, String exKind, String exKey, String exContent, String exCode, boolean canRepeat) {
		String taskVersion = "1";
		Object qryResult = SysUtils.queryFldValue("select FTaskVersion from B_ExchangeTask where FBizRecId=? and FexKind=? and FExKey=?", bizRecId, exKind, exKey);
		if (!canRepeat && qryResult != null) {
			return;
		}
		
		//id$操作人$意见
		String psnID = ContextHelper.getPerson().getID();
		String psnName = ContextHelper.getPerson().getName();
		String idea = exContent;
			if(exContent!=null && exContent!="" && exContent.contains("$")){
				String[] ary=exContent.split("\\$");
				psnID = ary[0];
				psnName= ary[1];
				idea = ary[2];
			}
			
		SysUtils.executeSql("insert into B_ExchangeTask(FExTaskId,version,fbizrecid,fRelID,FCreateTime,FCreaterID,"
				+ "FCreaterName,FExContent,FExCode,FExStatus,FExCount,FExKind,FExKey,FSource,FTaskVersion) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", CommonUtils.createGUID(), 0, bizRecId, taskId,
				CommonUtils.getCurrentDateTime(), psnID, psnName, idea, exCode, "未交换", 0, exKind, exKey, "业务系统", taskVersion);
	}

	/**
	 * 产生申办信息 申办流水号 事项编码 事项名称
	 */
	public static void genSb(String slActId, String sblsh, String sxbm, String sxmc, String sbxmmc, java.sql.Date lwrq, String sqrmc, String sqrdw, String sqrzjhm, String lxrxm, String lxrsj,
			String tztybm) {
		RequestContext requestContext = ActionUtils.getRequestContext();
		ActionContext actionContext = requestContext.getActionContext();
		String actName = actionContext.getActivity().getName();
		if (!actName.equalsIgnoreCase(slActId))
			return;
		// 有网上受理记录 不做处理
		String bizrecId = ProcessUtils.getProcessData1();
		Map<String, Object> qryResult = SysUtils.queryFldsValue("select * from B_PrepBizRec where ftjfs<>'窗口' and fbizrecid=?", bizrecId);
		if (qryResult != null)
			return;
		// 否则按窗口方式处理，产生网上受理信息
		else {
			String sql = "";
			Date date = new Date();
			qryResult = SysUtils.queryFldsValue("select * from B_PrepBizRec where ftjfs='0' and fbizrecid=?", bizrecId);
			if (qryResult == null) {
				sql = "insert into B_PrepBizRec(fbizrecid,fSerialNo,fYwlx,fSqdw,fSqrxm,fSqrsj,fLwrq,fXmmc,"
						+ "fItemCode,fTzxmtybm,fJssj,fSlsj,fStatus,fSjr,version,ftjfs) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				SysUtils.executeSql(sql, bizrecId, sblsh, sxmc, sqrdw, sqrmc, lxrsj, lwrq, sbxmmc, sxbm, tztybm, date, date, "已受理", ContextHelper.getPerson().getName(), 0, "窗口");
			} else {
				sql = "update B_PrepBizRec set fSerialNo=?,fYwlx=?,fSqdw=?,fSqrxm=?,fSqrsj=?,fLwrq=?,fXmmc=?," + "fItemCode=?,fTzxmtybm=?,fJssj=?,fSlsj=? where fbizrecid=?";
				SysUtils.executeSql(sql, sblsh, sxmc, sqrdw, sqrmc, lxrsj, lwrq, sbxmmc, sxbm, tztybm, date, date, bizrecId);
			}
		}
	}

	/**
	 * 流程驱动产生交换任务
	 * 
	 * @param actList
	 */
	public static void genExTaskByFlow(List<String> actList, List<String> ideaList) {
		// 获得action 判断
		RequestContext requestContext = ActionUtils.getRequestContext();
		ActionContext actionContext = requestContext.getActionContext();
		String actionName = actionContext.getAction().getName();
		String actName = actionContext.getActivity().getName();
		String bizrecId = ProcessUtils.getProcessData1();
		String taskId = ProcessUtils.getTaskInProcessContext().getId();

		//
		Map<String, Object> netData = SysUtils.queryFldsValue("select * from B_PrepBizRec where fBizRecId=?", bizrecId);
		// 如果是内网则不交换
		if (netData == null)
			return;
		String ftjfs = netData.get("FTJFS").toString();
		if (ftjfs.equals(cExKind_Nbxt))
			return;
		// 流转
		if (actionName.equalsIgnoreCase("advanceProcessAction")) {
			for (int exIndex = 0; exIndex < actList.size(); exIndex++) {
				String exchangeActs = actList.get(exIndex);
				// 支持多交换环节对应一个交换命令
				if (!ArrayUtils.contains(exchangeActs.split(","), actName))
					continue;
				// if (!actName.equalsIgnoreCase(actList.get(exIndex)))
				// continue;
				switch (exIndex) {
				case -1:
					return;
				case 0:
					slOpr(bizrecId);
					break;
				case 1:
					genExTask(bizrecId, taskId, cExKind_Sx, cSx_Cb, ideaList.get(exIndex - 1), "1", false);
					break;
				case 2:
					genExTask(bizrecId, taskId, cExKind_Sx, cSx_Sh, ideaList.get(exIndex - 1), "1", false);
					break;
				case 3:
					genExTask(bizrecId, taskId, cExKind_Sx, cSx_Pz, ideaList.get(exIndex - 1), "1", false);
					break;
				case 4:
					bjOpr(bizrecId);
					break;
				}
			}
		}
		// 挂起
		else if (actionName.equalsIgnoreCase("suspendProcessAction")) {
			gqOpr(bizrecId);
		}
		// 解挂
		else if (actionName.equalsIgnoreCase("resumeProcessAction")) {
			jcgqOpr(bizrecId);
		}
		// 作废
		else if (actionName.equalsIgnoreCase("abortProcessAction")) {
			bjOpr(bizrecId);
		}
		// 办结
		else if (actionName.equalsIgnoreCase("finishProcessAction")) {
			bjOpr(bizrecId);
		}
	}

	/**
	 * 网上预受理检查
	 */
	private static Row checkWSYSL(String serialNo) {
		// 状态检查
		String sql = "select p.*,b.fFlowId from B_PrepBizRec p left join B_BizRec b on b.fBizRecID=p.fBizRecId where p.fSerialNo=?";
		Map<String, String> sqls = new HashMap<String, String>();
		sqls.put(DatabaseProduct.DEFAULT.name(), sql);
		List<Object> params = new ArrayList<Object>();
		params.add(serialNo);
		Table t = SQL.select(sqls, params, FlowBizConsts.DATA_MODEL_CORE_FLOW);
		if (t.size() == 0)
			throw new BusinessException("网上预收件不存在，可能尚未交换，请稍候尝试");
		Row r = t.iterator().next();
		if (r.getValue("FFLOWID") != null) {
			throw new BusinessException("网上预收件已受理");
		}
		String status = r.getString("FSTATUS");
		if (status != null && !status.equals(cNet_Wsl)) {
			throw new BusinessException("当前案卷状态是[" + status + "，不允许预审通过");
		}
		// 时限检查
		Timestamp curTime = CommonUtils.getCurrentDateTime();
		Timestamp jssj = new Timestamp(((Date) r.getValue("FJSSJ")).getTime());
		Date limitDate = WorkDayUtils.calcDateAfterDays(jssj, new BigDecimal(3), "工作日", true);
		if (curTime.after(limitDate))
			throw new BusinessException("预审通过时限超期，后台服务将自动退件！");
		return r;
	}

	// 网上预受理
	public static String wsyslOpr(String serialNo) {
		Row rec = checkWSYSL(serialNo);
		//
		String bizRecId = rec.getString("FBIZRECID");
		String xmmc = rec.getString("FXMMC");
		// 测绘流水号
		String colb = rec.getString("FCOLB");

		// 设置状态
		SysUtils.executeSql("update B_PrepBizRec set FStatus='预审通过',FCZR=?,FYSTGSJ=? where fSerialNO=?", ContextHelper.getOperator().getName(), CommonUtils.getCurrentDateTime(), serialNo);
		String statusDesc = "您申请的\"" + xmmc + "\"事项（网上申请号：" + serialNo + "），已通过网上预受理审查。\n请参照网上办事大厅对应事项办事指南要求，如需提交实物材料，请您于三个工作日内，按规定将所需的实物材料报至广东省国土资源厅五楼办文窗口，如逾期未报或报送材料不齐，将作退件处理。";
		// 插入网站交换
		genExTask(bizRecId, "", cExKind_Twz, cNet_Ystg, statusDesc, "1", false);
		// 插入省信交换
		genExTask(bizRecId, "", cExKind_Sx, cSx_Wsysl, statusDesc, "1", false);
		if (!Utils.isEmptyString(colb))
			genExTask(bizRecId, "", cExKind_Chpt, cNet_Ystg, statusDesc, "1", false);
		return bizRecId;
	}

	/**
	 * 内部系统网上预受理
	 * 
	 * @param serialNo
	 * @return
	 */
	public static String innerSysWsyslOpr(String serialNo) {
		Row rec = checkWSYSL(serialNo);
		//
		String bizRecId = rec.getString("FBIZRECID");
		String xmmc = rec.getString("FXMMC");
		// 测绘流水号
		String colb = rec.getString("FCOLB");

		// 设置状态
		SysUtils.executeSql("update B_PrepBizRec set FStatus='预审通过',FCZR=?,FYSTGSJ=?,FTJFS='内部系统' where fSerialNO=?", ContextHelper.getOperator().getName(), CommonUtils.getCurrentDateTime(), serialNo);
		String statusDesc = "您申请的\"" + xmmc + "\"事项（网上申请号：" + serialNo + "），已通过网上预受理审查。\n请参照网上办事大厅对应事项办事指南要求，如需提交实物材料，请您于三个工作日内，按规定将所需的实物材料报至广东省国土资源厅五楼办文窗口，如逾期未报或报送材料不齐，将作退件处理。";
		// 插入网站交换
		genExTask(bizRecId, "", cExKind_Twz, cNet_Ystg, statusDesc, "1", false);
		// 插入省信交换
		genExTask(bizRecId, "", cExKind_Sx, cSx_Wsysl, statusDesc, "1", false);
		if (!Utils.isEmptyString(colb))
			genExTask(bizRecId, "", cExKind_Chpt, cNet_Ystg, statusDesc, "1", false);
		return bizRecId;
	}

	/**
	 * 受理
	 * 
	 * @param bizRecId
	 * @param isSt
	 */
	public static void slOpr(String bizRecId) {
		Map<String, Object> netData = SysUtils.queryFldsValue("select * from B_PrepBizRec where fBizRecId=?", bizRecId);
		if (netData == null)
			throw new BusinessException("网上受理记录不存在");
		String tfjs = netData.get("FTJFS").toString();
		if (cExKind_Twz.equals(tfjs)) {
			// 插入网站交换
			genExTask(bizRecId, "", cExKind_Twz, cNet_sl, "", "", false);
			// 判断测绘流水号是否存在
			Object colb = netData.get("FCOLB");
			if (colb != null && !colb.toString().equals("")) {
				genExTask(bizRecId, "", cExKind_Chpt, cNet_sl, "", "1", false);
			}
		} else if (tfjs.equals("窗口")) {
			// 如果来自窗口，根据受理信息产生申办记录
			genExTask(bizRecId, "", cExKind_Sx, cSx_Sb, "", "", false);
		}else if(cExKind_tysbpt.equals(tfjs)){
			genExTask(bizRecId, "", cExKind_tysbpt, cSx_Sl , "", "", false);
		}
		// 插入省信交换
		genExTask(bizRecId, ProcessUtils.getTaskInProcessContext().getId(), cExKind_Sx, cSx_Sl, "", "1", false);
	}

	/**
	 * 办结
	 * 
	 * @param bizRecId
	 * @param isSt
	 */
	public static void bjOpr(String bizRecId) {
		// 确认办结记录未产生
		Table bjTable = SysUtils.query("select * from B_ExchangeTask where FBizRecID=? and FExKey=?", bizRecId, "办结");
		if (bjTable.size() > 0)
			return;
		ProcessControl control = ProcessUtils.getProcessContext().getProcessControl();
		Object finishInfoEx = control.getExt("finishInfo");
		String exContent = "";
		String exCode = "6";
		String bjDisplayName = "办结";
		if (finishInfoEx != null) {
			JSONObject finishInfo = (JSONObject) JsonUtilsUtils.toFastJSON(finishInfoEx);
			FinishKind kind = FinishKind.valueOf((String) finishInfo.get("finishKind"));
			bjDisplayName = kind.getDisplayName();
			// JSONObject data = finishInfo.getJSONObject("tables");
			if (kind == FinishKind.fkCertification)
				exCode = "0";
			else if (kind == FinishKind.fkUntread)
				exCode = "1";
			else if (kind == FinishKind.fkAbort)
				exCode = "2";
			else if (kind == FinishKind.fkDelete)
				exCode = "3";
			else if (kind == FinishKind.fkSubmit)
				exCode = "4";
			else if (kind == FinishKind.fkApprizeAbort)
				exCode = "5";
			else
				exCode = "6";
		}
		Map<String, Object> netData = SysUtils.queryFldsValue("select * from B_PrepBizRec where fBizRecId=?", bizRecId);
		String tfjs = netData.get("FTJFS").toString();
		if (tfjs.equals("厅网站")) {
			// 插入网站交换
			genExTask(bizRecId, "", cExKind_Twz, bjDisplayName, exContent, exCode, false);
			// 判断测绘流水号是否存在
			Object colb = netData.get("FCOLB");
			if (colb != null && !colb.toString().equals("")) {
				genExTask(bizRecId, "", cExKind_Chpt, bjDisplayName, exContent, exCode, false);
			}
		}
		genExTask(bizRecId, "", cExKind_Sx, cSx_Bj, exContent, exCode, false);
	}

	/**
	 * 挂起
	 * 
	 * @param bizRecId
	 */
	public static void gqOpr(String bizrecId) {
		Map<String, Object> netData = SysUtils.queryFldsValue("select * from B_PrepBizRec where fBizRecId=?", bizrecId);
		String tfjs = netData.get("FTJFS").toString();
		ProcessControl control = ProcessUtils.getProcessContext().getProcessControl();
		Object suspendInfo = control.getExt("suspendInfo");
		Utils.check(suspendInfo != null, "非法的服务请求,不存在挂起信息");
		JSONObject infoJson = (JSONObject) JsonUtilsUtils.toFastJSON(suspendInfo);
		SuspendKind kind = SuspendKind.valueOf((String) infoJson.get("suspendKind"));
		String exContent = "";
		String taskId = ProcessUtils.getTaskInProcessContext().getId();
		// 补正告知
		if (kind == SuspendKind.skApprize) {
			if (tfjs.equals("厅网站")) {
				// 插入网站交换
				genExTask(bizrecId, taskId, cExKind_Twz, cNet_Bzgz, exContent, "", true);
				// 判断测绘流水号是否存在
				Object colb = netData.get("FCOLB");
				if (colb != null && !colb.toString().equals("")) {
					genExTask(bizrecId, taskId, cExKind_Chpt, cNet_Bzgz, exContent, "", true);
				}
			} else if (tfjs.equals(cExKind_tysbpt)) {
				// 插入统一受理交换
				genExTask(bizrecId, taskId, cExKind_tysbpt, cSx_Bzgz, exContent, "", false);
			}
			genExTask(bizrecId, taskId, cExKind_Sx, cSx_Bzgz, exContent, "", false);
			setPreBizRecState(bizrecId, "补正告知");
		}
		// 特别程序
		else if (kind == SuspendKind.skSpecialProcedure) {
			genExTask(bizrecId, taskId, cExKind_Sx, cSx_Tbcxsq, exContent, "", false);
		}
		// 转报办结
		else if (kind == SuspendKind.skSubmit) {
		}
	}

	/**
	 * 解除挂起
	 * 
	 * @param bizRecId
	 */
	public static void jcgqOpr(String bizrecId) {
		Map<String, Object> netData = SysUtils.queryFldsValue("select * from B_PrepBizRec where fBizRecId=?", bizrecId);
		String tfjs = netData.get("FTJFS").toString();
		Map<String, Object> suspendInfo = (Map<String, Object>) ContextHelper.getRequestContext().get(FlowBizConsts.RequestContext_BizRecSuspendInfo);
		Utils.check(suspendInfo != null, "非法的服务请求,不存在挂起信息");
		JSONObject infoJson = (JSONObject) JsonUtilsUtils.toFastJSON(suspendInfo);
		SuspendKind kind = SuspendKind.valueOf((String) infoJson.get("suspendKind"));
		String exContent = "";
		String taskId = ProcessUtils.getTaskInProcessContext().getId();
		// 补正告知
		if (kind == SuspendKind.skApprize) {
			if (tfjs.equals("厅网站")) {
				// 插入网站交换
				genExTask(bizrecId, bizrecId, cExKind_Twz, cNet_Bzsl, exContent, "", false);
				// 判断测绘流水号是否存在
				Object colb = netData.get("FCOLB");
				if (colb != null && !colb.toString().equals("")) {
					genExTask(bizrecId, taskId, cExKind_Chpt, cNet_Bzsl, exContent, "", false);
				}
			} else if (tfjs.equals(cExKind_tysbpt)) {
				// 插入统一受理交换
				genExTask(bizrecId, taskId, cExKind_tysbpt, cSx_Bzsl, exContent, "", false);
			}
			genExTask(bizrecId, taskId, cExKind_Sx, cSx_Bzsl, exContent, "", false);
			setPreBizRecState(bizrecId, "已受理");
		}
		// 特别程序
		else if (kind == SuspendKind.skSpecialProcedure) {
			genExTask(bizrecId, taskId, cExKind_Sx, cSx_Tbcxjg, exContent, "", false);
		}

	}

	// 设置预收件状态
	private static void setPreBizRecState(String bizRecID, String status) {
		SysUtils.executeSql("update B_PrepBizRec set fstatus=? where fbizrecid=?", status, bizRecID);
	}

}