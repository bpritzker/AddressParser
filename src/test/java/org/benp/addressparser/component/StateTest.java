package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;

import org.benp.addressparser.data.StateValue;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class StateTest {
	
	
	@Test
	public void getValue() {
		State state = new State();
		
		// null/empty test
		assertEquals("", state.getValue());
		
		
		StateValue tempStateValue = 
				new StateValue("MA", "MASSACHUSETTS", 
				ImmutableSet.<String>of("MA","MASS","MASSACHUSETTS"));
				
				
		state.setStateDefinition(tempStateValue);
		assertEquals("MA", state.getValue());
		
	}

}
