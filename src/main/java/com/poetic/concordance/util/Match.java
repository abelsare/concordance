package com.poetic.concordance.util;

import com.poetic.concordance.model.Line;

/**
 * Holds line that contains the matching word and the line that is above/below the matching line 
 * @author Adwait
 *
 */
public class Match {
	
	private Line matchingLine;
	private Line surroundingLine;
	
	/**
	 * Return the line that contains the search term
	 * @return line containing the search term
	 */
	public Line getMatchingLine() {
		return matchingLine;
	}
	
	/**
	 * Inject the line that contains the search term
	 * @param matchingLine line that has the search term
	 */
	public void setMatchingLine(Line matchingLine) {
		this.matchingLine = matchingLine;
	}
	
	/**
	 * Line that is either above or below the matching line. If the matching line is the last line
	 * in the sonnet then the line above it becomes a surrounding line. Otherwise it is always the
	 * line below the matching line
	 * @return line above/below the matching line
	 */
	public Line getSurroundingLine() {
		return surroundingLine;
	}
	
	/**
	 * Inject the surrounding line
	 * @param sorroundingLine
	 */
	public void setSurroundingLine(Line surroundingLine) {
		this.surroundingLine = surroundingLine;
	}
	
}
