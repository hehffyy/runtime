package fsexchange.ws.send.data.cooperation;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Parallel implements fsexchange.ws.send.data.XmlOut {

	private String cid;
	private String cbsnum;
	private String cprojectname;
	private String cstartdate;
	private String cenddate;
	private String cstatus;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("parallel");
		root.addElement("cid").addCDATA(this.cid);
		root.addElement("cbsnum").addCDATA(this.cbsnum);
		root.addElement("cprojectname").addCDATA(this.cprojectname);
		root.addElement("cstartdate").addCDATA(this.cstartdate);
		root.addElement("cenddate").addCDATA(this.cenddate);
		root.addElement("cstatus").addCDATA(this.cstatus);
		return root;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCbsnum() {
		return cbsnum;
	}

	public void setCbsnum(String cbsnum) {
		this.cbsnum = cbsnum;
	}

	public String getCprojectname() {
		return cprojectname;
	}

	public void setCprojectname(String cprojectname) {
		this.cprojectname = cprojectname;
	}

	public String getCstartdate() {
		return cstartdate;
	}

	public void setCstartdate(String cstartdate) {
		this.cstartdate = cstartdate;
	}

	public String getCenddate() {
		return cenddate;
	}

	public void setCenddate(String cenddate) {
		this.cenddate = cenddate;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
}
