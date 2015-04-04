package es.uem.cracking.master.dao.words.interfaces;

import java.util.List;

/**
 * 
 * Dictionary DAO interface
 * 
 * @author egrande
 * 
 */
public interface DictionaryDao<K, E>  {
	List<String> getWordsRange(K includeFromWord, K includeToWord);
	String getWord(long positionWithinOfDictionary);
	long getTotalWordsWithinOfDictionary();
}
