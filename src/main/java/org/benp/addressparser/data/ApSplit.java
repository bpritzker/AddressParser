package org.benp.addressparser.data;

import java.util.ArrayList;
import java.util.List;

public class ApSplit {

	private String value;
	
	/**
	 * This is the location in the splitter of where is split is located.
	 */
	private int splitIndex;

	public int getSplitIndex() {
		return splitIndex;
	}

	public void setSplitIndex(int splitIndex) {
		this.splitIndex = splitIndex;
	}

	public ApSplit(String value, int splitIndex) {
		this.value = value;
		this.splitIndex = splitIndex;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
//	public int getIndex() {
//		return index;
//	}
//	public void setIndex(int index) {
//		this.index = index;
//	}

	public static String joinValues(List<ApSplit> inValueIndices) {

		StringBuilder resultSb = new StringBuilder();
		String seperator = "";
		for (ApSplit currValueIndex : inValueIndices) {
			resultSb.append(seperator).append(currValueIndex.getValue());
			seperator = " ";
		}
		return resultSb.toString();
	}

	public static List<Integer> getIndices(List<ApSplit> inValueIndices) {

		List<Integer> resultIndices = new ArrayList<>();
		for (ApSplit currValueIndex : inValueIndices) {
			resultIndices.add(currValueIndex.getSplitIndex());
		}
		return resultIndices;
	}

	
	@Override
	public String toString() {
		return "ApSplit [value=" + value + ", splitIndex=" + splitIndex + "]";
	}

}
