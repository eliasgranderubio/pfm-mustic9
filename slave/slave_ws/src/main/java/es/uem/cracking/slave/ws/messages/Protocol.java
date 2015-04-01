package es.uem.cracking.slave.ws.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * THC-Hydra protocol enumeration
 * 
 * @author egrande
 *
 */
@XmlEnum(String.class)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Protocol")
public enum Protocol {
	telnet("telnet"),
	ftp("ftp"),
	httpHead("http-head"),
	httpGet("http-get"),
	httpGetForm("http-get-form"),
	httpPostForm("http-post-form"),
	rtsp("rtsp");
	
	
	// Private attributes
	
	/** THC-Hydra protocol value */
	private final String _value;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	Protocol(String value) {
		_value = value;
	}
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #_value}
	 */
	public String getValue() {
		return _value;
	}
	
}
