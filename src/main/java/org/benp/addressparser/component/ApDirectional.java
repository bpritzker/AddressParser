package org.benp.addressparser.component;

import org.benp.addressparser.data.ApDirectionalEnum;

public class ApDirectional extends ApComponentBase {
	
	private ApDirectionalEnum directional;

	public ApDirectionalEnum getDirectional() {
		return directional;
	}

	public void setDirectional(ApDirectionalEnum directional) {
		this.directional = directional;
	}

	@Override
	public String getValue() {
		// TODO: test null case
		if (directional == null) {
			return null;
		}
		return directional.getValue();
	}

}
