package org.benp.addressparser.component.street;

import org.benp.addressparser.component.ApComponentBase;
import org.benp.addressparser.component.ApDirectional;


/**
 * 
 * See: 1.7.2.8 Complex Element: Complete Street Name 
 * { Street Name Pre Modifier } + 
 * { Street Name Pre Directional } + 
 * { Street Name Pre Type } +
 * { Street Name *} +
 * { Street Name Post Type } +
 * { Street Name Post Directional } +
 * { Street Name Post Modifier } 
 * 
 * @author Ben
 *
 */

// TODO : Mappings MT is Mount????

public class ApStreetStreetName extends ApComponentBase {
	
	private ApDirectional preDirectional;
	private String name;
//	private ApDirectional postDirectional;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ApDirectional getPreDirectional() {
		return preDirectional;
	}
	public void setPreDirectional(ApDirectional preDirectional) {
		this.preDirectional = preDirectional;
	}

	
	@Override
	public String getValue() {

		if (name == null) {
			return "";
		}
		
		String separatorPrefix = "";
		
		StringBuilder resultSb = new StringBuilder();
		
		if (preDirectional != null && preDirectional.isValid()) {
			resultSb.append(preDirectional.getValue());
			separatorPrefix = " ";
		}
		
		if (name != null) {
			resultSb.append(separatorPrefix).append(name);
			separatorPrefix = " ";
		}
//		
//		if (postDirectional != null) {
//			resultSb.append(separatorPrefix).append(postDirectional.getValue());
//			separatorPrefix = " ";
//		}
		
		return resultSb.toString();
		
	}
	
	
	
	public String getNormalizedValue() {
		StringBuilder resultSb = new StringBuilder();
		
		String appendSplitter = "";
		
		if (preDirectional != null && preDirectional.isValid()) {
			resultSb.append(preDirectional.getNormalizedValue());
			appendSplitter = " ";
		}
		
		if (name != null) {
			resultSb.append(appendSplitter).append(name);
		}
		
		
		
		return resultSb.toString();

	}
	

}
