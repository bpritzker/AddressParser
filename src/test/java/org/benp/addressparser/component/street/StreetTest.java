package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
		assertFalse(street.isValid());
		
		street = buildSimpleStreet();
		assertEquals("742 Evergreen Terrace", street.getDefaultValue());
	}

	private Street buildSimpleStreet() {

		Street resultStreet = new Street();
		StreetNamePart1 streetNamePart1 = new StreetNamePart1();
		
		StreetNameNumber streetNumber = new StreetNameNumber();
		streetNumber.setAddressNumber(742);
		streetNumber.setValid(true);
		streetNamePart1.setAddressNumber(streetNumber);
		resultStreet.setStreet1(streetNamePart1);
		
		StreetNameStreet streetName = new StreetNameStreet();
		streetName.setName("Evergreen");
		streetName.setValid(true);
		resultStreet.getStreet1().setStreetName(streetName);
		
		StreetNamePostType streetSuffix = new StreetNamePostType();
		streetSuffix.setStreetPostType(StreetPostTypeEnum.TERRACE);
		streetSuffix.setValid(true);
		resultStreet.getStreet1().setStreetPostType(streetSuffix);
		
		return resultStreet;
	}
	
	
	@Test
	public void getNormalizedValue() {
		Street street = new Street();
		StreetNamePart1 streetNamePart1 = new StreetNamePart1();
		
		StreetNameNumber tempNumber = new StreetNameNumber();
		tempNumber.setAddressNumber(123);
		tempNumber.setValid(true);
		streetNamePart1 = new StreetNamePart1();
		streetNamePart1.setAddressNumber(tempNumber);
		street.setStreet1(streetNamePart1);
		StreetNameStreet tempStreetname = new StreetNameStreet();
		tempStreetname.setName("Fake");
		tempStreetname.setValid(true);
		street.getStreet1().setStreetName(tempStreetname);
		StreetNamePostType tempStreetSuffix = new StreetNamePostType();
		tempStreetSuffix.setStreetPostType(StreetPostTypeEnum.STREET);
		tempStreetSuffix.setValid(true);
		street.getStreet1().setStreetPostType(tempStreetSuffix);
		
		String actualProperValue = street.getValueNormalized();
		assertEquals("123 FAKE STREET", actualProperValue);
	}
	
	@Test
	public void getDefaultValue() {
		Street street = new Street();
		StreetNamePart1 streetNamePart1 = new StreetNamePart1();
		
		StreetNameNumber tempNumber = new StreetNameNumber();
		tempNumber.setAddressNumber(123);
		tempNumber.setValid(true);
		streetNamePart1 = new StreetNamePart1();
		streetNamePart1.setAddressNumber(tempNumber);
		street.setStreet1(streetNamePart1);
		StreetNameStreet tempStreetname = new StreetNameStreet();
		tempStreetname.setName("Fake");
		tempStreetname.setValid(true);
		street.getStreet1().setStreetName(tempStreetname);
		StreetNamePostType tempStreetSuffix = new StreetNamePostType();
		tempStreetSuffix.setStreetPostType(StreetPostTypeEnum.STREET);
		tempStreetSuffix.setValid(true);
		street.getStreet1().setStreetPostType(tempStreetSuffix);
		
		String actualProperValue = street.getDefaultValue();
		assertEquals("123 Fake Street", actualProperValue);
		
	}

}
