package com.poetic.concordance.model;


/**
 * Represents a line in a sonnet. Consists of words that are strings
 * @author Adwait
 *
 */
public class Line {

	private String words;

	/**
	 * Returns words of this line
	 * @return words
	 */
	public String getWords() {
		return words;
	}

	/**
	 * Inject words in the line
	 * @param words
	 */
	public void setWords(String words) {
		this.words = words;
	}
	
	
}
