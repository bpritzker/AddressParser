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
public class ApStreetStreetName extends ApComponentBase {
	
	private String name;
	private ApDirectional preDirectional;
	
	
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
	

}