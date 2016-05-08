package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.benp.addressparser.ApException;
import org.benp.addressparser.component.City;
import org.benp.addressparser.data.CityValues;
import org.benp.addressparser.data.CityValuesMock;
import org.benp.addressparser.data.Split;
import org.junit.Before;
import org.junit.Test;

public class CityParserTest extends CityParser {
	
	private CityParser cityParser;
	
	public CityParserTest() {
		super(null);
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
		assertEquals("SPRINGFIELD", actualCity.getCityValue().getName());

		splitter = new ApSplitter("742 Evergreen Terrace, South Boston");
		actualCity = cityParser.parse(splitter);
		assertEquals("SOUTH BOSTON", actualCity.getCityValue().getName());
		
		splitter = new ApSplitter("Springfield");
		actualCity = cityParser.parse(splitter);
		assertEquals("SPRINGFIELD", actualCity.getCityValue().getName());

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
	


	@Override
	protected CityValues getCityValues() throws ApException {
		CityValuesMock resultCityValues = new CityValuesMock();
		resultCityValues.init();
		return resultCityValues;
	}
	
	public CityParser buildTestingCityParser() {
		CityParserTest resultCityParser = new CityParserTest();
		return resultCityParser;
		
	}

}
