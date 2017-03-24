package org.benp.addressparser.data.normalize;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * There are situations where you have multiple keys that maps to 
 * multiple values.
 * @author Ben P
 *
 */
public class Mapping {
	
	private Map<String, MappingValue> mappings;
	/**
	 * These are values that have more than one key.
	 * Not very relevant for City/State but needed for Business normalization.
	 * Example: "SRC" and "SOURCE" are both keys that map into each other.
	 * 
	 * 
	 */
	private Set<String> ambiguousValues = new HashSet<>();

	public Set<String> getAmbiguousValues() {
		return ambiguousValues;
	}

	public void setAmbiguousValues(Set<String> inAmbiguousValues) {
		this.ambiguousValues = inAmbiguousValues;
	}

	public Map<String, MappingValue> getMappings() {
		return mappings;
	}

	public void setMappings(Map<String, MappingValue> inMappings) {
		this.mappings = inMappings;
	}

	/**
	 * Find the name in the values, Excluding the ambiguousValues
	 */
	public MappingValue fromValue(String inValueToFind) {
		for (MappingValue currValue : mappings.values()) {
			if (currValue.getValues().contains(inValueToFind)) {
				return currValue;
			}
		}
		return null;
	}

	

}
