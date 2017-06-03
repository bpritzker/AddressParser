package org.benp.addressparser.parser;

import org.benp.addressparser.common.AddressParserConfig;

public abstract class ParserBase {
	
	private AddressParserConfig config;

	public ParserBase(AddressParserConfig inConfig) {
		this.config = inConfig;
	}

	public AddressParserConfig getConfig() {
		return config;
	}
	
	
	
}
