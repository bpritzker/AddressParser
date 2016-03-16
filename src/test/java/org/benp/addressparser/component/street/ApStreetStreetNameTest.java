package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.junit.Test;

public class ApStreetStreetNameTest {
	
	@Test
	public void getValue() {
		
		
		ApStreetStreetName streetName = new ApStreetStreetName();
		assertEquals("", streetName.getValue());
		
		streetName.setName("Evergreen");
		assertEquals("Evergreen", streetName.getValue());
		
		ApDirectional directional = new ApDirectional();
		directional.setDirectional(ApDirectionalEnum.NORTH);
		streetName.setPreDirectional(directional);
		assertEquals("N Evergreen", streetName.getValue());
	}

}
