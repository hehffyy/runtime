import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justep.doc.Doc;
import com.justep.doc.DocUrlPatternHelper.PartType;
import com.justep.doc.Docs;
import com.justep.message.CommonMessages;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.util.Utils;

public class Bdc {

	/**
	 * 获得房产户型图的图片URL
	 * @param bizRecId
	 * @return
	 */
	public static String getHxtImageUrl(String bizRecId) {
		Map<String, String> sql = new HashMap<String, String>();
		sql.put(DatabaseProduct.DEFAULT.name(), "select a.fcfht from t_fdcqdu a ,t_fdcqduo b where b.fguid=a.fdcqID and b.fBizRecId=?");
		List<Object> binds = new ArrayList<Object>();
		binds.add(bizRecId);
		Table t = SQL.select(sql, binds, "/base/data");
		if (t.size() == 0) {
			return "";
		} else {
			String urlType = "";
			Utils.check(Utils.isNotEmptyString(urlType), CommonMessages.class, CommonMessages.PARAM_NULL1, "urlType");
			JSONObject json = JSON.parseObject(t.iterator().next().getString(0));
			Docs docs = new Docs();
			if (json.getString("docID") != null) {
				docs.queryByDocID(json.getString("docID"));
			} else if (json.getString("docPath") != null) {
				docs.queryByDocPath(json.getString("docPath"));
			}
			Doc doc = docs.getIterator().next();
			Map<String, String> params = new LinkedHashMap<String, String>();

			params.put("versionID", "last");

			params.put("partType", PartType.content.toString());
			params.put("fileID", doc.getsFileID());
			params.put("resultID", "");
			String url = doc.getDocUrl(urlType, params);
			return url;
		}
	}
}