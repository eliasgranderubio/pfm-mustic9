package es.uem.cracking.slave.hydra;

import java.util.List;


/**
 * 
 * THC-Hydra wrapper
 * 
 * @author egrande
 *
 */
public class THC_Hydra_Wrapper {
	
	// Private attributes
	
	/** Remote user to attack */
	private String remoteUser;
	/** Dictionary to check */
	private List<String> dictionary;
	/** Remote IP to attack */
	private String remoteIp;
	/** Remote port to attack */
	private Integer remotePort;
	/** Protocol to attack */
	private String protocol;
	/** Extra parameters to THC-Hydra */
	private String extraParams;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public THC_Hydra_Wrapper(String remoteUser, List<String> dictionary, String remoteIp, Integer remotePort, String protocol, String extraParams) {
		this.remoteUser  = remoteUser;
		this.dictionary  = dictionary;
		this.remoteIp    = remoteIp;
		this.remotePort  = remotePort;
		this.protocol    = protocol;
		this.extraParams = extraParams;
	}

	/**
	 * THC-Hydra attack
	 */
	public HydraResultSet attack() {
		// TODO egrande: you implement wrapper logic here
		
		// Prepare response and return
		HydraResultSet response = new HydraResultSet();
		// TODO egrande: set response
		return response;
	}

	
	// Only getters
	
	/**
	 * Gets {@link #remoteUser}
	 */
	public String getRemoteUser() {
		return remoteUser;
	}
	
	/**
	 * Gets {@link #dictionary}
	 */
	public List<String> getDictionary() {
		return dictionary;
	}
	
	/**
	 * Gets {@link #remoteIp}
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * Gets {@link #remotePort}
	 */
	public Integer getRemotePort() {
		return remotePort;
	}
	
	/**
	 * Gets {@link #protocol}
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Gets {@link #extraParams}
	 */
	public String getExtraParams() {
		return extraParams;
	}

}
