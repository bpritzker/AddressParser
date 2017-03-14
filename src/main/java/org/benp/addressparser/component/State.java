package org.benp.addressparser.component;

import org.benp.addressparser.data.StateValue;

public class State extends ComponentBase  {

	
	private StateValue stateDefinition;

	public StateValue getStateDefinition() {
		return stateDefinition;
	}

	public void setStateDefinition(StateValue stateDefinition) {
		this.stateDefinition = stateDefinition;
	}

	@Override
	public String getValueNormalized() {
		if (stateDefinition == null) {
			return "";
		}
		return stateDefinition.getCode();
	}

	@Override
	public String getValueDefault() {
		return stateDefinition.getCode();
	}

}
