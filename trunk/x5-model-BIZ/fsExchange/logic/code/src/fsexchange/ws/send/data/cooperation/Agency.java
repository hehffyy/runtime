package fsexchange.ws.send.data.cooperation;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;

/**
 * 代办人
 * @author Administrator
 *
 */
public class Agency implements XmlOut {
	private String id;
	private String agencyname;
	private String cardid;
	private String mobilephone;
	private String email;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("agency");
		root.addElement("id").addCDATA(this.id);
		root.addElement("agencyname").addCDATA(this.agencyname);
		root.addElement("cardid").addCDATA(this.cardid);
		root.addElement("mobilephone").addCDATA(this.mobilephone);
		root.addElement("email").addCDATA(this.email);
		return root;
	}
}
