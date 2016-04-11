package com.poetic.concordance.model;

import java.util.List;
import java.util.Map;

/**
 * A singleton datastore that holds all the sonnet data. The data is stored in maps to make retrieval efficient
 * @author Adwait
 *
 */
public class SonnetDataStore {
	
	private Map<Integer, Sonnet> sonnetDictionary;
	private Map<String, List<WordInformation>> wordDictionary;
	
	/**
	 * Returns the sonnet data keyed by the sonnet number
	 * @return map keyed by the sonnet number
	 */
	public Map<Integer, Sonnet> getSonnetDictionary() {
		return sonnetDictionary;
	}
	
	/**
	 * Inject the sonnet dictionary
	 * @param sonnetDictionary
	 */
	public void setSonnetDictionary(Map<Integer, Sonnet> sonnetDictionary) {
		this.sonnetDictionary = sonnetDictionary;
	}
	
	/**
	 * Returns the word dictionary for each word in each sonnet keyed by the word
	 * @return map keyed by the word which holds all information where the word is found
	 */
	public Map<String, List<WordInformation>> getWordDictionary() {
		return wordDictionary;
	}
	
	/**
	 * Injects word dictionary
	 * @param wordDictionary
	 */
	public void setWordDictionary(Map<String, List<WordInformation>> wordDictionary) {
		this.wordDictionary = wordDictionary;
	}
	
	

}
