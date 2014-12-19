package my.com.myriadeas.integral.assetmanagement.domain.model;

import static org.junit.Assert.*;

import my.com.myriadeas.integral.assetmanagement.application.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReleasedItemTest {
	
	private Item item;

	@Before
	public void setUp() throws Exception {
		item = new Item("item00000001", "resourcedescriptor01", null, null,
				ItemStatus.RELEASED);
	}

	@After
	public void tearDown() throws Exception {
		item = null;
	}

	@Test
	public void testRelease() {
		try {
			item.release();
			fail("Item should throw exception because released item cannot be release.");
		} catch (UnsupportedStatusTransitionException e) {
			
		}	
	}
	
	@Test
	public void testUnrelease() {
		try {
			item.unrelease();
		} catch (UnsupportedStatusTransitionException e) {
			fail("Item should not throw exception because released item can be unrelease.");
		}	
	}
	
	public void testDelete() {
		
		try {
			item.delete();
			fail("Item should throw exception because released item cannot be delete.");
		} catch (UnsupportedStatusTransitionException e) {
			
		}
	}

}
