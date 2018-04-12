import java.util.HashMap;
import java.util.Map;

import com.butone.elasticsearch.ElasticSearchClient;
import com.butone.elasticsearch.model.Transform;
import com.butone.x5Impl.BusinessServerImpl;
import com.justep.model.Config;
import com.justep.model.ConfigItem;
import com.justep.model.Model;
import com.justep.model.ModelUtils;
import com.justep.system.data.DatabaseProduct;
import com.justep.system.data.SQL;
import com.justep.system.data.Table;
import com.justep.util.Utils;

public class ElasticSearch {
	private static Transform transform;

	static {

		ElasticSearchClient client = new ElasticSearchClient();
		Model model = ModelUtils.getModel("/base/elasticSearch/config");
		Config config = model.getUseableConfig("elasticsearch");
		client.setClusterName(config.getItem("clusterName").getValue());
		config = (Config) model.getLocalObject("transportAddress", Config.TYPE);
		for (String n : config.getNames()) {
			try {
				ConfigItem i = config.getItem(n);
				String host = i.getValue("host");
				String ip = i.getValue("ip");
				String port = i.getValue("port");
				if (Utils.isNotEmptyString(ip)) {
					byte[] addr = ElasticSearchClient.ipToBytesByReg(ip);
					client.addNode(addr, Integer.parseInt(port));
				} else if (Utils.isNotEmptyString(host)) {
					client.addNode(host, Integer.parseInt(port));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		client.init();
		transform = new Transform(client);
	}

	public static Transform getElasticSearchTransform() {
		return transform;
	}

	public static void buildIndice(String dataModel, String concept, String alias, String columns) {
		transform.conceptToElasticSearchIndice(dataModel, concept, alias, columns);
	}

	public static void importDataToIndice(final String dataModel, final String concept, String sql, final String alias, Integer page, Integer limit) {
		if (page == null || page <= 0)
			page = 1;
		if (limit == null)
			limit = -1;
		if (limit == -1 || page > 20) {
			Map<String, String> sqlMaps = new HashMap<String, String>();
			sqlMaps.put(DatabaseProduct.DEFAULT.name(), "select count(*) FCNT from (" + sql + ") tmp");
			Table tab = SQL.select(sql, null, dataModel);
			long total = tab.iterator().next().getDecimal("FCNT").longValue();
			limit = new Long(total / 20).intValue();
			page = new Long(total / limit).intValue() + 1;
		}

		for (int i = 0; i < page; i++) {
			final long from = i * limit, to = (i + 1) * limit;
			final String batchSQL = "select t2.* from (select rownum no,t1.* from (" + sql + ") t1 where rownum<=" + to + ") t2 where no>" + from;
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					BusinessServerImpl.createRequestContext2(null, null, null, null, null);
					long l = System.currentTimeMillis();
					System.out.println("begin importDataToElasticSearch [" + concept + "] " + (from + 1) + " - " + to);
					try {
						transform.importDataToElasticSearch(dataModel, concept, batchSQL, alias);
					} finally {
						System.out.println("end importDataToElasticSearch [" + concept + "] " + (from + 1) + " - " + to + ", lost time:" + (System.currentTimeMillis() - l) / 1000);
						BusinessServerImpl.removeRequestContext();
					}
				}
			});
			t.setName("Thread-importDataToElasticSearch");
			t.start();
		}
	}
}