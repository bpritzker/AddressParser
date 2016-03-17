package org.benp.addressparser.parser;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.ApException;
import org.benp.addressparser.component.ApState;
import org.benp.addressparser.data.ApStateValue;
import org.benp.addressparser.data.ApStateValues;
import org.benp.addressparser.data.ApValueIndex;

public class ApStateParser extends ApParserBase {

	private ApStateValues stateValues;
	
	public ApStateParser(ApAddressParserConfig config) {
		super(config);
		stateValues = getStateValues();
	}
	

	public ApState parse(ApSplitter splitter) throws ApException {
		
		ApState resultState = new ApState();
		
		ApStateValue tempStateValue = null;
		// Check up to 3 values to the right (this will compensate for bad data at the end ) 
		// It also takes longer to process.
		for (int i=0; i < 3 && tempStateValue == null; i++) {
			
			ApValueIndex rightMost = splitter.getNextRightValue(i);
			if (rightMost == null) {
				return new ApState();
			}
			
			String rightMostValue = rightMost.getValue().toUpperCase();
			tempStateValue = stateValues.fromCode(rightMostValue);
		
			if (tempStateValue != null) {
				resultState.setValid(true);
				resultState.setStateDefinition(tempStateValue);
				resultState.addIndicies(rightMost.getIndex());
				splitter.addUsedSplitsAllRight(rightMost.getIndex());
			}
		}

		
		return resultState;
	}
	
	
	/**
	 * Override this for testing.
	 */
	protected ApStateValues getStateValues() {
		ApStateValues resultStateValues = new ApStateValues();
		resultStateValues.init();
		return resultStateValues;
	}	
	
	
}
