package com.acme.moneytransfer.domain.model.transfer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.acme.core.util.time.ApplicationClock.getFixedClockFor;
import static com.acme.core.util.time.ApplicationClock.getSystemDefaultClock;
import static com.acme.core.util.time.ApplicationClock.setCurrentClock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MoneyTransferIdTest {
	private static final String DOMAIN_SYMBOL = "MT";
	private static final String ENTITY_SYMBOL = "T";
	private static final String DOMAIN_ID = "MT-T-182004-XUS25IE9";
	private static final int YEAR = 2018;
	private static final int MONTH = 10;
	private static final int DAY = 03;
	private static final String ID_DATE_SEGMENT = "181003";

	@BeforeAll
	static void setUp() {
		setCurrentClock(getFixedClockFor(YEAR, MONTH, DAY));
	}

	@AfterAll
	static void cleanUp() {
		setCurrentClock(getSystemDefaultClock());
	}

	@Test
	void createsNewIdWithCorrectSymbols() {
		MoneyTransferId moneyTransferId = MoneyTransferId.newId();

		assertThat(moneyTransferId.id()).startsWith(DOMAIN_SYMBOL + "-" + ENTITY_SYMBOL + "-" + ID_DATE_SEGMENT + "-");
	}

	@Test
	void throwsIllegalArgumentExceptionWhenMoneyTransferIdHasIncorrectFormat() {
		String invalidDomainId = "OR-T-182X04-XUS25IE9";

		Throwable caughtException = catchThrowable(() -> MoneyTransferId.of(invalidDomainId));
		assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void createsMoneyTransferIdFromStringRepresentation() {
		MoneyTransferId moneyTransferId = MoneyTransferId.of(DOMAIN_ID);

		assertThat(moneyTransferId).isNotNull();
		assertThat(moneyTransferId.id()).isEqualTo(DOMAIN_ID);
	}
}