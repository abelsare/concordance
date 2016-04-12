package com.poetic.concordance.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.poetic.concordance.model.Sonnet;

/**
 * Unit tests for {@link SonnetFileReaderService}
 * @author Adwait
 *
 */
public class SonnetFileReaderServiceTest {

	private SonnetFileReaderService fileReaderService = new SonnetFileReaderService();
	
	@Test
	public void testInvalidDirectoryInput() throws IOException {
		String directory = "non-existant";
		try {
			fileReaderService.readSonnets(directory);
		} catch(FileNotFoundException e) {
			assertNotNull("Expecting an exception", e);
			assertEquals("Expecting a message", "Directory " +directory + " doesn't exist on the file system", e.getMessage());
		}
		
	}
	
	@Test
	public void testInputDirectoryEmpty() {
		File emptyDirectory = null;
		//Create an empty directory
		try {
			emptyDirectory = File.createTempFile("temp", null);
			emptyDirectory.mkdir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileReaderService.readSonnets(emptyDirectory.getAbsolutePath());
		} catch(IOException e) {
			assertNotNull("Expecting an exception", e);
			assertEquals("Expecting a message", emptyDirectory.getAbsolutePath() + " directory is empty", e.getMessage());
		}
		//Delete the empty directory after we're done
		emptyDirectory.delete();
	}
	
	@Test
	public void testReadSonnets() {
		URL sonnetsSourceUrl = getClass().getResource("/sonnets");
		List<Sonnet> sonnets;
		try {
			sonnets = fileReaderService.readSonnets(sonnetsSourceUrl.getPath());
			assertEquals("Expecting 10 sonnets to be read", 10, sonnets.size());
			assertEquals("Sonnet index should begin from 1", 1, sonnets.get(0).getNumber());
			assertEquals("Expecting the last sonnet to be 14 lines long", 14, sonnets.get(9).getTotalLines());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
