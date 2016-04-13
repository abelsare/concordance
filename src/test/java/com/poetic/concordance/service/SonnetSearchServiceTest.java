package com.poetic.concordance.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.poetic.concordance.model.Line;
import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.util.Match;
import com.poetic.concordance.util.SearchResult;

public class SonnetSearchServiceTest {

	private SonnetBuilderService sonnetBuilderService = new SonnetBuilderService();
	private List<Sonnet> sonnets = new ArrayList<Sonnet>();
	private SonnetSearchService searchService = new SonnetSearchService();
	
	@Before
	public void setUp() throws Exception {
		buildSonnets();
		sonnetBuilderService.buildSonnetData(sonnets);
	}

	@Test
	public void testNoSearchResult() {
		List<SearchResult> searchResult = searchService.search("abc");
		assertEquals("Expecting an empty result list", 0, searchResult.size());
	}
	
	@Test
	public void testSingleMatch() {
		List<SearchResult> searchResults = searchService.search("succession");
		assertEquals("Expecting only 1 result", 1, searchResults.size());
		SearchResult searchResult = searchResults.get(0);
		assertEquals("Expecting the right sonnet number", 2, searchResult.getSonnetNumber());
		List<Match> matches = searchResult.getMatches();
		Line matchingLine = matches.get(0).getMatchingLine();
		Line surroundingLine = matches.get(0).getSurroundingLine();
		assertEquals("Expecting the right matching line", "Proving his beauty by succession thine!", matchingLine.getWords());
		assertEquals("Expecting the surrounding line to be the one below the matching line", 
				"This were to be new made when thou art old", surroundingLine.getWords());
	}
	
	@Test
	public void testMultipleMatches() {
		List<SearchResult> searchResults = searchService.search("thou");
		assertEquals("Expecting only 1 result", 1, searchResults.size());
		SearchResult searchResult = searchResults.get(0);
		assertEquals("Expecting the right sonnet number", 2, searchResult.getSonnetNumber());
		List<Match> matches = searchResult.getMatches();
		assertEquals("Expecting 3 matches", 3, matches.size());
	}
	
	@Test
	public void testMatchOnLastLine() {
		List<SearchResult> searchResults = searchService.search("riper");
		assertEquals("Expecting only 1 result", 1, searchResults.size());
		SearchResult searchResult = searchResults.get(0);
		assertEquals("Expecting the right sonnet number", 1, searchResult.getSonnetNumber());
		List<Match> matches = searchResult.getMatches();
		Line matchingLine = matches.get(0).getMatchingLine();
		Line surroundingLine = matches.get(0).getSurroundingLine();
		assertEquals("Expecting the right matching line", "But as the riper should by time decease,", 
				matchingLine.getWords());
		assertEquals("Expecting the surrounding line to be the one above the matching line", 
				"That thereby beauty's rose might never die,", surroundingLine.getWords());
	}
	

	private void buildSonnets() {
		
		Sonnet sonnet1 = new Sonnet();
		sonnet1.setNumber(1);
		sonnet1.setTotalLines(3);
		
		List<Line> sonnet1Lines = new ArrayList<Line>();
		Line line1 = new Line();
		line1.setWords("From fairest creatures we desire increase,");
		Line line2 = new Line();
		line2.setWords("That thereby beauty's rose might never die,");
		Line line3 = new Line();
		line3.setWords("But as the riper should by time decease,");
		
		sonnet1Lines.add(line1);
		sonnet1Lines.add(line2);
		sonnet1Lines.add(line3);
		
		sonnet1.setLines(sonnet1Lines);
		
		Sonnet sonnet2 = new Sonnet();
		sonnet2.setNumber(2);
		sonnet2.setTotalLines(5);
		
		List<Line> sonnet2Lines = new ArrayList<Line>();
		Line line4 = new Line();
		line4.setWords("If thou couldst answer 'This fair child of mine");
		Line line5 = new Line();
		line5.setWords("Shall sum my count and make my old excuse,'");
		Line line6 = new Line();
		line6.setWords("Proving his beauty by succession thine!");
		Line line7 = new Line();
		line7.setWords("This were to be new made when thou art old");
		Line line8 = new Line();
		line8.setWords("And see thy blood warm when thou feel'st it cold.");
		
		sonnet2Lines.add(line4);
		sonnet2Lines.add(line5);
		sonnet2Lines.add(line6);
		sonnet2Lines.add(line7);
		sonnet2Lines.add(line8);
		
		sonnet2.setLines(sonnet2Lines);
		
		sonnets.add(sonnet1);
		sonnets.add(sonnet2);
	}

}
