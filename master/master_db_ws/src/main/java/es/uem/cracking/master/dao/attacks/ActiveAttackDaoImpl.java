package es.uem.cracking.master.dao.attacks;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.master.dao.attacks.interfaces.ActiveAttackDao;
import es.uem.cracking.master.jpa.Active_Attacks;
import es.uem.cracking.master.jpa.Attack_Windows;


/**
 * 
 * Active attack DAO implementation
 * 
 * @author egrande
 * 
 */
public class ActiveAttackDaoImpl implements ActiveAttackDao<Long,Active_Attacks> {

	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("master_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
    
    
	// Public methods
	
	/**
	 * Public constructor
	 */
    public ActiveAttackDaoImpl(){
    	entityManager = factory.createEntityManager();
    }
    
    /**
     * Gets all active attacks Id
     */
    @SuppressWarnings("unchecked")
	public List<Long> getAllActiveAttacksId() {
    	List<Long> ids = new ArrayList<Long>();
    	
    	// Get list
    	entityManager.getTransaction().begin();
		try {
			List<Active_Attacks> list = (List<Active_Attacks>) entityManager.createQuery("select a from Active_Attacks a").getResultList();
			if(list!=null){
				for(Active_Attacks aa : list ){
					ids.add(aa.getId());
				}
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
		}
		
		// Return
		return ids;
    }
    
	/**
	 * Overrides createFullAttack method
	 */
	@Override
	public void createFullAttack(AttackWindowsDaoImpl attackWindowsDao, String tool, String attackType, String remoteUser, String remoteIp, Integer remotePort,
								 String extraParam, String hashToCrack, String notifyToEmail, long windowSize, long totalWords) {
		Active_Attacks attack = prepareAttack(tool,attackType,remoteUser,remoteIp,remotePort,extraParam,hashToCrack,notifyToEmail);
		
		// Persist attack
		boolean firstStep = false;
		entityManager.getTransaction().begin();
		try {
			persist(attack);
			entityManager.flush();
			entityManager.getTransaction().commit();
			firstStep = true;
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace(System.err);
		}
		
		// Continues with attack windows
		if(firstStep) {
			List<Attack_Windows> windows = prepareAttackWindows(attack, windowSize, totalWords);
			if(windows != null && windows.size() > 0) {
				attackWindowsDao.persist(windows);
			}
		}
	}

	/**
	 * Overrides removeFullAttack method
	 */
	@Override
	public void removeFullAttack(Long id) {
		if(id != null){
			entityManager.getTransaction().begin();
			try {
				entityManager.createNativeQuery("delete from ATTACK_WINDOWS where active_attack_id=?")
			    								.setParameter(1, id.longValue())
			    								.executeUpdate();
				entityManager.createNativeQuery("delete from ACTIVE_ATTACKS where id=?")
												.setParameter(1, id.longValue())
												.executeUpdate();
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
	public void persist(Active_Attacks entity) {
		entityManager.persist(entity);
	}

	/**
	 * Overrides remove method
	 */
	@Override
	public void remove(Active_Attacks entity) {
		entityManager.remove(entity);
	}

	/**
	 * Overrides findById method
	 */
	@Override
	public Active_Attacks findById(Long id) {
		return entityManager.find(Active_Attacks.class,id);
	}

	
	// Private methods

	/**
	 * Prepares attack to persist
	 */
	private Active_Attacks prepareAttack(String tool, String attackType, String remoteUser, String remoteIp, Integer remotePort,
			 			  				 String extraParam, String hashToCrack, String notifyToEmail) {
		Active_Attacks attack = new Active_Attacks();
		if(tool!=null) {
			attack.setTool(tool);
		}
		if(attackType != null) {
			attack.setAttackType(attackType);
		}
		if(remoteUser != null) {
			attack.setRemoteUser(remoteUser);
		}
		if(remoteIp != null) {
			attack.setRemoteIp(remoteIp);
		}
		if(remotePort != null) {
			attack.setRemotePort(remotePort);
		}
		if(extraParam != null) {
			attack.setExtraParam(extraParam);
		}
		if(hashToCrack != null){
			attack.setHashToCrack(hashToCrack);
		}
		if(notifyToEmail != null) {
			attack.setNotifyToEmail(notifyToEmail);
		}
		return attack;
	}

	/**
	 * Prepares attack windows to persist
	 */
	private List<Attack_Windows> prepareAttackWindows(Active_Attacks attack, long windowSize, long totalWords) {
		List<Long> positions = calculatePositions(windowSize,totalWords);
		
		// Prepare attack windows
		List<Attack_Windows> windows = new ArrayList<Attack_Windows>();
		for(int i = 0; i< positions.size(); i+=2) {
			String firstDictionaryWord = (String) entityManager.createNativeQuery("select word from (select ROWNUM rn, word from dictionary_words d1) where rn=?1")
																	.setParameter(1, positions.get(i))
																	.getSingleResult();
			String lastDictionaryWord = (String) entityManager.createNativeQuery("select word from (select ROWNUM rn, word from dictionary_words d1) where rn=?1")
																	.setParameter(1, positions.get(i+1))
																	.getSingleResult();			
			Attack_Windows window = new Attack_Windows();
			window.setActiveAttackId(attack.getId());
			window.setFirstDictionaryWord(firstDictionaryWord);
			window.setLastDictionaryWord(lastDictionaryWord);
			windows.add(window);
		}
		
		// Return
		return windows;
	}
	
	/**
	 * Calculate positions within dictionary
	 */
	private static List<Long> calculatePositions(long windowSize, long totalWords) {
		List<Long> positions = new ArrayList<Long>();
		long i = 1;
		for(; i < totalWords;) {
			positions.add(i);
			i+=(windowSize-1);
			if(i<totalWords) {
				positions.add(i);
				i++;
			}
		}
		if(i>totalWords){
			positions.add(totalWords);
		}
		return positions;
	}
	
}
