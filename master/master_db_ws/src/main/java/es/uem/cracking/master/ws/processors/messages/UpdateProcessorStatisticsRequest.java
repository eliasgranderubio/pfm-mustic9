package es.uem.cracking.master.ws.processors.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Update processor statistics request
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="UpdateProcessorStatisticsRequest")
public class UpdateProcessorStatisticsRequest {
	
	// Private attributes
	
	/** Processor name */
	@XmlElement
	private String processorName;
	/** Total words processed */
	@XmlElement
	private long totalWordsProcessed;
	/** Total time processing */
	@XmlElement
	private long totalTimeProcessing;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #processorName}
	 */
	public String getProcessorName() {
		return processorName;
	}
	
	/**
	 * Sets {@link #processorName}
	 */
	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}
	
	/**
	 * Gets {@link #totalWordsProcessed}
	 */
	public long getTotalWordsProcessed() {
		return totalWordsProcessed;
	}
	
	/**
	 * Sets {@link #totalWordsProcessed}
	 */
	public void setTotalWordsProcessed(long totalWordsProcessed) {
		this.totalWordsProcessed = totalWordsProcessed;
	}
	
	/**
	 * Gets {@link #totalTimeProcessing}
	 */
	public long getTotalTimeProcessing() {
		return totalTimeProcessing;
	}
	
	/**
	 * Sets {@link #totalTimeProcessing}
	 */
	public void setTotalTimeProcessing(long totalTimeProcessing) {
		this.totalTimeProcessing = totalTimeProcessing;
	}
	
}
