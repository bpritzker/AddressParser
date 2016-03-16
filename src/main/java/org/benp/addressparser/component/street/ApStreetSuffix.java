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
	public String getValue() {
		return streetSuffix.getName();
	}
	

}
