package es.uem.cracking.master.dao.words;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uem.cracking.master.dao.words.interfaces.DictionaryDao;
import es.uem.cracking.master.jpa.Dictionary_Words;

/**
 * 
 * Dictionary DAO implementation
 * 
 * @author egrande
 * 
 */
public class DictionaryDaoImpl implements DictionaryDao<String,Dictionary_Words> {

	/** Entity manager factory */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("master_server_pu");
	
	/** Entity manager */
    private EntityManager entityManager;
    
    
    // Public methods
    
    /**
     * Public constructor
     */
    public DictionaryDaoImpl() {
    	entityManager = factory.createEntityManager();
    }
    
    
    // Override methods
    
	@SuppressWarnings("unchecked")
	/**
	 * Overrides getWordsRange method
	 */
	@Override
	public List<String> getWordsRange(String includeFromWord,	String includeToWord) {
		List<String> words = new ArrayList<String>();
		
		// Get words range
		try {
			List<Dictionary_Words> words_tmp = (List<Dictionary_Words>) entityManager.createQuery("select d from Dictionary_Words d where d.word >= :includeFromWord and d.word <= :includeToWord")
																.setParameter("includeFromWord", includeFromWord)
																.setParameter("includeToWord", includeToWord)
																.getResultList();
			if(words_tmp != null) {
				for(Dictionary_Words tmp : words_tmp ) {
					words.add(tmp.getWord());
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		// Return
		return words;
	}

	/**
	 * Overrides getWord method
	 */
	@Override
	public String getWord(long positionWithinOfDictionary) {
		String word = null;
		
		// Get word ----> select word from (select ROWNUM rn, word from dictionary_words d1) where rn=1;
		try {
			word = (String) entityManager.createNativeQuery("select word from (select ROWNUM rn, word from dictionary_words d1) where rn=?1")
															.setParameter(1, positionWithinOfDictionary)
															.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		// Return
		return word;
	}

	/**
	 * Overrides getTotalWordsWithinOfDictionary method
	 */
	@Override
	public long getTotalWordsWithinOfDictionary() {
		long result = 0;
		
		// Get total words
		try {
			result = (long) entityManager.createQuery("select count(d) from Dictionary_Words d").getSingleResult();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		// Return
		return result;
	}

}
