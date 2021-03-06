package org.benp.addressparser.component.street;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.benp.addressparser.component.ComponentBase;
import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.Split;
import org.benp.addressparser.data.normalize.MappingValue;


/**
 * 2.2.1.5 Street Name Post Type
 * 
 * 
 * @author Ben P
 *
 */
public class StreetNamePostType extends ComponentBase {

	private MappingValue streetPostType;
	private Directional streetNamePostTypeDirectional;

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
	public String getValueDefault() {
		return getValue(false);
	}
	
	
	private String getValue(boolean inNormalized) {
		StringBuilder resultSb = new StringBuilder();
		if (streetPostType == null) {
			return null;
		}
		
		if (inNormalized) {
			resultSb.append(streetPostType.getDefualtValue());
		} else {
			resultSb.append(WordUtils.capitalizeFully(streetPostType.getDefualtValue()));
		}
		
		if (streetNamePostTypeDirectional != null) {
			if (inNormalized) {
				resultSb.append(" ").append(streetNamePostTypeDirectional.getValueNormalized());
			} else {
				resultSb.append(" ").append(streetNamePostTypeDirectional.getValueDefault());
			}
		}
		
		return resultSb.toString();
	}

	
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///              BEGIN GETTERS AND SETTERS
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////	
	
	
	
	public Directional getStreetNamePostTypeDirectional() {
		return streetNamePostTypeDirectional;
	}

	public void setStreetNamePostTypeDirectional(Directional inStreetNamePostTypeDirectional) {
		streetNamePostTypeDirectional = inStreetNamePostTypeDirectional;
	}




	public MappingValue getStreetPostType() {
		return streetPostType;
	}




	public void setStreetPostType(MappingValue streetPostType) {
		this.streetPostType = streetPostType;
	}

}
