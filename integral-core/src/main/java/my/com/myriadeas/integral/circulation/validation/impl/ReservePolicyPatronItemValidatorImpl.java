package my.com.myriadeas.integral.circulation.validation.impl;

import java.util.List;

import my.com.myriadeas.integral.circulation.exception.PatronHasBorrowedItemByMaterialException;
import my.com.myriadeas.integral.circulation.request.ReserveRequest;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronItemValidator;
import my.com.myriadeas.integral.core.MaterialNotFoundException;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "reservePatronItemValidator")
public class ReservePolicyPatronItemValidatorImpl extends
		AbstractCirculationReservePatronItemValidator implements
		ReservePolicyPatronItemValidator {

	private static Logger logger = LoggerFactory
			.getLogger(ReservePolicyPatronItemValidatorImpl.class);

	@Override
	public void validate(ReserveRequest reserveRequest) {
		logger.debug("Entering validate(reserveRequest={})", reserveRequest);

		Item item = itemRepository.findByItemIdentifier(reserveRequest
				.getItemIdentifier());
		Patron patron = patronRepository.findByUsername(reserveRequest
				.getPatronIdentifier());
		validate(patron, item);

		logger.debug("Leaving validate(). ");
	}
	
	@Override
	protected void validateReserveCustomPolicyPatronItemValidator(Patron patron, Item item) {
		logger.debug(
				"Entering validateReserveCustomPolicyPatronItemValidator(patron={}, item={})",
				new Object[] { patron, item });

		validatePatronHasBorrowedItemByMaterial(patron, item);

		logger.debug("Leaving validateReserveCustomPolicyPatronItemValidator(). ");
	}

	private void validatePatronHasBorrowedItemByMaterial(Patron patron,
			Item item) throws MaterialNotFoundException,
			PatronHasBorrowedItemByMaterialException {
		logger.debug(
				"Entering validatePatronHasBorrowedItemByMaterial(patron={}, item={})",
				new Object[] { patron, item });

		Material material = item.getMaterial();
		logger.debug("material={}", material);
		if (material == null) {
			throw new MaterialNotFoundException("Material not found.");
		}
		List<Item> itemList = this.itemRepository.findByMaterial(material);
		logger.debug("item list={}", itemList);
		for (Item accession : itemList) {
			// No one borrow it
			if (accession.getPatron() != null) {
				validatePatronHasBorrowedItemByMaterial(patron.getUsername(),
						accession.getPatron().getUsername());
			}
		}

		logger.debug("Leaving validatePatronHasBorrowedItemByMaterial(). ");
	}

	protected void validatePatronHasBorrowedItemByMaterial(String patronId,
			String borrowerId) throws PatronHasBorrowedItemByMaterialException {
		logger.debug(
				"Entering validatePatronHasBorrowedItemByMaterial(patronId={}, borrowerId={})",
				new Object[] { patronId, borrowerId });

		if (patronId.equals(borrowerId)) {
			throw new PatronHasBorrowedItemByMaterialException(
					"Patron already borrowed item by material.");
		}

		logger.debug("Leaving validatePatronHasBorrowedItemByMaterial(). ");
	}

}
