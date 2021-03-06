package org.benp.addressparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.common.AddressParserConfig;
import org.benp.addressparser.common.ApException;
import org.benp.addressparser.component.Address;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.parser.CityParser;
import org.benp.addressparser.parser.CityParserTest;
import org.junit.Before;
import org.junit.Test;

public class AddressParserTest extends AddressParser {

	public AddressParserTest() throws ApException {
		super();
	}

	AddressParserTest addressParser;

	
	@Before
	public void before() {
		try {
			addressParser = new AddressParserTest();
		} catch (ApException e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test
//	public void parseAddressMultiParam() throws Exception {
//		
//		Address parseAddress(String street1, String street2, String city, String state, String zipCode) throws ApException {
//
//		
//	}


	
	@Test
	public void parseAddressBasic() throws Exception {
		
		Address actualAddress;
		
		// Check to make sure we don't cause any null pointer exceptions
		actualAddress = addressParser.parseAddress(null);
		assertFalse(actualAddress.isValid());
		
		// Check to make sure we don't cause any null pointer exceptions, and it's not valid
		actualAddress = addressParser.parseAddress("Invalid Address");
		assertFalse(actualAddress.isValid());
		
		// Basic test of a valid Address
		actualAddress = addressParser.parseAddress("742 Evergreen Terrace. Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);
	}
	
	
	/**
	 * These are cases where in the past the address parser failed.
	 * 
	 */
	@Test
	public void parseAddressFails() throws Exception {
		
		Address actualAddress;
		String address;
		
		// The street number has a letter in it
		address = "3161D Britannia Blvd. Kissimmee FL 34747";
		actualAddress = addressParser.parseAddress(address);
		assertEquals("3161D Britannia Boulevard", actualAddress.getStreeAddressDefault());
		assertEquals("34747", actualAddress.getZipCode().getZipCode());
		assertTrue(actualAddress.isValid());

		
		// Bad data after the Zip 
		address = "7777 Eisenhower Avenue Alexandria VA 02123 BADDATA";
		actualAddress = addressParser.parseAddress(address);
		assertEquals("7777 Eisenhower Avenue", actualAddress.getStreeAddressDefault());
		assertEquals("02123", actualAddress.getZipCode().getZipCode());
		assertTrue(actualAddress.isValid());

		
		// Bad data after the state
		address = "7777 Eisenhower Avenue Alexandria VA BADDATA";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());

		address = "1244 null St Washington DC 20018";
		actualAddress = addressParser.parseAddress(address);
		assertEquals("1244 Null Street", actualAddress.getStreet().getValueDefault());
		assertTrue(actualAddress.isValid());
		
		address = "2100 Clarendon Arlington VA 22201";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		
		
		address = "Dent Place Washington DC 20007";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		assertFalse(actualAddress.isComplete());
		assertEquals("Dent", actualAddress.getStreet().getStreet1().getStreetName().getName());

		
		address = "2461 Eisenhower Avenue Alexandria VA";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		
		address = "50 south drive  bethesda MD 20892";
		actualAddress = addressParser.parseAddress(address);
		assertTrue(actualAddress.isValid());
		assertTrue(actualAddress.isComplete());
	}
	



	/**
	 * These are some of the more complex addresses that need to be tested
	 * @throws Exception
	 */
	@Test
	public void parseAddressComplex() throws Exception {
		
		Address actualAddress;
		
		// Added "Apt 3"
		actualAddress = addressParser.parseAddress("742 Evergreen Terrace. Apt 3 Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);

		// Added prefixDirection to street
		actualAddress = addressParser.parseAddress("742 E Evergreen Terrace. Springfield MA 02111");
		assert742EvergreenTerrace(actualAddress);
		assertEquals(DirectionalEnum.EAST, actualAddress.getStreet().getStreet1().getStreetName().getPreDirectional().getDirectional());
		
		// Added prefixDirection to street
		actualAddress = addressParser.parseAddress("742 North Evergreen Terrace. Springfield MA 02111");
		assertEquals(DirectionalEnum.NORTH, actualAddress.getStreet().getStreet1().getStreetName().getPreDirectional().getDirectional());
		assert742EvergreenTerrace(actualAddress);
		
		
		
		actualAddress = addressParser.parseAddress("1120 20th Street  Washington DC 20036");
		assertEquals(1120, actualAddress.getStreet().getStreet1().getAddressNumber().getAddressNumber());
		assertEquals("20th", actualAddress.getStreet().getStreet1().getStreetName().getName());

		actualAddress = addressParser.parseAddress("1301 K Street  Washington DC 20005");
		assertEquals("K", actualAddress.getStreet().getStreet1().getStreetName().getName());
		
		actualAddress = addressParser.parseAddress("3514 CJ Barney Dr  Washington DC 20018");
		assertEquals("CJ Barney", actualAddress.getStreet().getStreet1().getStreetName().getName());
		
		actualAddress = addressParser.parseAddress("1800 Massachesetts Avenue  Washington DC");
		assertFalse(actualAddress.isComplete());
	}
	
	@Override
	protected CityParser getAddressParser(AddressParserConfig config) {
		CityParser resultCityParser = new CityParserTest();
		return resultCityParser;
	}
	
	
	
	/**
	 * So what is this used for??????
	 * I'm glad you asked. When creating tests, I will use the address below and add all kinds
	 * of trick stuff to test for.
	 * This method allows us to check all these since they should always be set.
	 * 
	 */
	private void assert742EvergreenTerrace(Address actualAddress) {
		assertTrue(actualAddress.isValid());
		assertEquals("02111", actualAddress.getZipCode().getZipCode());
		assertEquals("MA", actualAddress.getState().getStateDefinition().getCode());
		assertEquals("SPRINGFIELD", actualAddress.getCity().getCityName());
		assertEquals(742, actualAddress.getStreet().getStreet1().getAddressNumber().getAddressNumber());
		assertEquals("Evergreen", actualAddress.getStreet().getStreet1().getStreetName().getName());
		assertEquals("TERRACE", actualAddress.getStreet().getStreet1().getStreetPostType().getStreetPostType().getDefualtValue());
	}
	
	
	// TODO: Add validation for case where we get a city and a state but the state is NOT in the city/Sate mapping
	// Example: LYNCHBURG is valid for 5 states, verify an address is valid if LYNCHBURG's state is in the map
	// How to handle this case????? "123 MONSVIEW PL LYNCHBURGS VA" should "LYNCHBURGS" be an invalid city????
	
	
	
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
