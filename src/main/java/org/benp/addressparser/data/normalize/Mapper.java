package org.benp.addressparser.data.normalize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

/**
 * This class is used to hold all the different Mappings. 
 * Just create one of these to load and keep reference to mappings.
 * @author Ben P
 *
 */
public class Mapper {
	
	Logger logger = LoggerFactory.getLogger(Mapper.class);
	
	private AddressParserConfig config;
	private Mapping businessWord;
	private Mapping city;
	private Mapping streetPostType;
//	private Mapping missSpellings;
	
	
	
	private static final Pattern KEY_UNDESIRABLES = Pattern.compile("[\\Q][(){},.;!?<>%\\E]");
	
	public Mapping getCity() throws ApException {
		if (city == null) {
			city = loadMapping("AddressParser_UsCity_Default.csv",config);
		}
		return city;
	}
	
	public Mapping getStreetPostType() throws ApException {
		if (streetPostType == null) {
			streetPostType = loadMapping("StreetPostType.csv", config);
		}
		return streetPostType;
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
		
		
		List<List<String>> mappingFileValues = loadMappingValues(inMappingFileName);
		
		for (List<String> currVal : mappingFileValues) {
			String rawKey = currVal.get(0);
			String normalizedKey = normalizeKey(rawKey);
			String value = currVal.get(1);
			MappingValue tempMappingValues = tempMapping.get(normalizedKey);
			if (tempMappingValues == null) {
				tempMappingValues = new MappingValue();
				
				tempMappingValues.setRawKey(rawKey);
				tempMappingValues.setDefualtValue(rawKey);
			}
			tempMappingValues.addValue(value);
			boolean isDuplicateValue = allValues.add(value);
			if (isDuplicateValue) {
				ambiguousValues.add(value);
			}
			tempMapping.put(normalizedKey, tempMappingValues);
			
		}
		
		Mapping resultMapping = new Mapping();
		resultMapping.setAmbiguousValues(ambiguousValues);
		resultMapping.setMappings(tempMapping);
		return resultMapping;
	}

	
	private List<List<String>> loadMappingValues(String inMappingFileName) throws ApException {

		List<List<String>> resultValues = new ArrayList<>();
		Reader fileReader;
		
//		File mappingFile = 
//				new File(Mapper.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString() 
//				+ "org/benp/addressparser/data/normalize/" + inMappingFileName);

		ClassLoader classLoader = getClass().getClassLoader();
		File mappingFile;
		try {
			mappingFile = new File(classLoader.getResource("data/" + inMappingFileName).getFile());
		} catch (Exception e) {
			logger.debug("ERROR: Could not find mapping file!! <{}>", inMappingFileName );
			throw new ApException("Error, Could not find File <" + inMappingFileName + ">", e);
		}
		
		CsvListReader csvReader = null;
		try {
			fileReader = new FileReader(mappingFile);
			csvReader = new CsvListReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
			List<String> currLine = csvReader.read();
			while (currLine != null) {
				resultValues.add(currLine);
				currLine = csvReader.read();
			}
		} catch (FileNotFoundException fnfe) {
			ApException apException = new ApException("Could not open File.", fnfe);
			throw apException;
		} catch (IOException ioe) {
			ApException apException = new ApException("Error while reading file.", ioe);
			throw apException;
		} finally {
			IOUtils.closeQuietly(csvReader);
		}
		return resultValues;
	}

	/**
	 * Basically remove punctuation and capitalize
	 */
	protected String normalizeKey(String key) {
		
		if (key == null) {
			return null;
		}
		
		return KEY_UNDESIRABLES.matcher(key).replaceAll("");
		
//		String resultKey = key.replaceAll("[][(){},.;!?<>%]", "").toUpperCase();
//		return resultKey;
	}	
	

}
