package org.benp.addressparser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddressComparatorTest {
	
	
	
	
	@Test
	public void simpleTests() throws Exception {
		
		AddressComparator comparator = new AddressComparator();
		
		assertTrue(comparator.isSameSimple("123 Fake St. Springfield MA, 02453", "123 Fake St. Springfield MA, 02453"));
		assertTrue(comparator.isSameSimple(" 123 Fake St. Springfield MA, 02453", "123 Fake St. Springfield MA, 02453"));
		assertTrue(comparator.isSameSimple("123 Fake street Springfield MA, 02453", "123 Fake St. Springfield MA, 02453"));
		assertTrue(comparator.isSameSimple("123 Fake St. SPRINGFIELD, Massachusetts, 02453", "123 Fake St. Springfield MA, 02453"));
		

		
		
	}

}
