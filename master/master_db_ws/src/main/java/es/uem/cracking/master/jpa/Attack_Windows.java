package es.uem.cracking.master.jpa;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 
 * Attack_Windows entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "ATTACK_WINDOWS")
@SequenceGenerator(name="a_w_seq", sequenceName="ATTACK_WINDOWS_SEQ")
public class Attack_Windows {

	/** Attack window Id */
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="a_w_seq")
	@Column(name = "id")
	private long id;
	/** Active attack Id */
	@Column(name="active_attack_id")
	private long activeAttackId;
	/** Attemps */
	@Column(name = "attemps")
	private int attemps;
	/** Sent timestamp */
	@Column(name = "sent_timestamp")
	private Timestamp sentTimestamp;
	/** Brute force pattern */
	@Column(name = "bf_pattern")
	private String bfPattern;
	/** Is window processed */
	@Column(name = "processed")
	private boolean processed;
	/** Factor */
	@Column(name = "factor")
	private int factor;
	/** First dictionary word to this window */
	@Column(name="first_dictionary_word")
	private String firstDictionaryWord;
	/** Last dictionary word to this window */
	@Column(name="last_dictionary_word")
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
	public Timestamp getSentTimestamp() {
		return sentTimestamp;
	}

	/**
	 * Sets {@link #sentTimestamp}
	 */
	public void setSentTimestamp(Timestamp sentTimestamp) {
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
