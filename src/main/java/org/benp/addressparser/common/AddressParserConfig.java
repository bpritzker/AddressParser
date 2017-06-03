package org.benp.addressparser.common;

import java.io.File;

import org.benp.addressparser.component.State;

public class AddressParserConfig {
	
	public static String DEFAULT_COUNTRY_CODE = "US";
	
	private String defaultCountryCode;
	private State defaultState;
	private File dataDirectory;
	
	
	public AddressParserConfig() {
		defaultCountryCode = DEFAULT_COUNTRY_CODE;
		defaultState = null;
	}
	
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}

	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

	public State getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(State defaultState) {
		this.defaultState = defaultState;
	}


	
	
	
	public File getDataDirectory() {
		return dataDirectory;
	}

	public void setDataDirectory(File dataDirectory) {
		this.dataDirectory = dataDirectory;
	}


}
