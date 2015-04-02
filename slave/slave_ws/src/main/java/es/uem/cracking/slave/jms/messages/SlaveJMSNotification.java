package es.uem.cracking.slave.jms.messages;

import java.io.Serializable;


/**
 * 
 * Slave JMS notification
 * 
 * @author egrande
 *
 */
public class SlaveJMSNotification implements Serializable {

	// Private attributes
	
	/** Serial version UID */
	private static final long serialVersionUID = 4700834580861639160L;
	/** Active attack id */
	private long activeAttackId;
	/** Attack window Id */
	private long attackWindowId;
	/** Processor name */
	private String processorName;
	/** Total words processed */
	private int total_words_processed;
	/** Total time processing */
	private long total_time_processing;
	/** Is password found */
	private boolean isPasswordFound;
	/** Clear pass */
	private String clearPass;
	/** .rec file in Base64 */
	private String recFileInBase64;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public SlaveJMSNotification(long activeAttackId, long attackWindowId, String processorName,
			                    int total_words_processed, long total_time_processing, boolean isPasswordFound,
			                    String clearPass, String recFileInBase64) {
		this.activeAttackId = activeAttackId;
		this.attackWindowId = attackWindowId;
		this.processorName = processorName;
		this.total_words_processed = total_words_processed;
		this.total_time_processing = total_time_processing;
		this.isPasswordFound = isPasswordFound;
		this.clearPass = clearPass;
		this.recFileInBase64 = recFileInBase64;
	}
	
	/**
	 * Overrides {@link #toString()} method
	 */
	@Override
	public String toString() {
		String processorName_tmp = (processorName!=null) ? processorName : "" ;
		String clearPass_tmp = (clearPass!=null) ? clearPass : "" ;
		String recFileInBase64_tmp = (recFileInBase64!=null) ? recFileInBase64 : "";
		return "<notification>"
					+ "<activeAttackId>" + activeAttackId + "</activeAttackId>" 
					+ "<attackWindowId>" + attackWindowId + "</attackWindowId>"
					+ "<processorName>" + processorName_tmp + "</processorName>"
					+ "<total_words_processed>" + total_words_processed + "</total_words_processed>"
					+ "<total_time_processing>" + total_time_processing + "</total_time_processing>"
					+ "<isPasswordFound>" + isPasswordFound + "</isPasswordFound>"
					+ "<clearPass>" + clearPass_tmp + "</clearPass>"
					+ "<recFileInBase64>" + recFileInBase64_tmp + "</recFileInBase64>"	
			  + "</notification>";
	}
	
}
