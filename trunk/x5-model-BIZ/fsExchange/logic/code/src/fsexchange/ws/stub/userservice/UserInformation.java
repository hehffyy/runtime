package fsexchange.ws.stub.userservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for UserInformation complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="enableStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userClew" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="userEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userHkaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userIndicia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userNative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInformation", namespace = "http://model.tjsoft.com", propOrder = {
		"changetype", "createId", "createTime", "enableStatus", "exchange",
		"id", "modifyId", "modifyTime", "remark", "userAddress", "userAge",
		"userAnswer", "userCa", "userClew", "userDate", "userEmail",
		"userGender", "userHkaddress", "userIndicia", "userMobile", "userName",
		"userNative", "userPhone", "userPid", "userUid" })
public class UserInformation {

	@XmlElementRef(name = "changetype", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> changetype;
	@XmlElementRef(name = "createId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> createId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	@XmlElementRef(name = "enableStatus", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> enableStatus;
	@XmlElementRef(name = "exchange", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> exchange;
	@XmlElementRef(name = "id", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> id;
	@XmlElementRef(name = "modifyId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> modifyId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifyTime;
	@XmlElementRef(name = "remark", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> remark;
	@XmlElementRef(name = "userAddress", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userAddress;
	@XmlElementRef(name = "userAge", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userAge;
	@XmlElementRef(name = "userAnswer", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userAnswer;
	@XmlElementRef(name = "userCa", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userCa;
	@XmlElementRef(name = "userClew", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userClew;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar userDate;
	@XmlElementRef(name = "userEmail", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userEmail;
	@XmlElementRef(name = "userGender", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userGender;
	@XmlElementRef(name = "userHkaddress", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userHkaddress;
	@XmlElementRef(name = "userIndicia", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userIndicia;
	@XmlElementRef(name = "userMobile", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userMobile;
	@XmlElementRef(name = "userName", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userName;
	@XmlElementRef(name = "userNative", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userNative;
	@XmlElementRef(name = "userPhone", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userPhone;
	@XmlElementRef(name = "userPid", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userPid;
	@XmlElementRef(name = "userUid", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> userUid;

	/**
	 * Gets the value of the changetype property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getChangetype() {
		return changetype;
	}

	/**
	 * Sets the value of the changetype property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setChangetype(JAXBElement<String> value) {
		this.changetype = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the createId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getCreateId() {
		return createId;
	}

	/**
	 * Sets the value of the createId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setCreateId(JAXBElement<String> value) {
		this.createId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the createTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the value of the createTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreateTime(XMLGregorianCalendar value) {
		this.createTime = value;
	}

	/**
	 * Gets the value of the enableStatus property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getEnableStatus() {
		return enableStatus;
	}

	/**
	 * Sets the value of the enableStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setEnableStatus(JAXBElement<String> value) {
		this.enableStatus = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the exchange property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getExchange() {
		return exchange;
	}

	/**
	 * Sets the value of the exchange property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setExchange(JAXBElement<String> value) {
		this.exchange = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setId(JAXBElement<String> value) {
		this.id = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the modifyId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getModifyId() {
		return modifyId;
	}

	/**
	 * Sets the value of the modifyId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setModifyId(JAXBElement<String> value) {
		this.modifyId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the modifyTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getModifyTime() {
		return modifyTime;
	}

	/**
	 * Sets the value of the modifyTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setModifyTime(XMLGregorianCalendar value) {
		this.modifyTime = value;
	}

	/**
	 * Gets the value of the remark property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getRemark() {
		return remark;
	}

	/**
	 * Sets the value of the remark property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setRemark(JAXBElement<String> value) {
		this.remark = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userAddress property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserAddress() {
		return userAddress;
	}

	/**
	 * Sets the value of the userAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserAddress(JAXBElement<String> value) {
		this.userAddress = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userAge property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserAge() {
		return userAge;
	}

	/**
	 * Sets the value of the userAge property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserAge(JAXBElement<String> value) {
		this.userAge = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userAnswer property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserAnswer() {
		return userAnswer;
	}

	/**
	 * Sets the value of the userAnswer property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserAnswer(JAXBElement<String> value) {
		this.userAnswer = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userCa property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserCa() {
		return userCa;
	}

	/**
	 * Sets the value of the userCa property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserCa(JAXBElement<String> value) {
		this.userCa = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userClew property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserClew() {
		return userClew;
	}

	/**
	 * Sets the value of the userClew property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserClew(JAXBElement<String> value) {
		this.userClew = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getUserDate() {
		return userDate;
	}

	/**
	 * Sets the value of the userDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setUserDate(XMLGregorianCalendar value) {
		this.userDate = value;
	}

	/**
	 * Gets the value of the userEmail property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the value of the userEmail property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserEmail(JAXBElement<String> value) {
		this.userEmail = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userGender property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserGender() {
		return userGender;
	}

	/**
	 * Sets the value of the userGender property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserGender(JAXBElement<String> value) {
		this.userGender = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userHkaddress property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserHkaddress() {
		return userHkaddress;
	}

	/**
	 * Sets the value of the userHkaddress property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserHkaddress(JAXBElement<String> value) {
		this.userHkaddress = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userIndicia property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserIndicia() {
		return userIndicia;
	}

	/**
	 * Sets the value of the userIndicia property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserIndicia(JAXBElement<String> value) {
		this.userIndicia = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userMobile property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserMobile() {
		return userMobile;
	}

	/**
	 * Sets the value of the userMobile property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserMobile(JAXBElement<String> value) {
		this.userMobile = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userName property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserName(JAXBElement<String> value) {
		this.userName = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userNative property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserNative() {
		return userNative;
	}

	/**
	 * Sets the value of the userNative property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserNative(JAXBElement<String> value) {
		this.userNative = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userPhone property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserPhone() {
		return userPhone;
	}

	/**
	 * Sets the value of the userPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserPhone(JAXBElement<String> value) {
		this.userPhone = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userPid property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserPid() {
		return userPid;
	}

	/**
	 * Sets the value of the userPid property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserPid(JAXBElement<String> value) {
		this.userPid = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the userUid property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getUserUid() {
		return userUid;
	}

	/**
	 * Sets the value of the userUid property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setUserUid(JAXBElement<String> value) {
		this.userUid = ((JAXBElement<String>) value);
	}

}
