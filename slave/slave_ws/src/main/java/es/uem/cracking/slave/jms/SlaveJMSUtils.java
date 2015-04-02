package es.uem.cracking.slave.jms;


/**
 * 
 * Slave JMS utils
 * 
 * @author egrande
 *
 */
public class SlaveJMSUtils {

	// Public methods
	
	/**
	 * Sends JMS notification about attack information to Master node
	 */
	public static synchronized void sendJMSNotificationToMaster(long activeAttackId, long attackWindowId, String processorName,
			                                                    int total_words_processed, long total_time_processing, boolean isPasswordFound,
			                                                    String clearPass, String recFileInBase64) {
		// TODO egrande: implements JMS Notification
	}
	
}
