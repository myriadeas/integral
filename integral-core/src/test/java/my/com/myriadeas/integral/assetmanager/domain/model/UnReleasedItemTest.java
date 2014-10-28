package my.com.myriadeas.integral.assetmanager.domain.model;

import static org.junit.Assert.fail;
import my.com.myriadeas.integral.assetmanager.application.exception.UnsupportedStatusTransitionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnReleasedItemTest {

	private Item item;

	@Before
	public void setUp() throws Exception {
		item = new Item("item00000001", "resourcedescriptor01", null, null,
				ItemStatus.UNRELEASED);
	}

	@After
	public void tearDown() throws Exception {
		item = null;
	}

	@Test
	public void testRelease() {
		try {
			item.release();
		} catch (UnsupportedStatusTransitionException e) {
			fail("Item should not throw exception because unreleased item can be release.");
		}
	}

	@Test
	public void testUnrelease() {
		try {
			item.unrelease();
			fail("Item should throw exception because unreleased item cannot be unrelease.");
		} catch (UnsupportedStatusTransitionException e) {

		}
	}

	@Test
	public void testDelete() {
		try {
			item.delete();
		} catch (UnsupportedStatusTransitionException e) {
			fail("Item should not throw exception because unreleased item can be delete.");
		}
	}
}
