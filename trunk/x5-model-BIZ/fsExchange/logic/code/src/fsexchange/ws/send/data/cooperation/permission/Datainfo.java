package fsexchange.ws.send.data.cooperation.permission;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.XmlOut;

public class Datainfo implements XmlOut {

	private String label;
	private String labelname;
	private String value;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("datainfo");
		root.addElement("label").addCDATA(this.label);
		root.addElement("labelname").addCDATA(this.labelname);
		root.addElement("value").addCDATA(this.value);
		return root;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
