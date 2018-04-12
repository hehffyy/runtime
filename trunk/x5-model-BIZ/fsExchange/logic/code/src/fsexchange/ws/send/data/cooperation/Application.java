package fsexchange.ws.send.data.cooperation;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 申请人信息
 * @author Administrator
 *
 */
public class Application implements fsexchange.ws.send.data.XmlOut {
	private String id;
	private String appid;
	private String appname;
	private String apporg;
	private String cardid;
	private String appdate;
	private String mobilephone;
	private String phone;
	private String email;
	private String address;
	private String usertype;
	private String username;
	private String password;

	public Element getElement() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("application");
		root.addElement("id").addCDATA(this.id);
		root.addElement("appid").addCDATA(this.appid);
		root.addElement("appname").addCDATA(this.appname);
		root.addElement("apporg").addCDATA(this.apporg);
		root.addElement("cardid").addCDATA(this.cardid);
		root.addElement("appdate").addCDATA(this.appdate);
		root.addElement("mobilephone").addCDATA(this.mobilephone);
		root.addElement("phone").addCDATA(this.phone);
		root.addElement("email").addCDATA(this.email);
		root.addElement("address").addCDATA(this.address);
		root.addElement("usertype").addCDATA(this.usertype);
		root.addElement("username").addCDATA(this.username);
		root.addElement("password").addCDATA(this.password);

		return root;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getApporg() {
		return apporg;
	}

	public void setApporg(String apporg) {
		this.apporg = apporg;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
