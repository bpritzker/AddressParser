package org.benp.addressparser.common;

public enum ErrorCode {

	INVALID_ADDRESS_ADDRESS_LENGTH("The address length must be non null and more than 5 characters.");
	
	
	private String details;
	
	private ErrorCode(String details) {
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	
}
