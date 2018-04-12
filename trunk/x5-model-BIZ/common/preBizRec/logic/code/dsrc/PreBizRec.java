import java.math.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.*;

import com.butone.data.SQLUtils;
import com.butone.spi.FlowControl;
import com.butone.spi.FlowControlUtils;
import com.butone.workdate.WorkDayUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.*;
import com.justep.system.opm.OrgUnit;
import com.justep.system.process.ProcessUtils;
import com.justep.system.util.CommonUtils;
import com.justep.util.Utils;

public class PreBizRec {
	private static int cLimitDay = 5;
	// 网上受理状态
	private static String cNet_Wsl = "未受理";
	private static String cNet_Ystg = "预审通过";
	private static String cNet_Bysl = "不予受理";
	private static String cNet_Ysl = "已受理";
	private static String cNet_Xsyjscbtg = "形式要件审查不通过";

	// 交换任务分类
	private static String cExKind_Twz = "厅网站";
	private static String cExKind_Chpt = "测绘平台";
	private static String cExKind_Sx = "省信";
	private static String cExKind_tysbpt = "统一申办受理平台";
	private static String cExKind_Nbxt = "内部系统";

	// 省信状态
	private static String cSx_Wsysl = "网上预受理";
	private static String cSx_Sl = "受理";
	private static String cSx_Bj = "办结";

	public static Table queryV_PreBizRec(String concept, String select, String from, String aggregate, String dataModel, String fnModel, String condition, List range, Boolean distinct,
			String idColumn, String filter, Integer limit, Integer offset, String columns, String orderBy, String aggregateColumns, Map variables) {
		Table queryTable = BizData.query("B_PrepBizRec", idColumn, select, from, condition, range, filter, distinct, offset, limit, columns, orderBy, aggregate, aggregateColumns, variables,
				dataModel, fnModel);
		Iterator<Row> iter = queryTable.iterator();
		while (iter.hasNext()) {
			Row row = iter.next();
			Timestamp startDate = row.getDateTime("fJSSJ");
			if (row.getString("fStatus").equals("预审通过"))
				startDate = row.getDateTime("fYSTGSJ");
			Date endDate = WorkDayUtils.getDateAfterWorkDays(startDate, cLimitDay, false);
			System.out.print(endDate);
			Double day = WorkDayUtils.calcLostDaysBetween(CommonUtils.getCurrentDateTime(), endDate, "工作日", false, false);
			row.setDecimal("fRemainDays", new BigDecimal(day));
		}
		return queryTable;
	}

	/**
	 * 预审通过
	 * 
	 * @param bizRecId
	 */
	public static void ystg(String bizRecId) {
		String sql = "";
		sql = "select * from B_PrepBizRec p where not exists(select 1 from B_BizRec b where b.fbizrecid=p.fbizrecid ) and p.fBizRecID=?";
		Map<String, Object> values = SysUtils.queryFldsValue(sql, bizRecId);
		// 状态检查
		if (values == null)
			throw new BusinessException("案卷已受理 或者案卷不存在");
		String status = (String) values.get("FSTATUS");
		String xmmc = values.get("FXMMC").toString();
		String colb = (String) values.get("FCOLB");
		String serialno = String.valueOf(values.get("FSERIALNO"));
		String fexkind = String.valueOf(values.get("FTJFS"));
		Date jssj = (Date) values.get("FJSSJ");
		Date curTime = CommonUtils.getCurrentDateTime();
		if (status != null && !status.equals(cNet_Wsl)) {
			throw new BusinessException("当前案卷状态是[" + status + "，不允许预审通过");
		}
		// 交换时限检查
		if (!cExKind_Nbxt.equals(fexkind)) {
			Date limitDate = WorkDayUtils.calcDateAfterDays(jssj, new BigDecimal(5), "工作日", false);
			if (curTime.after(limitDate))
				throw new BusinessException("预审通过时限超期，后台服务将自动退件！");
		}
		// 设置状态
		SysUtils.executeSql("update B_PrepBizRec set FStatus=?,FCZR=?,FYSTGSJ=? where FBizRecId=?", "预审通过", ContextHelper.getOperator().getName(), curTime, bizRecId);
		// 产生交换
		String statusDesc = "您申请的\"" + xmmc + "\"事项（网上申请号：" + serialno + "），已通过网上预受理审查。\n请参照网上办事大厅对应事项办事指南要求，如需提交实物材料，请您于三个工作日内，按规定将所需的实物材料报至广东省国土资源厅五楼办文窗口，如逾期未报或报送材料不齐，将作退件处理。";
		if (cExKind_tysbpt.equals(fexkind)) {
			genExTask(bizRecId, cExKind_tysbpt, cSx_Wsysl, statusDesc, "1");
			// 插入省信交换
			genExTask(bizRecId, cExKind_Sx, cSx_Wsysl, "", "1");
		} else if ((cExKind_Twz.equals(fexkind))) {
			// 插入网站交换
			genExTask(bizRecId, cExKind_Twz, cNet_Ystg, statusDesc, "1");
			// 插入省信交换
			genExTask(bizRecId, cExKind_Sx, cSx_Wsysl, "", "1");
			// 测绘流水号存在 执行测绘平台交换
			if (!Utils.isEmptyString(colb))
				genExTask(bizRecId, cExKind_Chpt, cNet_Ystg, statusDesc, "1");
		}
	}

