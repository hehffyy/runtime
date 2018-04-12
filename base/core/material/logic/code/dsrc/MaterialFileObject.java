import com.alibaba.fastjson.JSONObject;


public class MaterialFileObject {

	
	private String docID;
	private String docName;
	private String  docPath;
	private String fileID;
 
	public String getDocID() {
		return docID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	
	public void readerFromJson(Object content ) {
		JSONObject json = (JSONObject) content;
 
		try {
			if (json.get("docID") != null)
				this.setDocID(json.get("docID").toString());
			if (json.get("docName") != null)
				this.setDocName(json.get("docName").toString());
			if (json.get("docPath") != null)
				this.setDocPath(json.get("docPath").toString());
			if (json.get("fileID") != null)
				this.setFileID(json.get("fileID").toString());
			 
		} catch (Exception e) {
			throw new RuntimeException("MaterialFileObject转换失败！\n" + content.toString(), e);
		}

	}
	
	

}
