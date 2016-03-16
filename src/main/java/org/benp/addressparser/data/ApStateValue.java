package org.benp.addressparser.data;

import java.util.Set;

/**
 * These are the possible values for the State.
 * We don't want an enum so we can add values if we start parsing countries other than US
 * 
 * Make sure you call the init before using it.
 * 
 * 
 * @NotThreadSafe - The set used to store all the values is static. To make this thread safe you would need to 
 * make it a class variable.
 * 
 * 
 * 
 * @author Ben
 *
 */
public class ApStateValue {
	
	
	
	/**
	 * This is two char code for the state
	 * Ex: MA, CA, DC
	 * 
	 * Use this to normalize the state.
	 */
	private final String code;
	
	private final String standardName;

	
	/*
	 * This is the common name used for the state.
	 * (This one is used to look up user entered values)
	 * Ex: MA, MASS, MASSACHUSETTS 
	 */
	private final Set<String> commonNames;
	
	
	
	
	public ApStateValue(String inCode, String inStandardName, Set<String> inCommonNames) {
		this.code = inCode;
		this.standardName = inStandardName;
		this.commonNames = inCommonNames;
	}
	

	public String getCode() {
		return code;
	}

	public String getStandardName() {
		return standardName;
	}


	public Set<String> getCommonNames() {
		return commonNames;
	}

}
