package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.account.AccountNumber;
import com.acme.core.domain.model.money.Amount;
import com.acme.moneytransfer.domain.model.transfer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.stream.Stream;

import static com.acme.core.domain.model.money.Amount.pln;
import static com.acme.core.util.EqualableArgMatcher.argIsEqualTo;
import static com.acme.moneytransfer.application.transfer.command.TestInputData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
class CreateMoneyTransferUseCaseTest {
	@Mock private MoneyTransferRepository moneyTransferRepository;
	@Mock private AccountInfoProvider accountInfoProvider;
	@Mock private AmountCurrencyConversionService amountCurrencyConversionService;
	@Mock private AccountOnBlacklistProvider accountOnBlacklistProvider;
	private CreateMoneyTransferUseCase createMoneyTransferUseCase;

	@BeforeEach
	public void setUp() {
		this.createMoneyTransferUseCase = new CreateMoneyTransferUseCase(
			this.moneyTransferRepository,
			this.accountInfoProvider,
			this.amountCurrencyConversionService,
			this.accountOnBlacklistProvider
		);
	}

	@Test
	void createsAndSavesNewTransferWhenAllInvariantsAreMet() {
		MoneyTransferCommand moneyTransferCommand = MoneyTransferCommand
			.builder(ACCOUNT_RAW_ID, ACCOUNT_RAW_NUMBER, RECIPIENT_NAME, AMOUNT_1574_25)
			.withAddress(ADDRESS_CITY, ADDRESS_STREET, ADDRESS_ZIP_CODE)
			.withDescription(TRANSFER_DESCRIPTON)
			.withDate(TRANSFER_DATE)
			.build();

		final MoneyTransferId moneyTransferId = MoneyTransferId.newId();
		final AccountId accountId = AccountId.of(ACCOUNT_RAW_ID);
		final AccountNumber accountNumber = AccountNumber.of(ACCOUNT_RAW_NUMBER);
		final Amount amount = pln(AMOUNT_1574_25);

		final MoneyTransfer moneyTransfer = new MoneyTransfer(
			moneyTransferId, accountId,
			new TargetAccountDetails(accountNumber, RECIPIENT_NAME,
				new TargetAccountDetails.Address(ADDRESS_CITY, ADDRESS_STREET, ADDRESS_ZIP_CODE)),
			amount,
			TRANSFER_DESCRIPTON, TRANSFER_DATE);

		final double sufficientAvailableFunds = 3528D;
		final boolean accountActive = true;
		final boolean accountNotOnTheBlackList = false;
		final Amount availableAmount = pln(sufficientAvailableFunds);
		final AccountInfo accountInfo = new AccountInfo(accountId, availableAmount, accountActive);
		final Amount convertedAmount = availableAmount;

		given(this.moneyTransferRepository.nextIdentifier()).willReturn(moneyTransferId);
		given(this.accountInfoProvider.getAccountInfoFor(accountId)).willReturn(accountInfo);
		given(this.accountOnBlacklistProvider.isAccountOnBlacklist(accountNumber)).willReturn(accountNotOnTheBlackList);
		given(this.amountCurrencyConversionService.convertToAmountWithCurrency(amount.getCurrency(), availableAmount)).willReturn(convertedAmount);

		this.createMoneyTransferUseCase.createTransfer(moneyTransferCommand);

		then(this.moneyTransferRepository).should().save(argIsEqualTo(moneyTransfer));
	}

	@ParameterizedTest(name = "{index} => when available founds are {0} and it''s {1} that the account is on a black list " +
		"and it''s {2} that the account is active then {3} exception should be thrown")
	@MethodSource("provideTestCases")
	@MockitoSettings(strictness = LENIENT)
	void throwsExceptionWhenAnyOfInvariantIsNotMet(double availableFunds, boolean isAccountOnBlackList,
												   boolean isAccountActive, Class<? extends Exception> expectedExceptionClass) {
		MoneyTransferCommand moneyTransferCommand = MoneyTransferCommand
			.builder(ACCOUNT_RAW_ID, ACCOUNT_RAW_NUMBER, RECIPIENT_NAME, AMOUNT_1574_25)
			.withAddress(ADDRESS_CITY, ADDRESS_STREET, ADDRESS_ZIP_CODE)
			.withDescription(TRANSFER_DESCRIPTON)
			.withDate(TRANSFER_DATE)
			.build();

		final MoneyTransferId moneyTransferId = MoneyTransferId.newId();
		final AccountId accountId = AccountId.of(ACCOUNT_RAW_ID);
		final AccountNumber accountNumber = AccountNumber.of(ACCOUNT_RAW_NUMBER);
		final Amount amount = pln(AMOUNT_1574_25);

		final Amount availableAmount = pln(availableFunds);
		final AccountInfo accountInfo = new AccountInfo(accountId, availableAmount, isAccountActive);
		final Amount convertedAmount = availableAmount;

		given(this.moneyTransferRepository.nextIdentifier()).willReturn(moneyTransferId);
		given(this.accountInfoProvider.getAccountInfoFor(accountId)).willReturn(accountInfo);
		given(this.accountOnBlacklistProvider.isAccountOnBlacklist(accountNumber)).willReturn(isAccountOnBlackList);
		given(this.amountCurrencyConversionService.convertToAmountWithCurrency(amount.getCurrency(), availableAmount)).willReturn(convertedAmount);

		Throwable caughtException = catchThrowable(() -> this.createMoneyTransferUseCase.createTransfer(moneyTransferCommand));

		assertThat(caughtException).isInstanceOf(expectedExceptionClass);
	}

	private static Stream<Arguments> provideTestCases() {
		final double insufficientFounds = 1258.41;
		final double sufficientFounds = 5820.75;

		final boolean accountIsOnBlackList = true;
		final boolean accountIsNotOnBlackList = false;

		final boolean accountIsActive = true;
		final boolean accountIsNotActive = false;

		return Stream.of(
			Arguments.of(insufficientFounds, accountIsNotOnBlackList, accountIsActive, NotEnoughFundsException.class),
			Arguments.of(sufficientFounds, accountIsOnBlackList, accountIsActive, AccountOnBlacklistException.class),
			Arguments.of(sufficientFounds, accountIsNotOnBlackList, accountIsNotActive, InactiveAccountException.class)
		);
	}
}
