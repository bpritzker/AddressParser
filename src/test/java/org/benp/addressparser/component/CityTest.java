package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.benp.addressparser.data.CityValue;
import org.junit.Test;

public class CityTest {
	
	
	@Test
	public void getValue() {
		
		City city = new City();
		assertEquals("", city.getValue());
		
		CityValue tempCityValue = new CityValue("BOSTON", new HashSet<>(Arrays.asList("MA")));
		
		city.setCityValue(tempCityValue);
		assertEquals("BOSTON", city.getValue());
	}

}
