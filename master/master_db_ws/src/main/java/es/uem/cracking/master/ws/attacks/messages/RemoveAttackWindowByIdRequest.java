package es.uem.cracking.master.ws.attacks.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Remove attack window by Id request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="RemoveAttackWindowByIdRequest")
public class RemoveAttackWindowByIdRequest {

	/** Attack Id */
	@XmlElement
	private long id;

	
	// Getters and setters
	
	/**
	 * Gets {@link #id}
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets {@link #id}
	 */
	public void setId(long id) {
		this.id = id;
	}

}
