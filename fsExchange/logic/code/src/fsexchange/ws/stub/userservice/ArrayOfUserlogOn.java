package fsexchange.ws.stub.userservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfUserlogOn complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUserlogOn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserlogOn" type="{http://model.tjsoft.com}UserlogOn" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUserlogOn", namespace = "http://model.tjsoft.com", propOrder = { "userlogOn" })
public class ArrayOfUserlogOn {

	@XmlElement(name = "UserlogOn", nillable = true)
	protected List<UserlogOn> userlogOn;

	/**
	 * Gets the value of the userlogOn property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the userlogOn property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getUserlogOn().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link UserlogOn }
	 * 
	 * 
	 */
	public List<UserlogOn> getUserlogOn() {
		if (userlogOn == null) {
			userlogOn = new ArrayList<UserlogOn>();
		}
		return this.userlogOn;
	}

}
