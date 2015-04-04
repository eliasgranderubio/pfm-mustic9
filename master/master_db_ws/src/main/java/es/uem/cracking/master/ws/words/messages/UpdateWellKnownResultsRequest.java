package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Update well known results request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="UpdateWellKnownResultsRequest")
public class UpdateWellKnownResultsRequest {

	// Private attributes
	
	/** Processor name */
	@XmlElement
	private String clearPass;

	
	// Getters and setters
	
	/**
	 * Gets {@link #clearPass}
	 */
	public String getClearPass() {
		return clearPass;
	}

	/**
	 * Sets {@link #clearPass}
	 */
	public void setClearPass(String clearPass) {
		this.clearPass = clearPass;
	}
	
}
