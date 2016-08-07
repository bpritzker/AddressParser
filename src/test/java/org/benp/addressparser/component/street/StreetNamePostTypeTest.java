package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.Directional;
import org.benp.addressparser.data.DirectionalEnum;
import org.benp.addressparser.data.StreetPostTypeEnum;
import org.junit.Test;

public class StreetNamePostTypeTest {
	
	
	
	@Test
	public void getProperValue() {
		
		StreetNamePostType streetNamePostType;
		
		streetNamePostType = new StreetNamePostType();
		streetNamePostType.setValid(true);
		streetNamePostType.setStreetPostType(StreetPostTypeEnum.ALLEY);;
		assertEquals("ALY", streetNamePostType.getDefaultValue());
		
		Directional tempDirectional = new Directional();
		tempDirectional.setDirectional(DirectionalEnum.SOUTH);
		tempDirectional.setValid(true);
		streetNamePostType.setStreetNamePostTypeDirectional(tempDirectional);
		assertEquals("ALY South", streetNamePostType.getDefaultValue());
	}
	
	

}
