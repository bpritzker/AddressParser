package org.benp.addressparser.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

/** 
 * This class is used to convert the Street Suffix file of values into the enum code.
 * 
 * @author Ben P
 *
 */
public class ApConvertStreetSuffixToEnum {

	public static void main(String[] args) {
		
		try {
			run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void run() throws Exception  {
		File csvDataFile = new File("C:/_usr/workspaces/workspace_ben/AddressParser/src/test/resources/data/AddressParser_StreetSuffix.csv");
		FileReader fileReader = new FileReader(csvDataFile);
		
		File outputFile = new File("C:/Temp/ApStreetSuffixEnum.txt");
		
		try (CsvListReader reader = new CsvListReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			
			List<String> currLine = reader.read();
			// throw out headers line by reading next line
			currLine = reader.read();
			Map<String, List<List<String>>> primaryNameToValues = new HashMap<>();
			
			// First just group them
			while (currLine != null) {
				String keyPrimaryName = currLine.get(0);
				List<List<String>> values = primaryNameToValues.get(keyPrimaryName);
				if (values == null) {
					values = new ArrayList<>();
				}
				
				if (keyPrimaryName.toUpperCase().equals("FORDS")) {
					System.out.println("Breakpoint");
				}
				
				values.add(currLine);
				primaryNameToValues.put(keyPrimaryName, values);
				currLine = reader.read();
			}
			
			List<String> keys = new ArrayList<>(primaryNameToValues.keySet());
			Collections.sort(keys);
			
			// now that we have them grouped, print out the enum constructors....
			for (String currKey : keys) {
				// We want each line to look like what is below....
				// ALLEY("ALLEY", "ALY", new HashSet<String>(Arrays.asList("ALLEE", "ALLEY", "ALLY", "ALY"))),
				List<List<String>> values = primaryNameToValues.get(currKey);
				
				
				if (currKey.toUpperCase().equals("FORDS")) {
					System.out.println("Breakpoint");
				}
				
				String primaryName = values.get(0).get(0).toUpperCase();
				String standardSuffix = values.get(0).get(2).toUpperCase();
				List<String> commonAbbrivs = getCommonAbbrivs(values);
				
				String commonAbbrivOutString = buildCommonAbbrivOut(commonAbbrivs);
				
				// now put it all together and print....
				writer.write(
						primaryName +
						"(" +
						"\"" + primaryName + "\"" +
						", \"" + standardSuffix + "\"" +
						", " + commonAbbrivOutString +
						"))," +
						"\n"
						);
				
			}
		
		}
		
		System.out.println("File created at: " + outputFile.getAbsolutePath());

	}



	private static List<String> getCommonAbbrivs(List<List<String>> groupedValues) {
		List<String> resultAbbrivs = new ArrayList<>();
		
		for (List<String> currGroup : groupedValues) {
			resultAbbrivs.addAll(currGroup);
		}
		
		
		
//		Set<String> tempSet = new HashSet<>(groupedValues);
		return new ArrayList<>(new HashSet<>(resultAbbrivs));
	}
	
	
	private static String buildCommonAbbrivOut(List<String> commonAbbrivs) {

		StringBuilder resultSb = new StringBuilder();
		resultSb.append("new HashSet<String>(Arrays.asList(");
		String prefix = "";
		for (String currAbbriv : commonAbbrivs) {
			resultSb.append(prefix).append("\"").append(currAbbriv).append("\"");
			prefix = ",";
		}
		resultSb.append(")");
		return resultSb.toString();
	}

}
