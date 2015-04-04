package es.uem.cracking.master.dao.words;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.master.dao.words.interfaces.WellKnownResultsDao;
import es.uem.cracking.master.jpa.Well_Known_Results;


/**
 * 
 * Well known results DAO implementation
 * 
 * @author egrande
 * 
 */
public class WellKnownResultsDaoImpl implements WellKnownResultsDao<String,Well_Known_Results> {

	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("master_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
    
    
    // Public methods
    
    /**
     * Public constructor
     */
    public WellKnownResultsDaoImpl(){
    	entityManager = factory.createEntityManager();
    }
    
    
    // Override methods
	@Override
	public synchronized boolean updateWellKnownResults(String clearPass) {
		Well_Known_Results result = findById(clearPass);
		
		// Update
		if(result == null) {
			result = new Well_Known_Results();
			result.setClearPass(clearPass);
			result.setTimes(1);
			// Create
			entityManager.getTransaction().begin();
			try {
				persist(result);
				entityManager.getTransaction().commit();
				return true;
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
				return false;
			}
		}
		else {
			result.setTimes(result.getTimes() + 1);
			// Create
			entityManager.getTransaction().begin();
			try {
				merge(result);
				entityManager.getTransaction().commit();
				return true;
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
				return false;
			}
		}
	}

    /**
	 * Overrides get method
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized List<Well_Known_Results> get() {
		List<Well_Known_Results> wellKnownResults = new ArrayList<Well_Known_Results>();
		
		// Get list
		entityManager.getTransaction().begin();
		try {
			wellKnownResults = (List<Well_Known_Results>) entityManager.createQuery("select w from Well_Known_Results w").getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
		}
		
		// Return
		return wellKnownResults;
	}

	/**
	 * Overrides persist method
	 */
	@Override
	public void persist(Well_Known_Results entity) {
		entityManager.persist(entity);
	}

	/**
	 * Overrides merge method
	 */
	@Override
	public void merge(Well_Known_Results entity) {
		entityManager.merge(entity);
	}

	/**
	 * Overrides findById method
	 */
	@Override
	public Well_Known_Results findById(String id) {
		return entityManager.find(Well_Known_Results.class, id);
	}

}
