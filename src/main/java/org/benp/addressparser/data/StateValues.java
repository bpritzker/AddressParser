package org.benp.addressparser.data;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class StateValues {
	
	private static Set<StateValue> values = new HashSet<>();
	
	public void init() {
		loadValues();
	}

	public Set<StateValue> getValues() {
		return values;
	}
	
	public StateValue fromCode(String inValue) {
		if (inValue == null) {
			return null;
		}
		
		for (StateValue currValue : values) {
			if (currValue.getCommonNames().contains(inValue)) {
				return currValue;
			}
		}
		return null;
	}
	

	protected void loadValues() {

		values = new HashSet<>();

		StateValue tempValue = new StateValue("IL", "ILLINOIS", 
				ImmutableSet.<String>of("IL","ILLINOIS"));
		values.add(tempValue);

		tempValue = new StateValue("MN", "MINNESOTA", 
				ImmutableSet.<String>of("MN","MINNESOTA"));
		values.add(tempValue);

		tempValue = new StateValue("AL", "ALABAMA", 
				ImmutableSet.<String>of("AL","ALABAMA"));
		values.add(tempValue);

		tempValue = new StateValue("NC", "NORTH CAROLINA", 
				ImmutableSet.<String>of("NC","NORTH CAROLINA","NORTHCAROLINA"));
		values.add(tempValue);

		tempValue = new StateValue("MT", "MONTANA", 
				ImmutableSet.<String>of("MT","MONTANA"));
		values.add(tempValue);

		tempValue = new StateValue("OK", "OKLAHOMA", 
				ImmutableSet.<String>of("OK","OKLAHOMA"));
		values.add(tempValue);

		tempValue = new StateValue("SC", "SOUTH CAROLINA", 
				ImmutableSet.<String>of("SC","SOUTH CAROLINA","SOUTHCAROLINA"));
		values.add(tempValue);

		tempValue = new StateValue("VI", "VIRGIN ISLANDS", 
				ImmutableSet.<String>of("VI","VIRGIN ISLANDS","VIRGINISLANDS"));
		values.add(tempValue);

		tempValue = new StateValue("NJ", "NEW JERSEY", 
				ImmutableSet.<String>of("NJ","NEW JERSEY","NEWJERSEY"));
		values.add(tempValue);

		tempValue = new StateValue("AZ", "ARIZONA", 
				ImmutableSet.<String>of("AZ","ARIZONA"));
		values.add(tempValue);

		tempValue = new StateValue("ID", "IDAHO", 
				ImmutableSet.<String>of("ID","IDAHO"));
		values.add(tempValue);

		tempValue = new StateValue("WA", "WASHINGTON", 
				ImmutableSet.<String>of("WA","WASHINGTON"));
		values.add(tempValue);

		tempValue = new StateValue("GU", "GUAM", 
				ImmutableSet.<String>of("GU","GUAM"));
		values.add(tempValue);

		tempValue = new StateValue("CO", "COLORADO", 
				ImmutableSet.<String>of("CO","COLORADO"));
		values.add(tempValue);

		tempValue = new StateValue("AS", "AMERICAN SAMOA", 
				ImmutableSet.<String>of("AS","AMERICAN SAMOA","AMERICANSAMOA"));
		values.add(tempValue);

		tempValue = new StateValue("UT", "UTAH", 
				ImmutableSet.<String>of("UT","UTAH"));
		values.add(tempValue);

		tempValue = new StateValue("MH", "MARSHALL ISLANDS", 
				ImmutableSet.<String>of("MH","MARSHALL ISLANDS","MARSHALLISLANDS"));
		values.add(tempValue);

		tempValue = new StateValue("GA", "GEORGIA", 
				ImmutableSet.<String>of("GA","GEORGIA"));
		values.add(tempValue);

		tempValue = new StateValue("DE", "DELAWARE", 
				ImmutableSet.<String>of("DE","DELAWARE"));
		values.add(tempValue);

		tempValue = new StateValue("CA", "CALIFORNIA", 
				ImmutableSet.<String>of("CA","CALIFORNIA"));
		values.add(tempValue);

		tempValue = new StateValue("AR", "ARKANSAS", 
				ImmutableSet.<String>of("AR","ARKANSAS"));
		values.add(tempValue);

		tempValue = new StateValue("PA", "PENNSYLVANIA", 
				ImmutableSet.<String>of("PA","PENNSYLVANIA"));
		values.add(tempValue);

		tempValue = new StateValue("OR", "OREGON", 
				ImmutableSet.<String>of("OR","OREGON"));
		values.add(tempValue);

		tempValue = new StateValue("FL", "FLORIDA", 
				ImmutableSet.<String>of("FL","FLORIDA"));
		values.add(tempValue);

		tempValue = new StateValue("KS", "KANSAS", 
				ImmutableSet.<String>of("KS","KANSAS"));
		values.add(tempValue);

		tempValue = new StateValue("HI", "HAWAII", 
				ImmutableSet.<String>of("HI","HAWAII"));
		values.add(tempValue);

		tempValue = new StateValue("IA", "IOWA", 
				ImmutableSet.<String>of("IA","IOWA"));
		values.add(tempValue);

		tempValue = new StateValue("NM", "NEW MEXICO", 
				ImmutableSet.<String>of("NM","NEW MEXICO","NEWMEXICO"));
		values.add(tempValue);

		tempValue = new StateValue("VT", "VERMONT", 
				ImmutableSet.<String>of("VT","VERMONT"));
		values.add(tempValue);

		tempValue = new StateValue("AA", "U.S. ARMED FORCES – AMERICAS", 
				ImmutableSet.<String>of("AA","U.S. ARMED FORCES – AMERICAS","USARMEDFORCESAMERICAS"));
		values.add(tempValue);

		tempValue = new StateValue("WI", "WISCONSIN", 
				ImmutableSet.<String>of("WI","WISCONSIN"));
		values.add(tempValue);

		tempValue = new StateValue("WV", "WEST VIRGINIA", 
				ImmutableSet.<String>of("WV","WEST VIRGINIA","WESTVIRGINIA"));
		values.add(tempValue);

		tempValue = new StateValue("OH", "OHIO", 
				ImmutableSet.<String>of("OH","OHIO"));
		values.add(tempValue);

		tempValue = new StateValue("AK", "ALASKA", 
				ImmutableSet.<String>of("AK","ALASKA"));
		values.add(tempValue);

		tempValue = new StateValue("NV", "NEVADA", 
				ImmutableSet.<String>of("NV","NEVADA"));
		values.add(tempValue);

		tempValue = new StateValue("VA", "VIRGINIA", 
				ImmutableSet.<String>of("VA","VIRGINIA"));
		values.add(tempValue);

		tempValue = new StateValue("NH", "NEW HAMPSHIRE", 
				ImmutableSet.<String>of("NH","NEW HAMPSHIRE","NEWHAMPSHIRE"));
		values.add(tempValue);

		tempValue = new StateValue("MD", "MARYLAND", 
				ImmutableSet.<String>of("MD","MARYLAND"));
		values.add(tempValue);

		tempValue = new StateValue("PW", "PALAU", 
				ImmutableSet.<String>of("PW","PALAU"));
		values.add(tempValue);

		tempValue = new StateValue("CT", "CONNECTICUT", 
				ImmutableSet.<String>of("CT","CONNECTICUT"));
		values.add(tempValue);

		tempValue = new StateValue("NE", "NEBRASKA", 
				ImmutableSet.<String>of("NE","NEBRASKA"));
		values.add(tempValue);

		tempValue = new StateValue("AP", "U.S. ARMED FORCES – PACIFIC", 
				ImmutableSet.<String>of("AP","U.S. ARMED FORCES – PACIFIC","USARMEDFORCESPACIFIC"));
		values.add(tempValue);

		tempValue = new StateValue("MI", "MICHIGAN", 
				ImmutableSet.<String>of("MI","MICHIGAN"));
		values.add(tempValue);

		tempValue = new StateValue("MO", "MISSOURI", 
				ImmutableSet.<String>of("MO","MISSOURI"));
		values.add(tempValue);

		tempValue = new StateValue("ME", "MAINE", 
				ImmutableSet.<String>of("ME","MAINE"));
		values.add(tempValue);

		tempValue = new StateValue("AE", "U.S. ARMED FORCES – EUROPE", 
				ImmutableSet.<String>of("AE","U.S. ARMED FORCES – EUROPE","USARMEDFORCESEUROPE"));
		values.add(tempValue);

		tempValue = new StateValue("PR", "PUERTO RICO", 
				ImmutableSet.<String>of("PR","PUERTO RICO","PUERTORICO"));
		values.add(tempValue);

		tempValue = new StateValue("TN", "TENNESSEE", 
				ImmutableSet.<String>of("TN","TENNESSEE"));
		values.add(tempValue);

		tempValue = new StateValue("MA", "MASSACHUSETTS", 
				ImmutableSet.<String>of("MA","MASS","MASSACHUSETTS"));
		values.add(tempValue);

		tempValue = new StateValue("FM", "FEDERATED STATES OF MICRONESIA", 
				ImmutableSet.<String>of("FM","FEDERATED STATES OF MICRONESIA","FEDERATEDSTATESOFMICRONESIA"));
		values.add(tempValue);

		tempValue = new StateValue("DC", "DISTRICT OF COLUMBIA", 
				ImmutableSet.<String>of("DC","DISTRICT OF COLUMBIA","DISTRICTOFCOLUMBIA"));
		values.add(tempValue);

		tempValue = new StateValue("TX", "TEXAS", 
				ImmutableSet.<String>of("TX","TEXAS"));
		values.add(tempValue);

		tempValue = new StateValue("IN", "INDIANA", 
				ImmutableSet.<String>of("IN","INDIANA"));
		values.add(tempValue);

		tempValue = new StateValue("SD", "SOUTH DAKOTA", 
				ImmutableSet.<String>of("SD","SOUTH DAKOTA","SOUTHDAKOTA"));
		values.add(tempValue);

		tempValue = new StateValue("KY", "KENTUCKY", 
				ImmutableSet.<String>of("KY","KENTUCKY"));
		values.add(tempValue);

		tempValue = new StateValue("RI", "RHODE ISLAND", 
				ImmutableSet.<String>of("RI","RHODE ISLAND","RHODEISLAND"));
		values.add(tempValue);

		tempValue = new StateValue("MS", "MISSISSIPPI", 
				ImmutableSet.<String>of("MS","MISSISSIPPI","MISS"));
		values.add(tempValue);

		tempValue = new StateValue("MP", "NORTHERN MARIANA ISLANDS", 
				ImmutableSet.<String>of("MP","NORTHERN MARIANA ISLANDS","NORTHERNMARIANAISLANDS"));
		values.add(tempValue);

		tempValue = new StateValue("LA", "LOUISIANA", 
				ImmutableSet.<String>of("LA","LOUISIANA"));
		values.add(tempValue);

		tempValue = new StateValue("ND", "NORTH DAKOTA", 
				ImmutableSet.<String>of("ND","NORTH DAKOTA","NORTHDAKOTA"));
		values.add(tempValue);

		tempValue = new StateValue("WY", "WYOMING", 
				ImmutableSet.<String>of("WY","WYOMING"));
		values.add(tempValue);

		tempValue = new StateValue("NY", "NEW YORK", 
				ImmutableSet.<String>of("NY","NEW YORK","NEWYORK"));
		values.add(tempValue);
	}
	
}
