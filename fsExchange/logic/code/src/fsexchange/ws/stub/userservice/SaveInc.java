package fsexchange.ws.stub.userservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://model.tjsoft.com}Inc"/>
 *         &lt;element name="in1" type="{http://model.tjsoft.com}UserlogOn"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "in0", "in1" })
@XmlRootElement(name = "saveInc")
public class SaveInc {

	@XmlElement(required = true, nillable = true)
	protected Inc in0;
	@XmlElement(required = true, nillable = true)
	protected UserlogOn in1;

	/**
	 * Gets the value of the in0 property.
	 * 
	 * @return possible object is {@link Inc }
	 * 
	 */
	public Inc getIn0() {
		return in0;
	}

	/**
	 * Sets the value of the in0 property.
	 * 
	 * @param value
	 *            allowed object is {@link Inc }
	 * 
	 */
	public void setIn0(Inc value) {
		this.in0 = value;
	}

	/**
	 * Gets the value of the in1 property.
	 * 
	 * @return possible object is {@link UserlogOn }
	 * 
	 */
	public UserlogOn getIn1() {
		return in1;
	}

	/**
	 * Sets the value of the in1 property.
	 * 
	 * @param value
	 *            allowed object is {@link UserlogOn }
	 * 
	 */
	public void setIn1(UserlogOn value) {
		this.in1 = value;
	}

}
