package com.poetic.concordance.util;

import java.util.List;


/**
 * Contains the lines in which the word is found
 * @author Adwait
 *
 */
public class SearchResult {
	
	private int sonnetNumber;
	private List<Match> matches;
	
	/**
	 * Sonnet number in which the word match occurred
	 * @return sonnet number
	 */
	public int getSonnetNumber() {
		return sonnetNumber;
	}
	
	/**
	 * Inject sonnet number in which the word match occurred
	 * @param sonnetNumber
	 */
	public void setSonnetNumber(int sonnetNumber) {
		this.sonnetNumber = sonnetNumber;
	}
	
	/**
	 * The line in which the word is present. Also has the line either above/below the matching line
	 * @return matching lines
	 */
	public List<Match> getMatches() {
		return matches;
	}
	
	/**
	 * Inject the matching line where the word is present. Also inject a line which is either above/below the matching line
	 * @param matchingLines
	 */
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	
}
