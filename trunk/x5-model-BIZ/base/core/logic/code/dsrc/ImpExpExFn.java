import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.butone.doc.DocExUtils;
import com.butone.excel.ExcelReportUtils;
import com.butone.logic.impl.ProcessLogicPluginContext;
import com.butone.system.SystemConst;
import com.butone.utils.StringUtils;
import com.butone.utils.SysUtils;
import com.justep.exception.BusinessException;
import com.justep.system.context.ContextHelper;
import com.justep.system.data.Row;
import com.justep.system.data.Table;
import com.justep.system.util.CommonUtils;

/**
 * 文件导入导出扩展函数库 2015-12-21
 * 
 * @author 李泽华
 * 
 */
public class ImpExpExFn {

	// 解析ExcelBook对象
	public static Object excelBook(InputStream input, String ext) throws IOException {
		// 兼容版本
		Object book = null;
		try {
			book = new XSSFWorkbook(input);
		} catch (Exception e) {
			input.reset();
			book = new HSSFWorkbook(input);
		}
		return book;
	}

	// 获得Sheet对象
	public static Object sheetByIndex(Object book, Integer index) {
		if (book instanceof XSSFWorkbook) {
			XSSFWorkbook excelBook = (XSSFWorkbook) book;
			if (index >= excelBook.getNumberOfSheets())
				return null;
			else
				return excelBook.getSheetAt(index);
		} else {
			HSSFWorkbook excelBook = (HSSFWorkbook) book;
			if (index >= excelBook.getNumberOfSheets())
				return null;
			else
				return excelBook.getSheetAt(index);
		}
	}

	// 解析Sheet对象
	public static Object excelSheet(InputStream input, String ext) throws IOException {
		Object book = excelBook(input, ext);
		return sheetByIndex(book, 0);
	}

	// 获得Row
	public static Object excelRow(Object sheet, Integer row) {
		if (sheet instanceof XSSFSheet) {
			XSSFSheet excelSheet = (XSSFSheet) sheet;
			return excelSheet.getRow(row);
		} else {
			HSSFSheet excelSheet = (HSSFSheet) sheet;
			return excelSheet.getRow(row);
		}
	}

