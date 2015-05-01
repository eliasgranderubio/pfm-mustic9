package es.uem.cracking.master;

import java.util.Calendar;


/**
 * Master Helper Implementation
 *
 * @author egrande
 */
public class MasterHelperImpl implements MasterHelperInterface {
    
    /**
     * Public constructor
     */
    public MasterHelperImpl() {
        super();
    }

    /**
     * Gets dateTime in milliseconds
     */
    public long getDateTimeInMilliseconds() {
        return Calendar.getInstance().getTimeInMillis();
    }
    
}
