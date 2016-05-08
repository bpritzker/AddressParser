package org.benp.addressparser.component;

import org.benp.addressparser.data.DirectionalEnum;

public class Directional extends ComponentBase {
	
	private DirectionalEnum directional;

	public DirectionalEnum getDirectional() {
		return directional;
	}

	public void setDirectional(DirectionalEnum inDirectional) {
		directional = inDirectional;
	}


	@Override
	public String getValue() {
		if (directional == null) {
			return null;
		}
		return directional.getValue();
	}

	public String getNormalizedValue() {
		if (directional == null) {
			return null;
		}
		return directional.getNormalizedValue();
	}

}
