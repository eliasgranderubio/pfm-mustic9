package es.uem.cracking.name_server.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * Servers entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "SERVERS")
@SequenceGenerator(name="server_id_seq", sequenceName="ID_SEQ")
public class Servers {
	
	// Private attributes
	
	/** Slave id */
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="server_id_seq")
	@Column(name = "id")
	private int id;
	/** IP to register */
	@Column(name = "ip")
	private String ip;
	/** Port to register */
	@Column(name = "port")
	private int port;
	/** Processor name to register */
	@Column(name = "processor_name")
	private String processor_name;
	/** Is server locked */
	@Column(name = "locked")
	private boolean locked;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #id}
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets {@link #id}
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets {@link #ip}
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Sets {@link #ip}
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets {@link #port}
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Sets {@link #port}
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets {@link #processor_name}
	 */
	public String getProcessor_name() {
		return processor_name;
	}

	/**
	 * Sets {@link #processor_name}
	 */
	public void setProcessor_name(String processor_name) {
		this.processor_name = processor_name;
	}

	/**
	 * Gets {@link #locked}
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets {@link #locked}
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
