package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.data.ApStateValue;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class ApStateTest {
	
	
	@Test
	public void getValue() {
		ApState state = new ApState();
		
		// null/empty test
		assertEquals("", state.getValue());
		
		
		ApStateValue tempStateValue = 
				new ApStateValue("MA", "MASSACHUSETTS", 
				ImmutableSet.<String>of("MA","MASS","MASSACHUSETTS"));
				
				
		state.setStateDefinition(tempStateValue);
		assertEquals("MA", state.getValue());
		
	}

}
