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
	private String recFileBase64; 
	
	
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
	 * Gets {@link #recFileBase64}
	 */
	public String getRecFileBase64() {
		return recFileBase64;
	}

	/**
	 * Sets {@link #recFileBase64}
	 */
	public void setRecFileBase64(String recFileBase64) {
		this.recFileBase64 = recFileBase64;
	}
		
}
