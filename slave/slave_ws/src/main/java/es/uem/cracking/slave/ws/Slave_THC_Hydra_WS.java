package es.uem.cracking.slave.ws;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.slave.hydra.HydraResultSet;
import es.uem.cracking.slave.hydra.THC_Hydra_Wrapper;
import es.uem.cracking.slave.jms.SlaveJMSUtils;
import es.uem.cracking.slave.utils.SlaveRecorder;
import es.uem.cracking.slave.utils.SlaveUtils;
import es.uem.cracking.slave.ws.messages.ThcHydraAttackRequest;


/**
 * 
 * THC-Hydra web service
 * 
 * @author egrande
 *
 */
@Stateless
@WebService(
		portName = "Slave_THC_Hydra_WS_Port",
        serviceName = "Slave_THC_Hydra_WS")
public class Slave_THC_Hydra_WS {
	
	// Private attributes
	
	/** Run in standalone mode */
	private boolean isStandalone = false;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Slave_THC_Hydra_WS() {
		register();
	}
	
	
	/**
	 * THC-Hydra attack
	 */
	@Oneway
	@WebMethod
	public void thcHydraAttack(@WebParam(name="thcHydraAttackRequest") ThcHydraAttackRequest thcHydraAttackRequest){
		// Notify to SlaveRecorder
		SlaveRecorder.getInstance().iAmWorking();
		
		// Attack
		long startTimeMs = System.currentTimeMillis( );
		THC_Hydra_Wrapper hydra = new THC_Hydra_Wrapper(thcHydraAttackRequest.getRemoteUser(),
														(thcHydraAttackRequest.getDictionary()!=null) ? thcHydraAttackRequest.getDictionary().getWords() : new ArrayList<String>(),
														thcHydraAttackRequest.getRemoteIp(),
														thcHydraAttackRequest.getRemotePort(),
														thcHydraAttackRequest.getType().getValue(),
														thcHydraAttackRequest.getExtraParam());
		HydraResultSet result = null;
		boolean exception = false;
		try {
			result = hydra.attack();
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			exception = true;
		}
		long taskTimeMs  = System.currentTimeMillis( ) - startTimeMs;
		
		// Prepare feedback
		if(!exception) {
			if(!isStandalone){
				// Send JMS notification
				SlaveJMSUtils.sendJMSNotificationToMaster(thcHydraAttackRequest.getActiveAttackId(),
														  thcHydraAttackRequest.getAttackWindowId(),
														  SlaveUtils.getProcessorName(),
														  (thcHydraAttackRequest.getDictionary()!=null && thcHydraAttackRequest.getDictionary().getWords()!=null) ? thcHydraAttackRequest.getDictionary().getWords().size() : 0,
														  taskTimeMs,
														  result.isPasswordFound(),
														  result.getClearPass(),
														  null);
														  
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
