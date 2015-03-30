package es.uem.cracking.name_server.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.name_server.dao.ServerDaoImpl;
import es.uem.cracking.name_server.jpa.Servers;
import es.uem.cracking.name_server.ws.messages.AvailableSlave;
import es.uem.cracking.name_server.ws.messages.DeleteLockedSlaveRequest;
import es.uem.cracking.name_server.ws.messages.GetAvailableSlavesResponse;
import es.uem.cracking.name_server.ws.messages.RegisterRequest;
import es.uem.cracking.name_server.ws.messages.RegisterResponse;

@Stateless
@WebService(
		portName = "Name_Server_WS_Port",
        serviceName = "Name_Server_WS")
public class Name_Server_WS {

	// Private attributes
	private ServerDaoImpl serverDao;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Name_Server_WS() {
		serverDao = new ServerDaoImpl();
	}
	
	/**
	 * Registers slave server
	 */
	@WebMethod
	public RegisterResponse register(@WebParam(name="registerRequest") RegisterRequest registerRequest){
		// Execute query
		boolean result = serverDao.registerServer(registerRequest.getIp(), registerRequest.getPort(), registerRequest.getProcessor_name());
		
		// Prepare response
		RegisterResponse response = new RegisterResponse();
		response.setRegistered(result);
		return response;
	}
	
	/**
	 * Gets available slave servers
	 */
	@WebMethod
	public GetAvailableSlavesResponse getAvailableSlaves(){
		// Execute query
		List<Servers> servers = serverDao.getAvailableServers();
		
		// Prepare response
		List<AvailableSlave> availableSlaves = new ArrayList<AvailableSlave>();
		for(Servers s : servers) {
			AvailableSlave as = new AvailableSlave();
			as.setId(s.getId());
			as.setIp(s.getIp());
			as.setPort(s.getPort());
			as.setProcessor_name(s.getProcessor_name());
			availableSlaves.add(as);
		}
		GetAvailableSlavesResponse response = new GetAvailableSlavesResponse();
		response.setAvailableSlaves(availableSlaves);
		return response;
	}
	
	/**
	 * Deletes locked slave server
	 */
	@WebMethod
	public void deleteLockedSlave(@WebParam(name="deleteLockedSlaveRequest") DeleteLockedSlaveRequest deleteLockedSlaveRequest){
		//Execute query
		serverDao.deleteLockedServer(deleteLockedSlaveRequest.getId());
	}
	
}
