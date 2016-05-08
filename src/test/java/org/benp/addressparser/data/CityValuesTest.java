package org.benp.addressparser.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityValuesTest {
	
	
	@Test
	public void loadValues() throws Exception {
		
		CityValues actualCityValues = new CityValues();
		actualCityValues.init();
		
		assertEquals(5, actualCityValues.fromName("LYNCHBURG").getStateCode().size());
	}

}
