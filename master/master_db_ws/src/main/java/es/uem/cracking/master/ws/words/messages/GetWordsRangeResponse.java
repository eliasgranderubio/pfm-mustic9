package es.uem.cracking.master.ws.words.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get words range request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetWordsRangeResponse")
public class GetWordsRangeResponse {

	// Private attributes
	
	/** Words */
	@XmlElementWrapper(name="words")
	@XmlElement
	private List<String> word;


	// Getters and setters
	
	/**
	 * Gets {@link #word}
	 */
	public List<String> getWord() {
		return word;
	}

	/**
	 * Sets {@link #word}
	 */
	public void setWord(List<String> word) {
		this.word = word;
	}
	
}
