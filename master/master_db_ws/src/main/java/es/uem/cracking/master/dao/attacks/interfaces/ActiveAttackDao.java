package es.uem.cracking.master.dao.attacks.interfaces;

import java.util.List;

import es.uem.cracking.master.dao.attacks.AttackWindowsDaoImpl;

/**
 * 
 * Active attack DAO interface
 * 
 * @author egrande
 * 
 */
public interface ActiveAttackDao <K, E> {
	List<K> getAllActiveAttacksId();
	void createFullAttack(AttackWindowsDaoImpl attackWindowsDao, String tool, String attackType, String remoteUser, String remoteIp,
			Integer remotePort, String extraParam, String hashToCrack, String notifyToEmail, 
			long windowSize, long totalWords);
	void removeFullAttack(K id);
	void persist(E entity);
	void remove(E entity);
	E findById(K id);
}
