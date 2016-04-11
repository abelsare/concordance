package com.poetic.concordance.service;

import java.util.List;


import com.poetic.concordance.model.Sonnet;

/**
 * Reads the sonnet input from the given input source. Converts the read input into {@link Sonnet} objects
 * @author Adwait
 *
 */
public interface SonnetReaderService {
	
	/**
	 * Reads the sonnet text from the given input source. Converts all the read input into Sonnet objects.
	 * @param inputSource the input source (directory/ db connection string) where the sonnet input lives
	 * @return a list of sonnet objects read from the input source
	 */
	List<Sonnet> readSonnets(String inputSource);

}
