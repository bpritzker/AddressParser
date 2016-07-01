package org.benp.addressparser.component.street;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.component.ComponentBase;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.StreetPostTypeEnum;


/**
 * 2.2.1.5 Street Name Post Type
 * 
 * 
 * @author Ben
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
	// Test the null case
	public String getValue() {
		StringBuilder resultSb = new StringBuilder();
		if (streetPostType == null) {
			return null;
		}
		resultSb.append(streetPostType.getStandardAbbreviation());
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
		
		return resultSb.toString();
	}
	

}
