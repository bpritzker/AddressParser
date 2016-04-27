package org.benp.addressparser.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.benp.addressparser.ApException;
import org.benp.addressparser.data.ApSplit;

public class ApSplitter {
	
	public static int INVALID_INDEX = -1;
	
	private  String stringToSplit;
	
	/**
	 * Once a section of the split has been used (or allocated) for a part of the address.
	 * It should not be used for a different part. 
	 * For example, If you have as a zip code, 02111 1231 those will be the last two 
	 * splits. They should not be used again when looking for a State.
	 */
	private Set<Integer> usedSplits;
	
	
	/**
	 * When trying to debug, this is one of the more complex parts of the address parser
	 * This put the splits in order we can see them better in the debugger.
	 */
	private List<ApSplit> splitsForDebug = new ArrayList<>();
	

	private Map<Integer,ApSplit> values;
	
	public Map<Integer, ApSplit> getValues() {
		return values;
	}


	public ApSplitter(String stringToSplit) {
		this.stringToSplit = stringToSplit;
		split();
	}
	
	
	private void split() {
		if (stringToSplit == null) {
			values = new HashMap<>();
			return;
		}
		values = getSplitValues(stringToSplit);
		usedSplits = new HashSet<>();
	}


	protected Map<Integer, ApSplit> getSplitValues(String stringToSplit) {
		
		Map<Integer, ApSplit> resultSplits = new HashMap<>();

		boolean prevCharIsSplitChar = true;
		int prevIndex = 0;
		int mapValueIndex = 0;
//		int splitIndexCounter = 0;
		if (stringToSplit != null) {
			for (int i=0; i < stringToSplit.length(); i++) {
			
				char currChar = stringToSplit.charAt(i);
				boolean isSplitChar = isSplitChar(currChar);
				if (isSplitChar) {
					if (! prevCharIsSplitChar) { 
						String tempValue = stringToSplit.substring(prevIndex, i);
						ApSplit tempSplit = new ApSplit(tempValue, mapValueIndex);
						resultSplits.put(mapValueIndex, tempSplit);
						splitsForDebug.add(tempSplit);
						mapValueIndex++;
						prevIndex = i;
					}
					
					prevCharIsSplitChar = true;
				} else {
					if (prevCharIsSplitChar) {
						// Don't put the split chars into the map
						prevIndex = i;
					}
					prevCharIsSplitChar = false;
				}
			}
		}

		// Handle the last case
		if (stringToSplit != null) {
			String tempValue = stringToSplit.substring(prevIndex);
			if (! StringUtils.isBlank(tempValue)) {
//				resultSplits.put(mapValueIndex, tempValue);
				ApSplit tempSplit = new ApSplit(tempValue, mapValueIndex);
				resultSplits.put(mapValueIndex, tempSplit);
				splitsForDebug.add(tempSplit);
			}
		}
		return resultSplits;
		
	}


	private boolean isSplitChar(char inChar) {
		if (inChar == ' ' || inChar == ',' || inChar == '.') {
			return true;
		} else {
			return false;
		}
	}


//	public String getValue(int valueIndex) throws ApException {
//		if (valueIndex > values.size()) {
//			throw new ApException("There are not that many elements in the list.", 
//					"valueIndex", valueIndex, "values.size()", values.size());
//		}
//		return values.get(valueIndex);
//	}
	
//	public void addUsedSplits(int... inUsedSplits) throws ApException {
//		// There is no shortcut for this :(
//		for (int i=0; i < inUsedSplits.length; i++) {
//			if (usedSplits.contains(inUsedSplits[i])) {
//				throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
//			}
//			usedSplits.add(inUsedSplits[i]);
//		}
//	}
	
	public void addUsedSplit(ApSplit inSplit) throws ApException {
		List<ApSplit> tempSplits = new ArrayList<>();
		tempSplits.add(inSplit);
		addUsedSplits(tempSplits);
	}
	
	public void addUsedSplits(List<ApSplit> inUsedSplits) throws ApException {
		
		for (ApSplit currSplit : inUsedSplits) {
			int splitIndex = currSplit.getSplitIndex();
			if (usedSplits.contains(splitIndex)) {
				throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
			} else {
				usedSplits.add(splitIndex);
			}
		}
//		
//		if (!Collections.disjoint(usedSplits, inUsedSplits)) {
//			throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
//		}
//		
//		this.usedSplits.addAll(inUsedSplits);
	}
	
	
//	public void addUsedSplits(List<Integer> inUsedSplits) throws ApException {
//		
//		if (!Collections.disjoint(usedSplits, inUsedSplits)) {
//			throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
//		}
//		
//		this.usedSplits.addAll(inUsedSplits);
//	}
	
