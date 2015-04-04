package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get words range request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetWordsRangeRequest")
public class GetWordsRangeRequest {

	// Private attributes
	
	/** Include from word */
	@XmlElement
	private String includeFromWord;
	/** Include to word */
	@XmlElement
	private String includeToWord;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #includeFromWord}
	 */	
	public String getIncludeFromWord() {
		return includeFromWord;
	}
	
	/**
	 * Sets {@link #includeFromWord}
	 */	
	public void setIncludeFromWord(String includeFromWord) {
		this.includeFromWord = includeFromWord;
	}
	
	/**
	 * Gets {@link #includeToWord}
	 */	
	public String getIncludeToWord() {
		return includeToWord;
	}
	
	/**
	 * Sets {@link #includeToWord}
	 */	
	public void setIncludeToWord(String includeToWord) {
		this.includeToWord = includeToWord;
	}
	
}
