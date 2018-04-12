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
 * Java class for UserlogOn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="UserlogOn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="changetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="exchange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logLoginname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logProvetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logSort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logUid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserlogOn", namespace = "http://model.tjsoft.com", propOrder = {
		"changetype", "createId", "createTime", "exchange", "logLoginname",
		"logProvetype", "logPwd", "logSort", "logUid", "modifyId",
		"modifyTime", "status" })
public class UserlogOn {

	@XmlElementRef(name = "changetype", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> changetype;
	@XmlElementRef(name = "createId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> createId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createTime;
	@XmlElementRef(name = "exchange", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> exchange;
	@XmlElementRef(name = "logLoginname", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> logLoginname;
	@XmlElementRef(name = "logProvetype", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> logProvetype;
	@XmlElementRef(name = "logPwd", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> logPwd;
	@XmlElementRef(name = "logSort", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> logSort;
	@XmlElementRef(name = "logUid", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> logUid;
	@XmlElementRef(name = "modifyId", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> modifyId;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar modifyTime;
	@XmlElementRef(name = "status", namespace = "http://model.tjsoft.com", type = JAXBElement.class)
	protected JAXBElement<String> status;

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
	 * Gets the value of the logLoginname property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getLogLoginname() {
		return logLoginname;
	}

	/**
	 * Sets the value of the logLoginname property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setLogLoginname(JAXBElement<String> value) {
		this.logLoginname = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the logProvetype property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getLogProvetype() {
		return logProvetype;
	}

	/**
	 * Sets the value of the logProvetype property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setLogProvetype(JAXBElement<String> value) {
		this.logProvetype = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the logPwd property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getLogPwd() {
		return logPwd;
	}

	/**
	 * Sets the value of the logPwd property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setLogPwd(JAXBElement<String> value) {
		this.logPwd = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the logSort property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getLogSort() {
		return logSort;
	}

	/**
	 * Sets the value of the logSort property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setLogSort(JAXBElement<String> value) {
		this.logSort = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the logUid property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getLogUid() {
		return logUid;
	}

	/**
	 * Sets the value of the logUid property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setLogUid(JAXBElement<String> value) {
		this.logUid = ((JAXBElement<String>) value);
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
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setStatus(JAXBElement<String> value) {
		this.status = ((JAXBElement<String>) value);
	}

}