	public static Object cellValue(Object sheet, Integer row, Integer col) {
		if (sheet instanceof XSSFSheet) {
			XSSFRow excelRow = (XSSFRow) excelRow(sheet, row);
			if (excelRow == null)
				return null;
			XSSFCell cell = excelRow.getCell(col);
			if (cell == null)
				return null;
			else {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					return cell.getStringCellValue();
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						java.util.Date dateTemp = cell.getDateCellValue();
						if (dateTemp == null)
							return null;
						else
							return CommonUtils.toDate(dateTemp);
					}
					double value = cell.getNumericCellValue();
					if (value == (int) value)
						return (int) value;
					else
						return value;

				} else
					return null;
			}
		} else {
			HSSFRow excelRow = (HSSFRow) excelRow(sheet, row);
			if (excelRow == null)
				return null;
			HSSFCell cell = excelRow.getCell(col);
			if (cell == null)
				return null;
			else {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					return cell.getStringCellValue();
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						java.util.Date dateTemp = cell.getDateCellValue();
						if (dateTemp == null)
							return null;
						else
							return CommonUtils.toDate(dateTemp);
					}
					double value = cell.getNumericCellValue();
					if (value == (int) value)
						return (int) value;
					else
						return value;

				} else
					return null;
			}
		}
	}

	/**
	 * 导出txt
	 * 
	 * @param content
	 *            内容
	 * @param charset
	 *            编码 UTF-8 GBK
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static InputStream exportTxt(String content, String charset) throws UnsupportedEncodingException {
		ByteArrayInputStream stream = new ByteArrayInputStream(content.getBytes(charset));
		return stream;
	}

	private static void unZip(String zipFile, String descDir) throws Exception {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
			;
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
	}

	public static String importFht(String bizKey, InputStream stream) throws Exception {
		String docPath = DocExUtils.uploadFile("房产分户图", stream, ".zip");
		SysUtils.executeSql("delete from B_SimpleDoc where FDocID=?", bizKey);
		SysUtils.executeSql("insert into B_SimpleDoc(FDocID,Version,FDocName,FDocPath,FCreator,FCreateTime)" + "values(?,0,?,?,?,sysdate)", bizKey,
				"房产分户图.zip", docPath, ContextHelper.getPerson().getName());
		String srcFile = SystemConst.getDocExPath() + docPath;
		String trgFolder = SystemConst.getDocExPath() + StringUtils.getNewGuid32() + "\\";
		try {
			unZip(srcFile, trgFolder);
			File trgFolderFile = new File(trgFolder);
			File[] files = trgFolderFile.listFiles()[0].listFiles();
			String errorInfo = "";
			for (File file : files) {
				String fileName = file.getName();
				String hid = fileName.substring(fileName.indexOf("_"));
				Map<String, Object> qryResult = SysUtils.queryFldsValue("select * from TB_H where hid=?", hid);
				if (qryResult == null) {
					if (errorInfo.equals(""))
						errorInfo = hid;
					else
						errorInfo = errorInfo + "," + hid;
				}
			}
			if (!errorInfo.equals(""))
				throw new BusinessException(errorInfo + "户表记录不存在");
			for (File file : files) {
				String fileName = file.getName();
				String hid = fileName.substring(fileName.indexOf("-"));
				FileInputStream inputStream = new FileInputStream(file);
				JSONObject docJson = DocExFn.genAttach(inputStream, fileName, "房产分户图");
				String docIds = "[" + docJson.toString() + "]";
				SysUtils.executeSql("update TB_H set fcfht=? where hid=?", docIds, hid);
			}
		} finally {
			SysUtils.deleteDir(new File(trgFolder));
		}
		return docPath;
	}

	public static InputStream simpleExcelExport(String templateKey, Object... params) throws Exception {
		InputStream stream = null;
		InputStream fileStream = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			//读取模板
			String sql = SysUtils.queryFldValue("select FSQL from B_OfficeTemplate where FTemplateKey=?", templateKey).toString();
			String templatePath = SystemConst.getDocExPath()
					+ SysUtils.queryFldValue("select fpath from B_OfficeVersion v,B_OfficeTemplate t where v.FBizKey=t.fid and  t.FTemplateKey=?",
							templateKey).toString();
			String format = "XLS";
			if (templatePath.endsWith("xlsx"))
				format = "XLSX";
			if (format.equalsIgnoreCase("XLS")) {
				fileStream = new FileInputStream(templatePath);
				HSSFWorkbook book = new HSSFWorkbook(fileStream);
				HSSFSheet sheet = book.getSheetAt(0);
				//读取字段
				List<String> fldList = new ArrayList<String>();
				int i = 0;
				HSSFRow hssfRow = sheet.getRow(1);
				while (true) {
					HSSFCell cell = hssfRow.getCell(i);
					if (cell == null)
						break;
					String fld = cell.getStringCellValue();
					if (fld != null && !fld.trim().equals(""))
						fldList.add(fld.toUpperCase());
					i = i + 1;
				}
				//读取数据
				int iRow = 1;
				Table table = SysUtils.query(sql, params);
				Iterator<Row> iter = table.iterator();
				while (iter.hasNext()) {
					hssfRow = sheet.getRow(iRow);
					if (hssfRow == null) {
						hssfRow = sheet.createRow(iRow);
					}
					Row row = iter.next();
					for (int j = 0; j < fldList.size(); j++) {
						Object value = row.getValue(fldList.get(j));
						int type = HSSFCell.CELL_TYPE_STRING;
						if (value == null)
							value = "";
						else if (value instanceof BigDecimal) {
							type = HSSFCell.CELL_TYPE_NUMERIC;
						}
						HSSFCell cell = hssfRow.getCell(j);
						if (cell == null)
							cell = hssfRow.createCell(j, type);
						if (value instanceof BigDecimal) {
							cell.setCellValue(((BigDecimal) value).doubleValue());
						} else
							cell.setCellValue(value.toString());
					}
					iRow = iRow + 1;
				}
				book.write(outStream);
			}
			stream = new ByteArrayInputStream(outStream.toByteArray());
		} finally {
			outStream.close();
			if (fileStream != null)
				fileStream.close();
		}
		return stream;
	}

	public static InputStream commonExcelExport(String templateKey, Map<String, Object> sqlParam, Map<String, Object> extendParam, String outFileType)
			throws Exception {
		return ExcelReportUtils.genReportStream(templateKey, sqlParam, outFileType, extendParam, null);
	}

	//导入cad坐标文件
	public static void impCadToTable(Object _importFileStream, String dkTableId, String coordTableId) throws Exception {
		Table dkTable = ProcessLogicPluginContext.getTableControlObjectTarget(dkTableId);
		//		Table coordTable = null;
		//		if (coordTableId != null)
		//			coordTable = ProcessLogicPluginContext.getTableControlObjectTarget(coordTableId);

		String tempUploadPath = SystemConst.getParamValue("tempUploadPath");
		ByteArrayInputStream in = (ByteArrayInputStream) _importFileStream;
		//写入临时文件夹
		String filePath = tempUploadPath + SysUtils.guid() + ".dwg";
		FileOutputStream out = new FileOutputStream(new File(filePath));
		try {
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
		} finally {
			out.close();
		}
		//请求SOE处理 ，返回JSON
		JSONObject paramJson = new JSONObject();
		paramJson.put("uploadedFilePath", filePath);
		paramJson.put("fileType", "dwg");
		paramJson.put("f", "json");
		JSONObject restJson = SysUtils.callRest(SystemConst.getOneMapSrvUrl() + "//gisFileService//shapefileToJson", paramJson);
		if (!restJson.getBoolean("success"))
			throw new BusinessException("导入DWG异常 ");
		//解析返回JSON到Table对象中
		JSONArray geomertryInfos = restJson.getJSONArray("geomertryInfos");
		for (int i = 0; i < geomertryInfos.size(); i++) {
			JSONArray rings = geomertryInfos.getJSONArray(i);
			JSONObject geometry = new JSONObject();
			geometry.put("rings", rings);
			Row row = dkTable.appendRow();
			row.setString("geometry", geometry.toJSONString());
		}
	}

	public static InputStream customSqlExcelExport(String templateKey, String customSql, Map<String, Object> sqlParam,
			Map<String, Object> extendParam, String outFileType) throws Exception {
		return ExcelReportUtils.genReportStream(templateKey, sqlParam, outFileType, extendParam, customSql);
	}
}
