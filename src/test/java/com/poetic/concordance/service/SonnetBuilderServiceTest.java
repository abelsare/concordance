package com.poetic.concordance.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.poetic.concordance.model.Line;
import com.poetic.concordance.model.Sonnet;
import com.poetic.concordance.model.SonnetDataStore;
import com.poetic.concordance.model.WordInformation;

/**
 * Unit tests for {@link SonnetBuilderService}
 * @author Adwait
 *
 */
public class SonnetBuilderServiceTest {

	private SonnetBuilderService sonnetBuilderService = new SonnetBuilderService();
	private List<Sonnet> sonnets;
	private SonnetDataStore dataStore = SonnetDataStore.getInstance();
	
	@Before
	public void setUp() throws Exception {
		sonnets = buildSonnets();
	}
	
	@Test
	public void testBuildSonnetDictionary() {
		sonnetBuilderService.buildSonnetData(sonnets);
		
		//Verify first sonnet details
		Sonnet firstSonnet = dataStore.getSonnetDictionary().get(1);
		assertNotNull("Expecting the first sonnet data to be present", firstSonnet);
		assertEquals("Expecting the first sonnet to have 3 lines", 3, firstSonnet.getTotalLines());
		
		//Verify second sonnet details
		Sonnet secondSonnet = dataStore.getSonnetDictionary().get(2);
		assertNotNull("Expecting the second sonnet data to be present", secondSonnet);
		assertEquals("Expecting the second sonnet to have 5 lines", 5, secondSonnet.getTotalLines());
	}
	
	@Test
	public void testBuildWordDictionary() {
		sonnetBuilderService.buildSonnetData(sonnets);
		Map<String, List<WordInformation>> wordDictionary = dataStore.getWordDictionary();
		assertNotNull("Expecting a non null word dictionary", wordDictionary);
		
		//Check if the contents are right
		List<WordInformation> firstSonnetWordInformation = wordDictionary.get("rose");
		assertNotNull("Expecting a matching entry", firstSonnetWordInformation);
		assertEquals("Expecting a match in the first sonnet", 1, firstSonnetWordInformation.get(0).getSonnetNumber());
		assertEquals("Expecting a line number entry", 1, firstSonnetWordInformation.get(0).getLineNumbers().size());
		
		List<WordInformation> secondSonnetWordInformation = wordDictionary.get("thou");
		assertNotNull("Expecting a matching entry", secondSonnetWordInformation);
		assertEquals("Expecting a match in the second sonnet", 2, secondSonnetWordInformation.get(0).getSonnetNumber());
		assertEquals("Expecting 3 line number entries", 3, secondSonnetWordInformation.get(0).getLineNumbers().size());
		
		//Check for repeated word in the same line
		assertNotNull("Expecting a matching entry", wordDictionary.get("my"));
		assertEquals("Expecting a match in the second sonnet", 2, wordDictionary.get("my").get(0).getSonnetNumber());
		assertEquals("Expecting only one line number entry", 1, wordDictionary.get("my").get(0).getLineNumbers().size());
	}
	
	private List<Sonnet> buildSonnets() {
		List<Sonnet> sonnets = new ArrayList<Sonnet>();
		
		Sonnet sonnet1 = mock(Sonnet.class);
		when(sonnet1.getNumber()).thenReturn(1);
		when(sonnet1.getTotalLines()).thenReturn(3);
		
		List<Line> lines = new ArrayList<Line>();
		
		Line line1 = mock(Line.class);
		when(line1.getWords()).thenReturn("From fairest creatures we desire increase,");
		lines.add(line1);
		Line line2 = mock(Line.class);
		when(line2.getWords()).thenReturn("That thereby beauty's rose might never die,");
		lines.add(line2);
		Line line3 = mock(Line.class);
		when(line3.getWords()).thenReturn("But as the riper should by time decease,");
		lines.add(line3);
		
		when(sonnet1.getLines()).thenReturn(lines);
		
		Sonnet sonnet2 = mock(Sonnet.class);
		when(sonnet2.getNumber()).thenReturn(2);
		when(sonnet2.getTotalLines()).thenReturn(5);
		
		List<Line> sonnet2Lines = new ArrayList<Line>();
		
		Line line4 = mock(Line.class);
		when(line4.getWords()).thenReturn("If thou couldst answer 'This fair child of mine");
		sonnet2Lines.add(line4);
		Line line5 = mock(Line.class);
		when(line5.getWords()).thenReturn("Shall sum my count and make my old excuse,'");
		sonnet2Lines.add(line5);
		Line line6 = mock(Line.class);
		when(line6.getWords()).thenReturn("Proving his beauty by succession thine!");
		sonnet2Lines.add(line6);
		Line line7 = mock(Line.class);
		when(line7.getWords()).thenReturn("This were to be new made when thou art old");
		sonnet2Lines.add(line7);
		Line line8 = mock(Line.class);
		when(line8.getWords()).thenReturn("And see thy blood warm when thou feel'st it cold.");
		sonnet2Lines.add(line8);
		
		when(sonnet2.getLines()).thenReturn(sonnet2Lines);
		
		sonnets.add(sonnet1);
		sonnets.add(sonnet2);
		
		return sonnets;
	}

}
