package org.benp.addressparser.component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CityTest {
	
	@Test
	public void getValue() {
		
		City city = new City();
		assertFalse(city.isValid());
	}

	
	@Test 
	public void equals() {
		City city = new City();

		assertTrue(city.equals(new City()));
		assertFalse(city.equals(new State()));

		city.setCityName("Springfield");

		City city2 = new City();
		city2.setCityName("Capital City");
		assertFalse(city.equals(city2));
		
		city2.setCityName("Springfield");
		assertTrue(city.equals(city2));
	}

}
