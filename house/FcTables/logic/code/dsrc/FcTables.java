import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.butone.data.SQLUtils;
import com.justep.exception.BusinessException;
import com.justep.model.Concept;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Relation;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.system.transform.Utils;

public class FcTables {
	private static String regxNames = "registerno|capturefid|buildingid|capturefid3|fid|buildingid|dbh|houseid|HouseBookFID|ffid|myfid|detailfid"
			.toLowerCase();
	private static String import_house_id = null;
	private static String import_houseReg_id = null;

	private static synchronized void doImportAllHouse(String xzq) throws NamingException, SQLException {
		if (import_house_id != null) {
			return;
		}
		import_house_id = "";
		System.out.println("****************************开始导入房屋信息****************************");
		Statement fcStatement = null;
		PreparedStatement bdcStatement = null;
		Statement delStatement = null;
		ResultSet resultSet = null;

		String dataModelFC = "/house/FcTables/data";
		String dataModelBDC = "/house/bdcHouse/data";
		Connection dbcConn = null, fcConn = null;
		long l = System.currentTimeMillis();
		System.out.println("开始导入房屋:" + xzq);
		int total = 0;
		try {

			// 查询 dataModelFC
			fcConn = ModelUtils.getConnection(dataModelFC);
			fcStatement = fcConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sqlHouse = "select replace(a.chouseid,'-','') houseid,replace(a.BuildingID,'-','') BuildingID,HouseBookID,a.Ceng,a.HouseRepose, RoomNo,ZxFlag,a.TxqFlag,a.CfFlag,  DISTRICT,a.HouseUse,a.HouseFrame,a.SumBuildArea,a.UseArea,loginUnitid from T_House a";
			resultSet = fcStatement.executeQuery(sqlHouse);
			// 插入 dataModelBDC
			String insertSql = "insert into H_House (version,HouseID,BuildingID,HouseBookID,Ceng,HouseRepose,RoomNo,ZXFlag,TxqFlag,CfFlag,DISTRICT,HouseUse,HouseFrame,SumBuildArea,UseArea,loginUnitid)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dbcConn = ModelUtils.getConnection(dataModelBDC);
			delStatement = dbcConn.createStatement();

			delStatement.executeUpdate("TRUNCATE TABLE h_house");
			bdcStatement = dbcConn.prepareStatement(insertSql);
			int batchCnt = 0;

			while (resultSet.next()) {
				bdcStatement.setInt(1, 0);
				bdcStatement.setString(2, resultSet.getString("HouseId".toUpperCase()));
				bdcStatement.setString(3, resultSet.getString("BuildingID".toUpperCase()));
				bdcStatement.setString(4, resultSet.getString("HouseBookID".toUpperCase()));
				bdcStatement.setString(5, resultSet.getString("Ceng".toUpperCase()));
				bdcStatement.setString(6, resultSet.getString("HouseRepose".toUpperCase()));
				bdcStatement.setString(7, resultSet.getString("RoomNo".toUpperCase()));
				bdcStatement.setString(8, resultSet.getString("ZXFlag".toUpperCase()));
				bdcStatement.setString(9, resultSet.getString("TxqFlag".toUpperCase()));
				bdcStatement.setInt(10, resultSet.getInt("CfFlag".toUpperCase()));
				bdcStatement.setString(11, resultSet.getString("DISTRICT".toUpperCase()));
				bdcStatement.setString(12, resultSet.getString("HouseUse".toUpperCase()));
				bdcStatement.setString(13, resultSet.getString("HouseFrame".toUpperCase()));
				bdcStatement.setBigDecimal(14, resultSet.getBigDecimal("SumBuildArea".toUpperCase()));
				bdcStatement.setBigDecimal(15, resultSet.getBigDecimal("UseArea".toUpperCase()));
				bdcStatement.setString(16, resultSet.getString("loginUnitid".toUpperCase()));
				bdcStatement.addBatch();
				batchCnt++;
				if (batchCnt == 200) {
					System.out.print(".");
					total += batchCnt;
					batchCnt = 0;
					if (total % 20000 == 0) {
						System.out.println();
					}
					bdcStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				bdcStatement.executeBatch();
				total += batchCnt;
			}
			System.out.println("****************************导入完成****************************");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导入房屋异常:" + e.getMessage(), e);
		} finally {
			import_house_id = null;
			System.out.println("\n导入房屋:" + xzq + " " + total + "条,耗时:" + (System.currentTimeMillis() - l) / 1000 + "秒");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception localException2) {
				}

			}
			if (fcStatement != null)
				try {

					fcStatement.close();
				} catch (Exception localException2) {
				}
			if (fcConn != null)
				try {

					fcConn.close();
				} catch (Exception localException3) {
				}
			if (bdcStatement != null) {
				try {
					bdcStatement.clearBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					bdcStatement.close();
				} catch (Exception localException2) {
				}
			}

			if (delStatement != null)
				try {
					delStatement.close();
				} catch (Exception localException2) {
				}
			if (dbcConn != null)
				try {

					dbcConn.close();
				} catch (Exception localException3) {
				}
		}
	}

	private static String CADDIR = "d:/cadFiles";

	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sf.format(new Date()));
	}

	private static void clearDirectory(File dir) {
		File[] files = dir.listFiles();
		if (files != null)
			for (File file : files) {
				if (file.isDirectory()) {
					clearDirectory(file);
				}
				file.delete();
			}
	}

	private static String import_HXT = null;

	public static void impHXTImage(String xzq) {
		if (Utils.isEmptyString(xzq)) {
			throw new BusinessException("行政区不能为空");
		}
		if (import_HXT != null) {
			return;
		}
		import_HXT = "";

		String filter = null;
		if ("德庆".equals(xzq)) {
			filter = "LoginUnitID='A0DD416B-AB92-49CD-AD3B-43CABC8D9486'";
		} else if ("封开".equals(xzq)) {
			filter = "LoginUnitID='75EDCEFE-ED69-4EE0-99C8-1D490FAF7D8C'";
		} else if ("高要".equals(xzq)) {
			filter = "LoginUnitID='87372051-2F41-4972-B7F6-CB536E7FBED0'";
		} else if ("广宁".equals(xzq)) {
			filter = "LoginUnitID='68FFD485-47E3-40B5-9874-D34892587390'";
		} else if ("怀集".equals(xzq)) {
			filter = "LoginUnitID='1DB4A74A-946D-4D8C-868D-AF15A23B2FF3'";
		} else if ("肇庆市".equals(xzq)) {
			filter = "LoginUnitID='F96EA453-7C8F-488C-BEB4-A696849BBA06'";
		} else {
			throw new BusinessException("无效行政区");
		}
		clearDirectory(new File(CADDIR + "/" + xzq));
		Statement fcStatement = null;
		PreparedStatement bdcStatement = null;
		Statement delStatement = null;
		ResultSet resultSet = null;

		String dataModelFC = "/house/FcTables/data";
		String dataModelBDC = "/house/bdcHouse/data";
		Connection dbcConn = null, fcConn = null;
		long l = System.currentTimeMillis();
		System.out.println("开始导入户型图:T_MapPhotoLib");
		int total = 0;
		try {
			// 查询 dataModelFC
			fcConn = ModelUtils.getConnection(dataModelFC);

			Model m = ModelUtils.getModel("/house/bdcHouse/ontology");
			Concept concept = (Concept) m.getLocalObject("T_MapPhotoLib", Concept.TYPE);
			String s1 = "", s2 = "";
			List<String> allFields = new ArrayList<String>();
			List<String> allTypes = new ArrayList<String>();
			for (Relation r : concept.getRelations()) {
				if (r.getDataType().equals("Text"))
					continue;
				s1 += r.getName() + ",";
				s2 += "?,";
				allFields.add(r.getName().toUpperCase());
				allTypes.add(r.getDataType());
			}

			// 插入 dataModelBDC
			String insertSql = "insert into H_MapPhotoLib (" + s1.substring(0, s1.length() - 1) + ")" + " values ("
					+ s2.substring(0, s2.length() - 1) + ")";
			dbcConn = ModelUtils.getConnection(dataModelBDC);
			delStatement = dbcConn.createStatement();
			delStatement.executeUpdate("delete from H_MapPhotoLib");

			bdcStatement = dbcConn.prepareStatement(insertSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int fileNameIdx = allFields.indexOf("FILENAME");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
			fcStatement = fcConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			try {
				fcStatement.execute("drop table  t_MapPhotoLib_t");
			} catch (Exception e) {

			}
			fcStatement.execute("create table t_MapPhotoLib_t(fid varchar(100),rowN decimal(18,0))");

			fcStatement.execute("insert into t_MapPhotoLib_t (fid,rowN) select fid, ROW_NUMBER() OVER(ORDER BY fid) rowN from t_MapPhotoLib"
					+ " where len(BHID)>0 and (rowN is null or rowN>=0) and " + filter);

			fcStatement.execute("update t_MapPhotoLib  set rowN = (select rowN from t_MapPhotoLib_t where t_MapPhotoLib_t.fid=t_MapPhotoLib.fid)"
					+ " where exists(select 1 from t_MapPhotoLib_t where t_MapPhotoLib_t.fid=t_MapPhotoLib.fid)");

			try {
				fcStatement.execute("drop table  t_MapPhotoLib_t");
			} catch (Exception e) {

			}
			resultSet = fcStatement.executeQuery("select max(rowN) ROWN from t_MapPhotoLib where len(BHID)>0 and " + filter);
			int maxR = 0;
			while (resultSet.next()) {
				maxR = resultSet.getInt("ROWN");
			}
			resultSet.close();
			System.out.println("户型图数量:" + maxR);
			int st = 1, e = 100;
			while (st <= maxR) {
				String sqlHouse = "select * from T_MapPhotoLib where rowN>=" + st + " and rowN<=" + e + " and " + filter;
				resultSet = fcStatement.executeQuery(sqlHouse);

				while (resultSet.next()) {
					for (int i = 0; i < allFields.size(); i++) {
						String dataType = allTypes.get(i);
						if ("String".equals(dataType)) {
							bdcStatement.setString(i + 1, resultSet.getString(allFields.get(i)));
						} else if ("Decimal".equals(dataType)) {
							bdcStatement.setBigDecimal(i + 1, resultSet.getBigDecimal(allFields.get(i)));
						} else if (dataType.matches("Time")) {
							bdcStatement.setTime(i + 1, resultSet.getTime(allFields.get(i)));
						} else if (dataType.matches("Date")) {
							bdcStatement.setDate(i + 1, resultSet.getDate(allFields.get(i)));
						} else if (dataType.matches("DateTime")) {
							bdcStatement.setTimestamp(i + 1, resultSet.getTimestamp(allFields.get(i)));
						} else if ("Integer".equals(dataType)) {
							bdcStatement.setInt(i + 1, resultSet.getInt(allFields.get(i)));
						} else if ("Float".equals(dataType)) {
							bdcStatement.setFloat(i + 1, resultSet.getFloat(allFields.get(i)));
						} else if ("Text".equals(dataType)) {
							String s = SQLUtils.getClobText(resultSet.getClob(allFields.get(i)));
							bdcStatement.setString(i + 1, s);
						}
					}

					Blob blob = resultSet.getBlob("PHOTO".toUpperCase());
					if (blob != null) {
						Timestamp time = resultSet.getTimestamp("CREATEDATE".toUpperCase());
						String path = "/" + xzq;
						if (time != null) {
							path += "/" + sf.format(time);
						}
						String s = resultSet.getString("FILENAME");
						String hid = resultSet.getString("BHID").replaceAll("-", "");
						path += "/" + hid + "_" + s;
						saveToFile(blob.getBinaryStream(), CADDIR + path);
						bdcStatement.setString(fileNameIdx + 1, path);
					} else {
						bdcStatement.setString(fileNameIdx + 1, null);
					}
					total++;
					bdcStatement.addBatch();

				}
				resultSet.close();
				bdcStatement.executeBatch();

				System.out.print(".");
				if (total % 10000 == 0) {
					System.out.println(total + "耗时:" + (System.currentTimeMillis() - l) / 1000 + "秒");
					l = System.currentTimeMillis();
				}
				st = e + 1;
				e += 100;
			}

			delStatement.execute("update H_MapPhotoLib set bhid=replace(bhid,'-','')");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导入户型图异常:" + e.getMessage(), e);
		} finally {
			import_HXT = null;
			System.out.println("\n导入户型图：" + total + "条");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception localException2) {
				}

			}
			if (fcStatement != null)
				try {
					fcStatement.close();
				} catch (Exception localException2) {
				}

			if (fcConn != null)
				try {

					fcConn.close();
				} catch (Exception localException3) {
				}
			if (bdcStatement != null) {
				try {
					bdcStatement.clearBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					bdcStatement.close();
				} catch (Exception localException2) {
				}
			}
			if (delStatement != null)
				try {
					delStatement.close();
				} catch (Exception localException2) {
				}
			if (dbcConn != null)
				try {
					dbcConn.close();
				} catch (Exception localException3) {
				}
		}

	}

	private static String replaceTxt(String s) {
		if (s == null)
			return null;
		return s.replaceAll("-", "");
	}

	/**
	 * 导入楼盘
	 * @param xzq
	 */
	private static void doImportBuild(String xzq) {
		String dataModelFC = "/house/FcTables/data";
		String dataModelBDC = "/house/bdcHouse/data";
		Connection dbcConn = null, fcConn = null;
		Statement fcStatement = null;
		Statement delStatement = null;
		ResultSet resultSet = null;
		PreparedStatement bdcStatement = null;
		long l = System.currentTimeMillis();
		System.out.println("开始导入楼盘:" + xzq);
		int total = 0;
		try {
			// 查询 dataModelFC
			fcConn = ModelUtils.getConnection(dataModelFC);
			fcStatement = fcConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sqlBuild = "select * from t_building";
			resultSet = fcStatement.executeQuery(sqlBuild);
			// 插入 dataModelBDC
			String insertSql = "insert into H_Building (version,Buildingid,dbh,zhno,itemno,CompleteDate,CompleteDateText,houseuse,houseFrame,baseBuildArea,Floor,"
					+ "sumbuildarea,ownerbuildarea,corpName,djh,landno,buildingno,DISTRICT,loginunitid)"
					//
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dbcConn = ModelUtils.getConnection(dataModelBDC);
			delStatement = dbcConn.createStatement();
			delStatement.executeUpdate("delete from H_Building");

			bdcStatement = dbcConn.prepareStatement(insertSql);
			int batchCnt = 0;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
			while (resultSet.next()) {
				bdcStatement.setInt(1, 0);
				bdcStatement.setString(2, replaceTxt(resultSet.getString("Buildingid".toUpperCase())));//replace(Buildingid,'-','')
				bdcStatement.setString(3, replaceTxt(resultSet.getString("dbh".toUpperCase())));//dbh=replace(dbh,'-','')
				bdcStatement.setString(4, resultSet.getString("zhno".toUpperCase()));
				bdcStatement.setString(5, resultSet.getString("itemno".toUpperCase()));
				String dateText = resultSet.getString("CompleteDate".toUpperCase());
				if (Utils.isNotEmptyString(dateText)) {
					try {
						sf.parse(dateText);
						bdcStatement.setDate(6, new java.sql.Date(sf.parse(dateText).getTime()));
					} catch (Exception e) {
					}
				} else {
					bdcStatement.setDate(6, null);
				}
				bdcStatement.setString(7, dateText);
				bdcStatement.setString(8, resultSet.getString("houseuse".toUpperCase()));
				bdcStatement.setString(9, resultSet.getString("houseFrame".toUpperCase()));
				bdcStatement.setBigDecimal(10, resultSet.getBigDecimal("baseBuildArea".toUpperCase()));
				bdcStatement.setInt(11, resultSet.getInt("Floor".toUpperCase()));
				bdcStatement.setBigDecimal(12, resultSet.getBigDecimal("sumbuildarea".toUpperCase()));
				bdcStatement.setBigDecimal(13, resultSet.getBigDecimal("ownerbuildarea".toUpperCase()));
				bdcStatement.setString(14, resultSet.getString("corpName".toUpperCase()));
				bdcStatement.setString(15, resultSet.getString("djh".toUpperCase()));
				bdcStatement.setString(16, resultSet.getString("landno".toUpperCase()));
				bdcStatement.setString(17, replaceTxt(resultSet.getString("buildingno".toUpperCase())));//buildingno=replace(buildingno,'-','')
				bdcStatement.setString(18, resultSet.getString("qx".toUpperCase()));
				bdcStatement.setString(19, resultSet.getString("loginunitid".toUpperCase()));
				bdcStatement.addBatch();
				batchCnt++;
				if (batchCnt == 200) {
					System.out.print(".");
					total += batchCnt;
					batchCnt = 0;
					if (total % 20000 == 0) {
						System.out.println();
					}
					bdcStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				bdcStatement.executeBatch();
				total += batchCnt;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导入楼盘异常:" + e.getMessage(), e);
		} finally {
			System.out.println("\n导入楼盘:" + xzq + " " + total + "条,耗时:" + (System.currentTimeMillis() - l) / 1000 + "秒");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception localException2) {
				}

			}
			if (fcStatement != null)
				try {

					fcStatement.close();
				} catch (Exception localException2) {
				}
			if (fcConn != null)
				try {

					fcConn.close();
				} catch (Exception localException3) {
				}
			if (bdcStatement != null) {
				try {
					bdcStatement.clearBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					bdcStatement.close();
				} catch (Exception localException2) {
				}
			}

			if (delStatement != null)
				try {
					delStatement.close();
				} catch (Exception localException2) {
				}
			if (dbcConn != null)
				try {

					dbcConn.close();
				} catch (Exception localException3) {
				}
		}
	}

