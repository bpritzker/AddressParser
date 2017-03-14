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
	public String getValueNormalized() {
		if (directional == null) {
			return null;
		}
		return directional.getValueNormalized();
	}

	@Override
	public String getValueDefault() {
		if (directional == null) {
			return null;
		}
		return directional.getValueProper();
	}

}
