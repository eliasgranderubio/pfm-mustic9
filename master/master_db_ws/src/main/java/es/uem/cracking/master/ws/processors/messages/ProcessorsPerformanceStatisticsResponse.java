package es.uem.cracking.master.ws.processors.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * Processors performance statistics response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="ProcessorsPerformanceStatisticsResponse")
public class ProcessorsPerformanceStatisticsResponse {

	// Private attributes
	
	/** Processors */
	@XmlElement
	private List<Processor> processors;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #processors}
	 */
	public List<Processor> getProcessors() {
		return processors;
	}

	/**
	 * Sets {@link #processors}
	 */
	public void setProcessors(List<Processor> processors) {
		this.processors = processors;
	}
	
}
