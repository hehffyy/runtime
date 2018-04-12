import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.butone.logic.impl.ExcelImport;
import com.justep.exception.BusinessException;
import com.justep.system.data.Table;

public class FileFn {

	public static int loadExcelToTable(InputStream excel, Object mappingConfig, Table table) throws DocumentException {
		ExcelImport imp = new ExcelImport();
		Document configDocument = null;
		if (mappingConfig instanceof Document) {
			configDocument = (Document) mappingConfig;
		} else if (mappingConfig instanceof String) {
			configDocument = DocumentHelper.parseText((String) mappingConfig);
		} else {
			throw new BusinessException("Excel与Table映射参数错误");
		}
		return imp.generate(excel, configDocument, table);
	}
}
