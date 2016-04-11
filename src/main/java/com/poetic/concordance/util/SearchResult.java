package com.poetic.concordance.util;

import java.util.List;

import com.poetic.concordance.model.Line;

/**
 * Contains the lines in which the word is found
 * @author Adwait
 *
 */
public class SearchResult {
	
	private int sonnetNumber;
	private List<Line> matchingLines;
	
	/**
	 * Sonnet number in which the word match occured
	 * @return sonnet number
	 */
	public int getSonnetNumber() {
		return sonnetNumber;
	}
	
	/**
	 * Inject sonnet number in which the word match occured
	 * @param sonnetNumber
	 */
	public void setSonnetNumber(int sonnetNumber) {
		this.sonnetNumber = sonnetNumber;
	}
	
	/**
	 * The line in which the word is present. Also has the line either above/below the matching line
	 * @return matching lines
	 */
	public List<Line> getMatchingLines() {
		return matchingLines;
	}
	
	/**
	 * Inject the matching line where the word is present. Also inject a line which is either above/below the matching line
	 * @param matchingLines
	 */
	public void setMatchingLines(List<Line> matchingLines) {
		this.matchingLines = matchingLines;
	}
	
	
}
