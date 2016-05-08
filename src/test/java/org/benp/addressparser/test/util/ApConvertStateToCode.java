package org.benp.addressparser.test.util;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.google.common.base.Splitter;

public class ApConvertStateToCode {

	public static void main(String[] args) {
		ApConvertStateToCode convertStateToCode = new ApConvertStateToCode();
		try {
			convertStateToCode.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() throws Exception {

		String projectRootDir = "C:/_usr/workspaces/workspace_ben";
		File dataFile = new File(projectRootDir + "/AddressParser/src/test/resources/data/AddressParser_StatesMap.csv");
		
        Map<String, List<String>> stateKeyCommonNames = new HashMap<>();
        try (ICsvListReader listReader = new CsvListReader(new FileReader(dataFile), CsvPreference.STANDARD_PREFERENCE);) {
            List<String> currLine;
            currLine = listReader.read();
            currLine = listReader.read();
            while( currLine != null ) {
                   
            	String key = currLine.get(0) + "-" + currLine.get(1);
            	String currValue = currLine.get(2);
            	List<String> tempValues = stateKeyCommonNames.get(key);
            	if (tempValues == null) {
            		tempValues = new ArrayList<>();
            	}
            	tempValues.add(currValue);
            	stateKeyCommonNames.put(key, tempValues);
            	currLine = listReader.read();
            }
        }
   

//		ApStateValue tempValue = new ApStateValue("MA", "MASSACHUSETTS", 
//				ImmutableSet.<String>of("MA", "MASSACHUSETTS", "MASS"));
//		values.add(tempValue);
        for (Map.Entry<String, List<String>> currGroupings : stateKeyCommonNames.entrySet()) {

        	String keyComposite = currGroupings.getKey();
        	List<String> keySplit = Splitter.on("-").splitToList(keyComposite);
        	
        	StringBuilder output = new StringBuilder();
        	output.append("tempValue = ")
        			.append("new ApStateValue(\"")
        			.append(keySplit.get(0).toUpperCase())
        			.append("\", \"")
        			.append(keySplit.get(1).toUpperCase())
        			.append("\", ")
        			.append("\n");
        	output.append("\t\tImmutableSet.<String>of(")
//        			.append(\"MA\", \"MASSACHUSETTS\", \"MASS\"")
        			.append(buildListOfNames(currGroupings.getValue()))
        			.append("));")
        			.append("\n");
        	output.append("values.add(tempValue);")
        			.append("\n");
        	System.out.println(output.toString());
        }
		
		
	}

	private Object buildListOfNames(List<String> values) {

		String resultString = "";
		String prefix = "";
		for (String currValue : values) {
			resultString = resultString + prefix + "\"" + currValue.toUpperCase() + "\"";
			prefix = ",";
		}
		return resultString;
	}

}
