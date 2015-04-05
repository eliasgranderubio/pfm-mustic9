package es.uem.cracking.master.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 
 * Active_Attacks entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "ACTIVE_ATTACKS")
@SequenceGenerator(name="a_a_seq", sequenceName="ACTIVE_ATTACKS_SEQ")
public class Active_Attacks {
	
	// Private attributes
	
	/** Attack Id */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="a_a_seq")
	@Column(name = "id")
	private long id;
	/** Tool name */
	@Column(name = "tool")
	private String tool;
	/** Attack type: hashing algorithms or network protocols */
	@Column(name = "attack_type")
	private String attackType;
	/** Hydra remote user */
	@Column(name = "remote_user")
	private String remoteUser;
	/** Hydra remote IP */
	@Column(name = "remote_ip")
	private String remoteIp;
	/** Hydra remote port */
	@Column(name = "remote_port")
	private int remotePort;
	/** Hydra or John extra parameters */
	@Column(name = "extra_param")
	private String extraParam;
	/** John hash to crack */
	@Column(name = "hash_to_crack")
	private String hashToCrack;
	/** Notify to email */
	@Column(name = "notify_to_email")
	private String notifyToEmail;
	/** One to many */
	@OneToMany(mappedBy="activeAttackId")
	@OrderBy("id ASC")
	private List<Attack_Windows> attackWindows;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #id}
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets {@link #id}
	 */
	public void setId(long id) {
		this.id = id;
	}
	
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
	public int getRemotePort() {
		return remotePort;
	}

	/**
	 * Sets {@link #remotePort}
	 */
	public void setRemotePort(int remotePort) {
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
	 * Gets {@link #attackWindows}
	 */
	public List<Attack_Windows> getAttackWindows() {
		return attackWindows;
	}
	
	/**
	 * Sets {@link #attackWindows}
	 */
	public void setAttackWindows(List<Attack_Windows> attackWindows) {
		this.attackWindows = attackWindows;
	}

}
