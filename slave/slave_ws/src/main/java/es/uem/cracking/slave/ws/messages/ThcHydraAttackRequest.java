package es.uem.cracking.slave.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * THC-Hydra attack request
 * 
 * @author egrande
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="ThcHydraAttackRequest")
public class ThcHydraAttackRequest {

	// Private attributes
	
	/** Active attack id */
	@XmlElement
	private long activeAttackId;
	/** Attack window id */
	@XmlElement
	private long attackWindowId;
	/** Protocol to attack */
	@XmlElement
	private Protocol type;
	/** Dictionary to check */
	@XmlElement
	private Dictionary dictionary;
	/** Remote user to attack */
	@XmlElement
	private String remoteUser;
	/** Remote IP to attack */
	@XmlElement
	private String remoteIp;
	/** Remote port to attack */
	@XmlElement
	private Integer remotePort;
	/** Extra parameters to THC-Hydra */
	@XmlElement
	private String extraParam;
	
	
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
	 * Gets {@link #type}
	 */
	public Protocol getType() {
		return type;
	}
	
	/**
	 * Sets {@link #type}
	 */
	public void setType(Protocol type) {
		this.type = type;
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
	
}
