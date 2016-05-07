package org.benp.addressparser.component.street;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.component.ApDirectional;
import org.benp.addressparser.data.ApDirectionalEnum;
import org.benp.addressparser.data.ApStreetPostTypeEnum;
import org.junit.Test;

public class StreetNamePostTypeTest {
	
	
	
	@Test
	public void getNormalizedValue() {
		
		StreetNamePostType streetNamePostType;
		
		
		streetNamePostType = new StreetNamePostType();
		streetNamePostType.setValid(true);
		streetNamePostType.setStreetPostType(ApStreetPostTypeEnum.ALLEY);;
		assertEquals("Alley", streetNamePostType.getNormalizedValue());
		
		ApDirectional tempDirectional = new ApDirectional();
		tempDirectional.setDirectional(ApDirectionalEnum.SOUTH);
		tempDirectional.setValid(true);
		streetNamePostType.setStreetNamePostTypeDirectional(tempDirectional);
		assertEquals("Alley South", streetNamePostType.getNormalizedValue());
	}
	
	

}
