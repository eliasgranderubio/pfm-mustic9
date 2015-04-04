package es.uem.cracking.master.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Well_Known_Results entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "WELL_KNOWN_RESULTS")
public class Well_Known_Results {

	// Private attributes
	
	/** Clear pass */
	@Id
	@Column(name = "clear_pass")
	private String clearPass;
	/** Times */
	@Column(name = "times")
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
