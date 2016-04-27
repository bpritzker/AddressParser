package org.benp.addressparser.component;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.data.ApSplit;

public abstract class ApComponentBase {
	
	private boolean isValid;
	
	// TODO: Should be List<ApSplit>
	private List<ApSplit> splitterIndecies = new ArrayList<>();
	
	public abstract String getValue();

	/**
	 * Always default to false.
	 */
	public ApComponentBase() {
		isValid = false;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	


	public List<ApSplit> getSplitterIndecies() {
		return splitterIndecies;
	}



	public void setSplitterIndecies(List<ApSplit> inSplitterIndecies) {
		this.splitterIndecies = inSplitterIndecies;
	}
	
	public void addSplitterIndecies(List<ApSplit> inSplitterIndecies) {
		this.splitterIndecies.addAll(inSplitterIndecies);
	}
	
	public void addSplitterIndex(ApSplit inSplitterIndecies) {
		splitterIndecies.add(inSplitterIndecies);
	}
	
	public void addSplitterIndecies(ApSplit split) {
		splitterIndecies.add(split);
	}
	
	
	
	public void addSplitterIndices(ApComponentBase inComponent) {
		// If null, we don't care
		if (inComponent != null) {
			this.splitterIndecies.addAll(inComponent.getSplitterIndecies());
		}
	}
	


	public ApSplit getRightMostSplit() {
		ApSplit result = null;
		
		if (splitterIndecies.size() > 0) {
			result = splitterIndecies.get(splitterIndecies.size() - 1);
		}
		return result;
	}


}
