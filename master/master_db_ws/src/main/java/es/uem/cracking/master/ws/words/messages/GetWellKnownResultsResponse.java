package es.uem.cracking.master.ws.words.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Get well known result response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetWellKnownResultsResponse")
public class GetWellKnownResultsResponse {

	// Private attributes
	
	/** Well known results */
	@XmlElement
	private List<WellKnownResult> wellKnownResults;

	
	// Getters and setters
	
	/**
	 * Gets {@link #wellKnownResults}
	 */	
	public List<WellKnownResult> getWellKnownResults() {
		return wellKnownResults;
	}

	/**
	 * Sets {@link #wellKnownResults}
	 */	
	public void setWellKnownResults(List<WellKnownResult> wellKnownResults) {
		this.wellKnownResults = wellKnownResults;
	}

}
