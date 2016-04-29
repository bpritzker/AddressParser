package org.benp.addressparser.data;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * At some point this might need to be a class to handle international.
 * 1.7.2.2 Street Name Pre Directional 
 * For now we ignore the case of "North E", we can always add it later
 * 
 * @author Ben
 *
 */
public enum ApDirectionalEnum {

	NORTH("NORTH", "North", ImmutableSet.<String>of("NORTH", "N")), 
	SOUTH("SOUTH", "South", ImmutableSet.<String>of("SOUTH", "S")), 
	EAST("EAST", "East", ImmutableSet.<String>of("EAST", "E")), 
	WEST("WEST", "West", ImmutableSet.<String>of("WEST", "W")), 
	NORTHEAST("NORTH EAST", "North East", ImmutableSet.<String>of("NORTH EAST", "NE", "N E")), 
	NORTHWEST("NORTH WEST", "North West", ImmutableSet.<String>of("NORTH WEST", "NW", "N W")),
	SOUTHEAST("SOUTH EAST", "South East", ImmutableSet.<String>of("SOUTH EAST", "SE", "S E")), 
	SOUTHWEST("SOUTH WEST", "South West", ImmutableSet.<String>of("SOUTH WEST", "SW", "S W"));
	
	
	private final String value;
	private final String normalizedValue;
	private final Set<String> mappings;

	private ApDirectionalEnum(String value, String normalizedValue, Set<String> mappings) {
		this.value = value;
		this.normalizedValue = normalizedValue;
		this.mappings = mappings;
	}

	public static ApDirectionalEnum fromMapping(String mappingName) {
		if (mappingName == null) {
			return null;
		}
		
		for (ApDirectionalEnum currDirectional : ApDirectionalEnum.values()) {
			if (currDirectional.mappings.contains(mappingName.toUpperCase())) {
				return currDirectional;
			}
		}
		return null;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getNormalizedValue() {
		return normalizedValue;
	}
	
	
}
