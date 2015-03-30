package es.uem.cracking.name_server.dao.interfaces;

import java.util.List;

/**
 * 
 * Server DAO interface
 * 
 * @author egrande
 * 
 */
public interface ServerDao<K, E> {
	boolean registerServer(String ip, int port, String processor_name);
	public List<E> getAvailableServers();
	public void deleteLockedServer(int id);
	void persist(E entity);
	E merge(E entity);
	void remove(E entity);
	E findById(K id);
}
