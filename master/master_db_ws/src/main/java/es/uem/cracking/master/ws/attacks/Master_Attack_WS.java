package es.uem.cracking.master.ws.attacks;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.master.dao.attacks.ActiveAttackDaoImpl;
import es.uem.cracking.master.dao.attacks.AttackWindowsDaoImpl;
import es.uem.cracking.master.jpa.Active_Attacks;
import es.uem.cracking.master.ws.attacks.messages.CreateFullAttackRequest;
import es.uem.cracking.master.ws.attacks.messages.GetActiveAttackRequest;
import es.uem.cracking.master.ws.attacks.messages.GetActiveAttackResponse;
import es.uem.cracking.master.ws.attacks.messages.GetAllActiveAttacksIdResponse;
import es.uem.cracking.master.ws.attacks.messages.RemoveFullAttackRequest;


/**
 * 
 * Master attack web service
 * 
 * @author egrande
 *
 */
@Stateless
@WebService(
		portName = "Master_Attack_WS_Port",
        serviceName = "Master_Attack_WS")
public class Master_Attack_WS {

	// Private attributes
	private ActiveAttackDaoImpl activeAttackDao;
	private AttackWindowsDaoImpl attackWindowsDao;
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Master_Attack_WS() {
		activeAttackDao = new ActiveAttackDaoImpl();
		attackWindowsDao = new AttackWindowsDaoImpl();
	}
	
	/**
	 * Creates full attack
	 */
	@Oneway
	@WebMethod
	public void createFullAttack(@WebParam(name="createFullAttackRequest") CreateFullAttackRequest createFullAttackRequest) {
		// Execute query
		activeAttackDao.createFullAttack(attackWindowsDao,
										 createFullAttackRequest.getTool(), createFullAttackRequest.getAttackType(), 
										 createFullAttackRequest.getRemoteUser(), createFullAttackRequest.getRemoteIp(),
										 createFullAttackRequest.getRemotePort(), createFullAttackRequest.getExtraParam(),
										 createFullAttackRequest.getHashToCrack(), createFullAttackRequest.getNotifyToEmail(),
										 createFullAttackRequest.getWindowSize(), createFullAttackRequest.getTotalWords());
	}
	
	/**
	 * Gets all active attacks Ids
	 */
	@WebMethod
	public GetAllActiveAttacksIdResponse getAllActiveAttacksId() {
		// Execute query
		List<Long> ids = activeAttackDao.getAllActiveAttacksId();
		 
		// Prepare response
		GetAllActiveAttacksIdResponse response = new GetAllActiveAttacksIdResponse();
		response.setId(ids);
		return response;
	}
	
	/**
	 * Gets active attack
	 */
	@WebMethod
	public GetActiveAttackResponse getActiveAttack(@WebParam(name="getActiveAttackRequest") GetActiveAttackRequest getActiveAttackRequest) {
		// Execute query
		Active_Attacks aa = activeAttackDao.findById(getActiveAttackRequest.getId());
		
		// Prepare response
		GetActiveAttackResponse response = new GetActiveAttackResponse();
		if(aa!=null) { 
			response.setId(aa.getId());
			response.setTool(aa.getTool());
			response.setAttackType(aa.getAttackType());
			response.setRemoteUser(aa.getRemoteUser());
			response.setRemoteIp(aa.getRemoteIp());
			response.setRemotePort(aa.getRemotePort());
			response.setExtraParam(aa.getExtraParam());
			response.setHashToCrack(aa.getHashToCrack());
			response.setNotifyToEmail(aa.getNotifyToEmail());
		}
		return response;
	}
	
	/**
	 * Deletes full attack
	 */
	@Oneway
	@WebMethod
	public void removeFullAttack(@WebParam(name="removeFullAttackRequest") RemoveFullAttackRequest removeFullAttackRequest) {
		// Execute query
		activeAttackDao.removeFullAttack(removeFullAttackRequest.getId());
	}
	
}
