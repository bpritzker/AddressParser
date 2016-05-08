package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.street.Street;
import org.benp.addressparser.component.street.StreetNameNumber;
import org.benp.addressparser.component.street.StreetNameStreet;
import org.benp.addressparser.component.street.StreetNamePostType;
import org.benp.addressparser.data.StreetPostTypeEnum;
import org.junit.Test;

public class StreetTest {
	
	
	@Test
	public void getValue() {
		
		Street street = new Street();
		assertEquals("", street.getValue());
		
		street = buildSimpleStreet();
		assertEquals("742 Evergreen TER", street.getValue());
	}

	private Street buildSimpleStreet() {

		Street resultStreet = new Street();
		
		StreetNameNumber streetNumber = new StreetNameNumber();
		streetNumber.setAddressNumber(742);
		streetNumber.setValid(true);
		resultStreet.setAddressNumber(streetNumber);
		
		StreetNameStreet streetName = new StreetNameStreet();
		streetName.setName("Evergreen");
		streetName.setValid(true);
		resultStreet.setStreetName(streetName);
		
		StreetNamePostType streetSuffix = new StreetNamePostType();
		streetSuffix.setStreetPostType(StreetPostTypeEnum.TERRACE);
		streetSuffix.setValid(true);
		resultStreet.setStreetPostType(streetSuffix);
		
		return resultStreet;
	}
	
	
	@Test
	public void getNormalizedValue() {
		Street apStreet = new Street();
		StreetNameNumber tempNumber = new StreetNameNumber();
		tempNumber.setAddressNumber(123);
		tempNumber.setValid(true);
		apStreet.setAddressNumber(tempNumber);
		StreetNameStreet tempStreetname = new StreetNameStreet();
		tempStreetname.setName("Fake");
		tempStreetname.setValid(true);
		apStreet.setStreetName(tempStreetname);
		StreetNamePostType tempStreetSuffix = new StreetNamePostType();
		tempStreetSuffix.setStreetPostType(StreetPostTypeEnum.STREET);
		tempStreetSuffix.setValid(true);
		apStreet.setStreetPostType(tempStreetSuffix);
		
		String actualNormalizedValue = apStreet.getNormalizedValue();
		assertEquals("123 Fake Street", actualNormalizedValue);
	}

}
