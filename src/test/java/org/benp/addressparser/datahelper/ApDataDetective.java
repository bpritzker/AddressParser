package org.benp.addressparser.datahelper;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.google.common.base.Splitter;

public class ApDataDetective {

	public static void main(String[] args) {

		ApDataDetective dataDetective= new ApDataDetective();
		dataDetective.run();
	}

	public void run() {

		try {
			
			runMaxCityNameSplits();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	/**
	 * This method will read in the City Names file and find the max number of parts a city name can have.
	 * This is needed in the city parser
	 */
	public void runMaxCityNameSplits() throws Exception {

		File cityFile = new File("C:/_usr/workspaces/workspace_ben/AddressParser/src/main/resources/data/AddressParser_UsCities.csv");
		Reader fileReader = new FileReader(cityFile);
		
		
		int maxParts = 0;
		String maxValue = "";

		try (CsvListReader reader = new CsvListReader(fileReader, CsvPreference.STANDARD_PREFERENCE)) {
		
			List<String> currLine = reader.read();
			while (currLine != null) {
				
				String cityName = currLine.get(1);
				List<String> splits = Splitter.on(" ").splitToList(cityName);
				
				if (splits.size() > maxParts) {
					maxParts = splits.size();
					maxValue = cityName;
				}
				
				if (splits.size() > 2) {
					System.out.println("City: " + cityName);
					
				}
				
				
				currLine = reader.read();
			}
		}
		
		System.out.println("Found Max: " + maxParts + "  -- Value: " + maxValue);
	}

}
