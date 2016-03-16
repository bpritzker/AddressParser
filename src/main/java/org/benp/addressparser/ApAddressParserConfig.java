package org.benp.addressparser;

import java.io.File;

import org.benp.addressparser.component.ApState;

public class ApAddressParserConfig {
	
	public static String DEFAULT_COUNTRY_CODE = "US";
	
	private String defaultCountryCode;
	private ApState defaultState;
	
	
	private File dataDirectory;
	
	
	public ApAddressParserConfig() {
		defaultCountryCode = DEFAULT_COUNTRY_CODE;
		defaultState = null;
	}
	
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}

	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

	public ApState getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(ApState defaultState) {
		this.defaultState = defaultState;
	}


	
	
	
	public File getDataDirectory() {
		return dataDirectory;
	}

	public void setDataDirectory(File dataDirectory) {
		this.dataDirectory = dataDirectory;
	}


}
