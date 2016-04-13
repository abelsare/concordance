package com.poetic.concordance.model;

import java.util.Set;

/**
 * Holds the sonnet and line information for a given word
 * @author Adwait
 *
 */
public class WordInformation {
	
	private int sonnetNumber;
	private Set<Integer> lineNumbers;
	
	/**
	 * Returns the sonnet number in which the word is found
	 * @return the sonnet number
	 */
	public int getSonnetNumber() {
		return sonnetNumber;
	}
	
	/**
	 * Inject the sonnet number in which the word is found
	 * @param sonnetNumber sonnet number
	 */
	public void setSonnetNumber(int sonnetNumber) {
		this.sonnetNumber = sonnetNumber;
	}
	
	/**
	 * Returns the line numbers in a sonnet in which the word is found
	 * @return the line numbers
	 */
	public Set<Integer> getLineNumbers() {
		return lineNumbers;
	}
	
	/**
	 * Inject the line numbers in which this word is found. Set ensures unique line numbers for repeated words
	 * in the same line
	 * @param lineNumbers line numbers
	 */
	public void setLineNumbers(Set<Integer> lineNumbers) {
		this.lineNumbers = lineNumbers;
	}

}
