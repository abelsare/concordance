package com.poetic.concordance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.poetic.concordance.model.Line;
import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.model.SonnetDataStore;
import com.poetic.concordance.model.WordInformation;
import com.poetic.concordance.util.Match;
import com.poetic.concordance.util.SearchResult;

/**
 * Searches the given word in the {@link SonnetDataStore}
 * @author Adwait
 *
 */
public class SonnetSearchService {
	
	private SonnetDataStore dataStore = SonnetDataStore.getInstance();

	/**
	 * Returns the results corresponding to the search term from all the sonnets
	 * @param word the search term
	 * @return list of search results matching the word being searched
	 */
	public List<SearchResult> search(String word) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		Map<Integer, Sonnet> sonnetDictionary = dataStore.getSonnetDictionary();
		//Get the word information from the data store
		List<WordInformation> wordEntries = dataStore.getWordDictionary().get(word);
		//For each entry in the word information list, get the matching line from the
		//sonnet dictionary.
		if(wordEntries != null) {
			//The search term is present in the dictionary
			for(WordInformation wordInformation : wordEntries) {
				//Build and populate the search result
				SearchResult searchResult = new SearchResult();
				searchResult.setSonnetNumber(wordInformation.getSonnetNumber());
				searchResult.setMatches(populateMatchingLines(wordInformation, sonnetDictionary));
				results.add(searchResult);
			}
		}	
		return results;
	}
	
	private List<Match> populateMatchingLines(WordInformation wordInformation, 
			Map<Integer, Sonnet> sonnetDictionary) {
		List<Match> matches = new ArrayList<Match>();
		//Get the required sonnet from the dictionary
		Sonnet currentSonnet = sonnetDictionary.get(wordInformation.getSonnetNumber());
		for(Integer lineNumber : wordInformation.getLineNumbers()) {
			List<Line> sonnetLines = currentSonnet.getLines();
			//Get the corresponding line from the sonnet. Line numbers start from 1 but the entries in the list
			//start from 0. So subtract 1 from the lineNumber to get the correct line
			Line matchingLine = sonnetLines.get(lineNumber - 1);
			Line surroundingLine = null;
			boolean matchOnTheLastLine = false;
			if(lineNumber.equals(currentSonnet.getTotalLines())) {
				//This means that the matching line is the last line of the sonnet
				//So, surrounding line should be the line above it
				surroundingLine = sonnetLines.get(lineNumber - 2);
				matchOnTheLastLine = true;
			} else {
				//Surrounding line will be the line below the matching line
				surroundingLine = sonnetLines.get(lineNumber);
			}
			//Build and collect the matching results
			Match matchResult = new Match();
			matchResult.setMatchingLine(matchingLine);
			matchResult.setSurroundingLine(surroundingLine);
			matchResult.setMatchOnTheLastLine(matchOnTheLastLine);
			matches.add(matchResult);
		}
		
		return matches;
	}
}
