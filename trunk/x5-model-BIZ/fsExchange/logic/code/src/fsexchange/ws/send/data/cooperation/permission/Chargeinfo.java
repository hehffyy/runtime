package fsexchange.ws.send.data.cooperation.permission;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Chargeinfo implements fsexchange.ws.send.data.XmlOut {
	private String totalcharge;
	private String finalcharge;
	private String chargeunit;
	private String chargeperson;
	private String chargetime;
	private String payer;
	private String banker;
	private String deptname;
	private String recordnum;
	private String paymentmethod;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("chargeinfo");
		/*root.addElement("totalcharge").addCDATA(this.totalcharge);
		root.addElement("finalcharge").addCDATA(this.finalcharge);
		root.addElement("chargeunit").addCDATA(this.chargeunit);
		root.addElement("chargeperson").addCDATA(this.chargeperson);
		root.addElement("chargetime").addCDATA(this.chargetime);
		root.addElement("payer").addCDATA(this.payer);
		root.addElement("banker").addCDATA(this.banker);
		root.addElement("deptname").addCDATA(this.deptname);
		root.addElement("recordnum").addCDATA(this.recordnum);
		root.addElement("paymentmethod").addCDATA(this.paymentmethod);*/
		return root;
	}

	public String getTotalcharge() {
		return totalcharge;
	}

	public void setTotalcharge(String totalcharge) {
		this.totalcharge = totalcharge;
	}

	public String getFinalcharge() {
		return finalcharge;
	}

	public void setFinalcharge(String finalcharge) {
		this.finalcharge = finalcharge;
	}

	public String getChargeunit() {
		return chargeunit;
	}

	public void setChargeunit(String chargeunit) {
		this.chargeunit = chargeunit;
	}

	public String getChargeperson() {
		return chargeperson;
	}

	public void setChargeperson(String chargeperson) {
		this.chargeperson = chargeperson;
	}

	public String getChargetime() {
		return chargetime;
	}

	public void setChargetime(String chargetime) {
		this.chargetime = chargetime;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getBanker() {
		return banker;
	}

	public void setBanker(String banker) {
		this.banker = banker;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRecordnum() {
		return recordnum;
	}

	public void setRecordnum(String recordnum) {
		this.recordnum = recordnum;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

}
