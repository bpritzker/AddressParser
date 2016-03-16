package org.benp.addressparser.component;

public class ApZipCode extends ApComponentBase {
	
	public static final int INVALID_ZIP_CODE = -1;
	
	
	private String zipCode;
	/**
	 * These are the second part of the zip code, the 4 extra digits. optional
	 */
	private String plus4Code;
	
	
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPlus4Code() {
		return plus4Code;
	}
	public void setPlus4Code(String plus4Code) {
		this.plus4Code = plus4Code;
	}
	
	
	@Override
	public String getValue() {
		
		if (zipCode == null) {
			return "";
		}
		
		String resultValue = zipCode;
		if (plus4Code != null) {
			resultValue += "-" + plus4Code;
		}
		return resultValue;
	}
	

	
}
