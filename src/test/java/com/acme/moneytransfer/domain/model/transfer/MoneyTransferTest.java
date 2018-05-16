package com.acme.moneytransfer.domain.model.transfer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.acme.core.util.time.ApplicationClock.getFixedClockFor;
import static com.acme.core.util.time.ApplicationClock.getSystemDefaultClock;
import static com.acme.core.util.time.ApplicationClock.setCurrentClock;
import static com.acme.moneytransfer.domain.model.transfer.Status.CANCELED;
import static com.acme.moneytransfer.domain.model.transfer.Status.COMMITTED;
import static com.acme.moneytransfer.domain.model.transfer.Status.CREATED;
import static com.acme.moneytransfer.domain.model.transfer.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MoneyTransferTest {
	private static final MoneyTransferId MONEY_TRANSFER_ID = MoneyTransferId.newId();


	@BeforeAll
	static void setUp() {
		setCurrentClock(getFixedClockFor(YEAR_2018, MONTH_5, DAY_16, HOUR_9, MINUTE_12, SECOND_47));
	}

	@AfterAll
	static void cleanUp() {
		setCurrentClock(getSystemDefaultClock());
	}

	@Test
	void createsMoneyTranferInstanceWithCorrectState() {
		MoneyTransfer moneyTransfer = new MoneyTransfer(
			MONEY_TRANSFER_ID, ACCOUNT_ID, TARGET_ACCOUNT_DETAILS, AMOUNT_PLN, DESCRIPTION, DATE);

		assertThat(moneyTransfer).isNotNull();
		assertThat(moneyTransfer.getId()).isEqualTo(MONEY_TRANSFER_ID);
		assertThat(moneyTransfer.getAccountId()).isEqualTo(ACCOUNT_ID);
		assertThat(moneyTransfer.getTargetAccountDetails()).isEqualTo(TARGET_ACCOUNT_DETAILS);
		assertThat(moneyTransfer.getAmount()).isEqualTo(AMOUNT_PLN);
		assertThat(moneyTransfer.getTransferDescription()).isEqualTo(DESCRIPTION);
		assertThat(moneyTransfer.getTransferDate()).isEqualTo(DATE);
		assertThat(moneyTransfer.getLastStatusChangeDate()).isNull();
		assertThat(moneyTransfer.getStatus()).isEqualTo(CREATED);
	}

	@Test
	void onCommitChangesStateToCommittedWhenTransferIsInCreatedState() {
		MoneyTransfer moneyTransfer = new MoneyTransfer(
			MONEY_TRANSFER_ID, ACCOUNT_ID, TARGET_ACCOUNT_DETAILS, AMOUNT_PLN, DESCRIPTION, DATE);

		moneyTransfer.commit();

		assertThat(moneyTransfer).isNotNull();
		assertThat(moneyTransfer.getStatus()).isEqualTo(COMMITTED);
		assertThat(moneyTransfer.getTransferDate()).isEqualTo(DATE);
		assertThat(moneyTransfer.getLastStatusChangeDate())
			.isEqualTo(LocalDateTime.of(YEAR_2018, MONTH_5, DAY_16, HOUR_9, MINUTE_12, SECOND_47));
	}

	@Test
	void onCommitThrowsIllegalStateExceptionWhenTransferIsNotInCreatedState() {
		MoneyTransfer moneyTransfer = new MoneyTransfer(
			MONEY_TRANSFER_ID, ACCOUNT_ID, TARGET_ACCOUNT_DETAILS, AMOUNT_PLN, DESCRIPTION, DATE);
		moneyTransfer.cancel();

		//when
		Throwable caughtException = catchThrowable(() -> moneyTransfer.commit());

		assertThat(caughtException)
			.withFailMessage("An IllegalStateException was expected to be thrown")
			.isNotNull();
		assertThat(caughtException).isInstanceOf(IllegalStateException.class);
	}

	@Test
	void onCancelChangesStateToCanceledWhenTransferIsInCreatedState() {
		MoneyTransfer moneyTransfer = new MoneyTransfer(
			MONEY_TRANSFER_ID, ACCOUNT_ID, TARGET_ACCOUNT_DETAILS, AMOUNT_PLN, DESCRIPTION, DATE);

		moneyTransfer.cancel();

		assertThat(moneyTransfer).isNotNull();
		assertThat(moneyTransfer.getStatus()).isEqualTo(CANCELED);
		assertThat(moneyTransfer.getTransferDate()).isEqualTo(DATE);
		assertThat(moneyTransfer.getLastStatusChangeDate())
			.isEqualTo(LocalDateTime.of(YEAR_2018, MONTH_5, DAY_16, HOUR_9, MINUTE_12, SECOND_47));
	}

	@Test
	void onCancelThrowsIllegalStateExceptionWhenTransferIsNotInCreatedState() {
		MoneyTransfer moneyTransfer = new MoneyTransfer(
			MONEY_TRANSFER_ID, ACCOUNT_ID, TARGET_ACCOUNT_DETAILS, AMOUNT_PLN, DESCRIPTION, DATE);
		moneyTransfer.commit();

		//when
		Throwable caughtException = catchThrowable(() -> moneyTransfer.cancel());

		assertThat(caughtException)
			.withFailMessage("An IllegalStateException was expected to be thrown")
			.isNotNull();
		assertThat(caughtException).isInstanceOf(IllegalStateException.class);
	}
}