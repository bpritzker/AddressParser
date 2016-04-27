package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.benp.addressparser.ApException;
import org.benp.addressparser.data.ApSplit;
import org.junit.Test;

public class ApSplitterTest extends ApSplitter {
	
	
	
	public ApSplitterTest() {
		super(null);
	}


	@Test
	public void getSplitValues() {
		
		String stringToSplit;
		Map<Integer, ApSplit> actual;
		
		getSplitValues(null);

		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		actual = getSplitValues(stringToSplit);
		assertEquals(6, actual.size());

		
		stringToSplit = "742 Evergreen Terrace, Springfield";
		actual = getSplitValues(stringToSplit);
		assertEquals(4, actual.size());
		
		
		stringToSplit = "742 E Evergreen Terrace";
		actual = getSplitValues(stringToSplit);
		assertEquals(4, actual.size());
		
		// extra spaces 
		stringToSplit = " 742 E   Evergreen  ,  Terrace";
		actual = getSplitValues(stringToSplit);
		assertEquals(4, actual.size());
		assertEquals("742", actual.get(0).getValue());
		assertEquals("E", actual.get(1).getValue());
		assertEquals("Evergreen", actual.get(2).getValue());
		assertEquals("Terrace", actual.get(3).getValue());
		
		
		// Extra spaces at the end
		stringToSplit = " 742 E   Evergreen  ,  Terrace   ";
		actual = getSplitValues(stringToSplit);
		assertEquals(4, actual.size());
		
	}
	
	@Test
	public void getValuesLeftTest() throws Exception {
		String stringToSplit = "742 Evergreen Terrace";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit actualNextLeft = splits.getNextLeftValue();
		assertEquals("742", actualNextLeft.getValue());
		splits.addUsedSplit(actualNextLeft);
		actualNextLeft = splits.getNextLeftValue();
		assertEquals("Evergreen", actualNextLeft.getValue());
		splits.addUsedSplit(actualNextLeft);
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
		
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit actualNextRight = splits.getNextRightValue();
		assertEquals("02111", actualNextRight.getValue());
		
		splits.addUsedSplit(actualNextRight);
		assertEquals("MA", splits.getNextRightValue().getValue());
	}

	@Test
	public void getRightValueOffset() throws Exception {

		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit actualSplit = splits.getNextRightValue(1);
		assertEquals("MA", actualSplit.getValue());
		
		splits.addUsedSplitsAllRight(actualSplit);
//		splits.addUsedSplits(5);
		assertEquals("Springfield", splits.getNextRightValue(0).getValue());
		assertEquals("Terrace", splits.getNextRightValue(1).getValue());
		assertEquals("Evergreen", splits.getNextRightValue(2).getValue());
	}

	
	@Test
	public void addUsedSplitsAllRight() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit tempSplit = splits.getNextRightValue(4);
		splits.addUsedSplitsAllRight(tempSplit);
		assertEquals(0, splits.getNextRightValue().getSplitIndex());
	}
	
	
	@Test
	public void getNextLeftValueOffsetValue() throws Exception {

		String stringToSplit;
		ApSplitter splits;

		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		splits = new ApSplitter(stringToSplit);
		ApSplit actualNextLeft = splits.getNextLeftValue();
		assertEquals("742", actualNextLeft.getValue());
		
		actualNextLeft = splits.getNextLeftValue(0);
		assertEquals("742", actualNextLeft.getValue());
		ApSplit actualNextLeftOffset = splits.getNextLeftValue(1);
		assertEquals("Evergreen", actualNextLeftOffset.getValue());
		
		splits.addUsedSplit(actualNextLeft);
		assertEquals("Evergreen", splits.getNextLeftValue().getValue());
		assertEquals("Evergreen", splits.getNextLeftValue(0).getValue());
		assertEquals("Terrace", splits.getNextLeftValue(1).getValue());
		
		
	}
	
	@Test
	public void testException() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit tempSplit = splits.getNextRightValue();
		splits.addUsedSplit(tempSplit);
		
		ApSplit badSplit = new ApSplit("DummyValue", 5);
		try {
			splits.addUsedSplit(badSplit);
			fail("The split was already used.");
		} catch (ApException ae) {
			assertEquals("Attempted to add a used Split that was already used in method 'addUsedSplits'.", ae.getAdditionalMessage());
		}
	}
	
	@Test
	public void testGetRemainingSplits() throws Exception {
		
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		ApSplit terraceSplit = splits.getNextRightValue(3);
		ApSplit number742Split = splits.getNextLeftValue();
		splits.addUsedSplit(number742Split);
		splits.addUsedSplitsAllRight(terraceSplit);
		List<ApSplit> actualRemainingSplits = splits.getRemainingSplits();
		assertEquals(1, actualRemainingSplits.size());
		
		splits = new ApSplitter(stringToSplit);
		splits.addUsedSplit(splits.getNextLeftValue());
		splits.addUsedSplit(splits.getNextRightValue(2));
		try {
			actualRemainingSplits = splits.getRemainingSplits();
			fail("Expected Exception");
		} catch (ApException ae) {
			// expected to catch exception
		}
		
		
	
	}
	
}
