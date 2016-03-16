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

	NORTH(ImmutableSet.<String>of("NORTH", "N")), 
	SOUTH(ImmutableSet.<String>of("SOUTH", "S")), 
	EAST(ImmutableSet.<String>of("EAST", "E")), 
	WEST(ImmutableSet.<String>of("WEST", "W")), 
	NORTHEAST(ImmutableSet.<String>of("NORTH EAST", "NE", "N E")), 
	NORTHWEST(ImmutableSet.<String>of("NORTH WEST", "NW", "N W")),
	SOUTHEAST(ImmutableSet.<String>of("SOUTH EAST", "SE", "S E")), 
	SOUTHWEST(ImmutableSet.<String>of("SOUTH WEST", "SW", "S W"));
	
	
	private final Set<String> mappings;
	
	private ApDirectionalEnum(Set<String> inMappings) {
		mappings = inMappings;
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
	
}