	public ApSplit getNextRightValue() {
		return  getNextRightValue(0);
	}
	
	
	public ApSplit getNextRightValue(int offset) {
		
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
		
//		return new ApSplit(values.get(valueIndex), valueIndex);
		return values.get(valueIndex);
	}
	
	public ApSplit getNextLeftValue() {
		return getNextLeftValue(0);
	}


	/**
	 * 
	 * @param offset - offset of 0 should return the same result as get nextLeftValue
	 * @return
	 */
	public ApSplit getNextLeftValue(int offset) {
		int nextAvailable = getNextLeftAvailable();
		if (nextAvailable != INVALID_INDEX) {
			for (int i=nextAvailable + offset; i < values.size(); i++) {
				if (! usedSplits.contains(i)) {
//					return new ApSplit(values.get(i), i);
					return values.get(i);
				}
			}
		}
		return null;
	}


	private int getNextLeftAvailable() {
		for (int i=0; i < values.size(); i++) {
			if (! usedSplits.contains(i)) {
				return i;
			}
		}
		return INVALID_INDEX;
	}


	public List<ApSplit> getValuesLeft(int leftIndex) {
		return getValues(leftIndex, -1);
	}
	
	
	/**
	 * TODO: Add checks in this....
	 * @param leftIndex
	 * @param rightIndex
	 * @return a list of all unused values between the two indices
	 */
	public List<ApSplit> getValues(int leftIndex, int rightIndex) {
		List<ApSplit> resultValues = new ArrayList<>();
		if (rightIndex == -1) {
			for (int i= leftIndex; i <= values.size(); i++) {
				if (! usedSplits.contains(i)) {
//					ApSplit tempValueIndex = new ApSplit(values.get(i), i);
					ApSplit tempValueIndex = values.get(i);
					resultValues.add(tempValueIndex);
				}
			}
		} else {
			for (int i= leftIndex; i <= rightIndex; i++) {
				if (! usedSplits.contains(i)) {
//					ApSplit tempValueIndex = new ApSplit(values.get(i), i);
					ApSplit tempValueIndex = values.get(i);
					resultValues.add(tempValueIndex);
				}
			}
		}
		return resultValues;
	}


	/**
	 * This method will mark the index and ALL indices to the right as used
	 * @param index
	 * @throws ApException 
	 */
	public void addUsedSplitsAllRight(ApSplit inSplit) throws ApException {
		int totalValues = values.size();
		List<Integer> tempUsedSplits = new ArrayList<>();
		for (int i=inSplit.getSplitIndex(); i < totalValues; i++) {
			if (! usedSplits.contains(i)) {
				tempUsedSplits.add(values.get(i).getSplitIndex());
			}
		}
		usedSplits.addAll(tempUsedSplits);
	}


	public static List<Integer> getValues(List<ApSplit> valueIndices) {
		List<Integer> resultValues = new ArrayList<>();
				
		for (ApSplit currIndex : valueIndices) {
			resultValues.add(currIndex.getSplitIndex());
		}
		return resultValues;
	}


	public List<ApSplit> getRightSplits(int numberOfSplitsToGet) {
		List<ApSplit> resultList = new ArrayList<>();
		for (int i=0; i < numberOfSplitsToGet; i++) {
			ApSplit tempSplit = getNextRightValue(i);
			resultList.add(tempSplit);
		}
		
		Collections.reverse(resultList);
		
		return resultList;
	}


	public List<ApSplit> getRemainingSplits() throws ApException {
		List<ApSplit> resultSplits = new ArrayList<>();
		boolean foundUnusedSplit = false;
		ApSplit nextLeft = getNextLeftValue();
		for (int i=nextLeft.getSplitIndex(); i < values.size(); i++) {
			if (!usedSplits.contains(i)) {
				if (foundUnusedSplit && !usedSplits.contains(i)) {
					throw new ApException("Called getRemainingSplits()."
							+ " but remaing splits were NOT continious."
							+ "It means that it found unused splits followed by used splits followed by unused splits.");
				}
				foundUnusedSplit = true;
				resultSplits.add(values.get(i));
			} 
		}
		return resultSplits;
	}


}
