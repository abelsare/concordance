package com.poetic.concordance.service;

import java.util.List;

import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.model.SonnetDataStore;

/**
 * Builds the sonnet data read by {@link SonnetReaderService} and creates {@link SonnetDataStore}.
 * The data is made available in a format that makes search easy
 * @author Adwait
 *
 */
public class SonnetBuilderService {
	
	/**
	 * Creates a {@link SonnetDataStore} from the list of sonnets
	 * @param sonnets list of sonnets read from the input location
	 */
	public void buildSonnetData(List<Sonnet> sonnets) {
		
	}

}
