package com.poetic.concordance.model;

import java.util.List;

/**
 * Represents a line in a sonnet. Consists of words that are strings
 * @author Adwait
 *
 */
public class Line {

	private List<String> words;

	/**
	 * Returns words of this line
	 * @return words
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * Inject words in the line
	 * @param words
	 */
	public void setWords(List<String> words) {
		this.words = words;
	}
	
	
}
