package es.uem.cracking.master.dao.attacks.interfaces;

import java.util.List;

/**
 * 
 * Attack Windows DAO interface
 * 
 * @author egrande
 * 
 */
public interface AttackWindowsDao<K, E> {
	List<K> getAttackWindowIdsToSend(long activeAttackId, Integer maxWindows);
	boolean updateAttackWindow(E entity);
	void removeById(K id);
	void persist(List<E> entities);
	void remove(List<E>  entities);
	void persist(E entity);
	E merge(E entity);
	void remove(E entity);
	E findById(K id);
}
