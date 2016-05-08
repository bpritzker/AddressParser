package org.benp.addressparser.parser;

import org.benp.addressparser.AddressParserConfig;

public abstract class ParserBase {
	
	private AddressParserConfig config;

	public ParserBase(AddressParserConfig config) {
		this.config = config;
	}

	public AddressParserConfig getConfig() {
		return config;
	}
	
	
	
}
