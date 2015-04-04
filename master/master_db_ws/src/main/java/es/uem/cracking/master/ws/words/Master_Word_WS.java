package es.uem.cracking.master.ws.words;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.master.dao.words.DictionaryDaoImpl;
import es.uem.cracking.master.dao.words.WellKnownResultsDaoImpl;
import es.uem.cracking.master.jpa.Well_Known_Results;
import es.uem.cracking.master.ws.words.messages.GetTotalWordsWithinOfDictionaryResponse;
import es.uem.cracking.master.ws.words.messages.GetWellKnownResultsResponse;
import es.uem.cracking.master.ws.words.messages.GetWordRequest;
import es.uem.cracking.master.ws.words.messages.GetWordResponse;
import es.uem.cracking.master.ws.words.messages.GetWordsRangeRequest;
import es.uem.cracking.master.ws.words.messages.GetWordsRangeResponse;
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
	private DictionaryDaoImpl dictionaryDao;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Master_Word_WS() {
		wellKnownResultsDao = new WellKnownResultsDaoImpl();
		dictionaryDao = new DictionaryDaoImpl();
	}
	
	/**
	 * Gets words range
	 */
	@WebMethod
	public GetWordsRangeResponse getWordsRange(@WebParam(name="getWordsRangeRequest") GetWordsRangeRequest getWordsRangeRequest) {
		List<String> words = new ArrayList<String>();
		//Execute query
		if(getWordsRangeRequest.getIncludeFromWord()!= null && getWordsRangeRequest.getIncludeToWord()!=null){
			words = dictionaryDao.getWordsRange(getWordsRangeRequest.getIncludeFromWord(), getWordsRangeRequest.getIncludeToWord());
		}
		
		// Prepare response
		GetWordsRangeResponse response = new GetWordsRangeResponse();
		response.setWord(words);
		return response;
	}
	
	/**
	 * Gets word
	 */
	@WebMethod
	public GetWordResponse getWord(@WebParam(name="getWordRequest") GetWordRequest getWordRequest) {
		// Execute query
		String word = dictionaryDao.getWord(getWordRequest.getPositionWithinOfDictionary());
		
		// Prepare response
		GetWordResponse response = new GetWordResponse();
		response.setWord(word);
		return response;
	}
	
	/**
	 * Gets total words
	 */
	@WebMethod
	public GetTotalWordsWithinOfDictionaryResponse getTotalWordsWithinOfDictionary() {
		// Execute query
		long count = dictionaryDao.getTotalWordsWithinOfDictionary();
		
		// Prepare response
		GetTotalWordsWithinOfDictionaryResponse response = new GetTotalWordsWithinOfDictionaryResponse();
		response.setTotalWords(count);
		return response;
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
