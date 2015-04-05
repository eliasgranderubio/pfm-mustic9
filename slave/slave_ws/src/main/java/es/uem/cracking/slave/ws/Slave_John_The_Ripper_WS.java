package es.uem.cracking.slave.ws;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.slave.jms.SlaveJMSUtils;
import es.uem.cracking.slave.john.JohnResultSet;
import es.uem.cracking.slave.john.John_The_Ripper_Wrapper;
import es.uem.cracking.slave.utils.SlaveRecorder;
import es.uem.cracking.slave.utils.SlaveUtils;
import es.uem.cracking.slave.ws.messages.JohnTheRipperAttackRequest;


/**
 * 
 * John The Ripper web service
 * 
 * @author egrande
 *
 */
@Stateless
@WebService(
		portName = "Slave_John_The_Ripper_WS_Port",
        serviceName = "Slave_John_The_Ripper_WS")
public class Slave_John_The_Ripper_WS {

	// Private attributes
	
	/** Run in standalone mode */
	private boolean isStandalone = false;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Slave_John_The_Ripper_WS() {
		register();
	}
	
	
	/**
	 * John the Ripper attack
	 */
	@Oneway
	@WebMethod
	public void johnTheRipperAttack(@WebParam(name="johnTheRipperAttackRequest") JohnTheRipperAttackRequest johnTheRipperAttackRequest) {
		// Notify to SlaveRecorder
		SlaveRecorder.getInstance().iAmWorking();
		
		// Attack		
		long startTimeMs = System.currentTimeMillis( );
		John_The_Ripper_Wrapper john = new John_The_Ripper_Wrapper(johnTheRipperAttackRequest.getConfigParam(),
																   johnTheRipperAttackRequest.getBfPattern(),
																   (johnTheRipperAttackRequest.getDictionary()!=null) ? johnTheRipperAttackRequest.getDictionary().getWords(): new ArrayList<String>(),
																   johnTheRipperAttackRequest.getFormat(),
																   johnTheRipperAttackRequest.getHashToCrack());
		JohnResultSet result = null;
		boolean exception = false;
		try{
			result = john.attack();
		}catch(Exception ex){
			ex.printStackTrace(System.err);
			exception = true;	
		}
		long taskTimeMs  = System.currentTimeMillis( ) - startTimeMs;
		
		if(!isStandalone){
			// Send JMS notification
			SlaveJMSUtils.sendJMSNotificationToMaster(johnTheRipperAttackRequest.getActiveAttackId(),
													  johnTheRipperAttackRequest.getAttackWindowId(),
													  SlaveUtils.getProcessorName(),
													  (johnTheRipperAttackRequest.getDictionary()!=null && johnTheRipperAttackRequest.getDictionary().getWords()!=null) ? johnTheRipperAttackRequest.getDictionary().getWords().size(): 0,
													  taskTimeMs,
													  result.isPasswordFound(),
													  result.getClearPass(),
													  result.getRecFileInBase64());
			
			// Register myself in the name server again as available
			register();
		}
		else {
			// Only for development purpose in standalone mode
			if(result.isPasswordFound() && result.getClearPass()!=null) {
				System.out.println("The clear password is: " + result.getClearPass());
			}
			else {
				System.out.println("Password was not found this time!!");
			}
		}
	}
	
	
	// Private methods
	
	/**
	 * Registers and checks if run in standalone mode
	 */
	private void register(){
		boolean registered = SlaveRecorder.getInstance().registerMyselfInTheNameServer();
		if(!registered) {
			isStandalone = true;
			System.err.println("This slave continues in standalone mode");
		}
		else {
			isStandalone = false;
		}
	}
	
}
