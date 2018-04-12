package fsexchange.ws.send.data;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DataXml implements XmlOut{
	private String orderid;
	private String cid;
	private String cbsnum;
	private String permid;
	private String subpermid;
	private String areaid;
	private String bsnum;
	private String commitdate;
	private String busistatus;
	private String bstype;
	
	public Element getElement() {
		Document document=DocumentHelper.createDocument() ;
		Element root = document.addElement("dataxml");
		root.addElement("orderid").addCDATA(this.orderid);
		root.addElement("cid").addCDATA(this.cid);
		root.addElement("cbsnum").addCDATA(this.cbsnum);
		root.addElement("permid").addCDATA(this.permid);
		root.addElement("subpermid").addCDATA(this.subpermid);
		root.addElement("areaid").addCDATA(this.areaid);
		root.addElement("bsnum").addCDATA(this.bsnum);
		root.addElement("commitdate").addCDATA(this.commitdate);
		root.addElement("busistatus").addCDATA(this.busistatus);
		root.addElement("bstype").addCDATA(this.bstype);
		return root;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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

	public String getPermid() {
		return permid;
	}

	public void setPermid(String permid) {
		this.permid = permid;
	}

	public String getSubpermid() {
		return subpermid;
	}

	public void setSubpermid(String subpermid) {
		this.subpermid = subpermid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getBsnum() {
		return bsnum;
	}

	public void setBsnum(String bsnum) {
		this.bsnum = bsnum;
	}

	public String getCommitdate() {
		return commitdate;
	}

	public void setCommitdate(String commitdate) {
		this.commitdate = commitdate;
	}

	public String getBusistatus() {
		return busistatus;
	}

	public void setBusistatus(String busistatus) {
		this.busistatus = busistatus;
	}

	public String getBstype() {
		return bstype;
	}

	public void setBstype(String bstype) {
		this.bstype = bstype;
	}
}
