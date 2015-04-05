package es.uem.cracking.master.dao.attacks;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.master.dao.attacks.interfaces.AttackWindowsDao;
import es.uem.cracking.master.jpa.Attack_Windows;


/**
 * 
 * Attack Windows DAO implementation
 * 
 * @author egrande
 * 
 */
public class AttackWindowsDaoImpl implements AttackWindowsDao<Long,Attack_Windows> {

	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("master_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
	
    
	// Public methods
	
	/**
	 * Public constructor
	 */
    public AttackWindowsDaoImpl() {
    	entityManager = factory.createEntityManager();
    }
    
    /**
     * Overrides persist method
     */
	@Override
	public void persist(List<Attack_Windows> entities) {
		if(entities != null) {
			entityManager.getTransaction().begin();
			try {
				for(Attack_Windows entity : entities) {
					persist(entity);
				}
				entityManager.getTransaction().commit();
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
			}
		}
	}

    /**
     * Overrides remove method
     */
	@Override
	public void remove(List<Attack_Windows> entities) {
		if(entities != null) {
			entityManager.getTransaction().begin();
			try {
				for(Attack_Windows entity : entities) {
					remove(entity);
				}
				entityManager.getTransaction().commit();
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
			}
		}
	}
	
    /**
     * Overrides persist method
     */
	@Override
	public void persist(Attack_Windows entity) {
		entityManager.persist(entity);
	}

    /**
     * Overrides merge method
     */
	@Override
	public Attack_Windows merge(Attack_Windows entity) {
		return entityManager.merge(entity);
	}

    /**
     * Overrides remove method
     */
	@Override
	public void remove(Attack_Windows entity) {
		entityManager.remove(entity);
	}

    /**
     * Overrides findById method
     */
	@Override
	public Attack_Windows findById(Long id) {
		return entityManager.find(Attack_Windows.class,id);
	}

}
