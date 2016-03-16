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

	NORTH("N", ImmutableSet.<String>of("NORTH", "N")), 
	SOUTH("S", ImmutableSet.<String>of("SOUTH", "S")), 
	EAST("E", ImmutableSet.<String>of("EAST", "E")), 
	WEST("W", ImmutableSet.<String>of("WEST", "W")), 
	NORTHEAST("NE", ImmutableSet.<String>of("NORTH EAST", "NE", "N E")), 
	NORTHWEST("NW", ImmutableSet.<String>of("NORTH WEST", "NW", "N W")),
	SOUTHEAST("SE", ImmutableSet.<String>of("SOUTH EAST", "SE", "S E")), 
	SOUTHWEST("SW", ImmutableSet.<String>of("SOUTH WEST", "SW", "S W"));
	
	
	private final Set<String> mappings;
	private final String value;
	


	private ApDirectionalEnum(String value, Set<String> mappings) {
		this.value = value;
		this.mappings = mappings;
	}

	public static ApDirectionalEnum fromMapping(String mappingName) {
		if (mappingName == null) {
			return null;
		}
		
		for (ApDirectionalEnum currDirectional : ApDirectionalEnum.values()) {
			if (currDirectional.mappings.contains(mappingName)) {
				return currDirectional;
			}
		}
		return null;
	}
	
	public String getValue() {
		return value;
	}
	
}
