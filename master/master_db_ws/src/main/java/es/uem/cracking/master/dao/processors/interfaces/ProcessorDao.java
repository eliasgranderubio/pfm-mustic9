package es.uem.cracking.master.dao.processors.interfaces;

import java.util.List;

/**
 * 
 * Processors DAO interface
 * 
 * @author egrande
 * 
 */
public interface ProcessorDao<K, E> {
	boolean updateProcessorPerformance(K processorName, long totalWordsProcessed, long totalTimeProcessing);
	List<E> get();
	void persist(E entity);
	void merge(E entity);
	E findById(K id);
}
