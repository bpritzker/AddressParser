package org.benp.addressparser;

import org.benp.addressparser.component.Address;
import org.benp.addressparser.component.City;
import org.benp.addressparser.component.SimpleAddress;
import org.benp.addressparser.component.State;
import org.benp.addressparser.component.ZipCode;
import org.benp.addressparser.component.street.Street;
import org.benp.addressparser.data.normalize.Mapper;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.CityParser;
import org.benp.addressparser.parser.StateParser;
import org.benp.addressparser.parser.ZipCodeParser;
import org.benp.addressparser.parser.street.StreetParser;

public class AddressParser {
	
//	private static final Logger	logger	=  LogManager.getLogger(ApAddressParser.class.getName());
	
	private StreetParser streetParser;
	private CityParser cityParser;
	private StateParser stateParser;
	private ZipCodeParser zipCodeParser;
	private Mapper mapper;
//	private final ApAddressParserConfig addressParserConfig; // Will comment back in when I start using it. 
	
	
	
	/**
	 * A simple easy to use constructor.
	 * Assumes some defaults that can all be changed.
	 * @throws ApException - Throws Exception if unable to load mappers
	 */
	public AddressParser() throws ApException {
		// Use all the defaults
		this(new AddressParserConfig());
	}
	
	
	/**
	 * Use this constructor if you don't want to use the defaults
	 * @throws ApException 
	 */
	public AddressParser(final AddressParserConfig inAddressParserConfig) throws ApException {
//		this.addressParserConfig = addressParserConfig;
		streetParser = new StreetParser(mapper, inAddressParserConfig);
		cityParser = getAddressParser(inAddressParserConfig);
		stateParser = new StateParser(inAddressParserConfig);
		zipCodeParser = new ZipCodeParser(inAddressParserConfig);
		mapper = new Mapper(inAddressParserConfig);
	}


	/**
	 * Want to get started quickly... use this. It takes in an address as a single string 
	 * will parse it and just exposes the most basic methods to get the data. 
	 * </B>
	 * Basically, it's "Simple" easy to use.
	 * </B>
	 * NOTE: This swallows the ApException!!!!!!
	 * If you want to parse the address correctly then use the main address parse. If it works you get 
	 * your address. If not, you get a null.
	 * @throws ApException 
	 */
	public SimpleAddress parseAddressSimple(final String address) {
		SimpleAddress resultSimpleAddress = null;
		try {
			Address tempAddress = parse(address);
			resultSimpleAddress = new SimpleAddress(tempAddress);
			
			resultSimpleAddress = new SimpleAddress(tempAddress);
		} catch (ApException ap) {
			// Seriously, swallow this. It's a simple parser. If it doesn't work then just return null.
		}
		return resultSimpleAddress;
	}
	
	

	/**
	 * Use this method to parse an address!
	 * @param addressString - A string that contains an Address
	 */
	public Address parseAddress(final String addressString) throws ApException {
		
		Address resultAddress;
		
		// There is no real addresses below 4 characters
		if (addressString == null || addressString.length() < 5) {
			resultAddress = new Address();
			resultAddress.setErrorCode(ErrorCode.INVALID_ADDRESS_ADDRESS_LENGTH);
			return resultAddress;
		}
		
		resultAddress = parse(addressString);
		return resultAddress;
	}
	
	public Address parseAddress(String street1, String street2, String city, String state, String zipCode) throws ApException {
		
		// TODO: Add test for this....
		Street apStreet = null;
		if (street1 != null || street2 != null) {
			String streetToSplit = null;
			if (street1 != null) {
				streetToSplit = street1;
			}
			
			if (street2 != null) {
				if (streetToSplit == null) {
					streetToSplit = street2;
				} else {
					streetToSplit += " " + street2;
				}
			}
			
			ApSplitter streetSplits = new ApSplitter(streetToSplit); 
			apStreet = streetParser.parse(streetSplits);
		}
		
		City apCity = null;
		if (city != null) {
			ApSplitter citySplits = new ApSplitter(city);
			apCity = cityParser.parse(citySplits);
		}
		
		State apState = null;
		if (state != null) {
			ApSplitter stateSplits = new ApSplitter(state);
			apState = stateParser.parse(stateSplits);
		}
		
		ZipCode apZipCode = null;
		if (zipCode != null) {
			ApSplitter zipCodeSplits = new ApSplitter(zipCode);
			apZipCode = zipCodeParser.parse(zipCodeSplits);
		}
		
		
		Address resultAddress = new Address();
		resultAddress.setStreet(apStreet);
		resultAddress.setCity(apCity);
		resultAddress.setState(apState);
		resultAddress.setZipCode(apZipCode);
		
		return resultAddress;
	}


	public Street parseStreet(final String street) throws ApException {
		ApSplitter splitter = new ApSplitter(street);
		Street resultStreet = streetParser.parse(splitter);
		return resultStreet;
	}
	
	
	
	private Address parse(final String address) throws ApException {

		// This will split into usable sections
		ApSplitter splitter = new ApSplitter(address);

		
		ZipCode zipCode = zipCodeParser.parse(splitter);
		State state = stateParser.parse(splitter);
		City city = cityParser.parse(splitter);
		Street street = streetParser.parse(splitter);
		
		Address resultAddress = new Address();
		resultAddress.setOrigString(address);
		resultAddress.setZipCode(zipCode);
		resultAddress.setState(state);
		resultAddress.setCity(city);
		resultAddress.setStreet(street);
		
		// a valid address can have either a valid zip code OR valid city and state
		// TODO: make a valid address configurable.
		if (street.isValid()) {
			if (zipCode.isValid() || (state.isValid() && city.isValid())) {
				resultAddress.setValid(true);
			}
		}
		return resultAddress;
	}
	
	

	
	/*
	 * Override this for testing with mock object
	 */
	protected CityParser getAddressParser(AddressParserConfig config) {
		return new CityParser(null, config);
	}


}
