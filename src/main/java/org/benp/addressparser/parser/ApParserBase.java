package org.benp.addressparser.parser;

import org.benp.addressparser.ApAddressParserConfig;

public abstract class ApParserBase {
	
	private ApAddressParserConfig config;

	public ApParserBase(ApAddressParserConfig config) {
		this.config = config;
	}

	public ApAddressParserConfig getConfig() {
		return config;
	}
	
	
	
}
