package es.uem.cracking.master.jpa;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * Attack_Windows entity
 * 
 * @author egrande
 *
 */
@Entity
@IdClass(Attack_Windows_Id.class)
@Table(name = "ATTACK_WINDOWS")
public class Attack_Windows {

	/** Active attack Id */
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="active_attack_id")
	private Active_Attacks activeAttackId;
	/** Attack window Id */
	@Id
	@Column(name = "id")
	private long id;
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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="first_dictionary_word")
	private Dictionary_Words firstDictionaryWord;
	/** Last dictionary word to this window */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="last_dictionary_word")
	private Dictionary_Words lastDictionaryWord;
	
	
	// Getters and setters
	
	/**
	 * Gets {@link #activeAttackId}
	 */
	public Active_Attacks getActiveAttackId() {
		return activeAttackId;
	}
	
	/**
	 * Sets {@link #activeAttackId}
	 */
	public void setActiveAttackId(Active_Attacks activeAttackId) {
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
	public Dictionary_Words getFirstDictionaryWord() {
		return firstDictionaryWord;
	}

	/**
	 * Sets {@link #firstDictionaryWord}
	 */
	public void setFirstDictionaryWord(Dictionary_Words firstDictionaryWord) {
		this.firstDictionaryWord = firstDictionaryWord;
	}

	/**
	 * Gets {@link #lastDictionaryWord}
	 */
	public Dictionary_Words getLastDictionaryWord() {
		return lastDictionaryWord;
	}

	/**
	 * Sets {@link #lastDictionaryWord}
	 */
	public void setLastDictionaryWord(Dictionary_Words lastDictionaryWord) {
		this.lastDictionaryWord = lastDictionaryWord;
	}
	
}
