package es.uem.cracking.master.ws.attacks;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.master.dao.attacks.ActiveAttackDaoImpl;
import es.uem.cracking.master.dao.attacks.AttackWindowsDaoImpl;
import es.uem.cracking.master.jpa.Active_Attacks;
import es.uem.cracking.master.jpa.Attack_Windows;
import es.uem.cracking.master.ws.attacks.messages.CreateFullAttackRequest;
import es.uem.cracking.master.ws.attacks.messages.FindAttackWindowByIdRequest;
import es.uem.cracking.master.ws.attacks.messages.FindAttackWindowByIdResponse;
import es.uem.cracking.master.ws.attacks.messages.GetActiveAttackRequest;
import es.uem.cracking.master.ws.attacks.messages.GetActiveAttackResponse;
import es.uem.cracking.master.ws.attacks.messages.GetAllActiveAttacksIdResponse;
import es.uem.cracking.master.ws.attacks.messages.GetAttackWindowIdsToSendRequest;
import es.uem.cracking.master.ws.attacks.messages.GetAttackWindowIdsToSendResponse;
import es.uem.cracking.master.ws.attacks.messages.RemoveAttackWindowByIdRequest;
import es.uem.cracking.master.ws.attacks.messages.RemoveFullAttackRequest;
import es.uem.cracking.master.ws.attacks.messages.UpdateAttackWindowRequest;
import es.uem.cracking.master.ws.attacks.messages.UpdateAttackWindowResponse;


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
	
	/**
	 * Gets attack window Ids to send
	 */
	@WebMethod
	public GetAttackWindowIdsToSendResponse getAttackWindowIdsToSend(@WebParam(name="getAttackWindowIdsToSendRequest") GetAttackWindowIdsToSendRequest getAttackWindowIdsToSendRequest) {
		// Execute query
		List<Long> ids = attackWindowsDao.getAttackWindowIdsToSend(getAttackWindowIdsToSendRequest.getActiveAttackId(), getAttackWindowIdsToSendRequest.getMaxWindows());
		
		// Prepare response
		GetAttackWindowIdsToSendResponse response = new GetAttackWindowIdsToSendResponse();
		response.setId(ids);
		return response;
	}

	/**
	 * Finds attack window by Id
	 */
	@WebMethod
	public FindAttackWindowByIdResponse findAttackWindowById(@WebParam(name="findAttackWindowByIdRequest") FindAttackWindowByIdRequest findAttackWindowByIdRequest) {
		// Execute query
		Attack_Windows aw = attackWindowsDao.findById(findAttackWindowByIdRequest.getId());
		
		// Prepare response
		FindAttackWindowByIdResponse response = new FindAttackWindowByIdResponse();
		if(aw!=null){
			response.setId(aw.getId());	
			response.setActiveAttackId(aw.getActiveAttackId());	
			response.setAttemps(aw.getAttemps());	
			response.setSentTimestamp((aw.getSentTimestamp()!=null)?aw.getSentTimestamp().getTime():null);	
			response.setBfPattern(aw.getBfPattern());	
			response.setProcessed(aw.isProcessed());	
			response.setFactor(aw.getFactor());	
			response.setFirstDictionaryWord(aw.getFirstDictionaryWord());	
			response.setLastDictionaryWord(aw.getLastDictionaryWord());
		}
		return response;
	}
	
	/**
	 * Updates attack window
	 */
	@WebMethod
	public UpdateAttackWindowResponse updateAttackWindow(@WebParam(name="updateAttackWindowRequest") UpdateAttackWindowRequest updateAttackWindowRequest){
		// Prepare entity
		Attack_Windows entity = new Attack_Windows();
		if(updateAttackWindowRequest!=null){
			entity.setId(updateAttackWindowRequest.getId());
			entity.setActiveAttackId(updateAttackWindowRequest.getActiveAttackId());
			entity.setAttemps(updateAttackWindowRequest.getAttemps());
			entity.setSentTimestamp((updateAttackWindowRequest.getSentTimestamp()!=null)? new Timestamp(updateAttackWindowRequest.getSentTimestamp()): null);
			entity.setBfPattern(updateAttackWindowRequest.getBfPattern());
			entity.setProcessed(updateAttackWindowRequest.isProcessed());
			entity.setFactor(updateAttackWindowRequest.getFactor());
			entity.setFirstDictionaryWord(updateAttackWindowRequest.getFirstDictionaryWord());
			entity.setLastDictionaryWord(updateAttackWindowRequest.getLastDictionaryWord());
		}
		
		// Execute query
		boolean result = attackWindowsDao.updateAttackWindow(entity);
		
		// Prepare response
		UpdateAttackWindowResponse response = new UpdateAttackWindowResponse();
		response.setUpdated(result);
		return response;
	}
	
	/**
	 * Removes attack window by Id
	 */
	@WebMethod
	public void removeAttackWindowById(@WebParam(name="removeAttackWindowByIdRequest") RemoveAttackWindowByIdRequest removeAttackWindowByIdRequest) {
		// Execute query
		attackWindowsDao.removeById(removeAttackWindowByIdRequest.getId());
	}
	
}
