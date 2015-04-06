package es.uem.cracking.master.ws.attacks.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Find attack window by Id response
 * 
 * @author egrande
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="FindAttackWindowByIdResponse")
public class FindAttackWindowByIdResponse {
	
	/** Attack window Id */
	@XmlElement
	private long id;
	/** Active attack Id */
	@XmlElement
	private long activeAttackId;
	/** Attemps */
	@XmlElement
	private int attemps;
	/** Sent timestamp */
	@XmlElement
	private Long sentTimestamp;
	/** Brute force pattern */
	@XmlElement
	private String bfPattern;
	/** Is window processed */
	@XmlElement
	private boolean processed;
	/** Factor */
	@XmlElement
	private int factor;
	/** First dictionary word to this window */
	@XmlElement
	private String firstDictionaryWord;
	/** Last dictionary word to this window */
	@XmlElement
	private String lastDictionaryWord;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #activeAttackId}
	 */
	public long getActiveAttackId() {
		return activeAttackId;
	}

	/**
	 * Sets {@link #activeAttackId}
	 */
	public void setActiveAttackId(long activeAttackId) {
		this.activeAttackId = activeAttackId;
	}
	
	/**
	 * Gets {@link #id}
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets {@link #id}
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets {@link #attemps}
	 */
	public int getAttemps() {
		return attemps;
	}
	
	/**
	 * Sets {@link #attemps}
	 */
	public void setAttemps(int attemps) {
		this.attemps = attemps;
	}
	
	/**
	 * Gets {@link #sentTimestamp}
	 */
	public Long getSentTimestamp() {
		return sentTimestamp;
	}

	/**
	 * Sets {@link #sentTimestamp}
	 */
	public void setSentTimestamp(Long sentTimestamp) {
		this.sentTimestamp = sentTimestamp;
	}

	/**
	 * Gets {@link #bfPattern}
	 */
	public String getBfPattern() {
		return bfPattern;
	}

	/**
	 * Sets {@link #bfPattern}
	 */
	public void setBfPattern(String bfPattern) {
		this.bfPattern = bfPattern;
	}

	/**
	 * Gets {@link #processed}
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * Sets {@link #processed}
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/**
	 * Gets {@link #factor}
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * Sets {@link #factor}
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * Gets {@link #firstDictionaryWord}
	 */
	public String getFirstDictionaryWord() {
		return firstDictionaryWord;
	}

	/**
	 * Sets {@link #firstDictionaryWord}
	 */
	public void setFirstDictionaryWord(String firstDictionaryWord) {
		this.firstDictionaryWord = firstDictionaryWord;
	}

	/**
	 * Gets {@link #lastDictionaryWord}
	 */
	public String getLastDictionaryWord() {
		return lastDictionaryWord;
	}

	/**
	 * Sets {@link #lastDictionaryWord}
	 */
	public void setLastDictionaryWord(String lastDictionaryWord) {
		this.lastDictionaryWord = lastDictionaryWord;
	}
	
}
