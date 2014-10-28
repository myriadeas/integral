package my.com.myriadeas.integral.cataloguing2.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;
import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorStatus;
import my.com.myriadeas.integral.cataloguing2.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.junit.Test;

public class ResourceDescriptorTest {

	String marc = "01263cam a2200337 a 4500001000800000005001700008008004100025906004500066010001700111020002700128035002600155040003100181043001200212050002500224066000700249245008300256250002200339260010300361300002500464500006900489651003600558700002100594700002300615740003800638880007000676880002400746880009100770880002300861880002300884985001800907520483020070825203548.0940207s1990    cc            000 0 chi    a7bcbccorignewduencipf19gy-rlinjack  a   92200709   a7805282439 :cRMBY5.50  a(CStRLIN)DCLP94-B2309  aDLC-RcDLC-RdCStRLINdDLC  aa-cc---00aDS721b.C572467 1990  c$1006880-01aZhongguo chuan tong wen hua qi guan /cGu Tai, Zhang Li deng bian zhu.  6880-02aDi 1 ban.  6880-03aChangchun Shi :bJilin wen shi chu ban she :bJilin sheng xin hua shu dian fa xing,c1990.  a13, 443 p. ;c19 cm.  aColophon title also in pinyin: Zhongguo chuantong wenhua qiguan. 0aChinaxCivilizationxAnecdotes.1 6880-04aGu, Tai.1 6880-05aZhang, Li.0 aZhongguo chuantong wenhua qiguan.006245-01/$1a中国传统文化奇观 /c顾太,　张利等编著.  6250-02/$1a第1版.  6260-03/$1a长春市　:b吉林文史出版社 :b吉林省新华书店发行,c1990.1 6700-04/$1a顾太.1 6700-05/$1a张利.  aspacingreload";

	@Test
	public void testConstructor() {
		ResourceDescriptor rd1 = new ResourceDescriptor(marc);
		ResourceDescriptor rd2 = new ResourceDescriptor(rd1.getMarcRecord());
		assertEquals(rd1.getJsonString(), rd2.getJsonString());
	}

	@Test
	public void testDraft() {
		ResourceDescriptor rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.DRAFT);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		events = rd.delete();
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DRAFT);
		events = rd.finalize(marc);
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DRAFT);
		try {
			rd.revise(marc);
			fail("Should throw exception because Draft cannot be changed to Completed.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DRAFT);
		try {
			rd.sendTodelete();
			fail("Should throw exception because Draft cannot be changed to Awaiting Deletion.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DRAFT);
		events = rd.update(marc);
		assertEquals(1, events.size());

	}

	@Test
	public void testCompleted() {
		ResourceDescriptor rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.COMPLETED);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		try {
			rd.delete();
			fail("Should throw exception because Completed cannot be changed to Deleted.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.COMPLETED);
		try {
			rd.finalize(marc);
			fail("Should throw exception because Completed cannot be changed to Completed.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.COMPLETED);
		events = rd.revise(marc);
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.COMPLETED);
		events = rd.sendTodelete();
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.COMPLETED);
		try {
			rd.update(marc);
			fail("Should throw exception because Completed cannot be updated.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

	}

	@Test
	public void testRevised() {
		ResourceDescriptor rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.REVISED);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		try {
			rd.delete();
			fail("Should throw exception because Revised cannot be changed to Deleted.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.REVISED);
		events = rd.finalize(marc);
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.REVISED);
		try {
			rd.revise(marc);
			fail("Should throw exception because Revised cannot be changed to Revised.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.REVISED);
		events = rd.sendTodelete();
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.REVISED);
		events = rd.update(marc);
		assertEquals(1, events.size());
	}
	
	@Test
	public void testAwaitingDeletion() {
		ResourceDescriptor rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus._AWAITING_DELETION);
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		events = rd.delete();
		assertEquals(1, events.size());

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus._AWAITING_DELETION);
		
		try {
			rd.finalize(marc);
			fail("Should throw exception because Awaiting Deletion cannot be changed to Completed.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus._AWAITING_DELETION);
		try {
			rd.revise(marc);
			fail("Should throw exception because Awaiting Deletion cannot be changed to Revised.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus._AWAITING_DELETION);
		try {
			rd.sendTodelete();
			fail("Should throw exception because Awaiting Deletion cannot be changed to Awaiting Deletion.");
		} catch (UnsupportedStatusTransitionException uste) {

		}
		

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus._AWAITING_DELETION);
		try {
			rd.update(marc);
			fail("Should throw exception because Awaiting Deletion cannot be updated.");
		} catch (UnsupportedStatusTransitionException uste) {

		}
	}
	
	@Test
	public void testDeleted() {
		
		ResourceDescriptor rd = generateResourceDescriptor(marc,
				ResourceDescriptorStatus.DELETED);
		try {
			rd.delete();
			fail("Should throw exception because Deleted cannot be changed to Deleted.");
		} catch (UnsupportedStatusTransitionException uste) {

		}
		
		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DELETED);
		try {
			rd.finalize(marc);
			fail("Should throw exception because Deleted cannot be changed to Completed.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DELETED);
		try {
			rd.revise(marc);
			fail("Should throw exception because Deleted cannot be changed to Revised.");
		} catch (UnsupportedStatusTransitionException uste) {

		}

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DELETED);
		try {
			rd.sendTodelete();
			fail("Should throw exception because Deleted cannot be changed to Awaiting Deletion.");
		} catch (UnsupportedStatusTransitionException uste) {

		}
		

		rd = generateResourceDescriptor(marc, ResourceDescriptorStatus.DELETED);
		try {
			rd.update(marc);
			fail("Should throw exception because Deleted cannot be updated.");
		} catch (UnsupportedStatusTransitionException uste) {

		}
	}

	private ResourceDescriptor generateResourceDescriptor(String marc,
			ResourceDescriptorStatus status) {

		Random rand = new Random();
		int id = rand.nextInt(99999999);
		String rdId = String.format("%10d", id);
		return new ResourceDescriptor(rdId, marc, status);

	}

}
