package org.benp.addressparser.component;

import java.util.ArrayList;
import java.util.List;

import org.benp.addressparser.data.Split;


/**
 * This is the base class for all components. 
 * 
 * All Components have a splitter indices that indicate what parts of the split they lay claim on.
 * 
 * 
 * @author Ben P
 *
 */
public abstract class ComponentBase {
	
	private boolean isValid= false; // explicit default to false
	
	private List<Split> splitterIndecies = new ArrayList<>();
	public boolean equals(ComponentBase inComponent) {
		return false;
	}
	
	
	/**
	 * Normalized it all upper case spelled out for all words. 
	 * Basically, the most normalized version we can come up with.
	 */
	public abstract String getValueNormalized();

	/**
	 * This is what an address would look like if you wrote it out properly
	 */
	public abstract String getDefaultValue();

	/**
	 * This is the configurable version. Until we start using the config just have it return the 
	 * proper value.
	 * </B>
	 * For now just have it return the default value
	 */
	public String getValueCustom() {
		return getDefaultValue();
	}

	
	/**
	 * Always default to false.
	 */
	public ComponentBase() {
		isValid = false;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	
	public void setValid(boolean inIsValid) {
		isValid = inIsValid;
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