	/**
	 * 受理
	 * 
	 * @param bizRecId
	 */
	public static void sl(String bizRecId) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String sql = "";
		Map<String, Object> recMap = SysUtils.queryFldsValue(
				"select fProcess,fStartActivity,b.fstatus,b.FYSTGSJ,b.FITEMCODE,b.FTJFS from B_PrepBizRec b left join T_SYS_SPSX_YWLC m  on b.fBizMappingId = m.fID where fBizRecID=?"
						+ " and not exists(select 1 from B_BizRec r where r.fbizrecid=b.fbizrecid)", bizRecId);
		// 状态检查
		if (recMap == null)
			throw new BusinessException("案卷已受理 或者案卷不存在");
		String status = (String) recMap.get("FSTATUS");
		if (status != null && !status.equals(cNet_Ystg)) {
			throw new BusinessException("当前案卷状态是[" + status + "，不允许受理");
		}
		String ftjfs = String.valueOf(recMap.get("FTJFS"));
		// 时限检查
		if (!cExKind_Nbxt.equals(ftjfs)) {
			Date ystgsj = (Date) recMap.get("FYSTGSJ");
			Date limitDate = WorkDayUtils.calcDateAfterDays(ystgsj, new BigDecimal(5), "工作日", false);
			if (CommonUtils.getCurrentDateTime().after(limitDate))
				throw new BusinessException("受理时限超期，后台服务将自动退件！");
		}
		// 映射检查
		String process = (String) recMap.get("FPROCESS");
		String activity = (String) recMap.get("FSTARTACTIVITY");
		String itemCode = (String) recMap.get("FITEMCODE");
		if (process == null)
			throw new BusinessException("事项[" + itemCode + "]未进行业务映射！");
		// 判断是否有未映射的附件
		BigDecimal notMapFileCount = (BigDecimal) SysUtils.queryFldValue("select count(*) from Net_Files f where f.FBizRecID=? and not exists (select 1 from B_prepBizRec b,T_SYS_SXCL_YWCL c where "
				+ "c.fSXLCID=b.fBizMappingId and f.FBizRecID=b.FBIzRecID)", bizRecId);
		if (Integer.parseInt(notMapFileCount.toString()) > 0)
			throw new BusinessException("事项文件未进行映射！");
		if (!cExKind_Nbxt.equals(ftjfs)) {
			// 处理附件映射（内部系统已同步材料不做处理）
			sql = "insert into B_Material(fID,version,fBizRecID,fMaterialID,fMaterialName,fMaterialType,fDocIds,FDispOrder,FISDEFSELECT,FMTNums) "
					+ "select sys_guid(),0,?,nvl(clm.fywclbh,clm.fspclbh),nvl(clm.fywclmc,clm.fspclmc),?,f.fdocids,rownum,?,? from Net_Files f join B_PrepBizRec b on f.FBizRecId=b.FBizRecID join T_SYS_SXCL_YWCL clm "
					+ "on clm.fsxlcid=b.fBizMappingId and clm.fspclbh=f.ftype where b.FTJFS<>'内部系统' and f.FBizReciD=? and f.fneturl is not null";
			SysUtils.executeSql(sql, bizRecId, "必要材料", "是", 1, bizRecId);
		}

