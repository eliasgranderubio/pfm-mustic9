package es.uem.cracking.master.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * Dictionary_Words entity
 * 
 * @author egrande
 *
 */
@Entity
@Table(name = "DICTIONARY_WORDS")
public class Dictionary_Words {

	// Private attributes
	
	/** Word */
	@Id
	@Column(name = "word")
	private String word;

	
	// Getters and setters
	
	/**
	 * Gets {@link #word}
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets {@link #word}
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
}
