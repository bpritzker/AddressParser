package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.data.ApCityValue;
import org.junit.Test;

public class ApCityTest {
	
	
	@Test
	public void getValue() {
		
		ApCity city = new ApCity();
		assertEquals("", city.getValue());
		
		ApCityValue tempCityValue = new ApCityValue("BOSTON", "MA");
		
		city.setCityValue(tempCityValue);
		assertEquals("BOSTON", city.getValue());
	}

}
