package org.benp.addressparser.parser;

import org.benp.addressparser.AddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.State;
import org.benp.addressparser.data.StateValue;
import org.benp.addressparser.data.StateValues;
import org.benp.addressparser.data.Split;

public class StateParser extends ParserBase {

	private StateValues stateValues;
	
	public StateParser(AddressParserConfig config) {
		super(config);
		stateValues = getStateValues();
	}
	

	public State parse(ApSplitter splitter) throws ApException {
		
		State resultState = new State();
		
		StateValue tempStateValue = null;
		// Check up to 3 values to the right (this will compensate for bad data at the end ) 
		// It also takes longer to process.
		for (int i=0; i < 3 && tempStateValue == null; i++) {
			
			Split rightMost = splitter.getNextRightValue(i);
			if (rightMost == null) {
				return new State();
			}
			
			String rightMostValue = rightMost.getValue().toUpperCase();
			tempStateValue = stateValues.fromCode(rightMostValue);
		
			if (tempStateValue != null) {
				resultState.setValid(true);
				resultState.setStateDefinition(tempStateValue);
				resultState.addSplitterIndex(rightMost);
				splitter.addUsedSplitsAllRight(rightMost);
			}
		}

		
		return resultState;
	}
	
	
	/**
	 * Override this for testing.
	 */
	protected StateValues getStateValues() {
		StateValues resultStateValues = new StateValues();
		resultStateValues.init();
		return resultStateValues;
	}	
	
	
}
