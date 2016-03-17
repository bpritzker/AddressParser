package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.benp.addressparser.ApException;
import org.benp.addressparser.data.ApValueIndex;

public class ApSplitter {
	
	public static int INVALID_INDEX = -1;
	
	private  String stringToSplit;
	
	/**
	 * Once a section of the split has been used (or allocated) for a part of the address.
	 * It should not be used for a different part. 
	 * For example, If you have as a zip code, 02111-1231 those will be the last two 
	 * splits. They should not be used again when looking for a State.
	 */
	private Set<Integer> usedSplits;
	

	private List<String> values;
	
	public ApSplitter(String stringToSplit) {
		this.stringToSplit = stringToSplit;
		split();
	}
	
	
	private void split() {
		if (stringToSplit == null) {
			values = new ArrayList<>();
			return;
		}
		values = Arrays.asList(stringToSplit.split("[^a-zA-Z0-9']+"));
		usedSplits = new HashSet<>();
	}


	public String getValue(int valueIndex) throws ApException {
		if (valueIndex > values.size()) {
			throw new ApException("There are not that many elements in the list.", 
					"valueIndex", valueIndex, "values.size()", values.size());
		}
		return values.get(valueIndex);
	}
	
//	public void addUsedSplit(int usedSplit) throws ApException {
//		if (usedSplits.contains(usedSplit)) {
//			throw new ApException("A used split was already used..", "usedSplit", usedSplit);
//		}
//		usedSplits.add(usedSplit);
//	}
	
	public void addUsedSplits(int... inUsedSplits) throws ApException {
		// There is no shortcut for this :(
		for (int i=0; i < inUsedSplits.length; i++) {
//			if (usedSplits.contains(usedSplit)) {
//			throw new ApException("A used split was already used..", "usedSplit", usedSplit);
//		}
			if (usedSplits.contains(inUsedSplits[i])) {
				throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
			}
			usedSplits.add(inUsedSplits[i]);
		}
	}
	
	public void addUsedSplits(List<Integer> inUsedSplits) throws ApException {
		
		if (!Collections.disjoint(usedSplits, inUsedSplits)) {
			throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
		}
		
		this.usedSplits.addAll(usedSplits);
	}
	
	public ApValueIndex getNextRightValue() {
		return  getNextRightValue(0);
	}
	
	
	public ApValueIndex getNextRightValue(int offset) {
		
		int nextAvailableIndex = -1;
		
		for (int i=values.size() - 1 ; i >= 0; i--) {
			if (! usedSplits.contains(i)) {
				nextAvailableIndex = i;
				break;
			}
		}
		
		if (nextAvailableIndex == -1) {
			return null;
		}
		
		int valueIndex = nextAvailableIndex - offset;
		if (valueIndex < 0 || valueIndex > values.size()) {
			return null;
		}
		
		return new ApValueIndex(values.get(valueIndex), valueIndex);
	}
	
	public ApValueIndex getNextLeftValue() {
		return getNextLeftValue(0);
	}


	public ApValueIndex getNextLeftValue(int offset) {
		for (int i=0 + offset; i < values.size(); i++) {
			if (! usedSplits.contains(i)) {
				return new ApValueIndex(values.get(i), i);
			}
		}
		return null;
	}


	/**
	 * TODO: Add checks in this....
	 * @param leftIndex
	 * @param rightIndex
	 * @return a list of all unused values between the two indices
	 */
	public List<ApValueIndex> getValues(int leftIndex, int rightIndex) {
		List<ApValueIndex> resultValues = new ArrayList<>();
		for (int i= leftIndex; i <= rightIndex; i++) {
			if (! usedSplits.contains(i)) {
				ApValueIndex tempValueIndex = new ApValueIndex(values.get(i), i);
				resultValues.add(tempValueIndex);
			}
		}
		return resultValues;
	}


	/**
	 * This method will mark the index and ALL indices to the right as used
	 * @param index
	 * @throws ApException 
	 */
	public void addUsedSplitsAllRight(int index) throws ApException {
		int totalValues = values.size();
		for (int i=index; i < totalValues; i++) {
			if (! usedSplits.contains(i)) {
				addUsedSplits(i);
			}
		}
		
	}

}
