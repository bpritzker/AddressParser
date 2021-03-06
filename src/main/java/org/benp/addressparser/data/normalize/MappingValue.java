package org.benp.addressparser.data.normalize;

import java.util.HashSet;
import java.util.Set;

public class MappingValue {
	
	private String rawKey;
//	private String normalizedKey;
	
	private Set<String> values = new HashSet<>();
	private String defualtValue;
	
	
	public Set<String> getValues() {
		return values;
	}
	public void setValues(Set<String> inValues) {
		values = inValues;
	}
	public String getDefualtValue() {
		return defualtValue;
	}
	public void setDefualtValue(String inDefualtValue) {
		defualtValue = inDefualtValue;
	}
	
	public void addValue(String inValue) {
		values.add(inValue);
	}
	public String getRawKey() {
		return rawKey;
	}
	public void setRawKey(String rawKey) {
		this.rawKey = rawKey;
	}

	

}
