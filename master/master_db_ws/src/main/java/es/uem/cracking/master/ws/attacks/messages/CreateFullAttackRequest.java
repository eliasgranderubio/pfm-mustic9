package es.uem.cracking.master.ws.attacks.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * Create full attack request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="CreateFullAttackRequest")
public class CreateFullAttackRequest {

	/** Tool name */
	@XmlElement
	private String tool;
	/** Attack type: hashing algorithms or network protocols */
	@XmlElement
	private String attackType;
	/** Hydra remote user */
	@XmlElement
	private String remoteUser;
	/** Hydra remote IP */
	@XmlElement
	private String remoteIp;
	/** Hydra remote port */
	@XmlElement
	private Integer remotePort;
	/** Hydra or John extra parameters */
	@XmlElement
	private String extraParam;
	/** John hash to crack */
	@XmlElement
	private String hashToCrack;
	/** Notify to email */
	@XmlElement
	private String notifyToEmail;
	/** Windows size */
	@XmlElement
	private long windowSize;
	/** Dictionary total words */
	@XmlElement
	private long totalWords;
	
	// Getters and setters
	
	/**
	 * Gets {@link #tool}
	 */
	public String getTool() {
		return tool;
	}
	
	/**
	 * Sets {@link #tool}
	 */
	public void setTool(String tool) {
		this.tool = tool;
	}
	
	/**
	 * Gets {@link #attackType}
	 */
	public String getAttackType() {
		return attackType;
	}
	
	/**
	 * Sets {@link #attackType}
	 */
	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
	
	/**
	 * Gets {@link #remoteUser}
	 */
	public String getRemoteUser() {
		return remoteUser;
	}
	
	/**
	 * Sets {@link #remoteUser}
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	
	/**
	 * Gets {@link #remoteIp}
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * Sets {@link #remoteIp}
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	
	/**
	 * Gets {@link #remotePort}
	 */
	public Integer getRemotePort() {
		return remotePort;
	}

	/**
	 * Sets {@link #remotePort}
	 */
	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	/**
	 * Gets {@link #extraParam}
	 */
	public String getExtraParam() {
		return extraParam;
	}
	
	/**
	 * Sets {@link #extraParam}
	 */
	public void setExtraParam(String extraParam) {
		this.extraParam = extraParam;
	}
	
	/**
	 * Gets {@link #hashToCrack}
	 */
	public String getHashToCrack() {
		return hashToCrack;
	}

	/**
	 * Sets {@link #hashToCrack}
	 */
	public void setHashToCrack(String hashToCrack) {
		this.hashToCrack = hashToCrack;
	}

	/**
	 * Gets {@link #notifyToEmail}
	 */
	public String getNotifyToEmail() {
		return notifyToEmail;
	}

	/**
	 * Sets {@link #notifyToEmail}
	 */
	public void setNotifyToEmail(String notifyToEmail) {
		this.notifyToEmail = notifyToEmail;
	}

	/**
	 * Gets {@link #windowSize}
	 */
	public long getWindowSize() {
		return windowSize;
	}

	/**
	 * Sets {@link #windowSize}
	 */
	public void setWindowSize(long windowSize) {
		this.windowSize = windowSize;
	}
	
	/**
	 * Gets {@link #totalWords}
	 */
	public long getTotalWords() {
		return totalWords;
	}

	/**
	 * Sets {@link #totalWords}
	 */
	public void setTotalWords(long totalWords) {
		this.totalWords = totalWords;
	}

}
