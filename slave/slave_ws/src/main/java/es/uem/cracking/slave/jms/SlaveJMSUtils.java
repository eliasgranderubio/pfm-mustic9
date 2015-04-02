package es.uem.cracking.slave.jms;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

import weblogic.jndi.Environment;
import es.uem.cracking.slave.jms.messages.SlaveJMSNotification;


/**
 * 
 * Slave JMS utils
 * 
 * @author egrande
 *
 */
public class SlaveJMSUtils {

	// Public attributes
	
	// TODO egrande: move this JMS properties to a properties file
	/** Weblogic URL where queue is */
	public final static String WEBLOGIC_URL="t3://masterandnameserver.ddns.net:7001";
	/** Defines the JNDI context factory */
	public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
	/** Defines the JMS context factory */
	public final static String JMS_FACTORY="jms/CrackingUemCF";
	/** Defines the queue */
	public final static String QUEUE="jms/CrackingUemQueue";
	
	
	// Private attributes
	
	private static QueueConnectionFactory qconFactory;
	private static QueueConnection qcon;
	private static QueueSession qsession;
	private static QueueSender qsender;
	private static Queue queue;
	private static TextMessage msg;
	
	
	// Public methods
	
	/**
	 * Sends JMS notification about attack information to Master node
	 */
	public static synchronized void sendJMSNotificationToMaster(long activeAttackId, long attackWindowId, String processorName,
			                                                    int total_words_processed, long total_time_processing, boolean isPasswordFound,
			                                                    String clearPass, String recFileInBase64) {
		
		SlaveJMSNotification notification = new SlaveJMSNotification(activeAttackId,attackWindowId,processorName,
																	 total_words_processed,total_time_processing,
																	 isPasswordFound,clearPass,recFileInBase64);
		try {
			Context ic = getInitialContext();
			SlaveJMSUtils sjmsu = new SlaveJMSUtils();
			sjmsu.init(ic);
			sjmsu.send(notification.toString());
			sjmsu.close();
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
	
	
	// Private methods
	
	/**
	 * Gets Initial Context
	 */
	private static Context getInitialContext() throws NamingException {
	   Environment env = new Environment();
	   env.setInitialContextFactory(JNDI_FACTORY);
	   env.setProviderURL(WEBLOGIC_URL);
	   env.setRequestTimeout(5000L);
	   env.setRMIClientTimeout(5000L);
	   env.setEnableServerAffinity(true);
	   return env.getInitialContext();
	}
	
	/**
	 * Creates all the necessary objects for sending messages to a JMS queue
	 */
	private void init(Context ctx) throws NamingException, JMSException {
	   qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
	   qcon = qconFactory.createQueueConnection();
	   qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	   queue = (Queue) ctx.lookup(QUEUE);
	   qsender = qsession.createSender(queue);
	   msg = qsession.createTextMessage();
	   qcon.start();
	}	
	
	/**
	 * Sends a message to a JMS queue
	 */
	private void send(String message) throws JMSException {
	   msg.setText(message);
	   qsender.send(msg);
	}
	
	/**
	 * Closes JMS objects
	 */
	private void close() throws JMSException {
	   qsender.close();
	   qsession.close();
	   qcon.close();
	}
	
	/**
	 * Private constructor
	 */
	private SlaveJMSUtils() {}
	
}
