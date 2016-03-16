package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApCity;
import org.benp.addressparser.data.ApCityValues;
import org.benp.addressparser.data.ApCityValuesMock;
import org.junit.Before;
import org.junit.Test;

public class ApCityParserTest extends ApCityParser {
	
	private ApCityParser cityParser;
	
	public ApCityParserTest() {
		super(null);
	}


	
	@Before
	public void before() {
		cityParser = buildTestingCityParser();
	}
	
	@Test
	public void parse() throws ApException {
		ApSplitter splitter;
		ApCity actualCity;
		
		splitter = new ApSplitter("742 Evergreen Terrace, Springfield");
		actualCity = cityParser.parse(splitter);
		assertEquals("SPRINGFIELD", actualCity.getCityValue().getName());

		splitter = new ApSplitter("742 Evergreen Terrace, South Boston");
		actualCity = cityParser.parse(splitter);
		assertEquals("SOUTH BOSTON", actualCity.getCityValue().getName());

	}


	@Override
	protected ApCityValues getCityValues() throws ApException {
		ApCityValuesMock resultCityValues = new ApCityValuesMock();
		resultCityValues.init();
		return resultCityValues;
	}
	
	public ApCityParser buildTestingCityParser() {
		ApCityParserTest resultCityParser = new ApCityParserTest();
		return resultCityParser;
		
	}

}
