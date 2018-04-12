package fsexchange.ws.send.data.cooperation.permission;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;

public class Certificate implements fsexchange.ws.send.data.XmlOut {
	//head
	private String id;
	private String code;
	private String fullname;
	private String name;
	private String desc;
	private String deptid;
	private String createtime;
	private String valid;
	private String version;
	private List<XmlOut> data = new ArrayList<XmlOut>();

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("certificate");
		/*root.addElement("id").addCDATA(this.id);
		root.addElement("code").addCDATA(this.code);
		root.addElement("fullname").addCDATA(this.fullname);
		root.addElement("name").addCDATA(this.name);
		root.addElement("desc").addCDATA(this.desc);
		root.addElement("deptid").addCDATA(this.deptid);
		root.addElement("createtime").addCDATA(this.createtime);
		root.addElement("valid").addCDATA(this.valid);
		root.addElement("version").addCDATA(this.version);
		Element data = root.addElement("data");
		for(XmlOut o : this.data){
			data.add(o.getElement());
		}*/
		return root;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<XmlOut> getData() {
		return data;
	}

	public void setData(List<XmlOut> data) {
		this.data = data;
	}

}