		// 设置任务状态
		updateNetStatus(bizRecId, cNet_Ysl);
		// 启动流程
		List<OrgUnit> executors = new ArrayList<OrgUnit>();
		executors.add(ContextHelper.getPersonMember());
		FlowControlUtils.startFlow(bizRecId, process, activity, executors);
	}

	/**
	 * 不予受理
	 * 
	 * @param bizRecId
	 */
	public static void bysl(String bizRecId, String reason) {
		String sql = "";
		sql = "select * from B_PrepBizRec p where not exists(select 1 from B_BizRec b where b.fbizrecid=p.fbizrecid ) and p.fBizRecID=?";
		Map<String, Object> values = SysUtils.queryFldsValue(sql, bizRecId);
		// 状态检查
		if (values == null)
			throw new BusinessException("案卷已受理 或者案卷不存在");
		String status = (String) values.get("FSTATUS");
		String colb = (String) values.get("FCOLB");
		String ftjfs = String.valueOf(values.get("FTJFS"));
		Timestamp jssj = (Timestamp) values.get("FJSSJ");
		Timestamp curTime = CommonUtils.getCurrentDateTime();
		if (!status.equals(cNet_Wsl) && !status.equals(cNet_Ystg)) {
			throw new BusinessException("当前案卷状态是[" + status + "，不允许此操作");
		}
		// 时限检查
		if (!cExKind_Nbxt.equals(ftjfs)) {
			Date limitDate = WorkDayUtils.calcDateAfterDays(jssj, new BigDecimal(cLimitDay), "工作日", false);
			if (curTime.after(limitDate))
				throw new BusinessException("时限超期，后台服务将自动退件！");
		}
		// 设置状态
		updateNetStatus(bizRecId, cNet_Bysl);
		if (cExKind_tysbpt.equals(ftjfs)) {
			// 统一申办
			if (status.equals(cNet_Wsl)) {
				genExTask(bizRecId, cExKind_tysbpt, cSx_Wsysl, reason, "2");
			} else {
				genExTask(bizRecId, cExKind_tysbpt, cSx_Sl, reason, "2");
			}
			// 插入省信交换
			if (status.equals(cNet_Wsl))
				genExTask(bizRecId, cExKind_Sx, cSx_Wsysl, reason, "2");
			else {
				genExTask(bizRecId, cExKind_Sx, cSx_Sl, reason, "2");
				genExTask(bizRecId, cExKind_Sx, cSx_Bj, reason, "1");
			}
		} else if ((cExKind_Twz.equals(ftjfs))) {
			// 插入网站交换
			genExTask(bizRecId, cExKind_Twz, cNet_Xsyjscbtg, reason, "2");
			// 插入省信交换
			if (status.equals(cNet_Wsl))
				genExTask(bizRecId, cExKind_Sx, cSx_Wsysl, reason, "2");
			else {
				genExTask(bizRecId, cExKind_Sx, cSx_Sl, reason, "2");
				genExTask(bizRecId, cExKind_Sx, cSx_Bj, reason, "1");
			}
			// 测绘流水号存在 执行测绘平台交换
			if (!Utils.isEmptyString(colb))
				genExTask(bizRecId, cExKind_Chpt, cNet_Xsyjscbtg, reason, "2");
		}
	}

	// 产生交换任务
	private static void genExTask(String bizRecId, String exKind, String exKey, String exContent, String exCode) {
		SysUtils.executeSql(
				"insert into B_ExchangeTask(FExTaskId,version,fbizrecid,fRelID,FCreateTime,FCreaterID,FCreaterName,FExContent,FExCode,FExStatus,FExCount,FExKind,FExKey,FSource,FTaskVersion,FExCldKind) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'办理过程')", CommonUtils.createGUID(), 0, bizRecId, "", CommonUtils.getCurrentDateTime(), ContextHelper.getPerson().getID(), ContextHelper
						.getPerson().getName(), exContent, exCode, "未交换", 0, exKind, exKey, "预收件", 1);
	}

	// 更新状态
	private static void updateNetStatus(String bizRecId, String status) {
		SysUtils.executeSql("update B_PrepBizRec set FStatus=?,FCZR=?,FYSTGSJ=? where FBizRecId=?", status, ContextHelper.getOperator().getName(), CommonUtils.getCurrentDateTime(), bizRecId);
	}

	// 产生状态数据
	// private static String genRecStatus(String bizRecId, String status, String
	// desc) {
	// String relId = CommonUtils.createGUID();
	// SysUtils.executeSql("insert into Net_RecStatus(FGUID,Version,fbizrecid,fstatus,fcreatetime,fStatusDesc) values(?,?,?,?,?,?)",
	// relId, 0,
	// bizRecId, status, CommonUtils.getCurrentDateTime(), desc);
	// return relId;
	// }

	public static void setBizMap(String bizRecId, String bizMappingId) {
		SysUtils.executeSql("update B_PrepBizRec set fBizMappingId=? where fBizRecID=?", bizMappingId, bizRecId);
	}

	public static void checSms(String ids, String state) {
		SysUtils.executeSql("update B_PrepBizRec set smsshzt=?,smsshr=?,smsshsj=sysdate where fbizrecid in (" + ids + ") and ( smsshzt is null or smsshzt='未审核')", state, ContextHelper.getPerson()
				.getName());
	}
}