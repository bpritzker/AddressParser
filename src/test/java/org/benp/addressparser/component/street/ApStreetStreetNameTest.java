package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.parser.street.ApStreetNameParser;
import org.junit.Before;
import org.junit.Test;

public class ApStreetStreetNameTest extends ApStreetNameParser {
	
	private ApStreetNameParser parser;
	
	public ApStreetStreetNameTest(ApAddressParserConfig config) {
		super(config);
	}



	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		parser = new ApStreetNameParser(addressParserConfig);
	}
	
	@Test
	public void getValue() {
		
		ApStreetStreetName actualAtreetName = new ApStreetStreetName();
		actualAtreetName = new ApStreetStreetName();
		assertEquals("", actualAtreetName.getValue());
		
		actualAtreetName.setName("Evergreen");
		assertEquals("Evergreen", actualAtreetName.getValue());
		
		ApDirectional directional = new ApDirectional();
		directional.setDirectional(ApDirectionalEnum.NORTH);
		directional.setValid(true);
//		actualAtreetName.setPreDirectional(directional);
//		assertEquals("NORTH Evergreen", actualAtreetName.getValue());
	}
	
	
	@Test
	public void getNormalizedValue() {
		String actualNormalizedValue;
		
		ApStreetStreetName tempApStreetName = new ApStreetStreetName();
		
		tempApStreetName.setName("Fake");
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("Fake", actualNormalizedValue);
		
		tempApStreetName.setName("Fake");
		ApDirectional tempApDirectional = new ApDirectional();
		tempApDirectional.setDirectional(ApDirectionalEnum.NORTH);
		tempApDirectional.setValid(true);
		tempApStreetName.setPreDirectional(tempApDirectional);
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("North Fake", actualNormalizedValue);
		
		
		// multiple street names
		tempApStreetName = new ApStreetStreetName();
		tempApStreetName.setName("Fake Fake2");
		tempApStreetName.setValid(true);
		actualNormalizedValue = tempApStreetName.getNormalizedValue();
		assertEquals("Fake Fake2", actualNormalizedValue);
		
		
	}

}
