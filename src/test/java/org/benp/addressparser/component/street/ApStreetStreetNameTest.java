package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.junit.Test;

public class ApStreetStreetNameTest {
	
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
		actualAtreetName.setPreDirectional(directional);
		assertEquals("N Evergreen", actualAtreetName.getValue());
	}

}
