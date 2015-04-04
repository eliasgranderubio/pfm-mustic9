package es.uem.cracking.master.dao.processors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.master.dao.processors.interfaces.ProcessorDao;
import es.uem.cracking.master.jpa.Processors_Performance_Online;


/**
 * 
 * Processors Online DAO implementation
 * 
 * @author egrande
 * 
 */
public class ProcessorOnlineDaoImpl implements ProcessorDao<String,Processors_Performance_Online> {

	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("master_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
	
	
	// Public methods
	
	/**
	 * Public constructor
	 */
	public ProcessorOnlineDaoImpl() {
		entityManager = factory.createEntityManager();
	}
	
    /**
	 * Overrides updateProcessorPerformance method
	 */
	@Override
	public synchronized boolean updateProcessorPerformance(String processorName, long totalWordsProcessed, long totalTimeProcessing) {
		Processors_Performance_Online processor = findById(processorName);

		// Update
		if(processor == null) {
			processor = new Processors_Performance_Online();
			processor.setProcessorName(processorName);
			if(totalWordsProcessed > 0) {
				processor.setTotalWordsProcessed(totalWordsProcessed);
			}
			if(totalTimeProcessing > 0) {
				processor.setTotalTimeProcessing(totalTimeProcessing);
			}
			long minutes = (processor.getTotalTimeProcessing() / 1000) / 60;
			if(minutes > 0) {
				processor.setWordsPerMinute(totalWordsProcessed / minutes);
			}
			// Create
			entityManager.getTransaction().begin();
			try {
				persist(processor);
				entityManager.getTransaction().commit();
				return true;
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
				return false;
			}
		}
		else {
			// Check values
			if(totalWordsProcessed > 0) {
				processor.setTotalWordsProcessed(processor.getTotalWordsProcessed() + totalWordsProcessed);
			}
			if(totalTimeProcessing > 0) {
				processor.setTotalTimeProcessing(processor.getTotalTimeProcessing() + totalTimeProcessing);
			}
			long minutes = (processor.getTotalTimeProcessing() / 1000) / 60;
			if(minutes > 0) {
				processor.setWordsPerMinute(totalWordsProcessed / minutes);
			}
			// Merge
			entityManager.getTransaction().begin();
			try {
				merge(processor);
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
	public synchronized List<Processors_Performance_Online> get() {
		List<Processors_Performance_Online> processors = new ArrayList<Processors_Performance_Online>();
		
		// Get list
		entityManager.getTransaction().begin();
		try {
			processors = (List<Processors_Performance_Online>) entityManager.createQuery("select p from Processors_Performance_Online p").getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
		}
		
		// Return
		return processors;
	}
	
	/**
	 * Overrides persist method
	 */
	@Override
	public void persist(Processors_Performance_Online entity) {
		entityManager.persist(entity);
	}

	/**
	 * Overrides merge method
	 */
	@Override
	public void merge(Processors_Performance_Online entity) {
		entityManager.merge(entity);
	}

	/**
	 * Overrides findById method
	 */
	@Override
	public Processors_Performance_Online findById(String id) {
		return entityManager.find(Processors_Performance_Online.class, id);
	}

}
