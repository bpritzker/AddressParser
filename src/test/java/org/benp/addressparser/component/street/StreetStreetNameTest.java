package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.parser.street.StreetNameStreetParser;
import org.junit.Test;

public class StreetStreetNameTest extends StreetNameStreetParser {

	public StreetStreetNameTest() {
		super(null, null);
	}

	
	@Test
	public void getValue() {
		
		StreetNameStreet actualAtreetName = new StreetNameStreet();
		actualAtreetName = new StreetNameStreet();
		assertFalse(actualAtreetName.isValid());
		
		actualAtreetName.setName("Evergreen");
		assertEquals("Evergreen", actualAtreetName.getDefaultValue());
		assertEquals("EVERGREEN", actualAtreetName.getValueNormalized());
		
		Directional directional = new Directional();
		directional.setDirectional(DirectionalEnum.NORTH);
		directional.setValid(true);
		actualAtreetName.setPreDirectional(directional);
		assertEquals("North Evergreen", actualAtreetName.getDefaultValue());
	}
	
	
	@Test
	public void getDefaultValue() {
		String actualProperValue;
		
		StreetNameStreet tempApStreetName = new StreetNameStreet();
		
		tempApStreetName.setName("Fake");
		actualProperValue = tempApStreetName.getDefaultValue();
		assertEquals("Fake", actualProperValue);
		
		tempApStreetName.setName("Fake");
		Directional tempApDirectional = new Directional();
		tempApDirectional.setDirectional(DirectionalEnum.NORTH);
		tempApDirectional.setValid(true);
		tempApStreetName.setPreDirectional(tempApDirectional);
		actualProperValue = tempApStreetName.getDefaultValue();
		assertEquals("North Fake", actualProperValue);
		
		
		// multiple street names
		tempApStreetName = new StreetNameStreet();
		tempApStreetName.setName("Fake Fake2");
		tempApStreetName.setValid(true);
		actualProperValue = tempApStreetName.getDefaultValue();
		assertEquals("Fake Fake2", actualProperValue);
		
		// Check for proper case
		tempApStreetName = new StreetNameStreet();
		tempApStreetName.setName("EVERGREEN");
		tempApStreetName.setValid(true);
		actualProperValue = tempApStreetName.getDefaultValue();
		assertEquals("Evergreen", actualProperValue);
		
	}

}
