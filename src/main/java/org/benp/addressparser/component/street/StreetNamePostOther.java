package org.benp.addressparser.component.street;

import java.util.List;

import org.benp.addressparser.component.ComponentBase;
import org.benp.addressparser.data.Split;

/**
 * Catch all for anything that we don't know what to do whit that appears at the end of 
 * the street name
 * @author Ben P
 *
 */
public class StreetNamePostOther extends ComponentBase {
	
	@Override
	public String getValueNormalized() {
		List<Split> tempSplits = getSplitterIndecies();
		if (tempSplits == null) {
			return "";
		}
		
		StringBuilder resultSb = new StringBuilder();
		String joinStr = "";
		for (Split currSplit : tempSplits) {
			resultSb.append(joinStr).append(currSplit.getValue());
			joinStr = " ";
		}
		return resultSb.toString();
	}

//	public Object getNormalizedValue() {
//
//		List<Split> tempSplits = getSplitterIndecies();
//		if (tempSplits == null || tempSplits.size() == 0) {
//			return "";
//		}
//		
//		StringBuilder resultSb = new StringBuilder();
//		String joinerStr = "";
//		for (Split currSplit : tempSplits) {
//			resultSb.append(joinerStr).append(currSplit.getValue());
//			joinerStr = " ";
//		}
//		return resultSb.toString();
//		
//	}
	
	
}
