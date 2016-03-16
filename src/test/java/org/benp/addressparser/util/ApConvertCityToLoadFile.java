package org.benp.addressparser.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class ApConvertCityToLoadFile {

	public static void main(String[] args) {
		try {
			run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void run() throws Exception {

		String projectRootDir = "C:/_usr/workspaces/workspace_ben";
		File dataFile = new File(projectRootDir + "/AddressParser/src/test/resources/data/statecity.csv");
		File outputFile = new File("C:/Temp/AddressParser_UsCity_Default.csv");
		
        try (ICsvListReader listReader = new CsvListReader(new FileReader(dataFile), CsvPreference.STANDARD_PREFERENCE);
        		ICsvListWriter writer = new CsvListWriter(new FileWriter(outputFile), CsvPreference.STANDARD_PREFERENCE)) {
            List<String> currLine;
            currLine = listReader.read();
            currLine = listReader.read();
            while( currLine != null ) {
                   
            	String cityName = currLine.get(2).toUpperCase();
            	String stateCode = currLine.get(0); // if we find we need the code we can always add it
//            	String stateCode = "";
            	
            	
//        		ApCityValue tempCityValue = new ApCityValue("BOSTON", "MA");
//        		values.add(tempCityValue);
            	
            	int intValue = -1;
            	try {
            		intValue = Integer.parseInt(cityName);
            	} catch (NumberFormatException nfe){
            		intValue = -1;
            	}
            	
            	
            	if (intValue == -1) {
            		List<String> output = new ArrayList<>();
            		output.add(cityName);
            		output.add(stateCode);
            		writer.write(output);
            		
//	            	StringBuilder output = new StringBuilder();
//	            	output.append("tempCityValue = new ApCityValue(\""+cityName+"\", \"\");\n");
//	            	output.append("values.add(tempCityValue);\n");
//	            	writer.write(output.toString());
            	}
            	
            	currLine = listReader.read();
            }
        }
   
        System.out.println("Created File: " + outputFile.getAbsolutePath());
		
		
		
	}

}
