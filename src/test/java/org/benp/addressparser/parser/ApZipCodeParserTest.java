package org.benp.addressparser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.benp.addressparser.ApAddressParserConfig;
import org.benp.addressparser.component.ApZipCode;
import org.junit.Before;
import org.junit.Test;

public class ApZipCodeParserTest extends ApZipCodeParser {
	


	public ApZipCodeParserTest() {
		super(null);
	}


	private ApZipCodeParser zipCodeParser;

	
	@Before
	public void before() {
		ApAddressParserConfig addressParserConfig = new ApAddressParserConfig();
		zipCodeParser = new ApZipCodeParser(addressParserConfig);
	}
	
	
	@Test
	public void parseZipCodeWholeAddress() throws Exception {
		
		ApSplitter splitter;
		ApZipCode actualZipCode;
		
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 02111");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("02111", actualZipCode.getZipCode());
		assertTrue(actualZipCode.isValid());
		
		
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 02111-8888");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("02111", actualZipCode.getZipCode());
		assertEquals("8888", actualZipCode.getPlus4Code());
		assertTrue(actualZipCode.isValid());
		
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA");
		actualZipCode = zipCodeParser.parse(splitter);
		assertFalse(actualZipCode.isValid());

		
		splitter = new ApSplitter("Springfield MA 02111 8888");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("02111", actualZipCode.getZipCode());
		assertEquals("8888", actualZipCode.getPlus4Code());
		assertTrue(actualZipCode.isValid());
		
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 0211-8888");
		actualZipCode = zipCodeParser.parse(splitter);
		assertFalse(actualZipCode.isValid());
	}
	

	@Test
	public void parseZipCodeCheckSetValues() throws Exception {
		
		ApSplitter splitter;
		
		splitter = new ApSplitter("Springfield MA 02111");
		zipCodeParser.parse(splitter);
		assertEquals(1, splitter.getNextRightValue().getIndex());
		
		splitter = new ApSplitter("742 Evergreen Terrace Springfield MA 02101-8888");
		zipCodeParser.parse(splitter);
		assertEquals(4, splitter.getNextRightValue().getIndex());
		
	}
	
	
	@Test
	public void parseZipCodeJustZip() throws Exception {
		
		ApSplitter splitter;
		ApZipCode actualZipCode;
		
		// case - address is less than 5 chars. Since we can't have a plus 4 code without 5 digit code this is NOT a valid zip code
		splitter = new ApSplitter("1234");
		actualZipCode = zipCodeParser.parse(splitter);
		assertFalse(actualZipCode.isValid());
		
		// case - simple example of zip code
		splitter = new ApSplitter("12345");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());

		// case - simple example of plus 4 code
		splitter = new ApSplitter("12345-6789");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());

		splitter = new ApSplitter("abc 12345");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());

		// case - contains text before the zip codes with plus 4 code
		splitter = new ApSplitter("abc 12345-6789");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());
		

		// case - contains space at end of string
		splitter = new ApSplitter("abc 12345-6789  ");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());
		
		// case - contains space at between zip code and plus code
		splitter = new ApSplitter("abc 12345 6789  ");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());

		// case - contains space at between zip code and plus code
		splitter = new ApSplitter("abc 12345 - 6789  ");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());

		// case - contains space at between zip code and plus code  minus space
		splitter = new ApSplitter("abc 12345 -6789  ");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());

		// case - contains space at between zip code and plus code  minus space
		splitter = new ApSplitter("abc 12345- 6789  ");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("12345", actualZipCode.getZipCode());
		assertEquals("6789", actualZipCode.getPlus4Code());
		
		
		splitter = new ApSplitter("Mountain View, CA 94043");
		actualZipCode = zipCodeParser.parse(splitter);
		assertEquals("94043", actualZipCode.getZipCode());
	}

}
