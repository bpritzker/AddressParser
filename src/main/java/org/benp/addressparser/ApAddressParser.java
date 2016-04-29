package org.benp.addressparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.benp.addressparser.component.ApAddress;
import org.benp.addressparser.component.ApCity;
import org.benp.addressparser.component.ApState;
import org.benp.addressparser.component.ApZipCode;
import org.benp.addressparser.component.street.ApStreet;
import org.benp.addressparser.parser.ApCityParser;
import org.benp.addressparser.parser.ApSplitter;
import org.benp.addressparser.parser.ApStateParser;
import org.benp.addressparser.parser.ApZipCodeParser;
import org.benp.addressparser.parser.street.ApStreetParser;

public class ApAddressParser {
	
	private static final Logger	logger	=  LogManager.getLogger(ApAddressParser.class.getName());
	
	private ApStreetParser streetParser;
	private ApCityParser cityParser;
	private ApStateParser stateParser;
	private ApZipCodeParser zipCodeParser;
//	private final ApAddressParserConfig addressParserConfig; // Will comment back in when I start using it. 
	
	
	
	/**
	 * A simple easy to use constructor.
	 * Assumes some defaults that can all be changed.
	 * @throws ApException 
	 */
	public ApAddressParser() {
		// Use all the defaults
		this(new ApAddressParserConfig());
	}
	
	
	/**
	 * Use this constructor if you don't want to use the defaults
	 * @throws ApException 
	 */
	public ApAddressParser(final ApAddressParserConfig addressParserConfig) {
//		this.addressParserConfig = addressParserConfig;
		streetParser = new ApStreetParser(addressParserConfig);
		cityParser = getAddressParser(addressParserConfig);
		stateParser = new ApStateParser(addressParserConfig);
		zipCodeParser = new ApZipCodeParser(addressParserConfig);
	}



	/**
	 * Use this method to parse an address!
	 * @param addressString - A string that contains an Address
	 */
	public ApAddress parseAddress(final String addressString) throws ApException {
		logger.trace("parseAddress called with '{}'", addressString);
		
		ApAddress resultAddress;
		
		// There is no real addresses below 4 characters
		if (addressString == null || addressString.length() < 5) {
			resultAddress = new ApAddress();
			resultAddress.setErrorCode(ApErrorCode.INVALID_ADDRESS_ADDRESS_LENGTH);
			return resultAddress;
		}
		
		resultAddress = parse(addressString);
		return resultAddress;
	}
	
	public ApAddress parseAddress(String street1, String street2, String city, String state, String zipCode) throws ApException {
		
		// TODO: Add test for this....
		ApStreet apStreet = null;
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
		
		ApCity apCity = null;
		if (city != null) {
			ApSplitter citySplits = new ApSplitter(city);
			apCity = cityParser.parse(citySplits);
		}
		
		ApState apState = null;
		if (state != null) {
			ApSplitter stateSplits = new ApSplitter(state);
			apState = stateParser.parse(stateSplits);
		}
		
		ApZipCode apZipCode = null;
		if (zipCode != null) {
			ApSplitter zipCodeSplits = new ApSplitter(zipCode);
			apZipCode = zipCodeParser.parse(zipCodeSplits);
		}
		
		
		ApAddress resultAddress = new ApAddress();
		resultAddress.setStreet(apStreet);
		resultAddress.setCity(apCity);
		resultAddress.setState(apState);
		resultAddress.setZipCode(apZipCode);
		
		return resultAddress;
	}


	public ApStreet parseStreet(final String street) throws ApException {
		ApSplitter splitter = new ApSplitter(street);
		ApStreet resultStreet = streetParser.parse(splitter);
		return resultStreet;
	}
	
	
	
	private ApAddress parse(final String address) throws ApException {

		// This will split into usable sections
		ApSplitter splitter = new ApSplitter(address);

		
		ApZipCode zipCode = zipCodeParser.parse(splitter);
		ApState state = stateParser.parse(splitter);
		ApCity city = cityParser.parse(splitter);
		ApStreet street = streetParser.parse(splitter);
		
		ApAddress resultAddress = new ApAddress();
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
	protected ApCityParser getAddressParser(ApAddressParserConfig config) {
		return new ApCityParser(config);
	}


}
