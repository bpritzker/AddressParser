package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;

import org.benp.addressparser.ApException;
import org.junit.Test;

public class ApSplitterTest extends ApSplitter {
	
	
	
	public ApSplitterTest() {
		super(null);
	}


	@Test
	public void getSplitValues() {
		
		String stringToSplit;
		Map<Integer, String> actual;
		
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
		assertEquals("742", actual.get(0));
		assertEquals("E", actual.get(1));
		assertEquals("Evergreen", actual.get(2));
		assertEquals("Terrace", actual.get(3));
		
		
		// Extra spaces at the end
		stringToSplit = " 742 E   Evergreen  ,  Terrace   ";
		actual = getSplitValues(stringToSplit);
		assertEquals(4, actual.size());
		
	}
	
	
	@Test
	public void getValue() throws Exception {
		
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		
		ApSplitter splits = new ApSplitter(stringToSplit);
		assertEquals("742", splits.getValue(0));
		assertEquals("Evergreen", splits.getValue(1));
		assertEquals("Terrace", splits.getValue(2));
		assertEquals("Springfield", splits.getValue(3));
		assertEquals("MA", splits.getValue(4));
		assertEquals("02111", splits.getValue(5));
	}
	
	@Test
	public void getRightValue() throws Exception {
		
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		assertEquals("02111", splits.getNextRightValue().getValue());
		
		splits.addUsedSplits(5);
		assertEquals("MA", splits.getNextRightValue().getValue());
	}

	@Test
	public void getRightValueOffset() throws Exception {

		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
//		assertEquals("MA", splits.getNextRightValue(1).getValue());
		
		splits.addUsedSplits(4);
		splits.addUsedSplits(5);
		assertEquals("Springfield", splits.getNextRightValue(0).getValue());
		assertEquals("Terrace", splits.getNextRightValue(1).getValue());
		assertEquals("Evergreen", splits.getNextRightValue(2).getValue());
	}
	
	
//	@Test
//	public void getValues() {
//		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
//		ApSplitter splits = new ApSplitter(stringToSplit);
//		List<ApValueIndex> actual = splits.getValues(0, 2);
//		assertEquals(3, actual.size());
//	}
	
	@Test
	public void addUsedSplitsAllRight() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		
		splits.addUsedSplitsAllRight(4);
		assertEquals(3, splits.getNextRightValue().getIndex());
	}
	
	
	@Test
	public void getNextLeftValueOffsetValue() throws Exception {

		String stringToSplit;
		ApSplitter splits;

		stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		splits = new ApSplitter(stringToSplit);
//		assertEquals("742", splits.getNextLeftValue().getValue());
//		assertEquals("742", splits.getNextLeftValue(0).getValue());
//		assertEquals("Evergreen", splits.getNextLeftValue(1).getValue());
		
		splits.addUsedSplits(0);
//		assertEquals("Evergreen", splits.getNextLeftValue().getValue());
//		assertEquals("Evergreen", splits.getNextLeftValue(0).getValue());
		assertEquals("Terrace", splits.getNextLeftValue(1).getValue());
		
		
	}
	
	@Test
	public void testException() throws Exception {
		String stringToSplit = "742 Evergreen Terrace Springfield MA 02111";
		ApSplitter splits = new ApSplitter(stringToSplit);
		
		splits.addUsedSplits(3);
		try {
			splits.addUsedSplits(3);
			fail("The split was already used.");
		} catch (ApException ae) {
			assertEquals("Attempted to add a used Split that was already used in method 'addUsedSplits'.", ae.getAdditionalMessage());
		}
	}
	
	
}
