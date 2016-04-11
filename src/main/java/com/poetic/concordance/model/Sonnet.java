package com.poetic.concordance.model;

import java.util.List;

/**
 * A sonnet in the system. Sonnet consists of line that are made up of words.
 * @author Adwait
 *
 */
public class Sonnet {
	
	private int number;
	private List<Line> lines;
	private int totalLines;
	
	/**
	 * Return the total number of lines in this sonnet
	 * @return lines in the sonnet
	 */
	public int getTotalLines() {
		return totalLines;
	}

	/**
	 * Inject the total lines in the sonnet
	 * @param totalLines
	 */
	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	/**
	 * Return the sonnet serial number
	 * @return sonner number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Inject the sonnet number
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * Return the lines of this sonnet
	 * @return the lines
	 */
	public List<Line> getLines() {
		return lines;
	}
	
	/**
	 * Inject the lines into the sonnet
	 * @param lines
	 */
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	

}
