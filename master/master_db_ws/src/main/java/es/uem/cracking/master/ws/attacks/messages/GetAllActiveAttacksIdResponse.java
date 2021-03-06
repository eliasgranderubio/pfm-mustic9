package es.uem.cracking.master.ws.attacks.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get all active attacks Ids response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetAllActiveAttacksIdResponse")
public class GetAllActiveAttacksIdResponse {

	/** Ids */
	@XmlElementWrapper(name="ids")
	@XmlElement
	private List<Long> id;

	
	// Getters and setters
	
	/**
	 * Gets {@link #id}
	 */
	public List<Long> getId() {
		return id;
	}

	/**
	 * Sets {@link #id}
	 */
	public void setId(List<Long> id) {
		this.id = id;
	}
	
}
