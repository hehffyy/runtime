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
 * Java class for Inc complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Inc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ebableStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incCa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="incDeputy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incDeputyid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incGsnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incIndicia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incLogindate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incNetwork" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incValidate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incmobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incxml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilevalidate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Inc", namespace = "http://model.tjsoft.com", propOrder = {
		"changetype", "createId", "createTime", "ebableStatus", "exchange",
		"id", "incAddr", "incCa", "incDate", "incDeputy", "incDeputyid",
		"incEmail", "incFax", "incGsnum", "incId", "incIndicia", "incKey",
		"incKind", "incLogindate", "incMoney", "incName", "incNetwork",
		"incPhone", "incValidate", "incmobile", "incxml", "mobilevalidate",
		"modifyId", "modifyTime", "remark" })
public class Inc {

	@XmlElementRef(name = "changetype", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> changetype;
	@XmlElementRef(name = "createId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> createId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	@XmlElementRef(name = "ebableStatus", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> ebableStatus;
	@XmlElementRef(name = "exchange", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> exchange;
	@XmlElementRef(name = "id", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> id;
	@XmlElementRef(name = "incAddr", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incAddr;
	@XmlElementRef(name = "incCa", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incCa;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar incDate;
	@XmlElementRef(name = "incDeputy", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incDeputy;
	@XmlElementRef(name = "incDeputyid", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incDeputyid;
	@XmlElementRef(name = "incEmail", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incEmail;
	@XmlElementRef(name = "incFax", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incFax;
	@XmlElementRef(name = "incGsnum", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incGsnum;
	@XmlElementRef(name = "incId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incId;
	@XmlElementRef(name = "incIndicia", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incIndicia;
	@XmlElementRef(name = "incKey", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incKey;
	@XmlElementRef(name = "incKind", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incKind;
	@XmlElementRef(name = "incLogindate", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incLogindate;
	@XmlElementRef(name = "incMoney", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incMoney;
	@XmlElementRef(name = "incName", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incName;
	@XmlElementRef(name = "incNetwork", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incNetwork;
	@XmlElementRef(name = "incPhone", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incPhone;
	@XmlElementRef(name = "incValidate", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incValidate;
	@XmlElementRef(name = "incmobile", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incmobile;
	@XmlElementRef(name = "incxml", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> incxml;
	@XmlElementRef(name = "mobilevalidate", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> mobilevalidate;
	@XmlElementRef(name = "modifyId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> modifyId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifyTime;
	@XmlElementRef(name = "remark", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> remark;

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
	 * Gets the value of the ebableStatus property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getEbableStatus() {
		return ebableStatus;
	}

	/**
	 * Sets the value of the ebableStatus property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setEbableStatus(JAXBElement<String> value) {
		this.ebableStatus = ((JAXBElement<String>) value);
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
	 * Gets the value of the incAddr property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncAddr() {
		return incAddr;
	}

	/**
	 * Sets the value of the incAddr property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncAddr(JAXBElement<String> value) {
		this.incAddr = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incCa property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncCa() {
		return incCa;
	}

	/**
	 * Sets the value of the incCa property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncCa(JAXBElement<String> value) {
		this.incCa = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIncDate() {
		return incDate;
	}

	/**
	 * Sets the value of the incDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIncDate(XMLGregorianCalendar value) {
		this.incDate = value;
	}

	/**
	 * Gets the value of the incDeputy property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncDeputy() {
		return incDeputy;
	}

	/**
	 * Sets the value of the incDeputy property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncDeputy(JAXBElement<String> value) {
		this.incDeputy = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incDeputyid property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncDeputyid() {
		return incDeputyid;
	}

	/**
	 * Sets the value of the incDeputyid property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncDeputyid(JAXBElement<String> value) {
		this.incDeputyid = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incEmail property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncEmail() {
		return incEmail;
	}

	/**
	 * Sets the value of the incEmail property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncEmail(JAXBElement<String> value) {
		this.incEmail = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incFax property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncFax() {
		return incFax;
	}

	/**
	 * Sets the value of the incFax property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncFax(JAXBElement<String> value) {
		this.incFax = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incGsnum property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncGsnum() {
		return incGsnum;
	}

	/**
	 * Sets the value of the incGsnum property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncGsnum(JAXBElement<String> value) {
		this.incGsnum = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncId() {
		return incId;
	}

	/**
	 * Sets the value of the incId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncId(JAXBElement<String> value) {
		this.incId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incIndicia property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncIndicia() {
		return incIndicia;
	}

	/**
	 * Sets the value of the incIndicia property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncIndicia(JAXBElement<String> value) {
		this.incIndicia = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incKey property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncKey() {
		return incKey;
	}

	/**
	 * Sets the value of the incKey property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncKey(JAXBElement<String> value) {
		this.incKey = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incKind property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncKind() {
		return incKind;
	}

	/**
	 * Sets the value of the incKind property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncKind(JAXBElement<String> value) {
		this.incKind = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incLogindate property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncLogindate() {
		return incLogindate;
	}

	/**
	 * Sets the value of the incLogindate property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncLogindate(JAXBElement<String> value) {
		this.incLogindate = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incMoney property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncMoney() {
		return incMoney;
	}

	/**
	 * Sets the value of the incMoney property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncMoney(JAXBElement<String> value) {
		this.incMoney = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incName property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncName() {
		return incName;
	}

	/**
	 * Sets the value of the incName property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncName(JAXBElement<String> value) {
		this.incName = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incNetwork property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncNetwork() {
		return incNetwork;
	}

	/**
	 * Sets the value of the incNetwork property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncNetwork(JAXBElement<String> value) {
		this.incNetwork = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incPhone property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncPhone() {
		return incPhone;
	}

	/**
	 * Sets the value of the incPhone property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncPhone(JAXBElement<String> value) {
		this.incPhone = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incValidate property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncValidate() {
		return incValidate;
	}

	/**
	 * Sets the value of the incValidate property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncValidate(JAXBElement<String> value) {
		this.incValidate = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incmobile property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncmobile() {
		return incmobile;
	}

	/**
	 * Sets the value of the incmobile property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncmobile(JAXBElement<String> value) {
		this.incmobile = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the incxml property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getIncxml() {
		return incxml;
	}

	/**
	 * Sets the value of the incxml property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setIncxml(JAXBElement<String> value) {
		this.incxml = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the mobilevalidate property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getMobilevalidate() {
		return mobilevalidate;
	}

	/**
	 * Sets the value of the mobilevalidate property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setMobilevalidate(JAXBElement<String> value) {
		this.mobilevalidate = ((JAXBElement<String>) value);
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

}
