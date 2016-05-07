package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.street.ApStreet;
import org.benp.addressparser.component.street.ApStreetAddressNumber;
import org.benp.addressparser.component.street.ApStreetStreetName;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.data.ApStreetPostTypeEnum;
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
		
		StreetNamePostType streetSuffix = new StreetNamePostType();
		streetSuffix.setStreetPostType(ApStreetPostTypeEnum.TERRACE);
		streetSuffix.setValid(true);
		resultStreet.setStreetPostType(streetSuffix);
		
		return resultStreet;
	}
	
	
	@Test
	public void getNormalizedValue() {
		ApStreet apStreet = new ApStreet();
		ApStreetAddressNumber tempNumber = new ApStreetAddressNumber();
		tempNumber.setAddressNumber(123);
		tempNumber.setValid(true);
		apStreet.setAddressNumber(tempNumber);
		ApStreetStreetName tempStreetname = new ApStreetStreetName();
		tempStreetname.setName("Fake");
		tempStreetname.setValid(true);
		apStreet.setStreetName(tempStreetname);
		StreetNamePostType tempStreetSuffix = new StreetNamePostType();
		tempStreetSuffix.setStreetPostType(ApStreetPostTypeEnum.STREET);
		tempStreetSuffix.setValid(true);
		apStreet.setStreetPostType(tempStreetSuffix);
		
		String actualNormalizedValue = apStreet.getNormalizedValue();
		assertEquals("123 Fake Street", actualNormalizedValue);
	}

}
