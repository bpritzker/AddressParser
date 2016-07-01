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
import org.benp.addressparser.data.Split;

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
	private List<Split> splitsForDebug = new ArrayList<>();
	

	private Map<Integer,Split> values;
	
	public Map<Integer, Split> getValues() {
		return values;
	}


	public ApSplitter(String inStringToSplit) {
		stringToSplit = inStringToSplit;
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


	protected Map<Integer, Split> getSplitValues(String inStringToSplit) {
		
		Map<Integer, Split> resultSplits = new HashMap<>();

		boolean prevCharIsSplitChar = true;
		int prevIndex = 0;
		int mapValueIndex = 0;
		if (inStringToSplit != null) {
			for (int i=0; i < inStringToSplit.length(); i++) {
			
				char currChar = inStringToSplit.charAt(i);
				boolean isSplitChar = isSplitChar(currChar);
				if (isSplitChar) {
					if (! prevCharIsSplitChar) { 
						String tempValue = inStringToSplit.substring(prevIndex, i);
						Split tempSplit = new Split(tempValue, mapValueIndex);
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
		if (inStringToSplit != null) {
			String tempValue = inStringToSplit.substring(prevIndex);
			if (! StringUtils.isBlank(tempValue)) {
				Split tempSplit = new Split(tempValue, mapValueIndex);
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

	
	public void addUsedSplit(Split inSplit) throws ApException {
		List<Split> tempSplits = new ArrayList<>();
		tempSplits.add(inSplit);
		addUsedSplits(tempSplits);
	}
	
	public void addUsedSplits(List<Split> inUsedSplits) throws ApException {
		
		for (Split currSplit : inUsedSplits) {
			int splitIndex = currSplit.getSplitIndex();
			if (usedSplits.contains(splitIndex)) {
				throw new ApException("Attempted to add a used Split that was already used in method 'addUsedSplits'.");
			} else {
				usedSplits.add(splitIndex);
			}
		}
	}
	
	
	public Split getNextRightValue() {
		return  getNextRightValue(0);
	}
	
	
	public Split getNextRightValue(int inOffset) {
		
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
		
		int valueIndex = nextAvailableIndex - inOffset;
		if (valueIndex < 0 || valueIndex > values.size()) {
			return null;
		}
		
		return values.get(valueIndex);
	}
	
	public Split getNextLeftValue() {
		return getNextLeftValue(0);
	}


	/**
	 * 
	 * @param inOffset - offset of 0 should return the same result as get nextLeftValue
	 * @return
	 */
	public Split getNextLeftValue(int inOffset) {
		int nextAvailable = getNextLeftAvailable();
		if (nextAvailable != INVALID_INDEX) {
			for (int i=nextAvailable + inOffset; i < values.size(); i++) {
				if (! usedSplits.contains(i)) {
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


	public List<Split> getValuesLeft(int inLeftIndex) throws ApException {
		return getValues(inLeftIndex, -1);
	}
	
	
	/**
	 * @param inLeftIndex
	 * @param inRightIndex
	 * @return a list of all unused values between the two indices
	 */
	public List<Split> getValues(int inLeftIndex, int inRightIndex) throws ApException {
		List<Split> resultValues = new ArrayList<>();
		
		if (inLeftIndex < 0) {
			throw new ApException("Invalid index was passed to getValues(). leftIndex must be 0 or greater",
					"leftIndex", inLeftIndex);
		}
		
		if (inRightIndex >= values.size()) {
			throw new ApException("Invalid index was passed to getValues(). rightIndex must be less than values size.",
					"rightIndex", inRightIndex);
		}
		
		if (inRightIndex == -1) {
			for (int i= inLeftIndex; i < values.size(); i++) {
				if (! usedSplits.contains(i)) {
					Split tempValueIndex = values.get(i);
					resultValues.add(tempValueIndex);
				}
			}
		} else {
			for (int i= inLeftIndex; i < inRightIndex; i++) {
				if (! usedSplits.contains(i)) {
					Split tempValueIndex = values.get(i);
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
	public void addUsedSplitsAllRight(Split inSplit) throws ApException {
		int totalValues = values.size();
		List<Integer> tempUsedSplits = new ArrayList<>();
		for (int i=inSplit.getSplitIndex(); i < totalValues; i++) {
			if (! usedSplits.contains(i)) {
				tempUsedSplits.add(values.get(i).getSplitIndex());
			}
		}
		usedSplits.addAll(tempUsedSplits);
	}


	public static List<Integer> getValues(List<Split> inValueIndices) {
		List<Integer> resultValues = new ArrayList<>();
				
		for (Split currIndex : inValueIndices) {
			resultValues.add(currIndex.getSplitIndex());
		}
		return resultValues;
	}


	public List<Split> getRightSplits(int inNumberOfSplitsToGet) {
		List<Split> resultList = new ArrayList<>();
		for (int i=0; i < inNumberOfSplitsToGet; i++) {
			Split tempSplit = getNextRightValue(i);
			resultList.add(tempSplit);
		}
		
		Collections.reverse(resultList);
		return resultList;
	}


	public List<Split> getRemainingSplits() throws ApException {
		List<Split> resultSplits = new ArrayList<>();
		boolean foundUsedSplit = false;
		Split nextLeft = getNextLeftValue();
		if (nextLeft == null) {
			return resultSplits;
		}
		
		for (int i=nextLeft.getSplitIndex(); i < values.size(); i++) {
			if (!usedSplits.contains(i)) {
				if (foundUsedSplit) {
					throw new ApException("Called getRemainingSplits()."
							+ " but remaing splits were NOT continious."
							+ "It means that it found unused splits followed by used splits followed by unused splits.");
				}
				resultSplits.add(values.get(i));
			} else {
				foundUsedSplit = true;
			}
		}
		return resultSplits;
	}

	
	
	public Split getSplit(int inIndex) throws ApException {
	
		if (inIndex > values.size() -1) {
			throw new ApException("Get Split index out of bounds", 
					"index", inIndex);
		}
		
		return values.get(inIndex);
	
	}


}
