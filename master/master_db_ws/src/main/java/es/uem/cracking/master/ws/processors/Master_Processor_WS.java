package es.uem.cracking.master.ws.processors;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import es.uem.cracking.master.dao.processors.ProcessorOfflineDaoImpl;
import es.uem.cracking.master.dao.processors.ProcessorOnlineDaoImpl;
import es.uem.cracking.master.jpa.Processors_Performance_Offline;
import es.uem.cracking.master.jpa.Processors_Performance_Online;
import es.uem.cracking.master.ws.processors.messages.Processor;
import es.uem.cracking.master.ws.processors.messages.ProcessorsPerformanceStatisticsResponse;
import es.uem.cracking.master.ws.processors.messages.UpdateProcessorStatisticsRequest;
import es.uem.cracking.master.ws.processors.messages.UpdateProcessorStatisticsResponse;

/**
 * 
 * Master processor web service
 * 
 * @author egrande
 *
 */
@Stateless
@WebService(
		portName = "Master_Processor_WS_Port",
        serviceName = "Master_Processor_WS")
public class Master_Processor_WS {

	// Private attributes
	private ProcessorOfflineDaoImpl processorOfflineDao;
	private ProcessorOnlineDaoImpl processorOnlineDao;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public Master_Processor_WS() {
		processorOfflineDao = new ProcessorOfflineDaoImpl();
		processorOnlineDao  = new ProcessorOnlineDaoImpl();
	}
	
	/**
	 * Updates processor offline statistics
	 */
	@WebMethod
	public UpdateProcessorStatisticsResponse updateProcessorOfflineStatistics(@WebParam(name="updateProcessorStatisticsRequest") UpdateProcessorStatisticsRequest updateProcessorStatisticsRequest){
		// Execute query
		boolean result = processorOfflineDao.updateProcessorPerformance(updateProcessorStatisticsRequest.getProcessorName(), updateProcessorStatisticsRequest.getTotalWordsProcessed(), updateProcessorStatisticsRequest.getTotalTimeProcessing());
	
		// Prepare response
		UpdateProcessorStatisticsResponse response = new UpdateProcessorStatisticsResponse();
		response.setUpdated(result);
		return response;
	}
	
	/**
	 * Updates processor online statistics
	 */
	@WebMethod
	public UpdateProcessorStatisticsResponse updateProcessorOnlineStatistics(@WebParam(name="updateProcessorStatisticsRequest") UpdateProcessorStatisticsRequest updateProcessorStatisticsRequest){
		// Execute query
		boolean result = processorOnlineDao.updateProcessorPerformance(updateProcessorStatisticsRequest.getProcessorName(), updateProcessorStatisticsRequest.getTotalWordsProcessed(), updateProcessorStatisticsRequest.getTotalTimeProcessing());
	
		// Prepare response
		UpdateProcessorStatisticsResponse response = new UpdateProcessorStatisticsResponse();
		response.setUpdated(result);
		return response;
	}
	
	/**
	 * Offline processors performance statistics
	 */
	@WebMethod
	public ProcessorsPerformanceStatisticsResponse offlineProcessorsPerformanceStatistics() {
		// Execute query
		List<Processors_Performance_Offline> processorsJPA = processorOfflineDao.get();
		
		// Prepare response
		List<Processor> processors = new ArrayList<Processor>();
		if(processorsJPA != null) {
			for( Processors_Performance_Offline p : processorsJPA ) {
				Processor tmp = new Processor();
				tmp.setProcessorName(p.getProcessorName());
				tmp.setTotalWordsProcessed(p.getTotalWordsProcessed());
				tmp.setTotalTimeProcessing(p.getTotalTimeProcessing());
				tmp.setWordsPerMinute(p.getWordsPerMinute());
				processors.add(tmp);
			}
		}
	
		// Return
		ProcessorsPerformanceStatisticsResponse response = new ProcessorsPerformanceStatisticsResponse();
		response.setProcessors(processors);
		return response;
	}
	
	/**
	 * Online processors performance statistics
	 */
	@WebMethod
	public ProcessorsPerformanceStatisticsResponse onlineProcessorsPerformanceStatistics() {
		// Execute query
		List<Processors_Performance_Online> processorsJPA = processorOnlineDao.get();
		
		// Prepare response
		List<Processor> processors = new ArrayList<Processor>();
		if(processorsJPA != null) {
			for( Processors_Performance_Online p : processorsJPA ) {
				Processor tmp = new Processor();
				tmp.setProcessorName(p.getProcessorName());
				tmp.setTotalWordsProcessed(p.getTotalWordsProcessed());
				tmp.setTotalTimeProcessing(p.getTotalTimeProcessing());
				tmp.setWordsPerMinute(p.getWordsPerMinute());
				processors.add(tmp);
			}
		}
	
		// Return
		ProcessorsPerformanceStatisticsResponse response = new ProcessorsPerformanceStatisticsResponse();
		response.setProcessors(processors);
		return response;
	}
	
}
