package org.benp.addressparser.component.street;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.component.ApComponentBase;
import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.data.ApStreetPostTypeEnum;


/**
 * 2.2.1.5 Street Name Post Type
 * 
 * 
 * @author Ben
 *
 */
public class StreetNamePostType extends ApComponentBase {

	private ApStreetPostTypeEnum streetPostType;
	private ApDirectional streetNamePostTypeDirectional;

	public ApDirectional getStreetNamePostTypeDirectional() {
		return streetNamePostTypeDirectional;
	}

	public void setStreetNamePostTypeDirectional(ApDirectional streetNamePostTypeDirectional) {
		this.streetNamePostTypeDirectional = streetNamePostTypeDirectional;
	}

	public ApStreetPostTypeEnum getStreetPostType() {
		return streetPostType;
	}

	public void setStreetPostType(ApStreetPostTypeEnum streetPostType) {
		this.streetPostType = streetPostType;
	}

	@Override
	// Test the null case
	public String getValue() {
		StringBuilder resultSb = new StringBuilder();
		if (streetPostType == null) {
			return null;
		}
		resultSb.append(streetPostType.getName());
		if (streetNamePostTypeDirectional != null) {
			resultSb.append(" ").append(streetNamePostTypeDirectional.getValue());
		}
		
		return resultSb.toString();
	}

	public String getNormalizedValue() {
		if (streetPostType == null) {
			return null;
		}
		
		StringBuilder resultSb = new StringBuilder();
		resultSb.append(WordUtils.capitalizeFully(streetPostType.getName()));
		
		if (streetNamePostTypeDirectional != null && streetNamePostTypeDirectional.isValid()) {
			resultSb.append(" ").append(streetNamePostTypeDirectional.getNormalizedValue());
		}
		
//		String tempName = streetPostType.getName();
//		String resultNormalized = tempName.substring(0, 1).toUpperCase() + tempName.substring(1).toLowerCase();
		return resultSb.toString();
	}
	

}
