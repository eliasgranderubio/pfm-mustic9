package es.uem.cracking.master.ws.words.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get word request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetWordRequest")
public class GetWordRequest {

	// Private attributes
	
	/** Position within of dictionary */
	@XmlElement
	private long positionWithinOfDictionary;

	
	// Getters and setters
	
	/**
	 * Gets {@link #positionWithinOfDictionary}
	 */	
	public long getPositionWithinOfDictionary() {
		return positionWithinOfDictionary;
	}

	/**
	 * Sets {@link #positionWithinOfDictionary}
	 */	
	public void setPositionWithinOfDictionary(long positionWithinOfDictionary) {
		this.positionWithinOfDictionary = positionWithinOfDictionary;
	}
	
}
