package es.uem.cracking.master.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Processors_Performance_Online entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "PROCESSORS_PERFORMANCE_ONLINE")
public class Processors_Performance_Online {

	// Private attributes
	
	/** Processor name */
	@Id
	@Column(name = "processor_name")
	private String processorName;
	/** Total words processed */
	@Column(name = "total_words_processed")
	private long totalWordsProcessed;
	/** Total time processing */
	@Column(name = "total_time_processing")
	private long totalTimeProcessing;
	/** Words per minute */
	@Column(name = "words_per_minute")
	private long wordsPerMinute;
	
	
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
	
	/**
	 * Gets {@link #wordsPerMinute}
	 */
	public long getWordsPerMinute() {
		return wordsPerMinute;
	}
	
	/**
	 * Sets {@link #wordsPerMinute}
	 */
	public void setWordsPerMinute(long wordsPerMinute) {
		this.wordsPerMinute = wordsPerMinute;
	}	

}
