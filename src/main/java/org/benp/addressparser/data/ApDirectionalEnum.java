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
	NORTHEAST("NORTH EAST", "Northeast", ImmutableSet.<String>of("NORTH EAST", "NORTHEAST", "NE", "N E")), 
	NORTHWEST("NORTH WEST", "Northwest", ImmutableSet.<String>of("NORTH WEST", "NORTHWEST", "NW", "N W")),
	SOUTHEAST("SOUTH EAST", "Southeast", ImmutableSet.<String>of("SOUTH EAST", "SOUTHEAST", "SE", "S E")), 
	SOUTHWEST("SOUTH WEST", "Southwest", ImmutableSet.<String>of("SOUTH WEST", "SOUTHWEST", "SW", "S W"));
	
	
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
