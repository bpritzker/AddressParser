package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.benp.addressparser.component.State;
import org.junit.Before;
import org.junit.Test;

public class StateParserTest {
	
	private StateParser stateParser;
	
	@Before
	public void onlyOnce() {
		stateParser = new StateParser(null);
	}
	
	@Test
	public void parseTest() throws Exception {
		ApSplitter splitter;
		State actualState;
		
		// case - state less than 1 char
		splitter = new ApSplitter("M");
		actualState = stateParser.parse(splitter);
		assertFalse(actualState.isValid());
		
		// case - simple example of state short code
		splitter = new ApSplitter("MA");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());

		splitter = new ApSplitter("Massachusetts");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());

		splitter = new ApSplitter("Mass");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());
		
		splitter = new ApSplitter(" MA ");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());

		splitter = new ApSplitter("123 Fake St MA ");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());
		
		splitter = new ApSplitter("123 Fake St MA 02111");
		actualState = stateParser.parse(splitter);
		assertEquals("MA", actualState.getStateDefinition().getCode());

	}
	
}
