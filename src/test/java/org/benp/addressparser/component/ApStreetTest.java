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
		streetNumber.setAddressNumber(742);
		streetNumber.setValid(true);
		resultStreet.setAddressNumber(streetNumber);
		
		ApStreetStreetName streetName = new ApStreetStreetName();
		streetName.setName("Evergreen");
		streetName.setValid(true);
		resultStreet.setStreetName(streetName);
		
		ApStreetSuffix streetSuffix = new ApStreetSuffix();
		streetSuffix.setStreetSuffix(ApStreetSuffixEnum.TERRACE);
		streetSuffix.setValid(true);
		resultStreet.setStreetSuffix(streetSuffix);
		
		return resultStreet;
		
		
	}

}
