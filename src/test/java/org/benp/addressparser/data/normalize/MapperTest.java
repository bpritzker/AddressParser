package org.benp.addressparser.data.normalize;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MapperTest {
	
	private Mapper mapper;
	
	{
		mapper = new Mapper(null);
	}

	
	
	@Test
	public void normalizeKey() {
		
		String actual = mapper.normalizeKey("ST. AUGUSTINE");
		assertEquals("ST AUGUSTINE", actual);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
