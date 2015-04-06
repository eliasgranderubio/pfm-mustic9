package es.uem.cracking.master.dao.attacks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
     * Overrides getAttackWindowIdsToSend method
     */
    @SuppressWarnings({ "unchecked" })
	public List<Long> getAttackWindowIdsToSend(long activeAttackId, Integer maxWindows) {
    	List<Long> ids = new ArrayList<Long>();
    	long halfHour = 30 * 60 * 1000;
    	Timestamp st = new Timestamp(Calendar.getInstance().getTimeInMillis() - halfHour);
    	
    	// Execute query
    	List<Attack_Windows> aws = (List<Attack_Windows>) entityManager.createQuery("select a from Attack_Windows a where a.activeAttackId = :activeAttackId and (a.processed = :procesedFalse or (a.processed = :procesedTrue and a.sentTimestamp <= :st))")
															    			.setParameter("activeAttackId", activeAttackId)
															    			.setParameter("procesedFalse", false)
															    			.setParameter("procesedTrue", true)
															    			.setParameter("st", st)
															    			.getResultList();
    	
    	// Prepare response
    	if(aws!=null && aws.size()>0) {
    		int max = (maxWindows!=null && maxWindows.intValue()>0 && maxWindows.intValue()<aws.size()) ? maxWindows.intValue() : aws.size();
    		for(int i=0 ; i < max ; i++) {
    			ids.add(aws.get(i).getId());
    		}
    	}
    	return ids;
    }
    
    /**
     * Overrides updateAttackWindow method
     */
    public boolean updateAttackWindow(Attack_Windows entity){
    	if(entity!=null && entity.getId() > 0) {
    		entityManager.getTransaction().begin();
			try {
				merge(entity);
				entityManager.getTransaction().commit();
				return true;
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace(System.err);
			}
    	}
    	return false;
    }
    
    /**
     * Overrides removeById method
     */
    public void removeById(Long id) {
    	if(id!=null) {
    		Attack_Windows a = findById(id);
    		if(a!=null){
    			entityManager.getTransaction().begin();
    			try {
    				remove(a);
    				entityManager.getTransaction().commit();
    			} catch(Exception e) {
    				entityManager.getTransaction().rollback();
    				e.printStackTrace(System.err);
    			}
    		}
    	}
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
