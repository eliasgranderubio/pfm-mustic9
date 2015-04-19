package es.uem.cracking.master_soa.scheduler;

import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.uem.cracking.master.ws.dispatcher.MasterDispatcher;
import es.uem.cracking.master.ws.dispatcher.MasterDispatcher2;


/**
 * 
 * Master SOA dispatch Job
 * 
 * @author egrande
 *
 */
public class MasterSOAJob implements Job {
	
	/** Master SOA dispatcher endpoint */
	// TODO egrande: move this master SOA dispatcher endpoint to a properties file
	public static final String MASTER_SOA_DISPATCHER_ENDPOINT = "http://localhost:7001/soa-infra/services/default/master_soa/Master_Dispatcher";
	

	// Private attributes
	
    private MasterDispatcher masterDispatcher;

    
    // Public methods

    /**
     * Public Job constructor
     */
    public MasterSOAJob() {
    	try { 
    		MasterDispatcher2 masterDispatcher_Service = new MasterDispatcher2(new URL(MASTER_SOA_DISPATCHER_ENDPOINT));
    		masterDispatcher = masterDispatcher_Service.getMasterDispatcherPt();
        } catch (Exception e) {
            System.err.println("Inaccessible '" + MASTER_SOA_DISPATCHER_ENDPOINT + "' WSDL.");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Job execution
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	masterDispatcher.dispatch(); 
    }

}
