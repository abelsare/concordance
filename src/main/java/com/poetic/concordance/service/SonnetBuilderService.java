package com.poetic.concordance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.poetic.concordance.model.Line;
import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.model.SonnetDataStore;
import com.poetic.concordance.model.WordInformation;

/**
 * Builds the sonnet data read by {@link SonnetReaderService} and creates {@link SonnetDataStore}.
 * The data is made available in a format that makes search easy
 * @author Adwait
 *
 */
public class SonnetBuilderService {
	
	private SonnetDataStore dataStore = SonnetDataStore.getInstance();
	
	/**
	 * Creates a {@link SonnetDataStore} from the list of sonnets
	 * @param sonnets list of sonnets read from the input location
	 */
	public void buildSonnetData(List<Sonnet> sonnets) {
		//Build a sonnet dictionary keyed by sonnet number
		Map<Integer, Sonnet> sonnetDictionary = buildSonnetDictionary(sonnets);
		//Set it on the data store
		dataStore.setSonnetDictionary(sonnetDictionary);
		//Build a word dictionary keyed by each word in the sonnet
		Map<String, List<WordInformation>> wordDictionary = buildWordDictionary(sonnets);
		//Set it on the data store
		dataStore.setWordDictionary(wordDictionary);
	}
	
	private Map<Integer, Sonnet> buildSonnetDictionary(List<Sonnet> sonnets) {
		Map<Integer, Sonnet> sonnetDictionary = new HashMap<Integer, Sonnet>();
		for(Sonnet sonnet : sonnets) {
			sonnetDictionary.put(sonnet.getNumber(), sonnet);
		}
		return sonnetDictionary;
	}
	
	private Map<String, List<WordInformation>> buildWordDictionary(List<Sonnet> sonnets) {
		Map<String, List<WordInformation>> wordDictionary = new HashMap<String, List<WordInformation>>();
		for(Sonnet sonnet : sonnets) {
			//Loop through each sonnet
			for(int i=0; i<sonnet.getLines().size(); i++) {
				//Parse each line of the sonnet
				Line line = sonnet.getLines().get(i);
				String lineText = line.getWords();
				//Tokenize the line to get the words
				List<String> words = tokenizeLine(lineText);
				for(String word: words) {
					//Check if the word is already present in the dictionary
					List<WordInformation> existingWordEntries = wordDictionary.get(word.toLowerCase());
					if(existingWordEntries == null) {
						//This means it is a new entry in the map
						List<WordInformation> newWordEntries = processNewWordEntry(sonnet.getNumber(), i+1);
						//Add the new wordInformation to the dictionary
						wordDictionary.put(word.toLowerCase(), newWordEntries);
					} else {
						processExistingWordEntry(sonnet.getNumber(), i+1, existingWordEntries);	
					}
				}
			}
		}
		return wordDictionary;
	}

	private void processExistingWordEntry(int sonnetNumber, int currentLineNumber, List<WordInformation> existingWordEntries) {
		WordInformation requiredWordInformation = null;
		//Get the wordInformation corresponding to the current sonnet
		for(WordInformation existingWordEntry : existingWordEntries) {
			if(existingWordEntry.getSonnetNumber() == sonnetNumber) {
				requiredWordInformation = existingWordEntry;
				break;
			}
		}
		if(requiredWordInformation == null) {
			//A word is repeated in different sonnets. Create new wordInformation and add to the
			//existing entries list
			WordInformation newWordInformation = createWordInformation(sonnetNumber, currentLineNumber);
			existingWordEntries.add(newWordInformation);
		} else {
			//A word is repeated in the same sonnet. Update the list of its lines
			requiredWordInformation.getLineNumbers().add(currentLineNumber);
		}
	}

	private List<WordInformation> processNewWordEntry(int sonnetNumber, int currentLineNumber) {
		List<WordInformation> newWordEntries = new ArrayList<WordInformation>();
		//Create a new wordInformation object and add it to the dictionary
		WordInformation wordInformation = createWordInformation(sonnetNumber, currentLineNumber);
		newWordEntries.add(wordInformation);
		return newWordEntries;
	}

	private WordInformation createWordInformation(int sonnetNumber, int currentLineNumber) {
		Set<Integer> matchingLineNumbers = new HashSet<Integer>();
		//Set the current line number as matching line for this word
		matchingLineNumbers.add(currentLineNumber);
		WordInformation wordInformation = new WordInformation();
		wordInformation.setSonnetNumber(sonnetNumber);
		wordInformation.setLineNumbers(matchingLineNumbers);
		return wordInformation;
	}
	
	private List<String> tokenizeLine(String lineText) {
		List<String> tokenizedWords = new ArrayList<String>();
		StringTokenizer lineTokenizer = new StringTokenizer(lineText);
		while(lineTokenizer.hasMoreTokens()) {
			//Remove characters such as ",:;." from the word
			tokenizedWords.add(sanitize(lineTokenizer.nextToken()));
		}
		return tokenizedWords;
	}
	
	private String sanitize(String word) {
		final String regex = "[,:;.!']+";
		return word.replaceAll(regex, "").trim();
	}
}