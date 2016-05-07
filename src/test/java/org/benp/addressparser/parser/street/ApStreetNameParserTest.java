package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.street.ApStreetNameParser;
import org.junit.Before;
import org.junit.Test;

public class ApStreetNameParserTest extends ApStreetNameParser {
	
	private ApStreetNameParser parser;
	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		parser = new ApStreetNameParser(addressParserConfig);
	}

	public ApStreetNameParserTest() {
		super(null);
	}
	
	
	
	@Test
	public void parse() throws Exception {
		
		ApSplitter splitter;
		ApStreetStreetName actualStreetName;
		
		splitter = new ApSplitter("Fake");
		actualStreetName = parser.parse(splitter);
		assertEquals("Fake", actualStreetName.getName());
		

		// Multiple names in the stree name
		splitter = new ApSplitter("Fake Fake2");
		actualStreetName = parser.parse(splitter);
		assertEquals("Fake Fake2", actualStreetName.getName());
		
		
		splitter = new ApSplitter("Senior 31");
		actualStreetName = parser.parse(splitter);
		assertEquals("Senior 31", actualStreetName.getName());


	}
	
	

}
