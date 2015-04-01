package es.uem.cracking.slave.john;

import java.util.List;


/**
 * 
 * John The Ripper wrapper
 * 
 * @author egrande
 *
 */
public class John_The_Ripper_Wrapper {

	// Private attributes
	
	/** Config parameter to John The Ripper */
	private String configParam;
	/** Brute force pattern */
	private String bfPattern;
	/** Dictionary to check */
	private List<String> dictionary;
	/** Format to attack */
	private String format;
	/** Hash to attack */
	private String hashToCrack;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public John_The_Ripper_Wrapper(String configParam, String bfPattern, List<String> dictionary, String format, String hashToCrack) {
		this.configParam = configParam;
		this.bfPattern   = bfPattern;
		this.dictionary  = dictionary;
		this.format		 = format;
		this.hashToCrack = hashToCrack;
	}

	/**
	 * John the Ripper attack
	 */
	public JohnResultSet attack() {
		// TODO egrande: you implement wrapper logic here
		
		// Prepare response and return
		JohnResultSet response = new JohnResultSet();
		// TODO egrande: set response
		return response;
	}

		
	// Only getters
	
	/**
	 * Gets {@link #configParam}
	 */
	public String getConfigParam() {
		return configParam;
	}

	/**
	 * Gets {@link #bfPattern}
	 */
	public String getBfPattern() {
		return bfPattern;
	}

	/**
	 * Gets {@link #dictionary}
	 */
	public List<String> getDictionary() {
		return dictionary;
	}

	/**
	 * Gets {@link #format}
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Gets {@link #hashToCrack}
	 */
	public String getHashToCrack() {
		return hashToCrack;
	}
	
}
