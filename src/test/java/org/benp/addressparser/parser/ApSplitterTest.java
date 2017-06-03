package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.benp.addressparser.common.ApException;
import org.benp.addressparser.data.Split;
import org.junit.Test;

public class ApSplitterTest extends ApSplitter {
	
	
	
	public ApSplitterTest() {
		super(null);
	}


	@Test
	public void getSplitValues() {
		
		String stringToSplit;
		Map<Integer, Split> actualSplitter;
		
		getSplitValues(null);

		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		actualSplitter = getSplitValues(stringToSplit);
		assertEquals(6, actualSplitter.size());

		
		stringToSplit = "742 Evergreen Terrace, Springfield";
		actualSplitter = getSplitValues(stringToSplit);
		assertEquals(4, actualSplitter.size());
		
		
		stringToSplit = "742 E Evergreen Terrace";
		actualSplitter = getSplitValues(stringToSplit);
		assertEquals(4, actualSplitter.size());
		
		// extra spaces 
		stringToSplit = " 742 E   Evergreen  ,  Terrace";
		actualSplitter = getSplitValues(stringToSplit);
		assertEquals(4, actualSplitter.size());
		assertEquals("742", actualSplitter.get(0).getValue());
		assertEquals("E", actualSplitter.get(1).getValue());
		assertEquals("Evergreen", actualSplitter.get(2).getValue());
		assertEquals("Terrace", actualSplitter.get(3).getValue());
		
		
		// Extra spaces at the end
		stringToSplit = " 742 E   Evergreen  ,  Terrace   ";
		actualSplitter = getSplitValues(stringToSplit);
		assertEquals(4, actualSplitter.size());
		
	}
	
	@Test
	public void getValuesLeftTest() throws Exception {
		String stringToSplit = "742 Evergreen Terrace";
		ApSplitter splitter = new ApSplitter(stringToSplit);
		Split actualNextLeft = splitter.getNextLeftValue();
		assertEquals("742", actualNextLeft.getValue());
		splitter.addUsedSplit(actualNextLeft);
		actualNextLeft = splitter.getNextLeftValue();
		assertEquals("Evergreen", actualNextLeft.getValue());
		splitter.addUsedSplit(actualNextLeft);
	}
	
	
//	@Test
//	public void getValue() throws Exception {
//		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
//		ApSplitter splits = new ApSplitter(stringToSplit);
//		assertEquals("742", splits.getValue(0));
//		assertEquals("Evergreen", splits.getValue(1));
//		assertEquals("Terrace", splits.getValue(2));
//		assertEquals("Springfield", splits.getValue(3));
//		assertEquals("MA", splits.getValue(4));
//		assertEquals("02111", splits.getValue(5));
//	}
	
	@Test
	public void getRightValue() throws Exception {
		
		String stringToSplit;
		ApSplitter splitter;
		Split actualNextRight;
		
		
		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		splitter = new ApSplitter(stringToSplit);
		actualNextRight = splitter.getNextRightValue();
		assertEquals("02111", actualNextRight.getValue());
		
		splitter.addUsedSplit(actualNextRight);
		assertEquals("MA", splitter.getNextRightValue().getValue());
		
	}

