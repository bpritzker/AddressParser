package org.benp.addressparser.data.mapping;

import java.util.HashSet;
import java.util.Set;

public class MappingValue {
	
//	private String key; 
	private Set<String> values = new HashSet<>();
	private String defualtValue;
	
	
//	public String getKey() {
//		return key;
//	}
//	public void setKey(String key) {
//		this.key = key;
//	}
	public Set<String> getValues() {
		return values;
	}
	public void setValues(Set<String> values) {
		this.values = values;
	}
	public String getDefualtValue() {
		return defualtValue;
	}
	public void setDefualtValue(String defualtValue) {
		this.defualtValue = defualtValue;
	}
	
	public void addValue(String inValue) {
		values.add(inValue);
	}

	

}
