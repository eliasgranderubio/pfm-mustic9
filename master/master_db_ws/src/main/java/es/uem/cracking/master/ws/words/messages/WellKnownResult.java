package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Well known result
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="WellKnownResult")
public class WellKnownResult {
	
	// Private attributes
	
	/** Clear pass */
	@XmlElement
	private String clearPass;
	/** Times */
	@XmlElement
	private int times;

	
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
	
	/**
	 * Gets {@link #times}
	 */
	public int getTimes() {
		return times;
	}
	
	/**
	 * Sets {@link #times}
	 */
	public void setTimes(int times) {
		this.times = times;
	}
	
}
