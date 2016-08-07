package org.benp.addressparser.parser.street;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.data.mapping.Mapper;
import org.benp.addressparser.parser.ApSplitter;
import org.junit.Before;
import org.junit.Test;

public class StreetNameStreetParserTest extends StreetNameStreetParser {
	
	private StreetNameStreetParser parser;
//	private BusinessAbbreviationValues businessAbbreviationValues;
	
	@Before
	public void before() throws ApException {
		AddressParserConfig addressParserConfig = new AddressParserConfig();
		Mapper mapper = new Mapper(addressParserConfig);
		parser = new StreetNameStreetParser(mapper, addressParserConfig);
	}

	public StreetNameStreetParserTest() {
		super(null, null);
	}
	
	
	
	@Test
	public void parse() throws Exception {
		
		ApSplitter splitter;
		StreetNameStreet actualStreetName;

		splitter = new ApSplitter("Fake");
		actualStreetName = parser.parse(splitter);
		assertEquals("Fake", actualStreetName.getName());
		

		// Multiple names in the street name
		splitter = new ApSplitter("Fake Fake2");
		actualStreetName = parser.parse(splitter);
		assertEquals("Fake Fake2", actualStreetName.getName());
		
		
		splitter = new ApSplitter("Senior 31");
		actualStreetName = parser.parse(splitter);
		assertEquals("Senior 31", actualStreetName.getName());
		
	}
	
	@Test
	public void parseBusinessAbbrivation() throws Exception {
		
		ApSplitter splitter;
		StreetNameStreet actualStreetName;

		splitter = new ApSplitter("Evergreen");
		actualStreetName = parser.parse(splitter);
		assertEquals("Evergreen", actualStreetName.getName());
		
		splitter = new ApSplitter("Mt Evergreen");
		actualStreetName = parser.parse(splitter);
		assertEquals("Mt Evergreen", actualStreetName.getDefaultValue());
		
		splitter = new ApSplitter("Mount Evergreen");
		actualStreetName = parser.parse(splitter);
		assertEquals("Mount Evergreen", actualStreetName.getDefaultValue());
		
	}
	
	@Test
	public void buildStreetName() throws Exception {
		List<String> streetNameValues;
		String actual;
		
		streetNameValues = new ArrayList<>();
		streetNameValues.add("Mount");
		streetNameValues.add("Evergreen");
		actual = parser.buildStreetName(streetNameValues);
		assertEquals("Mount Evergreen", actual);

		streetNameValues = new ArrayList<>();
		streetNameValues.add("Mount");
		streetNameValues.add("Athos");
		actual = parser.buildStreetName(streetNameValues);
		assertEquals("Mount Athos", actual);

		streetNameValues = new ArrayList<>();
		streetNameValues.add("Fake");
		streetNameValues.add("Fake2");
		actual = parser.buildStreetName(streetNameValues);
		assertEquals("Fake Fake2", actual);
		
		streetNameValues = new ArrayList<>();
		streetNameValues.add("Senior");
		streetNameValues.add("31");
		actual = parser.buildStreetName(streetNameValues);
		assertEquals("Senior 31", actual);
	}
	

}
