package org.benp.addressparser.test.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;


/**
 * This class is used to convert the data resource .csv files into code that can be copied into the corrosponding classes.
 * It's a little of a hack since it's just a one time run program.
 * 
 * @author Ben P
 *
 */
public class ApConvertCsvToCode {
	
//	private String projectRootDir = "C:/";
	private String projectRootDir = "C:/_usr/workspaces/workspace_ben/addressparser/src/main/resources/data";
	
	private String outputDir = "C:/Temp";
	
	
	private final String CONVERT_TYPE_STATES_MAP = "STATES_MAP";
	private final String CONVERT_TYPE_STREET_SUFFIX_MAP = "STREET_SUFFIX_MAP";
//	private final String CONVERT_TYPE_FREE_ZIPCODE_DB = "FREE_ZIPCODE_DB";
	

	public static void main(String[] args) {
		System.out.println("\n\nStarting....\n\n");
		ApConvertCsvToCode convertCsvToCode = new ApConvertCsvToCode();
		try {
			convertCsvToCode.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\nDone.\n\n");
	}


	private void run() throws Exception {
		
		convertToCode(projectRootDir, "AddressParser_StatesMap.csv", CONVERT_TYPE_STATES_MAP);
		
//		convertFreeZipCodeDb()
		
	}


	/**
	 * This method is used to convert downloaded mapping files to code that you can copy and paste into the corrosponding 
	 * parsers. 
	 * For example, There is an AddressParser_StateMap. Since this map does not change often and it is much quicker to 
	 * make it part of the program than perform File IO we convert the file into code and put the map in the ApStateParser class.
	 * @param inFileRootDir
	 * @param outFileName
	 * @param convertType
	 * @throws Exception
	 */
	private void convertToCode(String inFileRootDir, String outFileName, String convertType) throws Exception {
		
		File fileToMap = new File(inFileRootDir + "/" + outFileName);
		File outFile = new File(outputDir + "/" + outFileName + ".txt");
		if (! fileToMap.exists()) {
			throw new Exception("Could not find " + outFileName + " at: " + fileToMap.getAbsolutePath());
		}
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(fileToMap));
			writer = new BufferedWriter(new FileWriter(outFile));
			String currLine = reader.readLine();
			
			while (currLine != null) {
				if (currLine.length() > 0) {
					List<String> splits = Splitter.on(",").trimResults().splitToList(currLine);
					if (convertType.equals(CONVERT_TYPE_STATES_MAP)) {
						writeStatesMap(splits, writer);
					} else if (convertType.equals(CONVERT_TYPE_STREET_SUFFIX_MAP)) {
						writeStreeSuffix(splits, writer);
					} else {
						throw new Exception("Undefined comvert type: " + CONVERT_TYPE_STATES_MAP);
					}

				}
				currLine = reader.readLine();
			}
			
			System.out.println("Created File: " + outFile.getAbsolutePath());
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(writer);
		}
	}

	
	/**
	 * For a proper name just upper case the first char then lower case the rest
	 */
	private String toProperName(String inName) {
		return inName.substring(0, 1).toUpperCase() + inName.substring(1).toLowerCase();
	}
	
	

	private void writeStatesMap(List<String> splits, BufferedWriter writer) throws Exception {
		writer.write("\ttempApState = new ApState(\""+splits.get(0)+"\", \""+toProperName(splits.get(1))+"\", \""+splits.get(2)+"\");\n");
		writer.write("\tresultDefaultStateAbbreviations.put(tempApState.getCommonName(), tempApState);\n");
	}
	
	
	
	private void writeStreeSuffix(List<String> splits, BufferedWriter writer) throws Exception {
		writer.write("\ttempStreeSuffix = new ApStreetSuffix(\""+splits.get(0)+"\", \""+splits.get(1)+"\", \""+splits.get(2)+"\");" + "\n");
		writer.write("\tresultStreeSuffixMap.put(tempStreeSuffix.getCommonAbbreviation().toUpperCase(), tempStreeSuffix);\n");
	}

}
