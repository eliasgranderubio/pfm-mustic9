package es.uem.cracking.slave.hydra;


/**
 * 
 * THC-Hydra result set
 * 
 * @author egrande
 *
 */
public class HydraResultSet {

	// Private attributes
	
	/** Is password found */
	private boolean passwordFound;
	/** Clear password */
	private String clearPass;

	
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
	
}
