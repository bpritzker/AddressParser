package org.benp.addressparser.data.mapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;

import com.google.common.base.Splitter;

/**
 * This class is used to hold all the different Mappings. 
 * Just create one of these to load and keep reference to mappings.
 * @author Ben P
 *
 */
public class Mapper {
	
	private AddressParserConfig config;
	private Mapping businessWord;
	private Mapping city;
	
	public Mapping getCity() throws ApException {
		if (city == null) {
			city = loadMapping("AddressParser_UsCity_Default.csv",config);
		}
		return city;
	}

	public Mapper(AddressParserConfig inConfig) {
		config = inConfig;
	}

	public Mapping getBusinessWord() throws ApException {
		if (businessWord == null) {
			businessWord = loadMapping("BusinessWordAbbreviations.csv",config);
		}
		return businessWord;
	}
	
	
	
	
	protected Mapping loadMapping(String inMappingFileName, AddressParserConfig inConfig) throws ApException {
		Map<String, MappingValue> tempMapping = new HashMap<>();
		Set<String> allValues = new HashSet<>();
		Set<String> ambiguousValues = new HashSet<>();
		File mappingFile = 
				new File(Mapper.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString() 
				+ "org/benp/addressparser/data/mapping/" + inMappingFileName);

		try (BufferedReader reader = new BufferedReader(new FileReader(mappingFile))) {
			String currLine = reader.readLine();
			while (currLine != null) {
				// NOTE, this is NOT CSV, it's just a simple comma delimited
				// Keys and values can not have commas, if we need them will will need to change this.
				List<String> splits = Splitter.on(",").splitToList(currLine);
				if (splits.size() != 2) {
					throw new ApException("The mapping file has errors. "
							+ "\nThere are NO commas allowed in either the keys or values.",
							"File", mappingFile.getAbsolutePath(),
							"currLine", currLine);
				}
				String key = splits.get(0);
				String value = splits.get(1);
				MappingValue tempMappingValues = tempMapping.get(key);
				if (tempMappingValues == null) {
					tempMappingValues = new MappingValue();
				}
				tempMappingValues.addValue(value);
				boolean isDuplicateValue = allValues.add(value);
				if (isDuplicateValue) {
					ambiguousValues.add(value);
				}
				
				tempMapping.put(key, tempMappingValues);
				currLine = reader.readLine();
			}
			
		} catch (FileNotFoundException fnfe) {
			throw new ApException("Could not find default mapping file.", fnfe, 
					"mappingFileName", inMappingFileName,
					"mappingFile", mappingFile.getAbsolutePath());
		} catch (IOException ioe) {
			throw new ApException("Error reading mapping file!", ioe, 
					"mappingFileName", inMappingFileName,
					"mappingFile", mappingFile.getAbsolutePath());
		}
		Mapping resultMapping = new Mapping();
		resultMapping.setAmbiguousValues(ambiguousValues);
		resultMapping.setMappings(tempMapping);
		return resultMapping;
	}	
	

}
