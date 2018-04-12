import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.butone.utils.StringUtils;
import com.justep.filesystem.FileSystemWrapper;
import com.justep.model.Concept;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.model.Relation;
import com.justep.system.data.ModifyState;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.data.TableMetaData;
import com.justep.system.data.TableUtils;
import com.justep.system.transform.SimpleTransform;
import com.justep.system.util.BizSystemException;

public class BoundaryFileImport {

	public static void main(String[] args) {
		int n = 1;
		while (true) {
			int m = (n * 7 + 5);
			System.out.println(n + ":" + m);
			if (m % 3 == 1 && m % 5 == 2) {
				System.out.println(n);
				break;
			}
			n++;
		}
	}

	private static final String DATAMODEL = "/base/system/fileImpExp/data";

	/**
	 * 界址点文件导入
	 * @param uploadFile
	 * @param fileType
	 * @param returnData
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Map<String, Table> boundaryFileImport(InputStream uploadFile, String fileType, String returnData)
			throws UnsupportedEncodingException, NamingException, SQLException {
		Map<String, Table> ret = null;
		fileType = URLDecoder.decode(fileType, "UTF-8").replaceAll("\\\\", "/");
		if (fileType != null) {
			ret = analyseTxtFile(uploadFile);
		}
		if (ret == null)
			new BizSystemException("解析文件失败,不支持的文件类型：" + fileType);
		if ("true".equals(returnData))
			return ret;
		else
			return new HashMap<String, Table>();
	}

	/**
	 * 解析txt文件
	 * @param fileName
	 */
	private static Map<String, Table> analyseTxtFile(InputStream fileName) {

		Map<String, Table> map = new HashMap<String, Table>();
		BufferedReader reader = null;

		/**项目信息*/
		Table tab_xm2 = TableUtils.createTable("BA_XMXX", DATAMODEL);

		/**地块信息*/
		Table tab_dk = TableUtils.createTable("BA_DKXX", DATAMODEL);

		/**坐标信息*/
		Table tab_zb = TableUtils.createTable("BA_ZBXX", DATAMODEL);

		try {
			reader = new BufferedReader(new InputStreamReader(fileName, "gb2312"));
			String text = null;
			int count = 1;
			String xmGuid = StringUtils.getNewGuid32();
			Row tempRow = tab_xm2.appendRow(xmGuid);

			List<String> columnList = new ArrayList<String>();
			Collection<String> colle = tab_xm2.getColumnNames();
			for (String colName : colle) {
				columnList.add(colName);
			}
			TableMetaData meta = tab_xm2.getMetaData();
			String dkGuid = "";
			while ((text = reader.readLine()) != null) {
				text = text.trim();
				//处理只有属性，没有项目的导入文件
				if (count == 1 && text.equals("[属性描述]")) {
					count = 23;
				}

				if ("".equals(text))
					continue;
				if (text.indexOf("=") > 0) {
					// 项目信息/属性信息
					String[] pArry = text.split("=");
					if (pArry.length == 0)
						continue;
					String value = "";
					if (pArry.length == 2)
						value = pArry[1];
					String columnType = meta.getColumnMetaData(count).getType();
					Object objValue = SimpleTransform.transToObj(columnType, value);
					tempRow.setValue(columnList.get(count), objValue);
					tempRow.setState(ModifyState.NONE);
					count++;
				} else {
					String[] pointArry = text.split(",");
					if (pointArry.length < 4)
						continue;

					boolean bLandStart = pointArry.length > 4;
					if (bLandStart) {
						// 地块信息
						dkGuid = StringUtils.getNewGuid32();
						Row row_dk = tab_dk.appendRow(dkGuid);
						row_dk.setInteger("fJZDS", new Integer(pointArry[0]));
						row_dk.setDecimal("fDKMJ", new BigDecimal(pointArry[1]));
						row_dk.setString("fDKMC", pointArry[3]);
						//row_dk.setString("fXMMC", pointArry[4]);
						//row_dk.setString("fJLTXZX", pointArry[4]);
						row_dk.setString("fTFH", pointArry[5]);
						row_dk.setString("fDKYT", pointArry[6]);
						//row_dk.setString("", xmGuid);
						row_dk.setState(ModifyState.NONE);

					} else {
						// 坐标信息
						String fNo = pointArry[0];
						int ringIndex = Integer.parseInt(pointArry[1]);
						String x = pointArry[2];
						String y = pointArry[3];
						Row row_zb = tab_zb.appendRow();
						row_zb.setString("fDianH", fNo);
						row_zb.setInteger("fQH", ringIndex);
						row_zb.setDecimal("fXZB", new BigDecimal(x));
						row_zb.setDecimal("fYZB", new BigDecimal(y));
						row_zb.setString("fDKXXID", dkGuid);

						row_zb.setState(ModifyState.NONE);
					}
				}
			}
			map.put("BA_XMXX", tab_xm2);
			map.put("BA_DKXX", tab_dk);
			map.put("BA_ZBXX", tab_zb);
			return map;
		} catch (Exception e) {
			throw new BizSystemException("解析文件失败：" + e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据xml创建table
	 * @param xmlFileName
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Table createTable(String xmlFileName) throws DocumentException {

		List<String> names_prop = new ArrayList<String>();
		List<String> types_prop = new ArrayList<String>();
		String fileName = FileSystemWrapper.instance().getRealPath("/base/system/fileImpExp/" + xmlFileName);
		File file = new File(fileName);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext()) {
			Element field = (Element) it.next();
			names_prop.add(field.attributeValue("type"));
			types_prop.add(field.attributeValue("name"));
		}
		return TableUtils.createTable(null, names_prop, types_prop);
	}

	public static List<Object> getImportTable() {

		Model model = ModelUtils.getModel("/base/system/fileImpExp/ontology");
		Concept concept = model.getUseableConcept("BA_XMXX");
		List<Object> list = new ArrayList<Object>();

		for (Relation relation : concept.getRelations()) {
			JSONObject object = new JSONObject();
			object.put("relation", relation.getName());
			object.put("relationName", relation.getLabel("zh_CN"));
			list.add(relation.getName() + "&" + relation.getLabel("zh_CN"));
		}
		return list;
	}

}