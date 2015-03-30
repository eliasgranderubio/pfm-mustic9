package es.uem.cracking.name_server.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Register response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="RegisterResponse")
public class RegisterResponse {
	
	// Private attributes
	
	/** Registered flag */
	@XmlElement
	private boolean registered;

	
	// Getters and setters
	
	/**
	 * Gets {@link #registered}
	 */
	public boolean isRegistered() {
		return registered;
	}

	/**
	 * Sets {@link #registered}
	 * @param registered
	 */
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
}
