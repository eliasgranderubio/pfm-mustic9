package es.uem.cracking.slave.john;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;


/**
 * 
 * John The Ripper wrapper
 * 
 * @author egrande
 *
 */
public class John_The_Ripper_Wrapper {
	
	public static final String DICTIONARY_NAME = "dic.txt";
	public static final String OUTPUT_FILE = System.getProperty("user.home") + File.separator + ".john" + File.separator + "john.pot";
	public static final String CONFIG_FILE = "john.conf";
	public static final String HASH_FILENAME = "hash.txt";
	
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
	public JohnResultSet attack() throws Exception {		
		// Init checks
		if ((this.dictionary == null) || (this.format == null) || (this.hashToCrack == null)){
			throw new Exception("Incomplete input parameters (Dictionary, Format or Hash to Crack)");
		}
		
		// Prepare dictionary and cmd
		File dictionaryFile = doDictionary();
		File hashFile = hashToFile();
		File confFile = null;
		String cmd = "john";
		if (this.configParam != null){
			confFile = doConfFile();
			cmd += " --config=" + confFile.getAbsolutePath(); 
		}
		cmd += " --wordlist=" + dictionaryFile.getAbsolutePath();
		cmd += " --format=" + this.format;
		cmd += " " + hashFile.getAbsolutePath();
		
		// Execute linux cmd
		if (!System.getProperty("os.name").equals("Linux")) {
			throw new Exception("Funcionality not yet implemented in this OS");
		}
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(cmd);
		proc.waitFor();	
		
		//Delete input files
		dictionaryFile.delete();
		hashFile.delete();
		if (this.configParam != null){
			confFile.delete();
		}
		
		//Parse John output
		boolean found = false;
		String clearPass = null;
		
		File outputfile = new File(OUTPUT_FILE);
		if(!outputfile.exists()) {
			throw new Exception("John could not create the output file '" + outputfile.getAbsolutePath() + "'");
		}
		BufferedReader br = new BufferedReader(new FileReader(outputfile));
		String rawLine = br.readLine();	//read hash+clearpass
		if(rawLine!=null) {
			String password[] = rawLine.split(":");
			clearPass = password[1];
			found = true;
		}

		//Delete john.pot file
		br.close();
		outputfile.delete();
		
		// Prepare response and return
		JohnResultSet response = new JohnResultSet();
		response.setPasswordFound(found);
		response.setClearPass(clearPass);
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
	
	/**
	 * Creates hash file
	 */
	private File hashToFile() throws Exception {
		if(this.hashToCrack == null) {
			throw new Exception("Hash must not be empty");
		}
		File f = new File(HASH_FILENAME);
		PrintWriter writer = new PrintWriter(f);
		writer.println(this.hashToCrack);
		writer.close();
		return f;
	}

	/**
	 * Creates John configuration file
	 */
	private File doConfFile() throws Exception {
		if(configParam == null) {
			throw new Exception("John configuration file must not be empty");
		}
		File f = new File(CONFIG_FILE);
		PrintWriter writer = new PrintWriter(f);
		writer.println(this.configParam);
		writer.close();
		return f;
	}

}