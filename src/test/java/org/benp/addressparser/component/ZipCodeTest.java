package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZipCodeTest {
	
	
	
	@Test
	public void getValue() {
		
		ZipCode zipCode = new ZipCode();
		
		assertEquals("", zipCode.getValue());
		
		zipCode.setZipCode("02123");
		assertEquals("02123", zipCode.getValue());
		
		zipCode.setPlus4Code("1234");
		assertEquals("02123-1234", zipCode.getValue());

		
		
	}

}
