package org.benp.addressparser.component;

import org.benp.addressparser.data.ApStateValue;

public class ApState extends ApComponentBase  {

	
	private ApStateValue stateDefinition;

	public ApStateValue getStateDefinition() {
		return stateDefinition;
	}

	public void setStateDefinition(ApStateValue stateDefinition) {
		this.stateDefinition = stateDefinition;
	}

	@Override
	public String getValue() {
		if (stateDefinition == null) {
			return "";
		}
		return stateDefinition.getCode();
	}


}
