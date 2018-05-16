package com.acme.moneytransfer.domain.model.transfer;

import org.junit.jupiter.api.Test;

import static com.acme.moneytransfer.domain.model.transfer.TestData.ACCOUNT_ID;
import static com.acme.moneytransfer.domain.model.transfer.TestData.AMOUNT_PLN;
import static org.assertj.core.api.Assertions.assertThat;

class AccountInfoTest {
	@Test
	void createsAccountInfoWithCorrectState() {
		boolean isActive = true;
		AccountInfo accountInfo = new AccountInfo(ACCOUNT_ID, AMOUNT_PLN, isActive);

		assertThat(accountInfo).isNotNull();
		assertThat(accountInfo.getAccountId()).isEqualTo(ACCOUNT_ID);
		assertThat(accountInfo.getAvailableFunds()).isEqualTo(AMOUNT_PLN);
		assertThat(accountInfo.isActive()).isTrue();
	}

	@Test
	void instancesOfAccountInfoWithTheSameStateAreEqual() {
		boolean isActive = true;
		AccountInfo accountInfo = new AccountInfo(ACCOUNT_ID, AMOUNT_PLN, isActive);
		AccountInfo sameAccountInfo = new AccountInfo(ACCOUNT_ID, AMOUNT_PLN, isActive);

		assertThat(accountInfo).isEqualTo(sameAccountInfo);
	}

	@Test
	void instancesOfAccountInfoWithDifferentStateAreNotEqual() {
		boolean isActive = true;
		boolean isNotActive = false;
		AccountInfo accountInfo = new AccountInfo(ACCOUNT_ID, AMOUNT_PLN, isActive);
		AccountInfo differentAccountInfo = new AccountInfo(ACCOUNT_ID, AMOUNT_PLN, isNotActive);

		assertThat(accountInfo).isNotEqualTo(differentAccountInfo);
	}
}