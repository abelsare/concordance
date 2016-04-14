package com.poetic.concordance.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.poetic.concordance.model.Line;
import com.poetic.concordance.model.Sonnet;

/**
 * Implements {@link SonnetReaderService}. Reads the input from the files specified by the location in inputSource
 * parameter
 * @author Adwait
 *
 */
public class SonnetFileReaderService implements SonnetReaderService {

	public List<Sonnet> readSonnets(String inputSource) throws FileNotFoundException, IOException {
		List<Sonnet> sonnets = new ArrayList<Sonnet>();
		int sonnetNumber = 1;
		File inputDirectory = new File(inputSource.trim());
		if(inputDirectory.exists()) {
			File[] sonnetFiles = inputDirectory.listFiles();
			if(sonnetFiles != null && sonnetFiles.length > 0) {
				for(File sonnetFile: sonnetFiles) {
					//Build a sonnet object from the text of each file in the directory
					Sonnet sonnet = buildSonnet(sonnetNumber++, sonnetFile);
					sonnets.add(sonnet);
				}
			} else {
				// Directory is empty
				throw new IOException("" +inputDirectory + " directory is empty");
			}
		} else {
			//Invalid directory
			throw new FileNotFoundException("Directory " +inputDirectory + " doesn't exist on the file system");
		}
		
		return sonnets;
	}
	
	private Sonnet buildSonnet(int sonnetNumber, File sonnetFile) throws IOException {
		List<Line> sonnetLines = new ArrayList<Line>();
		BufferedReader bufferedReader = null;
		Sonnet sonnet = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(sonnetFile));
			String wordLine = bufferedReader.readLine();
			while(wordLine != null) {
				//Parse a line from the file and create a line object. Store all the lines in a list.
				Line sonnetLine = new Line();
				sonnetLine.setWords(wordLine);
				sonnetLines.add(sonnetLine);
				wordLine = bufferedReader.readLine();
			}
			//Build a sonnet object from the parsed lines and the given sonnetNumber
			sonnet = new Sonnet();
			sonnet.setNumber(sonnetNumber);
			sonnet.setLines(sonnetLines);
			sonnet.setTotalLines(sonnetLines.size());
		} catch(IOException e) {
			throw new IOException("Error reading a sonnet file");
		} finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sonnet;
	}

}
