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
 *         &lt;element name="in0" type="{http://model.tjsoft.com}ArrayOfUserlogOn"/>
 *         &lt;element name="in1" type="{http://model.tjsoft.com}ArrayOfUserInformation"/>
 *         &lt;element name="in2" type="{http://model.tjsoft.com}ArrayOfInc"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "in0", "in1", "in2" })
@XmlRootElement(name = "updateOrsaveUser")
public class UpdateOrsaveUser {

	@XmlElement(required = true, nillable = true)
	protected ArrayOfUserlogOn in0;
	@XmlElement(required = true, nillable = true)
	protected ArrayOfUserInformation in1;
	@XmlElement(required = true, nillable = true)
	protected ArrayOfInc in2;

	/**
	 * Gets the value of the in0 property.
	 * 
	 * @return possible object is {@link ArrayOfUserlogOn }
	 * 
	 */
	public ArrayOfUserlogOn getIn0() {
		return in0;
	}

	/**
	 * Sets the value of the in0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfUserlogOn }
	 * 
	 */
	public void setIn0(ArrayOfUserlogOn value) {
		this.in0 = value;
	}

	/**
	 * Gets the value of the in1 property.
	 * 
	 * @return possible object is {@link ArrayOfUserInformation }
	 * 
	 */
	public ArrayOfUserInformation getIn1() {
		return in1;
	}

	/**
	 * Sets the value of the in1 property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfUserInformation }
	 * 
	 */
	public void setIn1(ArrayOfUserInformation value) {
		this.in1 = value;
	}

	/**
	 * Gets the value of the in2 property.
	 * 
	 * @return possible object is {@link ArrayOfInc }
	 * 
	 */
	public ArrayOfInc getIn2() {
		return in2;
	}

	/**
	 * Sets the value of the in2 property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfInc }
	 * 
	 */
	public void setIn2(ArrayOfInc value) {
		this.in2 = value;
	}

}
