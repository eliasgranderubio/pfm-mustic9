package es.uem.cracking.slave.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * John the Ripper attack request
 * 
 * @author egrande
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="JohnTheRipperAttackRequest")
public class JohnTheRipperAttackRequest {

	// Private attributes
	
	/** Active attack id */
	@XmlElement
	private long activeAttackId;
	/** Attack window id */
	@XmlElement
	private long attackWindowId;
	/** Format to attack */
	@XmlElement
	private String format;
	/** Dictionary to check */
	@XmlElement
	private Dictionary dictionary;
	/** Brute force pattern */
	@XmlElement
	private String bfPattern;
	/** Hash to attack */
	@XmlElement
	private String hashToCrack;
	/** Config parameter to John The Ripper */
	@XmlElement
	private String configParam;
	
	
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
	 * Gets {@link #attackWindowId}
	 */
	public long getAttackWindowId() {
		return attackWindowId;
	}
	
	/**
	 * Sets {@link #attackWindowId}
	 */
	public void setAttackWindowId(long attackWindowId) {
		this.attackWindowId = attackWindowId;
	}
	
	/**
	 * Gets {@link #format}
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * Sets {@link #format}
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Gets {@link #dictionary}
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	/**
	 * Sets {@link #dictionary}
	 */
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * Gets {@link #bfPattern}
	 */
	public String getBfPattern() {
		return bfPattern;
	}

	/**
	 * Sets {@link #bfPattern}
	 */
	public void setBfPattern(String bfPattern) {
		this.bfPattern = bfPattern;
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
	 * Gets {@link #configParam}
	 */
	public String getConfigParam() {
		return configParam;
	}

	/**
	 * Sets {@link #configParam}
	 */
	public void setConfigParam(String configParam) {
		this.configParam = configParam;
	}
	
}
