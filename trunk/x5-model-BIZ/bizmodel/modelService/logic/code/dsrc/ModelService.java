public class ModelService {
	private static final String DATA_MODEL = "/bizmodel/modelService/data";

	public static void createElasticSearchIndice(final String concept) {
		ElasticSearch.buildIndice(DATA_MODEL, concept, concept, null);
	}

	public static void importDataToElasticSearch(final String concept) {
		ElasticSearch.importDataToIndice(DATA_MODEL, concept, "select * from " + concept, concept, null, null);
	}
}