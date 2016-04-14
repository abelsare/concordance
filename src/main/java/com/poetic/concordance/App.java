package com.poetic.concordance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.service.SonnetBuilderService;
import com.poetic.concordance.service.SonnetFileReaderService;
import com.poetic.concordance.service.SonnetReaderService;
import com.poetic.concordance.service.SonnetSearchService;
import com.poetic.concordance.util.Match;
import com.poetic.concordance.util.SearchResult;

/**
 * Runs the concordance system.
 *
 */
public class App {
	
    public static void main( String[] args ) {
    	if(args.length == 0 || args[0].trim().length() == 0) {
    		System.out.println("Usage: java -jar target/concordance-1.0-SNAPSHOT.jar <input-directory>");
    	} else {
    		System.out.println("Reading sonnet files from " +args[0] + " directory ...");
    		SonnetReaderService readerService = new SonnetFileReaderService();
    		Scanner scanner = null;
    		try {
    			//Read the sonnets from the input directory
				List<Sonnet> sonnets = readerService.readSonnets(args[0]);
				//Build the dictionaries to enable quick search
				SonnetBuilderService builderService = new SonnetBuilderService();
				builderService.buildSonnetData(sonnets);
				while(true) {
					System.out.print("Enter a word that you wish to search: ");
					scanner = new Scanner(System.in);
					//Execute search
					SonnetSearchService searchService = new SonnetSearchService();
					String searchTerm = scanner.nextLine();
					List<SearchResult> searchResults = searchService.search(searchTerm);
					if(searchResults.isEmpty()) {
						System.out.println("No match found for word: " +searchTerm + ". Please try another one ");
					} else {
						//Display results
						displayResults(searchResults);
					}
				}
			} catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				if(scanner != null) {
					scanner.close();
				}
			}
    	}
        
    }

	private static void displayResults(List<SearchResult> searchResults) {
		for(SearchResult searchResult: searchResults) {
			System.out.println("Sonnet " +searchResult.getSonnetNumber() + ":");
			System.out.println();
			for(Match match: searchResult.getMatches()) {
				if(match.isMatchOnTheLastLine()) {
					//If match is on the last line then the surrounding line is the line above the matching
					//line. The display order needs to be flipped in this case.
					System.out.println(match.getSurroundingLine().getWords());
					System.out.println(match.getMatchingLine().getWords());
				} else {
					//Surrounding line is the line below the matching line. Display accordingly.
					System.out.println(match.getMatchingLine().getWords());
					System.out.println(match.getSurroundingLine().getWords());
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
