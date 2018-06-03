package com.acme.moneytransfer.application.transfer.command;

import java.time.LocalDate;

import static com.acme.core.util.time.ApplicationTime.dateNow;

/**
 *
 */
class TestInputData {
	static final String ACCOUNT_RAW_ID = "CD-A-180510-dfadres8";
	static final String ACCOUNT_RAW_NUMBER = "15421542187854561548";
	static final String RECIPIENT_NAME = "some recipient";
	static final double AMOUNT_1574_25 = 1574.25;
	static final String ADDRESS_CITY = "Prague";
	static final String ADDRESS_STREET = "8 avenue";
	static final String ADDRESS_ZIP_CODE = "57-874";
	static final String TRANSFER_DESCRIPTON = "my another transfer";
	static final LocalDate TRANSFER_DATE = dateNow();
}
