package es.uem.cracking.name_server.ws.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get available slaves response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetAvailableSlavesResponse")
public class GetAvailableSlavesResponse {

	// Private attributes
	
	/** Available slaves */
	@XmlElement
	private List<AvailableSlave> availableSlaves;
		
	
	// Getters and setters
	
	/**
	 * Gets {@link #availableSlaves}
	 */
	public List<AvailableSlave> getAvailableSlaves() {
		return availableSlaves;
	}

	/**
	 * Sets {@link #availableSlaves}
	 */
	public void setAvailableSlaves(List<AvailableSlave> availableSlaves) {
		this.availableSlaves = availableSlaves;
	}

}
