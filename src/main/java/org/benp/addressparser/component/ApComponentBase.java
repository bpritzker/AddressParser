package org.benp.addressparser.component;

import java.util.ArrayList;
import java.util.List;

public abstract class ApComponentBase {
	
	private boolean isValid;
	
	private List<Integer> splitterIndecies = new ArrayList<>();

	/**
	 * Always default to false.
	 */
	public ApComponentBase() {
		isValid = false;
	}
	
	
	public void setIndicies(int... indices) {
		for (int i=0; i < indices.length; i++) {
			splitterIndecies.add(indices[i]);
		}
	}
	
	
	
	public boolean isValid() {
		return isValid;
	}
	
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}



	public List<Integer> getSplitterIndecies() {
		return splitterIndecies;
	}



	public void setSplitterIndecies(List<Integer> splitterIndecies) {
		this.splitterIndecies = splitterIndecies;
	}
	
	public void setSplitterIndecies(int[] splitterIndecies) {
		// There is no shortcut for this :(
		for (int i=0; i < splitterIndecies.length; i++) {
			this.splitterIndecies.add(splitterIndecies[i]);
		}
	}
	
	
	public void addSplitterIndices(ApComponentBase inComponent) {
		// If null, we don't care
		if (inComponent != null) {
			this.splitterIndecies.addAll(inComponent.getSplitterIndecies());
		}
	}



}
