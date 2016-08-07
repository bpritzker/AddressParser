package org.benp.addressparser.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.benp.addressparser.data.StateValue;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class StateTest {
	
	
	@Test
	public void getValue() {
		State state = new State();
		
		// null/empty test
		assertFalse(state.isValid());
		
		
		StateValue tempStateValue = 
				new StateValue("MA", "MASSACHUSETTS", 
				ImmutableSet.<String>of("MA","MASS","MASSACHUSETTS"));
				
				
		state.setStateDefinition(tempStateValue);
		assertEquals("MA", state.getValueNormalized());
		
	}

}
