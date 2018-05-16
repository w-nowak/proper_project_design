package com.acme.moneytransfer.domain.model.transfer;

import org.junit.jupiter.api.Test;

import static com.acme.moneytransfer.domain.model.transfer.TestData.ACCOUNT_NUMBER;
import static com.acme.moneytransfer.domain.model.transfer.TestData.ADDRESS;
import static com.acme.moneytransfer.domain.model.transfer.TestData.RECIPIENT;
import static org.assertj.core.api.Assertions.assertThat;

class TargetAccountDetailsTest {

	@Test
	void createsInstanceOfTargetAccountDetailsInCorrectState() {
		TargetAccountDetails targetAccountDetails = new TargetAccountDetails(ACCOUNT_NUMBER, RECIPIENT, ADDRESS);

		assertThat(targetAccountDetails).isNotNull();
		assertThat(targetAccountDetails.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
		assertThat(targetAccountDetails.getRecipientName()).isEqualTo(RECIPIENT);
		assertThat(targetAccountDetails.getAddress()).isEqualTo(ADDRESS);
	}

	@Test
	void instanceOfTargetAccountDetailsWithTheSameStateAreEqual() {
		TargetAccountDetails targetAccountDetails = new TargetAccountDetails(ACCOUNT_NUMBER, RECIPIENT, ADDRESS);
		TargetAccountDetails sameTargetAccountDetails = new TargetAccountDetails(ACCOUNT_NUMBER, RECIPIENT, ADDRESS);

		assertThat(targetAccountDetails).isEqualTo(sameTargetAccountDetails);
	}

	@Test
	void instanceOfTargetAccountDetailsWithDifferentStateAreNotEqual() {
		TargetAccountDetails targetAccountDetails = new TargetAccountDetails(ACCOUNT_NUMBER, RECIPIENT, ADDRESS);
		String differentRecipient = "different recipient";
		TargetAccountDetails differentTargetAccountDetails = new TargetAccountDetails(ACCOUNT_NUMBER, differentRecipient, ADDRESS);

		assertThat(targetAccountDetails).isNotEqualTo(differentTargetAccountDetails);
	}

}