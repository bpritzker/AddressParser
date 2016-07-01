package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.parser.street.StreetNameStreetParser;
import org.junit.Test;

public class StreetStreetNameTest extends StreetNameStreetParser {
	
//	private ApStreetNameParser parser;
	
	public StreetStreetNameTest() {
		super(null, null);
	}



	
//	@Before
//	public void before() {
//		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
//		parser = new ApStreetNameParser(addressParserConfig);
//	}
	
	@Test
	public void getValue() {
		
		StreetNameStreet actualAtreetName = new StreetNameStreet();
		actualAtreetName = new StreetNameStreet();
		assertEquals("", actualAtreetName.getValue());
		
		actualAtreetName.setName("Evergreen");
		assertEquals("Evergreen", actualAtreetName.getValue());
		
		Directional directional = new Directional();
		directional.setDirectional(DirectionalEnum.NORTH);
		directional.setValid(true);
//		actualAtreetName.setPreDirectional(directional);
//		assertEquals("NORTH Evergreen", actualAtreetName.getValue());
	}
	
	
	@Test
	public void getNormalizedValue() {
		String actualNormalizedValue;
		
		StreetNameStreet tempApStreetName = new StreetNameStreet();
		
		tempApStreetName.setName("Fake");
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("Fake", actualNormalizedValue);
		
		tempApStreetName.setName("Fake");
		Directional tempApDirectional = new Directional();
		tempApDirectional.setDirectional(DirectionalEnum.NORTH);
		tempApDirectional.setValid(true);
		tempApStreetName.setPreDirectional(tempApDirectional);
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("North Fake", actualNormalizedValue);
		
		
		// multiple street names
		tempApStreetName = new StreetNameStreet();
		tempApStreetName.setName("Fake Fake2");
		tempApStreetName.setValid(true);
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("Fake Fake2", actualNormalizedValue);
		
		// Check for proper case
		tempApStreetName = new StreetNameStreet();
		tempApStreetName.setName("EVERGREEN");
		tempApStreetName.setValid(true);
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("Evergreen", actualNormalizedValue);
		
	}

}
