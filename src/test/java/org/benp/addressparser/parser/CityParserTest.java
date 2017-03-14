package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.benp.addressparser.ApException;
import org.benp.addressparser.component.City;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.normalize.MapperMock;
import org.benp.addressparser.data.normalize.Mapping;
import org.benp.addressparser.data.normalize.MappingValue;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Splitter;

public class CityParserTest extends CityParser {
	
	private CityParser cityParser;
	
	public CityParserTest() {
		super(null, null);
	}


	
	@Before
	public void before() {
		cityParser = buildTestingCityParser();
	}
	
	@Test
	public void parse() throws ApException {
		ApSplitter splitter;
		City actualCity;
		
		splitter = new ApSplitter("742 Evergreen Terrace, Springfield");
		actualCity = cityParser.parse(splitter);
		assertEquals("SPRINGFIELD", actualCity.getCityName());

		splitter = new ApSplitter("742 Evergreen Terrace, South Boston");
		actualCity = cityParser.parse(splitter);
		assertEquals("SOUTH BOSTON", actualCity.getCityName());
		
		splitter = new ApSplitter("Springfield");
		actualCity = cityParser.parse(splitter);
		assertEquals("SPRINGFIELD", actualCity.getCityName());

	}
	
	@Test
	public void buildTryValues() {
		ApSplitter splitter;
		List<List<Split>> actualValues;
		
		// Simple 1 value test
		splitter = new ApSplitter("Springfield");
		actualValues = buildTryValues(splitter, 1);
		assertEquals(1, actualValues.size());
		
		actualValues = buildTryValues(splitter, 2);
		assertEquals(1, actualValues.size());

		actualValues = buildTryValues(splitter, 3);
		assertEquals(1, actualValues.size());
		
		
		// Harder two value test
		splitter = new ApSplitter("new Springfield");
		actualValues = buildTryValues(splitter, 1);
		assertEquals(1, actualValues.size());
		assertEquals(1, actualValues.get(0).size());
		assertEquals("Springfield", actualValues.get(0).get(0).getValue());
		
		actualValues = buildTryValues(splitter, 2);
		assertEquals(2, actualValues.size());
		assertEquals(1, actualValues.get(0).size());
		assertEquals("Springfield", actualValues.get(0).get(0).getValue());
		
		assertEquals(2, actualValues.get(1).size());
		assertEquals("new", actualValues.get(1).get(0).getValue());
		assertEquals("Springfield", actualValues.get(1).get(1).getValue());
		

		actualValues = buildTryValues(splitter, 3);
		assertEquals(2, actualValues.size());
		
		
		// Now test with 3 values
		splitter = new ApSplitter("Old North Haverbrook");
		actualValues = buildTryValues(splitter, 1);
		assertEquals(1, actualValues.size());
		assertEquals("Haverbrook", actualValues.get(0).get(0).getValue());
		
		// Skip the 2 split tests

		actualValues = buildTryValues(splitter, 3);
		assertEquals(3, actualValues.size());
		assertEquals(1, actualValues.get(0).size());
		assertEquals("Haverbrook", actualValues.get(0).get(0).getValue());
		
		
		assertEquals(2, actualValues.get(1).size());
		assertEquals("North", actualValues.get(1).get(0).getValue());
		assertEquals("Haverbrook", actualValues.get(1).get(1).getValue());

		assertEquals(3, actualValues.get(2).size());
		assertEquals("Old", actualValues.get(2).get(0).getValue());
		assertEquals("North", actualValues.get(2).get(1).getValue());
		assertEquals("Haverbrook", actualValues.get(2).get(2).getValue());
	}
	
	@Test
	public void findCityMappingValue() throws Exception {
		
		String cityNameToFind = "SAINT AUGUSTINE";
		MappingValue actual = findCityMappingValue(cityNameToFind);
		assertNotNull(actual);
	}
	
	
	@Test
	public void findBusinessMatch() throws Exception {
		
		MapperMock mockMapper = buildMockMapper();
		
		CityParser localCityParser = new CityParser(mockMapper, null);
		List<String> splits = Splitter.on(" ").splitToList("SAINT AUGUSTINE");
		
		MappingValue actualMappingValue = localCityParser.findBusinessMatch(null, splits);
		assertNotNull(actualMappingValue);
	}
		


//	@Override
//	protected CityValues getCityValues() throws ApException {
//		CityValuesMock resultCityValues = new CityValuesMock();
//		resultCityValues.init();
//		return resultCityValues;
//	}
	
	private MapperMock buildMockMapper() {
		Mapping businessWordOverride = new Mapping();
		Map<String, MappingValue> tempMapping = new HashMap<>();
		MappingValue tempMappingValue = new MappingValue();
		tempMappingValue.addValue("ST");
		tempMappingValue.addValue("SAINT");
		tempMappingValue.setDefualtValue("ST");
		tempMapping.put("SAINT", tempMappingValue);
		businessWordOverride.setMappings(tempMapping);
		
		
		
		Mapping cityOverride = new Mapping();
		tempMapping = new HashMap<>();
		tempMappingValue = new MappingValue();
		tempMappingValue.addValue("FL");
		tempMappingValue.setDefualtValue("FL");
		tempMapping.put("ST AUGUSTINE", tempMappingValue);
		cityOverride.setMappings(tempMapping);
		
		
		MapperMock resultMockMapper = new MapperMock();
		resultMockMapper.setBusinessWordOverride(businessWordOverride);
		resultMockMapper.setCityOverride(cityOverride);

		
		
		return resultMockMapper;
		
	}



	public CityParser buildTestingCityParser() {
		CityParserTest resultCityParser = new CityParserTest();
		return resultCityParser;
		
	}

}