//	public static void generateFangChanData(String xzq) {
//		if (Utils.isEmptyString(xzq)) {
//			throw new BusinessException("行政区不能为空");
//		}
//
//		String dataModelBDC = "/house/bdcHouse/data";
//		Statement preStatement = null;
//		Statement bdcStatement = null;
//		Connection dbcConn = null;
//		//		Transaction tran = ContextHelper.getTransaction();
//		try {
//			System.out.println("****************************开始生成房产数据****************************");
//			dbcConn = ModelUtils.getConnection(dataModelBDC);
//			preStatement = dbcConn.createStatement();
//			System.out.println("创建临时表");
//			try {
//				preStatement.execute("drop table h_housebuildZD");
//			} catch (Exception e) {
//			}
//			try {
//				preStatement.execute("drop table h_housebuildZD_fix");
//			} catch (Exception e) {
//			}
//
//			try {
//				preStatement.execute("drop table h_BuildZD_fix");
//			} catch (Exception e) {
//			}
//
//			try {
//				preStatement.execute("drop table h_housereg_distinct");
//			} catch (Exception e) {
//			}
//
//			// 
//			System.out.println("创建临时表【自然幢宗地对照表】");
//			preStatement.execute("create table h_housebuildZD as"
//					+ " select distinct a.registerno,a.buildingid, b.terraid from h_houseregbase b,h_houseregmain a"
//					+ " where a.registerno = b.registerno and a.loginunitid =b.loginunitid"
//					+ " and (instr(b.status,'注销')=0 or b.status is null)  and a.buildingid is not null"
//					+ " and exists(select 1 from tb_zdjbxx c where c.zddm=b.terraid) and b.terraid<>'待定'");
//
//			System.out.println("提取【自然幢宗地对照表】中唯一宗地代码的自然幢 ");
//			preStatement.execute("create table h_housebuildZD_fix as"
//					+ " select registerno hid,buildingid zrcid,max(terraid) zddm from h_housebuildZD" + " group by registerno,buildingid"
//					+ " having count(distinct terraid)=1");
//			preStatement.execute("create unique index IDX_HOUSEBUILDZD_FIX on H_HOUSEBUILDZD_FIX (HID, ZRCID, ZDDM)");
//			preStatement.execute("create table h_BuildZD_fix as" + " select zrcid buildingid,max(zddm) zddm,zrcid from h_housebuildZD_fix"
//					+ " group by zrcid  having count(distinct zddm)=1");
//
//			System.out.println("登记薄中的使用buildingno，与楼盘表一对一的数据修正为buildingid");
//			preStatement.execute("update h_BuildZD_fix a set zrcid =(select buildingid from h_building b where b.buildingno=a.buildingid)"
//					+ " where (select count(*) from h_building b where b.buildingno=a.buildingid)=1");
//
//			System.out.println("登记薄中的使用buildingno，与楼盘表未一对多的，产生新编 buildingno-buldingid");
//			preStatement.execute("update h_building b set b.zqno = null");
//			preStatement.execute("update h_building b set b.zqno=b.buildingno||'-'||buildingid "
//					+ " where exists(select 1 from h_BuildZD_fix a where a.buildingid=a.zrcid and b.buildingno=a.buildingid)");
//
//			System.out.println("提取登记薄中有效房屋数据");
//			preStatement
//					.execute("create table h_housereg_distinct as"
//							+ " select b.registerno hid,b.buildingid zrcid ,max(houserepose) houserepose,max(Floor) Floor,max(houseno) houseno,max(houseuse) houseuse,max(houseframe) houseframe,max(UseArea) UseArea,max(UseBuildArea) UseBuildArea from h_houseregbase a,h_houseregmain b"
//							+ " where a.loginunitid =b.loginunitid and a.registerno = b.registerno "
//							+ " and (instr(a.status,'注销')=0 or a.status is null)  group by  b.registerno,b.buildingid");
//
//			preStatement.execute("create unique index idx_housereg_distinct_0 on h_housereg_distinct(hid)");
//			preStatement.execute("create index idx_housereg_distinct_1 on h_housereg_distinct(zrcid)");
//
//			bdcStatement = dbcConn.createStatement();
//			System.out.println("删除数据....");
//			bdcStatement.execute("delete tb_h where impXZQ = '" + xzq + "'");
//			bdcStatement.execute("delete tb_zrz where impXZQ = '" + xzq + "'");
//			bdcStatement.execute("delete tb_fcxmb where impXZQ = '" + xzq + "'");
//
//			System.out.println("0)按buildingno拆分幢");
//			bdcStatement
//					.execute("insert into tb_zrz (impxzq,version,zrcid,ysdm,zph,xmid,zddm,jzwzt,zcs,fwjg,fwyt,scjzmj,jgrq)"
//							+ " select '"
//							+ xzq
//							+ "',0,zqno,'6001030110',zhno,itemno,zddm,'0',floor,houseframe,houseuse,sumbuildarea,CompleteDate  from h_building a,h_BuildZD_fix b"
//							+ " where a.zqno is not null and a.buildingno=b.buildingid"
//							+ " and not exists(select 1 from tb_zrz t where t.zrcid=zqno)");
//			System.out.println("0.1)提取确权房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,HXJG,scjzmj,sctnjzmj,scftjzmj,mph,zrcID) select '" + xzq
//					+ "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.houseframe, a.sumbuildarea,a.usearea,a.FTArea,HouseNo,b.zrcid"
//					+ " from tb_zrz b,h_building c,h_housetradeqsdetail a  where b.jzwzt='0' and c.zqno=b.zrcid and c.dbh=a.dbh"
//					+ " and not exists(select 1 from tb_h t where t.hid=houseid)");
//			System.out.println("0.2)提取楼盘房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,ycjzmj,yctnjzmj,ycftjzmj,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)"
//					+ " select '" + xzq + "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.ycjzmj,a.ycsymj,a.ycjzmj-a.ycsymj,"
//					+ " a.sumbuildarea,a.usearea,a.sumbuildarea-a.usearea,roomno,b.zrcid  from tb_zrz b,h_house a"
//					+ " where b.jzwzt='0' and subStr(zrcid,instr(b.zrcid,'-')+1)=a.buildingid"
//					+ " and not exists(select 1 from tb_h t where t.hid=houseid)");
//
//			System.out.println("1)提取唯一buildingno自然幢");
//			bdcStatement
//					.execute("insert into tb_zrz (impxzq,version,zrcid,ysdm,zph,xmid,zddm,jzwzt,zcs,fwjg,fwyt,scjzmj,jgrq)  select distinct '"
//							+ xzq
//							+ "',0,b.zrcid,'6001030110',zhno,itemno,zddm,'1',floor,houseframe,houseuse,sumbuildarea,CompleteDate from h_building a,h_BuildZD_fix b"
//							+ " where b.zrcid=a.buildingid and not exists(select 1 from tb_zrz t where t.zrcid=b.zrcid)");
//			System.out.println("1.1)提取登记薄房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,zrcid, ch,mph,zl,fwyt1,hxjg,scjzmj,sctnjzmj)" + " select distinct '" + xzq
//					+ "',0,d.hid,c.zrcid,Floor,houseno,houserepose, houseuse,houseframe,UseArea,UseBuildArea  from tb_zrz c,h_housereg_distinct d"
//					+ " where c.jzwzt = '1' and d.zrcid=c.zrcid" + " and not exists(select 1 from tb_h t where t.hid=d.hid)");
//			System.out.println("1.2)提取确权房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,HXJG,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)" + " select distinct '"
//					+ xzq + "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.houseframe,a.sumbuildarea,a.usearea,a.FTArea,HouseNo,b.zrcid"
//					+ " from tb_zrz b,h_building c,h_housetradeqsdetail a" + " where b.jzwzt='1' and b.zrcid=c.buildingid and c.zqno is null"
//					+ " and c.dbh=a.dbh and (select count(*) from h_building t where t.dbh=a.dbh)=1"
//					+ " and not exists(select 1 from tb_h t where t.hid=houseid)");
//			System.out.println("1.3)提取楼盘房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,ycjzmj,yctnjzmj,ycftjzmj,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)"
//					+ " select distinct '" + xzq + "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.ycjzmj,a.ycsymj,a.ycjzmj-a.ycsymj,"
//					+ " a.sumbuildarea,a.usearea,a.sumbuildarea-a.usearea,  roomno,b.zrcid from tb_zrz b,h_house a"
//					+ " where b.jzwzt='1' and b.zrcid=a.buildingid and not exists(select 1 from tb_h t where t.hid=a.houseid)");
//			System.out.println("2)提取确权dbh唯一的幢");
//			bdcStatement
//					.execute("insert into tb_zrz (impxzq,version,zrcid,ysdm,zph,xmid,zddm,jzwzt,fwjg,fwyt,scjzmj,jgrq )"
//							+ " select   '"
//							+ xzq
//							+ "',0,b.buildingid,'6001030110',max(nvl(ZHNo,itemno)),max(itemno),max(landno),'2',max(a.houseframe),max(b.houseuse),max(a.sumbuildarea),max(CompleteDate)"
//							+ " from h_housetradeqs_dong a,h_building b " + " where a.dbh=b.dbh and b.zqno is null"
//							+ " and (select count(*) from h_building t where t.zqno is null and t.dbh=a.dbh)=1"
//							+ " and not exists(select 1 from tb_zrz t where t.zrcid=b.buildingid)" + " group by b.buildingid");
//			System.out.println("2.1)提取幢下确权房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,HXJG,scjzmj,sctnjzmj,scftjzmj,mph,zrcID) select  '" + xzq
//					+ "',0,a.houseid,'6001030140',max(ceng),max(houserepose),max(fwhxjg),max(a.houseframe),"
//					+ " max(a.sumbuildarea),max(a.usearea),max(a.FTArea),max(HouseNo),max(b.zrcid)"
//					+ " from tb_zrz b,h_building c,h_housetradeqsdetail a where b.jzwzt='2' and b.zrcid=c.buildingid" + " and c.dbh=a.dbh "
//					+ " and not exists(select 1 from tb_h t where t.hid=a.houseid) group by a.houseid");
//
//			System.out.println("3)提取确权buildingno唯一的幢");
//			bdcStatement
//					.execute("insert into tb_zrz (impxzq,version,zrcid,ysdm,zph,xmid,zddm,jzwzt,zcs,fwjg,fwyt,scjzmj,jgrq)"
//							+ " select '"
//							+ xzq
//							+ "',0,b.buildingid,'6001030110',max(nvl(ZHNo,itemno)),max(itemno),max(landno),'3',max(floor),max(a.houseframe),max(b.houseuse),max(a.sumbuildarea),max(CompleteDate)"
//							+ " from h_housetradeqs_dong a,h_building b " + " where b.buildingno=a.buildingid and b.zqno is null"
//							+ " and (select count(*) from h_building t where t.zqno is null and t.buildingno=a.buildingid)=1"
//							+ " and not exists(select 1 from tb_zrz t where t.zrcid=b.buildingid) group by b.buildingid");
//
//			System.out.println("3.1)提取幢下确权房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,HXJG,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)  select '" + xzq
//					+ "',0,a.houseid,'6001030140',max(a.ceng),max(a.houserepose),max(a.fwhxjg),max(a.houseframe),"
//					+ " max(a.sumbuildarea),max(a.usearea),max(a.FTArea),max(HouseNo),max(b.zrcid)"
//					+ " from tb_zrz b,h_building c,h_housetradeqs_dong d,h_housetradeqsdetail a"
//					+ " where b.jzwzt='3' and b.zrcid=c.buildingid and c.zqno is null"
//					+ " and c.buildingno=d.buildingid and d.dbh=a.dbh  and not exists(select 1 from tb_h t where t.hid=a.houseid)"
//					+ " group by a.houseid");
//
//			System.out.println("提取 (2、3)幢下楼盘房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,ycjzmj,yctnjzmj,ycftjzmj,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)"
//					+ " select distinct '" + xzq + "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.ycjzmj,a.ycsymj,a.ycjzmj-a.ycsymj,"
//					+ " a.sumbuildarea,a.usearea,a.sumbuildarea-a.usearea, roomno,b.zrcid  from tb_zrz b,h_house a"
//					+ " where b.jzwzt in ('2','3') and b.zrcid=a.buildingid" + " and not exists(select 1 from tb_h t where t.hid=a.houseid)");
//
//			System.out.println("4)提取剩余楼盘");
//			bdcStatement.execute("insert into tb_zrz (impxzq,version,zrcid,ysdm,zph,xmid,zddm,jzwzt,zcs,fwjg,fwyt,scjzmj,jgrq)  select distinct '"
//					+ xzq
//					+ "',0,a.buildingid,'6001030110',zhno,itemno,landno,'4',floor,houseframe,houseuse,sumbuildarea,CompleteDate from h_building a"
//					+ " where a.zqno is null and not exists(select 1 from tb_zrz t where t.zrcid=a.buildingid)");
//
//			System.out.println("4.1)提取确权房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,HXJG,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)  select  '" + xzq
//					+ "',0,a.houseid,'6001030140',max(a.ceng),max(a.houserepose),max(a.fwhxjg),max(a.houseframe),"
//					+ " max(a.sumbuildarea),max(a.usearea),max(a.FTArea),max(HouseNo),max(b.zrcid)"
//					+ " from tb_zrz b,h_building c,h_housetradeqsdetail a  where b.jzwzt='4' and b.zrcid=c.buildingid"
//					+ " and c.dbh=c.dbh and not exists(select 1 from tb_h t where t.hid=a.houseid) group by a.houseid");
//			System.out.println("4.2)提取确权楼盘房屋");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,ysdm,ch,zl,hx,ycjzmj,yctnjzmj,ycftjzmj,scjzmj,sctnjzmj,scftjzmj,mph,zrcID)"
//					+ " select distinct '" + xzq + "',0,houseid,'6001030140',ceng,houserepose,fwhxjg,a.ycjzmj,a.ycsymj,a.ycjzmj-a.ycsymj,"
//					+ " a.sumbuildarea,a.usearea,a.sumbuildarea-a.usearea, roomno,b.zrcid from tb_zrz b,h_house a"
//					+ " where b.jzwzt ='4' and b.zrcid=a.buildingid and not exists(select 1 from tb_h t where t.hid=a.houseid)");
//
//			System.out.println("4.3) 剩余的房子");
//			bdcStatement.execute("insert into tb_h (impxzq,version,hid,zrcid, ch,mph,zl,fwyt1,hxjg,scjzmj,sctnjzmj)" + " select '" + xzq
//					+ "',0,d.hid,d.zrcid,Floor,houseno,houserepose,houseuse,houseframe,UseArea,UseBuildArea from h_housereg_distinct d"
//					+ " where not exists(select 1 from tb_h t where t.hid=d.hid)");
//
//			System.out.println("待定没有幢ID的房屋zrcid设置为hid");
//			bdcStatement.execute("update tb_h set zrcid=hid where (zrcid is null or length(zrcid)=0 or zrcid = '待定') and impxzq='" + xzq + "'");
//
//			System.out.println("产生独户独幢");
//			bdcStatement.execute("insert into tb_zrz (ZRCID,version,YSDM,impxzq) select distinct ZRCID,0,'6001030110','" + xzq + "' from tb_h a"
//					+ " where ZRCID is not null and not exists(select 1 from tb_zrz b where a.zrcid=b.zrcid)");
//
//			bdcStatement.execute("update tb_zrz a set zddm = (select max(terraid) from h_houseregbase b where registerno=a.zrcid and terraid<>'待定')"
//					+ " where impxzq='" + xzq + "' and exists (select 1 from h_houseregbase b where registerno=a.zrcid  and terraid<>'待定')");
//
//			bdcStatement
//					.execute("update tb_zrz a set qxdm = decode(impxzq,'肇庆市','441202','德庆','441226','封开','441225','高要','441283','广宁','441223','怀集','441224')"
//							+ " where impxzq='" + xzq + "' and qxdm is null");
//			System.out.println("4.4)修正房屋坐落、门牌号、房屋编码、区县代码");
//			bdcStatement.execute("update tb_h a set zl = (select max(houserepose) from h_houseregbase b where a.hid=b.registerno)"
//					+ " where zl is null and impxzq='" + xzq + "' "
//					+ " and exists(select 1 from h_houseregbase b where a.hid=b.registerno and houserepose is not null)");
//
//			bdcStatement.execute("update tb_h a set zl = (select max(houserepose) from h_housetradeqsdetail b where a.hid=b.houseid)"
//					+ " where zl is null and impxzq='" + xzq + "' "
//					+ " and exists(select houserepose from h_housetradeqsdetail b where a.hid=b.houseid and houserepose is not null)");
//
//			bdcStatement.execute("update tb_h a set zl = (select houserepose from h_house b where a.hid=b.houseid)"
//					+ " where zl is null and impxzq='" + xzq + "' "
//					+ " and exists(select houserepose from h_house b where a.hid=b.houseid and houserepose is not null) ");
//
//			bdcStatement.execute("update tb_h set mph = zl where mph is null and impxzq = '" + xzq + "'");
//
//			bdcStatement.execute("update tb_h set fwbm=hid where impxzq = '" + xzq + "'");
//
//			bdcStatement
//					.execute("update tb_h a set qxdm = decode(impxzq,'肇庆市','441202','德庆','441226','封开','441225','高要','441283','广宁','441223','怀集','441224')"
//							+ " where impxzq='" + xzq + "' and qxdm is null");
//			System.out.println("5)提取项目");
//
//			//--由楼盘生成 项目信息
//			bdcStatement.execute("insert into tb_fcxmb (XMID,VERSION,XMMC,impxzq) select distinct XMID,0,XMID,'" + xzq + "' from tb_zrz a"
//					+ " where impxzq='" + xzq + "' and XMID is not null and not exists(select 1 from tb_fcxmb b where b.xmid=a.xmid)");
//			bdcStatement.execute("update tb_fcxmb a set xmzl = (select itemrepose from h_fcxmb b where a.xmid=b.presellname) where impxzq='" + xzq
//					+ "' and exists(select 1 from h_fcxmb b where a.xmid=b.presellname)");
//			System.out.println("5.1)更新项目行政区");
//			bdcStatement
//					.execute("update tb_fcxmb a set qxdm = decode(impxzq,'肇庆市','441202','德庆','441226','封开','441225','高要','441283','广宁','441223','怀集','441224')"
//							+ " where impxzq='" + xzq + "' and qxdm is null");
//
//			bdcStatement
//					.execute("create or replace view v_xm_kfs as"
//							+ " select distinct PresellName xmid,'KFS_'||b.autoid kfsid from H_FCXMB a,h_kfsdata b where a.djh = b.djh and a.loginunitid=b.loginunitid");
//			System.out.println("5.1)更新项目开发商ID");
//			bdcStatement
//					.execute("update tb_fcxmb a set KFSID = (select kfsid from v_xm_kfs b where a.xmid=b.xmid) where exists(select kfsid from v_xm_kfs b where a.xmid=b.xmid) and KFSID is null and a.impxzq='"
//							+ xzq + "'");
//			try {
//				bdcStatement.execute("drop view v_xm_kfs");
//			} catch (Exception e) {
//			}
//
//			try {
//				preStatement.execute("drop table h_housebuildZD");
//			} catch (Exception e) {
//			}
//			try {
//				preStatement.execute("drop table h_housebuildZD_fix");
//			} catch (Exception e) {
//			}
//
//			try {
//				preStatement.execute("drop table h_BuildZD_fix");
//			} catch (Exception e) {
//			}
//
//			try {
//				preStatement.execute("drop table h_housereg_distinct");
//			} catch (Exception e) {
//			}
//			System.out.println("****************************导入完成***************************");
//			//			tran.commit();
//
//		} catch (Exception e) {
//			//			try {
//			//				tran.rollback();
//			//			} catch (SQLException e1) {
//			//				e1.printStackTrace();
//			//			}
//			throw new BusinessException("生成不动产房产数据异常:" + e.getMessage(), e);
//		} finally {
//			if (preStatement != null) {
//				try {
//					preStatement.execute("drop table H_house_tdsyq");
//				} catch (Exception localException2) {
//				}
//				try {
//					preStatement.close();
//				} catch (Exception localException2) {
//				}
//			}
//			if (bdcStatement != null)
//				try {
//					bdcStatement.close();
//				} catch (Exception localException2) {
//				}
//
//			if (dbcConn != null)
//				try {
//
//					dbcConn.close();
//				} catch (Exception localException3) {
//				}
//		}
//	}

	public static synchronized void importHouseRegData(String xzq) throws NamingException, SQLException {
		if (Utils.isEmptyString(xzq)) {
			throw new BusinessException("行政区不能为空");
		}
		if (import_houseReg_id != null) {
			return;
		}
		import_houseReg_id = "";

		String filter = "1=1";
		try {
			System.out.println("****************************开始导入登记薄****************************");
			copyArchiveTable(filter, "T_HouseRegBase", "H_HouseRegBase");
			copyArchiveTable(filter, "T_HouseRegObligee", "H_HouseRegObligee");
			copyArchiveTable(filter, "T_HouseRegMain", "H_HouseRegMain");
			copyArchiveTable(filter, "T_HouseRegMortgage", "H_HouseRegMortgage");
			copyArchiveTable(filter, "T_HouseRegPledgeAbuilding", "H_HouseRegPledgeAbuilding");
			copyArchiveTable(filter, "T_HouseRegSealup", "H_HouseRegSealup");
			copyArchiveTable(filter, "T_HouseRegCaution", "H_HouseRegCaution");
			copyArchiveTable(filter, "T_HouseRegObjection", "H_HouseRegObjection");
			copyArchiveTable(filter, "T_HouseRegPledge", "H_HouseRegPledge");

			copyHouseProTable(filter, "T_HouseTradeQs", "H_HouseTradeQs");
			copyHouseProTable(filter, "T_HouseTradeQs_Dong", "H_HouseTradeQs_Dong");
			copyHouseProTable(filter, "T_HouseTradeQsDetail", "H_HouseTradeQsDetail");
			copyHouseProTable(filter, "T_HousePledge", "H_HousePledge");
			copyHouseProTable(filter, "T_HousePledgeDetail", "H_HousePledgeDetail");
			copyHouseProTable(filter, "T_HouseCancel", "H_HouseCancel");
			copyHouseProTable(filter, "T_HouseCancelDetail", "H_HouseCancelDetail");
			copyHouseProTable(filter, "T_HouseCFDetail", "H_HouseCFDetail");
			copyHouseProTable(filter, "T_RealtyCommCircs", "H_RealtyCommCircs");
			copyHouseProTable("1=1", "HousePresell", "H_HousePresell");
			copyHouseProTable("1=1", "HousePresellDetail", "H_HousePresellDetail");

			copyHouseProTable("1=1", "KFSData", "H_KFSData");
			copyHouseProTable("1=1", "HousePresellItem", "H_FCXMB");
			doImportAllHouse("");//H_House
			doImportBuild("");//H_Building

			copyHouseProTable("1=1", "T_spfht", "H_spfht");
			copyHouseProTable("1=1", "HT_Buyer", "H_Buyer");

			System.out.println("****************************导入完成****************************");
		} finally {
			import_houseReg_id = null;
		}
	}

	private static void saveToFile(java.io.InputStream inputStream, String fileName) throws IOException {
		byte[] buff = new byte[1024 * 8];
		int l = -1;
		File file = new File(fileName);
		int n = 1;
		while (file.exists()) {
			String f = fileName.substring(0, fileName.lastIndexOf(".")) + n + fileName.substring(fileName.lastIndexOf("."));
			file = new File(f);
			n++;
		}
		file.getParentFile().mkdirs();

		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream(file));
		while ((l = inputStream.read(buff)) != -1) {
			bufos.write(buff, 0, l);
		}
		bufos.flush();
		bufos.close();
	}

	private static void copyHouseProTable(String filter, String from, String to) {
		Statement fcStatement = null;
		PreparedStatement bdcStatement = null;
		Statement delStatement = null;
		ResultSet resultSet = null;

		String dataModelFC = "/house/FcTables/data";
		String dataModelBDC = "/house/bdcHouse/data";
		Connection dbcConn = null, fcConn = null;
		long l = System.currentTimeMillis();
		System.out.println("开始导入登记数据:" + from);
		int total = 0;
		try {
			// 查询 dataModelFC
			fcConn = ModelUtils.getConnection(dataModelFC);
			fcStatement = fcConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sqlHouse = "select * from " + from + " where " + filter;

			Model m = ModelUtils.getModel("/house/bdcHouse/ontology");
			Concept concept = (Concept) m.getLocalObject(from, Concept.TYPE);
			String s1 = "", s2 = "";
			List<String> allFields = new ArrayList<String>();
			List<String> allTypes = new ArrayList<String>();
			for (Relation r : concept.getRelations()) {
				if (r.getDataType().equals("Text"))
					continue;
				s1 += r.getName() + ",";
				s2 += "?,";
				allFields.add(r.getName().toUpperCase());
				allTypes.add(r.getDataType());
			}

			// 插入 dataModelBDC
			String insertSql = "insert into " + to + " (" + s1.substring(0, s1.length() - 1) + ")" + " values (" + s2.substring(0, s2.length() - 1)
					+ ")";
			dbcConn = ModelUtils.getConnection(dataModelBDC);
			delStatement = dbcConn.createStatement();
			delStatement.executeUpdate("TRUNCATE TABLE " + to);

			bdcStatement = dbcConn.prepareStatement(insertSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int batchCnt = 0;
			resultSet = fcStatement.executeQuery(sqlHouse);

			while (resultSet.next()) {
				for (int i = 0; i < allFields.size(); i++) {
					String dataType = allTypes.get(i);
					if ("String".equals(dataType)) {
						String name = allFields.get(i);
						String v = resultSet.getString(allFields.get(i));
						if (name.toLowerCase().matches(regxNames)) {
							v = replaceTxt(v);
						}
						bdcStatement.setString(i + 1, v);
					} else if ("Decimal".equals(dataType)) {
						bdcStatement.setBigDecimal(i + 1, resultSet.getBigDecimal(allFields.get(i)));
					} else if (dataType.matches("Time")) {
						bdcStatement.setTime(i + 1, resultSet.getTime(allFields.get(i)));
					} else if (dataType.matches("Date")) {
						bdcStatement.setDate(i + 1, resultSet.getDate(allFields.get(i)));
					} else if (dataType.matches("DateTime")) {
						bdcStatement.setTimestamp(i + 1, resultSet.getTimestamp(allFields.get(i)));
					} else if ("Integer".equals(dataType)) {
						bdcStatement.setInt(i + 1, resultSet.getInt(allFields.get(i)));
					} else if ("Float".equals(dataType)) {
						bdcStatement.setFloat(i + 1, resultSet.getFloat(allFields.get(i)));
					} else if ("Text".equals(dataType)) {
						String s = SQLUtils.getClobText(resultSet.getClob(allFields.get(i)));
						bdcStatement.setString(i + 1, s);
					}
				}
				bdcStatement.addBatch();
				batchCnt++;
				if (batchCnt == 200) {
					System.out.print(".");
					total += batchCnt;
					batchCnt = 0;
					if (total % 20000 == 0) {
						System.out.println(total);
					}
					bdcStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				bdcStatement.executeBatch();
				total += batchCnt;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导入登记数据" + from + "异常:" + e.getMessage(), e);
		} finally {
			System.out.println("\n导入登记数据" + from + "=" + total + "条,耗时:" + (System.currentTimeMillis() - l) / 1000 + "秒");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception localException2) {
				}

			}
			if (fcStatement != null)
				try {

					fcStatement.close();
				} catch (Exception localException2) {
				}
			if (fcConn != null)
				try {

					fcConn.close();
				} catch (Exception localException3) {
				}
			if (bdcStatement != null) {
				try {
					bdcStatement.clearBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					bdcStatement.close();
				} catch (Exception localException2) {
				}
			}
			if (delStatement != null)
				try {
					delStatement.close();
				} catch (Exception localException2) {
				}
			if (dbcConn != null)
				try {
					dbcConn.close();
				} catch (Exception localException3) {
				}
		}
	}

	private static void copyArchiveTable(String filter, String from, String to) {
		Statement fcStatement = null;
		PreparedStatement bdcStatement = null;
		Statement delStatement = null;
		ResultSet resultSet = null;

		String dataModelFC = "/house/FcArchive/data";
		String dataModelBDC = "/house/dbcArchive/data";
		Connection dbcConn = null, fcConn = null;
		long l = System.currentTimeMillis();
		System.out.println("开始导入登记数据:" + from);
		int total = 0;
		try {
			// 查询 dataModelFC
			fcConn = ModelUtils.getConnection(dataModelFC);
			fcStatement = fcConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sqlHouse = "select * from " + from + " where " + filter;

			Model m = ModelUtils.getModel("/house/dbcArchive/ontology");
			Concept concept = (Concept) m.getLocalObject(from, Concept.TYPE);
			String s1 = "", s2 = "";
			List<String> allFields = new ArrayList<String>();
			List<String> allTypes = new ArrayList<String>();
			for (Relation r : concept.getRelations()) {
				if (r.getDataType().equals("Text"))
					continue;
				s1 += r.getName() + ",";
				s2 += "?,";
				allFields.add(r.getName().toUpperCase());
				allTypes.add(r.getDataType());
			}

			// 插入 dataModelBDC
			String insertSql = "insert into " + to + " (" + s1.substring(0, s1.length() - 1) + ")" + " values (" + s2.substring(0, s2.length() - 1)
					+ ")";
			dbcConn = ModelUtils.getConnection(dataModelBDC);
			delStatement = dbcConn.createStatement();
			delStatement.executeUpdate("TRUNCATE TABLE " + to);

			bdcStatement = dbcConn.prepareStatement(insertSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int batchCnt = 0;
			resultSet = fcStatement.executeQuery(sqlHouse);
			while (resultSet.next()) {
				for (int i = 0; i < allFields.size(); i++) {
					String dataType = allTypes.get(i);
					if ("String".equals(dataType)) {
						String name = allFields.get(i);
						String v = resultSet.getString(allFields.get(i));
						if (name.toLowerCase().matches(regxNames)) {
							v = replaceTxt(v);
						}
						bdcStatement.setString(i + 1, v);
					} else if ("Decimal".equals(dataType)) {
						bdcStatement.setBigDecimal(i + 1, resultSet.getBigDecimal(allFields.get(i)));
					} else if (dataType.matches("Time")) {
						bdcStatement.setTime(i + 1, resultSet.getTime(allFields.get(i)));
					} else if (dataType.matches("Date")) {
						bdcStatement.setDate(i + 1, resultSet.getDate(allFields.get(i)));
					} else if (dataType.matches("DateTime")) {
						bdcStatement.setTimestamp(i + 1, resultSet.getTimestamp(allFields.get(i)));
					} else if ("Integer".equals(dataType)) {
						bdcStatement.setInt(i + 1, resultSet.getInt(allFields.get(i)));
					} else if ("Text".equals(dataType)) {
						String s = SQLUtils.getClobText(resultSet.getClob(allFields.get(i)));
						bdcStatement.setString(i + 1, s);
					}
				}
				bdcStatement.addBatch();
				batchCnt++;
				if (batchCnt == 200) {
					System.out.print(".");
					total += batchCnt;
					batchCnt = 0;
					if (total % 20000 == 0) {
						System.out.println(total);
					}
					bdcStatement.executeBatch();
				}
			}
			if (batchCnt > 0) {
				bdcStatement.executeBatch();
				total += batchCnt;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导入登记数据" + from + "异常:" + e.getMessage(), e);
		} finally {
			System.out.println("\n导入登记数据" + from + total + "条,耗时:" + (System.currentTimeMillis() - l) / 1000 + "秒");
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception localException2) {
				}

			}
			if (fcStatement != null)
				try {

					fcStatement.close();
				} catch (Exception localException2) {
				}
			if (fcConn != null)
				try {

					fcConn.close();
				} catch (Exception localException3) {
				}
			if (bdcStatement != null) {
				try {
					bdcStatement.clearBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					bdcStatement.close();
				} catch (Exception localException2) {
				}
			}
			if (delStatement != null)
				try {
					delStatement.close();
				} catch (Exception localException2) {
				}
			if (dbcConn != null)
				try {
					dbcConn.close();
				} catch (Exception localException3) {
				}
		}
	}

}