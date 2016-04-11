package com.poetic.concordance.model;

import java.util.List;

/**
 * Holds the sonnet and line information for a given word
 * @author Adwait
 *
 */
public class WordInformation {
	
	private int sonnetNumber;
	private List<Integer> lineNumbers;
	
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
	public List<Integer> getLineNumbers() {
		return lineNumbers;
	}
	
	/**
	 * Inject the line numbers in which this word is found
	 * @param lineNumbers line numbers
	 */
	public void setLineNumbers(List<Integer> lineNumbers) {
		this.lineNumbers = lineNumbers;
	}

}
