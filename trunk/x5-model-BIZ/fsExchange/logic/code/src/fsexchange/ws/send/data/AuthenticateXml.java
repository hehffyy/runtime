package fsexchange.ws.send.data;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AuthenticateXml implements XmlOut {
	private String authenticateid;
	private String permid;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("authenticate");
		root.addElement("authenticateid").addCDATA(this.authenticateid);
		root.addElement("permid").addCDATA(this.permid);
		return root;
	}

	public String getAuthenticateid() {
		return authenticateid;
	}

	public void setAuthenticateid(String authenticateid) {
		this.authenticateid = authenticateid;
	}

	public String getPermid() {
		return permid;
	}

	public void setPermid(String permid) {
		this.permid = permid;
	}
}
