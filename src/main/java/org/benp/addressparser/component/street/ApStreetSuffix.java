package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ApComponentBase;
import org.benp.addressparser.data.ApStreetSuffixEnum;

public class ApStreetSuffix extends ApComponentBase {

	private ApStreetSuffixEnum streetSuffix;

	public ApStreetSuffixEnum getStreetSuffix() {
		return streetSuffix;
	}

	public void setStreetSuffix(ApStreetSuffixEnum streetSuffix) {
		this.streetSuffix = streetSuffix;
	}

	@Override
	// Test the null case
	public String getValue() {
		if (streetSuffix == null) {
			return null;
		}
		return streetSuffix.getName();
	}

	public String getNormalizedValue() {
		if (streetSuffix == null) {
			return null;
		}
		String tempName = streetSuffix.getName();
		String resultNormalized = tempName.substring(0, 1).toUpperCase() + tempName.substring(1).toLowerCase();
		return resultNormalized;
	}
	

}
