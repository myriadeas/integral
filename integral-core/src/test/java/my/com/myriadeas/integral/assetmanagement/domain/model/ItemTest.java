package my.com.myriadeas.integral.assetmanagement.domain.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.assetmanagement.domain.event.ItemDeleted;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemReleased;
import my.com.myriadeas.integral.assetmanagement.domain.event.ItemUnreleased;
import my.com.myriadeas.integral.assetmanagement.domain.model.Item;
import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.junit.Test;

public class ItemTest {

	private String itemIdentifier = "item00000001";

	@Test
	public void testRelease() {

		Item newItem = getItemWithItemStatus(ItemStatus.NEW);
		Map<String, DomainEvent> actualEvents = newItem.release();

		DomainEvent expectedEvent = new ItemReleased(itemIdentifier);
		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		expectedEvents.put("itemReleased", expectedEvent);

		assertEquals(expectedEvents, actualEvents);
		assertEquals(ItemStatus.RELEASED, newItem.getItemStatus());

	}

	@Test
	public void testUnrelease() {

		Item releseditem = getItemWithItemStatus(ItemStatus.RELEASED);
		Map<String, DomainEvent> actualEvents = releseditem.unrelease();

		DomainEvent expectedEvent = new ItemUnreleased(itemIdentifier);
		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		expectedEvents.put("itemUnreleased", expectedEvent);

		assertEquals(expectedEvents, actualEvents);
		assertEquals(ItemStatus.UNRELEASED, releseditem.getItemStatus());

	}
	
	@Test
	public void testDelete() {
		
		Item unreleaseItem = getItemWithItemStatus(ItemStatus.UNRELEASED);
		Map<String, DomainEvent> actualEvents = unreleaseItem.delete();

		DomainEvent expectedEvent = new ItemDeleted(itemIdentifier);
		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		expectedEvents.put("itemDeleted", expectedEvent);

		assertEquals(expectedEvents, actualEvents);
		assertEquals(ItemStatus.DELETED, unreleaseItem.getItemStatus());
	}

	public Item getItemWithItemStatus(ItemStatus status) {
		return new Item(itemIdentifier, "resourcedescriptor01", null, null,
				status);
	}

}
