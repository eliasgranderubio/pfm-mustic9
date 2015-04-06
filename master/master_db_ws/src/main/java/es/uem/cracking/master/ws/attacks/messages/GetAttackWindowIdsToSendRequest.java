package es.uem.cracking.master.ws.attacks.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get all active attacks Ids request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetAttackWindowIdsToSendRequest")
public class GetAttackWindowIdsToSendRequest {

	/** Active attack Id */
	@XmlElement
	private long activeAttackId;
	/** Max windows */
	@XmlElement
	private Integer maxWindows;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #activeAttackId}
	 */
	public long getActiveAttackId() {
		return activeAttackId;
	}

	/**
	 * Sets {@link #activeAttackId}
	 */
	public void setActiveAttackId(long activeAttackId) {
		this.activeAttackId = activeAttackId;
	}

	/**
	 * Gets {@link #maxWindows}
	 */
	public Integer getMaxWindows() {
		return maxWindows;
	}

	/**
	 * Sets {@link #maxWindows}
	 */
	public void setMaxWindows(Integer maxWindows) {
		this.maxWindows = maxWindows;
	}
	
}