	@Test
	public void getNextRightValueOffset() throws Exception {

		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splitter = new ApSplitter(stringToSplit);
		Split actualNextRight = splitter.getNextRightValue(1);
		assertEquals("MA", actualNextRight.getValue());
		
		splitter.addUsedSplitsAllRight(actualNextRight);
		assertEquals("Springfield", splitter.getNextRightValue(0).getValue());
		assertEquals("Terrace", splitter.getNextRightValue(1).getValue());
		assertEquals("Evergreen", splitter.getNextRightValue(2).getValue());
		
		stringToSplit = "742 Evergreen Terrace South";
		splitter = new ApSplitter(stringToSplit);
		Split tempSplit = splitter.getSplit(2);
		splitter.addUsedSplit(tempSplit);
		actualNextRight = splitter.getNextRightValue(tempSplit.getSplitIndex());
		assertEquals("Evergreen", actualNextRight.getValue());
	}

	
	@Test
	public void addUsedSplitsAllRight() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splitter = new ApSplitter(stringToSplit);
		Split tempSplit = splitter.getSplit(4);
		splitter.addUsedSplitsAllRight(tempSplit);
		Split actualNextRight = splitter.getNextRightValue();
		assertEquals(3, actualNextRight.getSplitIndex());
	}
	
	
	@Test
	public void getNextLeftValueOffsetValue() throws Exception {

		String stringToSplit;
		ApSplitter splitter;

		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		splitter = new ApSplitter(stringToSplit);
		Split actualNextLeft = splitter.getNextLeftValue();
		assertEquals("742", actualNextLeft.getValue());
		
		actualNextLeft = splitter.getNextLeftValue(0);
		assertEquals("742", actualNextLeft.getValue());
		Split actualNextLeftOffset = splitter.getNextLeftValue(1);
		assertEquals("Evergreen", actualNextLeftOffset.getValue());
		
		splitter.addUsedSplit(actualNextLeft);
		assertEquals("Evergreen", splitter.getNextLeftValue().getValue());
		assertEquals("Evergreen", splitter.getNextLeftValue(0).getValue());
		assertEquals("Terrace", splitter.getNextLeftValue(1).getValue());
		
		
	}
	
	@Test
	public void testException() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splitter = new ApSplitter(stringToSplit);
		Split tempSplit = splitter.getNextRightValue();
		splitter.addUsedSplit(tempSplit);
		
		Split badSplit = new Split("DummyValue", 5);
		try {
			splitter.addUsedSplit(badSplit);
			fail("The split was already used.");
		} catch (ApException ae) {
			assertEquals("Attempted to add a used Split that was already used in method 'addUsedSplits'.", ae.getAdditionalMessage());
		}
	}
	
	@Test
	public void testGetRemainingSplits() throws Exception {

		List<Split> actualRemainingSplits;
		ApSplitter splitter;
		
		// Test all with 3 values
		splitter = new ApSplitter("South Princeton Circle");
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(3, actualRemainingSplits.size());
		
		// All Splits are used
		splitter = new ApSplitter("South Princeton Circle");
		splitter.addUsedSplit(splitter.getSplit(0));
		splitter.addUsedSplit(splitter.getSplit(1));
		splitter.addUsedSplit(splitter.getSplit(2));
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(0, actualRemainingSplits.size());
		
		// Test with the first left value used (one value on the left used)
		splitter = new ApSplitter("742 Evergreen");
		splitter.addUsedSplit(splitter.getNextLeftValue());
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(1, actualRemainingSplits.size());

		// This caused an exception
		splitter = new ApSplitter("742 Evergreen St");
		splitter.addUsedSplit(splitter.getNextLeftValue());
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(2, actualRemainingSplits.size());
		
		
		// Test used split with two values between them. Caused exception
		splitter = new ApSplitter("580 Green Dolphin Drive South");
		Split tempSplit1 = splitter.getSplit(0);
		splitter.addUsedSplit(tempSplit1);
		Split tempSplit2 = splitter.getSplit(3);
		splitter.addUsedSplitsAllRight(tempSplit2);
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(2, actualRemainingSplits.size());
		
		// Test used split on the left and a number of them on the left
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 02111");
		Split terraceSplit = splitter.getSplit(2);
		splitter.addUsedSplitsAllRight(terraceSplit);
		Split number742Split = splitter.getSplit(0);
		splitter.addUsedSplit(number742Split);
		actualRemainingSplits = splitter.getRemainingSplits();
		assertEquals(1, actualRemainingSplits.size());
		
		
		

		
		// Test the exception is thrown when the remaining splits are NOT contagious
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 02111");
		splitter.addUsedSplit(splitter.getSplit(0));
		splitter.addUsedSplit(splitter.getSplit(4));
		try {
			actualRemainingSplits = splitter.getRemainingSplits();
			fail("Expected Exception");
		} catch (ApException ae) {
			// expected to catch exception so do nothing.
		}
	}
	
}
