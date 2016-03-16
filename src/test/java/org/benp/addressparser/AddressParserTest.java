package org.benp.addressparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.component.ApAddress;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApStreetSuffixEnum;
import org.benp.addressparser.parser.ApCityParser;
import org.benp.addressparser.parser.ApCityParserTest;
import org.junit.Before;
import org.junit.Test;

public class AddressParserTest extends ApAddressParser {

	AddressParserTest addressParser;

	
	@Before
	public void before() {
		addressParser = new AddressParserTest();
	}


	
	@Test
	public void parseAddressSimple() throws Exception {
		
		ApAddress actualAddress;
		
		// Check to make sure we don't cause any null pointer exceptions
		actualAddress = addressParser.parseAddress(null);
		assertFalse(actualAddress.isValid());
		// Check to make sure we don't cause any null pointer exceptions, and it's not valid
		actualAddress = addressParser.parseAddress("Invalid Address");
		assertFalse(actualAddress.isValid());
		
		actualAddress = addressParser.parseAddress("742 Evergreen Terrace. Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);
	}
	
	@Test
	public void parseAddressFails() throws Exception {
		
		ApAddress actualAddress;
		String address;

		// Bad data after the Zip 
		address = "2461 Eisenhower Avenue Alexandria VA 02123 BADDATA";
		actualAddress = addressParser.parseAddress(address);
		assertEquals("02123", actualAddress.getZipCode().getZipCode());
		assertTrue(actualAddress.isValid());

		
		// Bad data after the state
		address = "2461 Eisenhower Avenue Alexandria VA BADDATA";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());

		address = "1244 null St Washington DC 20018";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());

		address = "1014 St # Washington DC 20018";
		actualAddress = addressParser.parseAddress(address);
		assertFalse(actualAddress.isValid());
		
		
		
		address = "2100 Clarendon Arlington VA 22201";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		
		
		address = "Dent Place Washington DC 20007";
		actualAddress = addressParser.parseAddress(address);
		assertFalse(actualAddress.isValid());

		
		address = "2461 Eisenhower Avenue Alexandria VA";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		
	}
	



	@Test
	public void parseAddressComplex() throws Exception {
		
		ApAddress actualAddress;
		
		// Added "Apt 3"
		actualAddress = addressParser.parseAddress("742 Evergreen Terrace. Apt 3 Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);
		
		// Added prefixDirection to street
		actualAddress = addressParser.parseAddress("742 E Evergreen Terrace. Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);
		assertEquals(ApDirectionalEnum.EAST, actualAddress.getStreet().getStreetName().getPreDirectional().getDirectional());

		// Added prefixDirection to street
		actualAddress = addressParser.parseAddress("742 S W. Evergreen Terrace Springfield MA 02111");
		assertEquals(ApDirectionalEnum.SOUTHWEST, actualAddress.getStreet().getStreetName().getPreDirectional().getDirectional());
		assert742EvergreenTerrace(actualAddress);
		
		// Added prefixDirection to street
		actualAddress = addressParser.parseAddress("742 North Evergreen Terrace. Springfield MA 02111");
		assertEquals(ApDirectionalEnum.NORTH, actualAddress.getStreet().getStreetName().getPreDirectional().getDirectional());
		assert742EvergreenTerrace(actualAddress);

	}
	
	@Override
	protected ApCityParser getAddressParser(ApAddressParserConfig config) {
		ApCityParser resultCityParser = new ApCityParserTest();
		return resultCityParser;
	}
	
	
	
	/**
	 * So what is this used for??????
	 * I'm glad you asked. When creating tests, I will use the address below and add all kinds
	 * of trick stuff to test for.
	 * This method allows us to check all these since they should always be set.
	 * 
	 */
	private void assert742EvergreenTerrace(ApAddress actualAddress) {
		assertTrue(actualAddress.isValid());
		assertEquals("02111", actualAddress.getZipCode().getZipCode());
		assertEquals("MA", actualAddress.getState().getStateDefinition().getCode());
		assertEquals("SPRINGFIELD", actualAddress.getCity().getCityValue().getName());
		assertEquals("742", actualAddress.getStreet().getPrimaryNumber().getNumber());
		assertEquals("Evergreen", actualAddress.getStreet().getStreetName().getName());
		assertEquals(ApStreetSuffixEnum.TERRACE, actualAddress.getStreet().getStreetSuffix().getStreetSuffix());
	}
	
	
//	
//
//	/**
//	 * These are address that it needs to parse that work like
//	 * the existing JGeocoder
//	 */
//	private void testJGeocoderAddresses() throws Exception {
//		
//		ApAddress actualAddress = addressParser.parseAddress("Google Inc 1600 Amphitheatre Pky, Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Google Inc, 1600 Amphitheatre Parkway, Mountain View CA");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("1600 Amphitheatre Pky, 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Google Inc, 1600 Amphitheatre Parkway, suite 200, Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Google Inc, 1600 South Amphitheatre Parkway, Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Google Inc, 1600 S W. Amphitheatre Parkway, Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Google Inc, 1600 Amphitheatre Parkway N.W., Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("1600 Amphitheatre Parkway Google Inc, Mountain View, CA 94043");
//		assertNull(actualAddress);
//
//		actualAddress = addressParser.parseAddress("Attn: larry page: 1600 Amphitheatre Parkway 1st Floor, Mountain View, CA 94043");
//		assertNull(actualAddress);
//	}
//	


}