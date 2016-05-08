package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.street.StreetNameStreetParser;
import org.junit.Before;
import org.junit.Test;

public class StreetNameStreetParserTest extends StreetNameStreetParser {
	
	private StreetNameStreetParser parser;
	
	@Before
	public void before() {
		AddressParserConfig addressParserConfig = new AddressParserConfig();
		parser = new StreetNameStreetParser(addressParserConfig);
	}

	public StreetNameStreetParserTest() {
		super(null);
	}
	
	
	
	@Test
	public void parse() throws Exception {
		
		ApSplitter splitter;
		StreetNameStreet actualStreetName;
		
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
