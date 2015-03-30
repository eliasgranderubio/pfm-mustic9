package es.uem.cracking.name_server.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Delete locked slave request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="DeleteLockedSlaveRequest")
public class DeleteLockedSlaveRequest {

	// Private attributes
	
	/** Slave id */
	@XmlElement
	private int id;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #id}
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets {@link #id}
	 */
	public void setId(int id) {
		this.id = id;
	}
		
}
