package es.uem.cracking.slave.john;

/**
 * 
 * John the Ripper result set
 * 
 * @author egrande
 *
 */
public class JohnResultSet {

	// Private attributes
	
	/** Is password found */
	private boolean passwordFound;
	/** Clear password */
	private String clearPass;
	/** .rec file with John state in Base64 */
	private String recFileInBase64; 
	
	
	// Getters and Setters
	
	/**
	 * Gets {@link #passwordFound}
	 */
	public boolean isPasswordFound() {
		return passwordFound;
	}
	
	/**
	 * Sets {@link #passwordFound}
	 */
	public void setPasswordFound(boolean passwordFound) {
		this.passwordFound = passwordFound;
	}
	
	/**
	 * Gets {@link #clearPass}
	 */
	public String getClearPass() {
		return clearPass;
	}
	
	/**
	 * Sets {@link #clearPass}
	 */
	public void setClearPass(String clearPass) {
		this.clearPass = clearPass;
	}

	/**
	 * Gets {@link #recFileInBase64}
	 */
	public String getRecFileInBase64() {
		return recFileInBase64;
	}

	/**
	 * Sets {@link #recFileInBase64}
	 */
	public void setRecFileInBase64(String recFileInBase64) {
		this.recFileInBase64 = recFileInBase64;
	}
		
}
