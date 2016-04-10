package org.benp.addressparser.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApCityValuesTest {
	
	
	@Test
	public void loadValues() throws Exception {
		
		ApCityValues actualCityValues = new ApCityValues();
		actualCityValues.init();
		
		assertEquals(5, actualCityValues.fromName("LYNCHBURG").getStateCode().size());
	}

}
