package es.uem.cracking.master.ws.processors.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Update processor statistics response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="UpdateProcessorStatisticsResponse")
public class UpdateProcessorStatisticsResponse {

	// Private attributes
	
	/** Updated flag */
	@XmlElement
	private boolean updated;

	
	// Getters and setters
	
	/**
	 * Gets {@link #updated}
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * Sets {@link #updated}
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
}
