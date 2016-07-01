package org.benp.addressparser.component;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class CityTest {
	
	@Test
	public void getValue() {
		
		City city = new City();
		assertFalse(city.isValid());
//		
//		CityValue tempCityValue = new CityValue("BOSTON", new HashSet<>(Arrays.asList("MA")));
//		
//		city.setCityValue(tempCityValue);
//		assertEquals("BOSTON", city.getValue());
	}

}
