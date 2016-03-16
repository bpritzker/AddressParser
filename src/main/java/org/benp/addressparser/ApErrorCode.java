package org.benp.addressparser;

public enum ApErrorCode {

	INVALID_ADDRESS_ADDRESS_LENGTH("The address length must be non null and more than 5 characters.");
	
	
	private String details;
	
	private ApErrorCode(String details) {
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	
}
