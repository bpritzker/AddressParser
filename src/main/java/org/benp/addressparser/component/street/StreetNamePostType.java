package org.benp.addressparser.component.street;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.component.ComponentBase;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.StreetPostTypeEnum;


/**
 * 2.2.1.5 Street Name Post Type
 * 
 * 
 * @author Ben P
 *
 */
public class StreetNamePostType extends ComponentBase {

	private StreetPostTypeEnum streetPostType;
	private Directional streetNamePostTypeDirectional;

	public Directional getStreetNamePostTypeDirectional() {
		return streetNamePostTypeDirectional;
	}

	public void setStreetNamePostTypeDirectional(Directional inStreetNamePostTypeDirectional) {
		streetNamePostTypeDirectional = inStreetNamePostTypeDirectional;
	}

	public StreetPostTypeEnum getStreetPostType() {
		return streetPostType;
	}

	public void setStreetPostType(StreetPostTypeEnum inStreetPostType) {
		streetPostType = inStreetPostType;
	}

	
	// FIXME: Should do this better. In the Base, remove the getValues and make it abstract
	@Override
	public List<Split> getSplitterIndecies() {
		List<Split> resultSplits = new ArrayList<>(super.getSplitterIndecies());
		if (streetNamePostTypeDirectional != null) {
			resultSplits.addAll(streetNamePostTypeDirectional.getSplitterIndecies());
		}
		return resultSplits;
	}
	
	
	
	
	@Override
	public String getValueNormalized() {
		return getValue(true);
	}
	
	@Override
	public String getDefaultValue() {
		return getValue(false);
	}
	
	
	private String getValue(boolean inIsNormalized) {
		StringBuilder resultSb = new StringBuilder();
		if (streetPostType == null) {
			return null;
		}
		
//		if (inIsNormalized) {
			resultSb.append(streetPostType.getStandardAbbreviation());
//		} else {
//			resultSb.append(WordUtils.capitalizeFully(streetPostType.getStandardAbbreviation()));
//		}
		
		if (streetNamePostTypeDirectional != null) {
			if (inIsNormalized) {
				resultSb.append(" ").append(streetNamePostTypeDirectional.getValueNormalized());
			} else {
				resultSb.append(" ").append(streetNamePostTypeDirectional.getDefaultValue());
			}
		}
		
		return resultSb.toString();
	}

//	public String getNormalizedValue() {
//		if (streetPostType == null) {
//			return null;
//		}
//		
//		StringBuilder resultSb = new StringBuilder();
//		resultSb.append(WordUtils.capitalizeFully(streetPostType.getName()));
//		
//		if (streetNamePostTypeDirectional != null && streetNamePostTypeDirectional.isValid()) {
//			resultSb.append(" ").append(streetNamePostTypeDirectional.getNormalizedValue());
//		}
//		
//		return resultSb.toString();
//	}
	

}
