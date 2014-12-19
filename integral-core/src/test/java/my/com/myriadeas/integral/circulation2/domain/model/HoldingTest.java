package my.com.myriadeas.integral.circulation2.domain.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.circulation2.domain.service.HoldingGroupService;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.junit.Test;

public class HoldingTest {

	@Test
	public void testRelease() {
		Holding holding = new Holding("0000000001");
		ItemCategory itemCategory = new ItemCategory("TEST_ICAT");
		HoldingGroupService holdingGroupService = new HoldingGroupService() {
			@Override
			public HoldingGroup findOrCreate(ItemCategory itemCategory) {
				return new HoldingGroup(itemCategory);
			}

		};
		((NewIso) HoldingStatus.NEW.getOperations())
				.setHoldingGroupService(holdingGroupService);

		Map<String, DomainEvent> events = holding.release(itemCategory);

		Map<String, DomainEvent> expectedEvents = new HashMap<String, DomainEvent>();
		DomainEvent expectedEvent = new HoldingReleased(null, "0000000001",
				null);
		expectedEvents.put("holdingReleased", expectedEvent);
		assertEquals(expectedEvents, events);
		assertEquals(holding.getStatus(), HoldingStatus.PENDING_AVAILABLE);
	}

}
