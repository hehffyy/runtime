import java.util.ArrayList;
import java.util.List;


public class MenuCollection {
	
	private String id;
	private String name;
	private String url;
	private String process;
	private String activity;
	private List<MenuCollection> childNodes;
	
	public List<MenuCollection> getChildNodes() {
		if(childNodes ==null)
		childNodes = new ArrayList<MenuCollection>();
		return childNodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setChildNodes(List<MenuCollection> childNodes) {
		this.childNodes = childNodes;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

}
