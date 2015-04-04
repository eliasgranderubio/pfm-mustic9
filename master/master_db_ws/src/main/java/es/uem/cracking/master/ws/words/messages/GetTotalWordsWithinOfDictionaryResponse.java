package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get total words within of dictionary response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetTotalWordsWithinOfDictionaryResponse")
public class GetTotalWordsWithinOfDictionaryResponse {

	// Private attributes
	
	/** Position within of dictionary */
	@XmlElement
	private long totalWords;

	
	// Getters and setters
	
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
