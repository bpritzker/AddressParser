package org.benp.addressparser.data.normalize;

import org.benp.addressparser.common.ApException;

public class MapperMock extends Mapper {
	
	private Mapping businessWordOverride = null;
	private Mapping cityOverride = null;


	public MapperMock() {
		super(null);
	}
	
	@Override 
	public Mapping getBusinessWord() throws ApException {
		
		if (businessWordOverride == null) {
			return super.getBusinessWord();
		}
		return businessWordOverride;
	}

	public void setBusinessWordOverride(Mapping businessWordOverride) {
		this.businessWordOverride = businessWordOverride;
	}
	
	
	
	
	
	@Override 
	public Mapping getCity() throws ApException {
		if (cityOverride == null) {
			return super.getCity();
		}
		return cityOverride;
	}

	public void setCityOverride(Mapping cityOverride) {
		this.cityOverride = cityOverride;
	}
}
