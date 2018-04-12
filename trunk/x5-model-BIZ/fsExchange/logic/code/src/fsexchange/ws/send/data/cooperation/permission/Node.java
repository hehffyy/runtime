package fsexchange.ws.send.data.cooperation.permission;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import fsexchange.ws.send.data.NodeKind;
import fsexchange.ws.send.data.XmlOut;

public class Node implements XmlOut, NodeDate, NodeKind {
	private Date nodedate;
	private String nodeType;
	private String subType;
	private String nodeid;
	private String nodename;
	private String nodeactor;
	private String nodeactorgh;
	private String nodeactorzwmc;
	private String nodeactorzwdm;
	private String department;
	private String handlerdate;
	private String handleridea;
	private String handlerfid;

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getKind() {
		return nodename;
	}

	public Date getDate() {
		return this.nodedate;
	};

	public void setDate(Date date) {
		this.nodedate = date;

	}

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("node");
		root.addElement("nodeid").addCDATA(this.nodeid);
		root.addElement("nodename").addCDATA(this.nodename);
		root.addElement("nodeactor").addCDATA(this.nodeactor);
		root.addElement("nodeactorgh").addCDATA(this.nodeactorgh);
		root.addElement("nodeactorzwmc").addCDATA(this.nodeactorzwmc);
		root.addElement("nodeactorzwdm").addCDATA(this.nodeactorzwdm);
		root.addElement("department").addCDATA(this.department);
		root.addElement("handlerdate").addCDATA(this.handlerdate);
		root.addElement("handleridea").addCDATA(this.handleridea);
		root.addElement("handlerfid").addCDATA(this.handlerfid);
		return root;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getNodeactor() {
		return nodeactor;
	}

	public void setNodeactor(String nodeactor) {
		this.nodeactor = nodeactor;
	}

	public String getNodeactorgh() {
		return nodeactorgh;
	}

	public void setNodeactorgh(String nodeactorgh) {
		this.nodeactorgh = nodeactorgh;
	}

	public String getNodeactorzwmc() {
		return nodeactorzwmc;
	}

	public void setNodeactorzwmc(String nodeactorzwmc) {
		this.nodeactorzwmc = nodeactorzwmc;
	}

	public String getNodeactorzwdm() {
		return nodeactorzwdm;
	}

	public void setNodeactorzwdm(String nodeactorzwdm) {
		this.nodeactorzwdm = nodeactorzwdm;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getHandlerdate() {
		return handlerdate;
	}

	public void setHandlerdate(String handlerdate) {
		this.handlerdate = handlerdate;
	}

	public String getHandleridea() {
		return handleridea;
	}

	public void setHandleridea(String handleridea) {
		this.handleridea = handleridea;
	}

	public String getHandlerfid() {
		return handlerfid;
	}

	public void setHandlerfid(String handlerfid) {
		this.handlerfid = handlerfid;
	}
}
