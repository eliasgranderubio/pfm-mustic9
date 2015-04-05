package es.uem.cracking.slave.john;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * 
 * John The Ripper wrapper
 * 
 * @author egrande
 *
 */
public class John_The_Ripper_Wrapper {
	
	public static final String DICTIONARY_NAME = "dic.txt";
	public static final String OUTPUT_FILE = "/root/.john/john.pot";
	public static final String CONFIG_FILE = "john.conf";
	
	
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
		
		File dictionaryFile = doDictionary();
		File hashFile = hashToFile();
		String cmd = "john";
		if (this.configParam != null){
			cmd += " --config=" + CONFIG_FILE; 
		}
		cmd += " --wordlist=" + dictionaryFile.getAbsolutePath();
		cmd += " --format=" + this.format;
		cmd += " " + hashFile.getAbsolutePath();
		
		// Execute linux cmd
		if (!System.getProperty("os.name").equals("Linux")) {
			throw new Exception("Funcionality not yet implemented in this OS");
		}
		// TODO egrande: you implement wrapper logic here
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(cmd);
		proc.waitFor();	
		//Delete dictionary file
		dictionaryFile.delete();
		//Delete hash file
		hashFile.delete();
		
		//Parse John output
		boolean found = false;
		String clearPass = null;
		
		File f = new File(OUTPUT_FILE);
		if (f.exists()){	
			BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FILE));
			String rawLine = br.readLine();	//read hash+clearpass
			String password[] = rawLine.split(":");
			clearPass = password[1];
			found = true;

			br.close();
		
			//Delete john.pot file
			Runtime rt1 = Runtime.getRuntime();
			Process proc1 = rt1.exec("rm /root/.john/john.pot");
			proc1.waitFor();
		}
		
		// Prepare response and return
		JohnResultSet response = new JohnResultSet();
		response.setPasswordFound(found);
		response.setClearPass(clearPass);
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
	
	private File hashToFile() throws Exception {
		if(this.hashToCrack == null) {
			throw new Exception("Hash must not be empty");
		}
			File f = new File("hash.txt");
			PrintWriter writer = new PrintWriter(f);
			writer.println(this.hashToCrack);
			writer.close();
			return f;
	}

	/**
	* Creates John configuration file
	*/
	/*
	private File doConf() throws Exception {
		if(configParam.size() == 0) {
			throw new Exception("John configuration file must not be empty");
		}
			File f = new File(CONFIG_FILE);
			PrintWriter writer = new PrintWriter(f);
			for(String word : configParam) {
				writer.println(word);
			}
			writer.close();
			return f;
	}*/

	/**
	 * Main test method
	 */
	/*public static void main(String [] args) throws Exception{
		
		List <String> dictionary = new ArrayList();
		
		dictionary.add("1234");
		dictionary.add("123456");
		dictionary.add("1234567");
		dictionary.add("123456789");	
	
		John_The_Ripper_Wrapper j= new John_The_Ripper_Wrapper(null, null, dictionary, "raw-md5","25f9e794323b453885f5181f1b624d0b");
	
		j.attack();
	
	
	}*/

}