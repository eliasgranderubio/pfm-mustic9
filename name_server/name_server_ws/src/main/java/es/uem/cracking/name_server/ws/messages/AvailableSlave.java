package es.uem.cracking.name_server.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Available slave
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="AvailableSlave")
public class AvailableSlave {

	// Private attributes
	
	/** Slave id */
	@XmlElement
	private int id;
	/** IP to register */
	@XmlElement
	private String ip;
	/** Port to register */
	@XmlElement
	private int port;
	/** Processor name to register */
	@XmlElement
	private String processor_name;
	
	
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
		
}
