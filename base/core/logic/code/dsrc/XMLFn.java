import java.util.List;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.justep.util.Utils;

public class XMLFn {

	public static Document createDocument(String text) throws DocumentException {
		if (Utils.isEmptyString(text)) {
			return DocumentHelper.createDocument();
		} else
			return DocumentHelper.parseText(text);
	}

	public static Element addNode(Branch node, String name, Object value) {
		Element ele = node.addElement(name);
		if (value != null)
			ele.setText(value.toString());
		return ele;
	}

	public static Node setNodeText(Node node, String text) {
		node.setText(text);
		return node;
	}

	public static Element addAttribute(Element ele, String name, Object value) {
		ele.addAttribute(name, value == null ? "" : value.toString());
		return ele;
	}

	public static Element setNodeCDATA(Element ele, Object value) {
		ele.addCDATA(value == null ? "" : value.toString());
		return ele;
	}

	public static String asXML(Node node) {
		return node.asXML();
	}
	
	public static Element getRootElement(Document doc) {
		return doc.getRootElement();
	}
	
	public static List<?> getElements(Element ele, String name){
		return ele.elements(name);
	}
	
	public static int getElementListSize(List<?> elementList){
		return elementList.size();
	}
	
	public static Object getElementFromElementList(List<?> elementList, int i){
		return elementList.get(i);
	}
	
	public static Element getElement(Element ele, String name){
		return ele.element(name);
	}
	
	public static String getChildElementText(Element ele, String name){
		return ele.elementText(name);
	}
	
	public static String getElementText(Element ele){
		return ele.getText();
	}
	
	public static String getAttributeValue(Element ele, String name){
		return ele.attributeValue(name);
	}
}
