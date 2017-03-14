package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.data.normalize.MappingValue;
import org.junit.Test;

public class StreetNamePostTypeTest {
	
	
	
	@Test
	public void getProperValue() {
		
		StreetNamePostType streetNamePostType;
		
		streetNamePostType = new StreetNamePostType();
		streetNamePostType.setValid(true);
		MappingValue streetPostTypeValue = new MappingValue();
		streetPostTypeValue.setDefualtValue("ALLEY");
		streetNamePostType.setStreetPostType(streetPostTypeValue);
		assertEquals("Alley", streetNamePostType.getValueDefault());
		
		Directional tempDirectional = new Directional();
		tempDirectional.setDirectional(DirectionalEnum.SOUTH);
		tempDirectional.setValid(true);
		streetNamePostType.setStreetNamePostTypeDirectional(tempDirectional);
		assertEquals("Alley South", streetNamePostType.getValueDefault());
	}
	
	

}
