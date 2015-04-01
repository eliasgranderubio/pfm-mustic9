package es.uem.cracking.slave.utils;


/**
 * 
 * Singleton slave recorder
 * 
 * @author egrande
 *
 */
public class SlaveRecorder {

	// Private attributes
	
	private static SlaveRecorder INSTANCE = null;
	private static boolean registered = false;
	 
    
	// Public methods
 
	/**
	 * Returns Singleton instance
	 */
    public synchronized static SlaveRecorder getInstance() {
        if (INSTANCE == null) {
        	INSTANCE = new SlaveRecorder();
        }
        return INSTANCE;
    }
    
    
	/**
	 * Registers this slave in the name server and returns true if was possible
	 */
    public synchronized boolean registerMyselfInTheNameServer() {
    	if(!registered) {
    		registered = SlaveUtils.registerMyselfInTheNameServer();
    	}
    	return registered;
    }
    
    /**
     * This slave is working so it is not registered in the name server
     */
    public synchronized void iAmWorking() {
    	registered = false;
    }
	
    
    // Private methods
    
    /**
     * Private constructor 
     */
    private SlaveRecorder(){}
    
}
