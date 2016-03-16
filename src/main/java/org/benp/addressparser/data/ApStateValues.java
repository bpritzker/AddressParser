package org.benp.addressparser.data;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class ApStateValues {
	
	private static Set<ApStateValue> values = new HashSet<>();
	
	public void init() {
		loadValues();
	}

	public Set<ApStateValue> getValues() {
		return values;
	}
	
	public ApStateValue fromCode(String inValue) {
		if (inValue == null) {
			return null;
		}
		
		for (ApStateValue currValue : values) {
			if (currValue.getCommonNames().contains(inValue)) {
				return currValue;
			}
		}
		return null;
	}
	

	protected void loadValues() {

		values = new HashSet<>();

		ApStateValue tempValue = new ApStateValue("IL", "ILLINOIS", 
				ImmutableSet.<String>of("IL","ILLINOIS"));
		values.add(tempValue);

		tempValue = new ApStateValue("MN", "MINNESOTA", 
				ImmutableSet.<String>of("MN","MINNESOTA"));
		values.add(tempValue);

		tempValue = new ApStateValue("AL", "ALABAMA", 
				ImmutableSet.<String>of("AL","ALABAMA"));
		values.add(tempValue);

		tempValue = new ApStateValue("NC", "NORTH CAROLINA", 
				ImmutableSet.<String>of("NC","NORTH CAROLINA","NORTHCAROLINA"));
		values.add(tempValue);

		tempValue = new ApStateValue("MT", "MONTANA", 
				ImmutableSet.<String>of("MT","MONTANA"));
		values.add(tempValue);

		tempValue = new ApStateValue("OK", "OKLAHOMA", 
				ImmutableSet.<String>of("OK","OKLAHOMA"));
		values.add(tempValue);

		tempValue = new ApStateValue("SC", "SOUTH CAROLINA", 
				ImmutableSet.<String>of("SC","SOUTH CAROLINA","SOUTHCAROLINA"));
		values.add(tempValue);

		tempValue = new ApStateValue("VI", "VIRGIN ISLANDS", 
				ImmutableSet.<String>of("VI","VIRGIN ISLANDS","VIRGINISLANDS"));
		values.add(tempValue);

		tempValue = new ApStateValue("NJ", "NEW JERSEY", 
				ImmutableSet.<String>of("NJ","NEW JERSEY","NEWJERSEY"));
		values.add(tempValue);

		tempValue = new ApStateValue("AZ", "ARIZONA", 
				ImmutableSet.<String>of("AZ","ARIZONA"));
		values.add(tempValue);

		tempValue = new ApStateValue("ID", "IDAHO", 
				ImmutableSet.<String>of("ID","IDAHO"));
		values.add(tempValue);

		tempValue = new ApStateValue("WA", "WASHINGTON", 
				ImmutableSet.<String>of("WA","WASHINGTON"));
		values.add(tempValue);

		tempValue = new ApStateValue("GU", "GUAM", 
				ImmutableSet.<String>of("GU","GUAM"));
		values.add(tempValue);

		tempValue = new ApStateValue("CO", "COLORADO", 
				ImmutableSet.<String>of("CO","COLORADO"));
		values.add(tempValue);

		tempValue = new ApStateValue("AS", "AMERICAN SAMOA", 
				ImmutableSet.<String>of("AS","AMERICAN SAMOA","AMERICANSAMOA"));
		values.add(tempValue);

		tempValue = new ApStateValue("UT", "UTAH", 
				ImmutableSet.<String>of("UT","UTAH"));
		values.add(tempValue);

		tempValue = new ApStateValue("MH", "MARSHALL ISLANDS", 
				ImmutableSet.<String>of("MH","MARSHALL ISLANDS","MARSHALLISLANDS"));
		values.add(tempValue);

		tempValue = new ApStateValue("GA", "GEORGIA", 
				ImmutableSet.<String>of("GA","GEORGIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("DE", "DELAWARE", 
				ImmutableSet.<String>of("DE","DELAWARE"));
		values.add(tempValue);

		tempValue = new ApStateValue("CA", "CALIFORNIA", 
				ImmutableSet.<String>of("CA","CALIFORNIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("AR", "ARKANSAS", 
				ImmutableSet.<String>of("AR","ARKANSAS"));
		values.add(tempValue);

		tempValue = new ApStateValue("PA", "PENNSYLVANIA", 
				ImmutableSet.<String>of("PA","PENNSYLVANIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("OR", "OREGON", 
				ImmutableSet.<String>of("OR","OREGON"));
		values.add(tempValue);

		tempValue = new ApStateValue("FL", "FLORIDA", 
				ImmutableSet.<String>of("FL","FLORIDA"));
		values.add(tempValue);

		tempValue = new ApStateValue("KS", "KANSAS", 
				ImmutableSet.<String>of("KS","KANSAS"));
		values.add(tempValue);

		tempValue = new ApStateValue("HI", "HAWAII", 
				ImmutableSet.<String>of("HI","HAWAII"));
		values.add(tempValue);

		tempValue = new ApStateValue("IA", "IOWA", 
				ImmutableSet.<String>of("IA","IOWA"));
		values.add(tempValue);

		tempValue = new ApStateValue("NM", "NEW MEXICO", 
				ImmutableSet.<String>of("NM","NEW MEXICO","NEWMEXICO"));
		values.add(tempValue);

		tempValue = new ApStateValue("VT", "VERMONT", 
				ImmutableSet.<String>of("VT","VERMONT"));
		values.add(tempValue);

		tempValue = new ApStateValue("AA", "U.S. ARMED FORCES – AMERICAS", 
				ImmutableSet.<String>of("AA","U.S. ARMED FORCES – AMERICAS","USARMEDFORCESAMERICAS"));
		values.add(tempValue);

		tempValue = new ApStateValue("WI", "WISCONSIN", 
				ImmutableSet.<String>of("WI","WISCONSIN"));
		values.add(tempValue);

		tempValue = new ApStateValue("WV", "WEST VIRGINIA", 
				ImmutableSet.<String>of("WV","WEST VIRGINIA","WESTVIRGINIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("OH", "OHIO", 
				ImmutableSet.<String>of("OH","OHIO"));
		values.add(tempValue);

		tempValue = new ApStateValue("AK", "ALASKA", 
				ImmutableSet.<String>of("AK","ALASKA"));
		values.add(tempValue);

		tempValue = new ApStateValue("NV", "NEVADA", 
				ImmutableSet.<String>of("NV","NEVADA"));
		values.add(tempValue);

		tempValue = new ApStateValue("VA", "VIRGINIA", 
				ImmutableSet.<String>of("VA","VIRGINIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("NH", "NEW HAMPSHIRE", 
				ImmutableSet.<String>of("NH","NEW HAMPSHIRE","NEWHAMPSHIRE"));
		values.add(tempValue);

		tempValue = new ApStateValue("MD", "MARYLAND", 
				ImmutableSet.<String>of("MD","MARYLAND"));
		values.add(tempValue);

		tempValue = new ApStateValue("PW", "PALAU", 
				ImmutableSet.<String>of("PW","PALAU"));
		values.add(tempValue);

		tempValue = new ApStateValue("CT", "CONNECTICUT", 
				ImmutableSet.<String>of("CT","CONNECTICUT"));
		values.add(tempValue);

		tempValue = new ApStateValue("NE", "NEBRASKA", 
				ImmutableSet.<String>of("NE","NEBRASKA"));
		values.add(tempValue);

		tempValue = new ApStateValue("AP", "U.S. ARMED FORCES – PACIFIC", 
				ImmutableSet.<String>of("AP","U.S. ARMED FORCES – PACIFIC","USARMEDFORCESPACIFIC"));
		values.add(tempValue);

		tempValue = new ApStateValue("MI", "MICHIGAN", 
				ImmutableSet.<String>of("MI","MICHIGAN"));
		values.add(tempValue);

		tempValue = new ApStateValue("MO", "MISSOURI", 
				ImmutableSet.<String>of("MO","MISSOURI"));
		values.add(tempValue);

		tempValue = new ApStateValue("ME", "MAINE", 
				ImmutableSet.<String>of("ME","MAINE"));
		values.add(tempValue);

		tempValue = new ApStateValue("AE", "U.S. ARMED FORCES – EUROPE", 
				ImmutableSet.<String>of("AE","U.S. ARMED FORCES – EUROPE","USARMEDFORCESEUROPE"));
		values.add(tempValue);

		tempValue = new ApStateValue("PR", "PUERTO RICO", 
				ImmutableSet.<String>of("PR","PUERTO RICO","PUERTORICO"));
		values.add(tempValue);

		tempValue = new ApStateValue("TN", "TENNESSEE", 
				ImmutableSet.<String>of("TN","TENNESSEE"));
		values.add(tempValue);

		tempValue = new ApStateValue("MA", "MASSACHUSETTS", 
				ImmutableSet.<String>of("MA","MASS","MASSACHUSETTS"));
		values.add(tempValue);

		tempValue = new ApStateValue("FM", "FEDERATED STATES OF MICRONESIA", 
				ImmutableSet.<String>of("FM","FEDERATED STATES OF MICRONESIA","FEDERATEDSTATESOFMICRONESIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("DC", "DISTRICT OF COLUMBIA", 
				ImmutableSet.<String>of("DC","DISTRICT OF COLUMBIA","DISTRICTOFCOLUMBIA"));
		values.add(tempValue);

		tempValue = new ApStateValue("TX", "TEXAS", 
				ImmutableSet.<String>of("TX","TEXAS"));
		values.add(tempValue);

		tempValue = new ApStateValue("IN", "INDIANA", 
				ImmutableSet.<String>of("IN","INDIANA"));
		values.add(tempValue);

		tempValue = new ApStateValue("SD", "SOUTH DAKOTA", 
				ImmutableSet.<String>of("SD","SOUTH DAKOTA","SOUTHDAKOTA"));
		values.add(tempValue);

		tempValue = new ApStateValue("KY", "KENTUCKY", 
				ImmutableSet.<String>of("KY","KENTUCKY"));
		values.add(tempValue);

		tempValue = new ApStateValue("RI", "RHODE ISLAND", 
				ImmutableSet.<String>of("RI","RHODE ISLAND","RHODEISLAND"));
		values.add(tempValue);

		tempValue = new ApStateValue("MS", "MISSISSIPPI", 
				ImmutableSet.<String>of("MS","MISSISSIPPI","MISS"));
		values.add(tempValue);

		tempValue = new ApStateValue("MP", "NORTHERN MARIANA ISLANDS", 
				ImmutableSet.<String>of("MP","NORTHERN MARIANA ISLANDS","NORTHERNMARIANAISLANDS"));
		values.add(tempValue);

		tempValue = new ApStateValue("LA", "LOUISIANA", 
				ImmutableSet.<String>of("LA","LOUISIANA"));
		values.add(tempValue);

		tempValue = new ApStateValue("ND", "NORTH DAKOTA", 
				ImmutableSet.<String>of("ND","NORTH DAKOTA","NORTHDAKOTA"));
		values.add(tempValue);

		tempValue = new ApStateValue("WY", "WYOMING", 
				ImmutableSet.<String>of("WY","WYOMING"));
		values.add(tempValue);

		tempValue = new ApStateValue("NY", "NEW YORK", 
				ImmutableSet.<String>of("NY","NEW YORK","NEWYORK"));
		values.add(tempValue);
	}
	
}
