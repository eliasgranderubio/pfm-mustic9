package es.uem.cracking.slave.hydra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 
 * THC-Hydra wrapper
 * 
 * @author egrande
 *
 */
public class THC_Hydra_Wrapper {
	
	public static final String DICTIONARY_NAME = "dic.txt";
	public static final String OUTPUT_FILE = "out.txt";
	
	
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
	public HydraResultSet attack() throws Exception {
		
		// Init checks
		if ((remoteUser==null) || (dictionary==null) || (remoteIp == null)) {
			throw new Exception("Incomplete input parameters!");
		}
		
		// Prepare dictionary and cmd
		File dictionaryFile = doDictionary();
		String cmd = "hydra -f -o " + OUTPUT_FILE;
		cmd += " -l " + remoteUser;
		cmd += " -P " + dictionaryFile.getAbsolutePath();
		cmd += " " + remoteIp;
		if (remotePort!=null) {
			cmd += " -s " + remotePort;
		}
		if (protocol!=null) {
			cmd += " " + protocol;
		}
		if(protocol.equals("http-get") || protocol.equals("http-head") || protocol.equals("http-post-form") || protocol.equals("http-get-form")){
			if (extraParams!=null) {
				cmd += " " + extraParams;
			}
		}
		
		// Execute linux cmd
		if(!System.getProperty("os.name").equals("Linux")) {
			throw new Exception("Functionality not yet implemented in this OS");
		}
		Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(cmd);
        proc.waitFor();
        
        // Delete dictionary file
        dictionaryFile.delete();
		
		// Parse hydra output
        boolean found = false;
        String clearPass = null;
		File outputFile = new File(OUTPUT_FILE);
		if(!outputFile.exists()) {
			throw new Exception("Hydra could not create the output file");
		}
		BufferedReader br = new BufferedReader(new FileReader(outputFile));
		String l2=br.readLine(); 		//info atack
		if((l2=br.readLine())!=null){ 	//success login and pass
			StringTokenizer st = new StringTokenizer (l2);
	        boolean searching=true;
	        while(searching){	// Searching password to take next word, success pass
	        	String 	temp= st.nextToken();
	            String 	word=temp.replaceAll("\\W", "");
	            if (word.equals("password")){
	            	searching=false;
	            }
	       } 	
	       String temp = st.nextToken();
	       clearPass = temp.replaceAll("\\W+", "");
	       found = true;
		}

		// Close and delete output file
		br.close();
		outputFile.delete();
		        
		// Prepare response and return
		HydraResultSet response = new HydraResultSet();
		response.setPasswordFound(found);
		response.setClearPass(clearPass);
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
	
	
	// Private methods

	/**
	 * Creates dictionary file
	 */
	private File doDictionary() throws Exception {
		if(dictionary.size() == 0) {
			throw new Exception("Dictionary must not be empty");
		}
		File f = new File(DICTIONARY_NAME);
		PrintWriter writer = new PrintWriter(f);
		for(String word : dictionary) {
			writer.println(word);
		}
		writer.close();
		return f;
	}
	
}
