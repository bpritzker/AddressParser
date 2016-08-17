package org.benp.addressparser.component.street;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.component.ComponentBase;
import org.benp.addressparser.component.Directional;


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
 * @author Ben P
 *
 */

// TODO : Mappings MT is Mount????

public class StreetNameStreet extends ComponentBase {
	
	private Directional preDirectional;
	private String name;
	


	
	@Override
	public String getValueNormalized() {
		return getValue(true);
	}
	
	@Override
	public String getDefaultValue() {
		return getValue(false);
	}
	
	
	/**
	 * Default - 
	 * Normalized - 
	 * @param inNormalized
	 * @return
	 */
	private String getValue(boolean inNormalized) {

		if (name == null) {
			return "";
		}
		
		String separatorPrefix = "";
		
		StringBuilder resultSb = new StringBuilder();
		
		if (preDirectional != null && preDirectional.isValid()) {
			if (inNormalized) {
				resultSb.append(preDirectional.getValueNormalized());
			} else {
				resultSb.append(WordUtils.capitalizeFully(preDirectional.getValueNormalized()));
			}
			separatorPrefix = " ";
		}
		
		if (name != null) {
			if (inNormalized) {
				resultSb.append(separatorPrefix).append(name.toUpperCase());
			} else {
				resultSb.append(separatorPrefix).append(WordUtils.capitalizeFully(name));
			}
			separatorPrefix = " ";
		}
		return resultSb.toString();
	}	
	
	
//	public String getNormalizedValue() {
//		StringBuilder resultSb = new StringBuilder();
//		
//		String appendSplitter = "";
//		
//		if (preDirectional != null && preDirectional.isValid()) {
//			resultSb.append(preDirectional.getValueNormalized());
//			appendSplitter = " ";
//		}
//		
//		if (name != null) {
//			resultSb.append(appendSplitter).append(WordUtils.capitalizeFully(name));
//		}
//		return resultSb.toString();
//	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Directional getPreDirectional() {
		return preDirectional;
	}
	public void setPreDirectional(Directional preDirectional) {
		this.preDirectional = preDirectional;
	}
}
