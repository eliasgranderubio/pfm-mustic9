package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get word response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetWordResponse")
public class GetWordResponse {
	
	// Private attributes
	
	/** Word */
	@XmlElement
	private String word;

	
	// Getters and setters
	
	/**
	 * Gets {@link #word}
	 */	
	public String getWord() {
		return word;
	}

	/**
	 * Sets {@link #word}
	 */	
	public void setWord(String word) {
		this.word = word;
	}
	
}
