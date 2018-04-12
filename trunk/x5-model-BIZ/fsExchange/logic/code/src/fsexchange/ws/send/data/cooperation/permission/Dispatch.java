package fsexchange.ws.send.data.cooperation.permission;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;

public class Dispatch implements XmlOut {
	private String disptachid;
	private String disptachname;
	private String disptachstatus;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("dispatch");
		/*
		root.addElement("disptachid").addCDATA(this.disptachid);
		root.addElement("disptachname").addCDATA(this.disptachname);
		root.addElement("disptachstatus").addCDATA(this.disptachstatus);
		*/
		return root;
	}

	public String getDisptachid() {
		return disptachid;
	}

	public void setDisptachid(String disptachid) {
		this.disptachid = disptachid;
	}

	public String getDisptachname() {
		return disptachname;
	}

	public void setDisptachname(String disptachname) {
		this.disptachname = disptachname;
	}

	public String getDisptachstatus() {
		return disptachstatus;
	}

	public void setDisptachstatus(String disptachstatus) {
		this.disptachstatus = disptachstatus;
	}

}
