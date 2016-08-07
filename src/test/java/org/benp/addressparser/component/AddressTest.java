package org.benp.addressparser.component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddressTest {

	
	@Test
	public void equals() {
		Address address = new Address();
		
		// Test for class equality
		assertTrue(address.equals(new Address()));
		
		// Test for class not equals
		assertFalse(address.equals(new State()));
		
		
//		address.setCity(city);
		
	}
}
