package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.benp.addressparser.component.ApState;
import org.junit.Before;
import org.junit.Test;

public class ApStateParserTest {
	
	private ApStateParser stateParser;
	
	@Before
	public void onlyOnce() {
		stateParser = new ApStateParser(null);
	}
	
	@Test
	public void parseTest() throws Exception {
		ApSplitter splitter;
		ApState actualState;
		
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
