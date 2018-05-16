package com.acme.moneytransfer.domain.model.transfer;


import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.account.AccountNumber;
import com.acme.core.domain.model.money.Amount;

import java.time.LocalDate;

import static com.acme.core.util.time.ApplicationTime.dateNow;

class TestData {
	static final AccountId ACCOUNT_ID = AccountId.of("CD-A-180510-dfadres8");
	static final AccountNumber ACCOUNT_NUMBER = AccountNumber.of("15421542187854561548");
	static final TargetAccountDetails.Address ADDRESS  =
		new TargetAccountDetails.Address("Some street", "Wroclaw", "53100");
	static final String RECIPIENT = "some recipient";
	static final TargetAccountDetails TARGET_ACCOUNT_DETAILS =
		new TargetAccountDetails(ACCOUNT_NUMBER, RECIPIENT, ADDRESS);
	static final Amount AMOUNT_PLN = Amount.pln(1254.84);
	static final String DESCRIPTION = "some description";
	static final LocalDate DATE = dateNow();

	static final int YEAR_2018 = 2018;
	static final int MONTH_5 = 5;
	static final int DAY_16 = 16;
	static final int HOUR_9 = 9;
	static final int MINUTE_12 = 12;
	static final int SECOND_47 = 47;

	private TestData() {

	}
}
