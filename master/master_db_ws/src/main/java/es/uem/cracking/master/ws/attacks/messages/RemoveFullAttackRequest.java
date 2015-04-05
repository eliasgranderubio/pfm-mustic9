package es.uem.cracking.master.ws.attacks.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Remove full attack request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="RemoveFullAttackRequest")
public class RemoveFullAttackRequest {

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
