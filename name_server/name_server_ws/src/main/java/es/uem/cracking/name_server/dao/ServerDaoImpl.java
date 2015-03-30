package es.uem.cracking.name_server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.name_server.dao.interfaces.ServerDao;
import es.uem.cracking.name_server.jpa.Servers;

/**
 * 
 * Servers DAO
 * 
 * @author egrande
 *
 */
public class ServerDaoImpl implements ServerDao<Long, Servers> {
	
	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("name_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
	
	
	// Public methods
    
    /**
     * Public constructor
     */
    public ServerDaoImpl(){
    	entityManager = factory.createEntityManager();
    }
	
	/**
	 * Registers server into database
	 */
	public synchronized boolean registerServer(String ip, int port, String processor_name) {
		// Prepare server
		Servers server = new Servers();
		server.setIp(ip);
		server.setPort(port);
		server.setProcessor_name(processor_name);
		
		// Persist server
		entityManager.getTransaction().begin();
		try {
			persist(server);
			entityManager.getTransaction().commit();
			return true;
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
			return false;
		}
	}
	
	/**
	 * Gets available servers
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<Servers> getAvailableServers(){
		List<Servers> servers = new ArrayList<Servers>();
		
		// Get list
		entityManager.getTransaction().begin();
		try {
			List<Servers> tmp = (List<Servers>) entityManager.createQuery("select s from Servers s").getResultList();
			for(Servers s : tmp){
				if(!s.isLocked()) {
					s.setLocked(true);
					merge(s);
					servers.add(s);
				}
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			servers = new ArrayList<Servers>();
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
		}
		return servers;
	}

	/**
	 * Deletes locked server
	 */
	public synchronized void deleteLockedServer(int id) {
		Servers s = findById((long) id);
		
		// Remove
		if(s != null && s.isLocked()) {
			entityManager.getTransaction().begin();
			try {			
				remove(s);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
			}
		}
	}

	
	// Override methods
	
	/**
	 * Overrides persist
	 */
	@Override
	public void persist(Servers entity) {
		entityManager.persist(entity);
	}

	/**
	 * Overrides merge
	 */
	@Override
	public Servers merge(Servers entity) {
		return entityManager.merge(entity);
	}

	/**
	 * Overrides remove
	 */
	@Override
	public void remove(Servers entity) {
		entityManager.remove(entity);
	}

	/**
	 * Overrides findById
	 */
	@Override
	public Servers findById(Long id) {
		return entityManager.find(Servers.class, id);
	}
	
}
