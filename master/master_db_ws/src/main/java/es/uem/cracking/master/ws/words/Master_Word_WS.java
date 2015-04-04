package es.uem.cracking.master.ws.words;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.master.dao.words.WellKnownResultsDaoImpl;
import es.uem.cracking.master.jpa.Well_Known_Results;
import es.uem.cracking.master.ws.words.messages.GetWellKnownResultsResponse;
import es.uem.cracking.master.ws.words.messages.UpdateWellKnownResultsRequest;
import es.uem.cracking.master.ws.words.messages.UpdateWellKnownResultsResponse;
import es.uem.cracking.master.ws.words.messages.WellKnownResult;

/**
 * 
 * Master word web service
 * 
 * @author egrande
 *
 */
@Stateless
@WebService(
		portName = "Master_Word_WS_Port",
        serviceName = "Master_Word_WS")
public class Master_Word_WS {

	// Private attributes
	private WellKnownResultsDaoImpl wellKnownResultsDao;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Master_Word_WS() {
		wellKnownResultsDao = new WellKnownResultsDaoImpl();
	}
	
	/**
	 * Updates well known results
	 */
	@WebMethod
	public UpdateWellKnownResultsResponse updateWellKnownResults(@WebParam(name="updateWellKnownResultsRequest") UpdateWellKnownResultsRequest updateWellKnownResultsRequest) {
		// Execute query
		boolean result = wellKnownResultsDao.updateWellKnownResults(updateWellKnownResultsRequest.getClearPass());
		
		// Prepare response
		UpdateWellKnownResultsResponse response = new UpdateWellKnownResultsResponse();
		response.setUpdated(result);
		return response;
	}
	
	/**
	 * Gets well known results
	 */
	@WebMethod
	public GetWellKnownResultsResponse getWellKnownResults() {
		// Execute query
		List<Well_Known_Results> wellKnownResults = wellKnownResultsDao.get();
		
		// Prepare response
		List<WellKnownResult> results = new ArrayList<WellKnownResult>();
		if(wellKnownResults != null) {
			for( Well_Known_Results w : wellKnownResults) {
				WellKnownResult tmp = new WellKnownResult();
				tmp.setClearPass(w.getClearPass());
				tmp.setTimes(w.getTimes());
				results.add(tmp);
			}
		}
		
		// Return
		GetWellKnownResultsResponse response = new GetWellKnownResultsResponse();
		response.setWellKnownResults(results);
		return response;
	}
	
}
