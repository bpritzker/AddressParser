package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.component.street.ApStreetSuffix;
import org.benp.addressparser.data.ApStreetSuffixEnum;
import org.junit.Test;

public class ApStreetTest {
	
	
	@Test
	public void getValue() {
		
		ApStreet street = new ApStreet();
		assertEquals("", street.getValue());
		
		street = buildSimpleStreet();
		assertEquals("742 Evergreen TERRACE", street.getValue());
	}

	private ApStreet buildSimpleStreet() {

		ApStreet resultStreet = new ApStreet();
		
		ApStreetAddressNumber streetNumber = new ApStreetAddressNumber();
		streetNumber.setNumber("742");
		resultStreet.setPrimaryNumber(streetNumber);
		
		ApStreetStreetName streetName = new ApStreetStreetName();
		streetName.setName("Evergreen");
		resultStreet.setStreetName(streetName);
		
		ApStreetSuffix streetSuffix = new ApStreetSuffix();
		streetSuffix.setStreetSuffix(ApStreetSuffixEnum.TERRACE);
		resultStreet.setStreetSuffix(streetSuffix);
		
		return resultStreet;
		
		
	}

}
