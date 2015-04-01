package es.uem.cracking.slave.ws.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Dictionary words
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Dictionary")
public class Dictionary {

	// Private attributes
	
	/** Dictionary words */
	@XmlElement(name="word")
	private List<String> words;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #words}
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * Sets {@link #words}
	 */
	public void setWords(List<String> words) {
		this.words = words;
	}
	
}
