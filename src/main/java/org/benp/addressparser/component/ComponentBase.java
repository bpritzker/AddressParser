package org.benp.addressparser.component;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.data.Split;

public abstract class ComponentBase {
	
	private boolean isValid;
	
	private List<Split> splitterIndecies = new ArrayList<>();
	
	public abstract String getValue();

	/**
	 * Always default to false.
	 */
	public ComponentBase() {
		isValid = false;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	


	public List<Split> getSplitterIndecies() {
		return splitterIndecies;
	}



	public void setSplitterIndecies(List<Split> inSplitterIndecies) {
		this.splitterIndecies = inSplitterIndecies;
	}
	
	public void addSplitterIndecies(List<Split> inSplitterIndecies) {
		this.splitterIndecies.addAll(inSplitterIndecies);
	}
	
	public void addSplitterIndex(Split inSplitterIndecies) {
		splitterIndecies.add(inSplitterIndecies);
	}
	
	public void addSplitterIndecies(Split split) {
		splitterIndecies.add(split);
	}
	
	
	
	public void addSplitterIndices(ComponentBase inComponent) {
		// If null, we don't care
		if (inComponent != null) {
			this.splitterIndecies.addAll(inComponent.getSplitterIndecies());
		}
	}
	


	public Split getRightMostSplit() {
		Split result = null;
		
		if (splitterIndecies.size() > 0) {
			result = splitterIndecies.get(splitterIndecies.size() - 1);
		}
		return result;
	}


}
