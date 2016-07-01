package org.benp.addressparser.component;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.data.Split;

public abstract class ComponentBase {
	
	private boolean isValid;
	
	private List<Split> splitterIndecies = new ArrayList<>();

	public abstract String getValue();
	
//	/**
//	 * Normalized it all upper case for all words. 
//	 * Basically, the most normalized version we can come up with.
//	 * @return
//	 */
//	public abstract String getValueNormalized();
//
//	/**
//	 * This is where the first letter is capitalized for all values except the State
//	 * @return
//	 */
//	public abstract String getValueProper();
//
//	/**
//	 * This is the configurable version. Until we start using the config just have it return the 
//	 * proper value.
//	 * @return
//	 */
//	public String getValueCustom() {
//		return getValueProper();
//	}

	
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
