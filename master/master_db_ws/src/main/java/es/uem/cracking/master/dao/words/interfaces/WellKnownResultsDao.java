package es.uem.cracking.master.dao.words.interfaces;

import java.util.List;


/**
 * 
 * Well known results DAO interface
 * 
 * @author egrande
 * 
 */
public interface WellKnownResultsDao<K, E> {
	boolean updateWellKnownResults(K clearPass);
	List<E> get();
	void persist(E entity);
	void merge(E entity);
	E findById(K id);
}
