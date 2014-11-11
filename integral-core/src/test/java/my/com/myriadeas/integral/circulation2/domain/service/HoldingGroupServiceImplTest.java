package my.com.myriadeas.integral.circulation2.domain.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.notNull;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroup;
import my.com.myriadeas.integral.circulation2.domain.model.HoldingGroupRepository;
import my.com.myriadeas.integral.circulation2.domain.model.ItemCategory;

import org.easymock.EasyMockSupport;
import org.junit.Test;

public class HoldingGroupServiceImplTest extends EasyMockSupport {

	private HoldingGroupService holdingGroupService = new HoldingGroupServiceImpl();

	@Test
	public void testFindOrCreate() {
		HoldingGroupRepository holdingGroupRepository = createMock(HoldingGroupRepository.class);
		ItemCategory itemCategory = new ItemCategory("OS");
		HoldingGroup existingHoldingGroup = new HoldingGroup(itemCategory);
		expect(holdingGroupRepository.findByItemCategory(itemCategory))
				.andReturn(existingHoldingGroup).times(1);
		ItemCategory nonExistItemCategory = new ItemCategory("RS");
		expect(holdingGroupRepository.findByItemCategory(nonExistItemCategory))
				.andReturn(null).times(1);
		HoldingGroup newHoldingGroup = new HoldingGroup(nonExistItemCategory);
		expect(holdingGroupRepository.save(notNull(HoldingGroup.class))).andStubReturn(
				newHoldingGroup);
		replay(holdingGroupRepository);

		((HoldingGroupServiceImpl) holdingGroupService)
				.setHoldingGroupRepository(holdingGroupRepository);
		HoldingGroup actual = holdingGroupService.findOrCreate(itemCategory);
		assertEquals(existingHoldingGroup, actual);

		actual = holdingGroupService.findOrCreate(nonExistItemCategory);
		assertEquals(newHoldingGroup, actual);
		verify(holdingGroupRepository);

	}
}
