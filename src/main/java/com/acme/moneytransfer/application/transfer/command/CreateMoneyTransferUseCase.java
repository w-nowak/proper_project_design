package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.account.AccountNumber;
import com.acme.core.domain.model.money.Amount;
import com.acme.moneytransfer.domain.model.transfer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.acme.util.preconditions.Preconditions.requireNonNull;
import static com.acme.util.preconditions.Preconditions.requireThat;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Component
public class CreateMoneyTransferUseCase {
    private final MoneyTransferRepository moneyTransferRepository;
    private final AccountInfoProvider accountInfoProvider;
    private final AmountCurrencyConversionService amountCurrencyConversionService;
    private final AccountOnBlacklistProvider accountOnBlacklistProvider;

    @Autowired
	public CreateMoneyTransferUseCase(MoneyTransferRepository moneyTransferRepository,
									  AccountInfoProvider accountInfoProvider,
									  AmountCurrencyConversionService amountCurrencyConversionService,
									  AccountOnBlacklistProvider accountOnBlacklistProvider) {
		this.moneyTransferRepository = requireNonNull(moneyTransferRepository, "moneyTransferRepository");
		this.accountInfoProvider = requireNonNull(accountInfoProvider, "accountInfoProvider");
		this.amountCurrencyConversionService = requireNonNull(amountCurrencyConversionService, "amountCurrencyConversionService");
		this.accountOnBlacklistProvider = requireNonNull(accountOnBlacklistProvider, "accountOnBlacklistProvider");
	}

	@Transactional(REQUIRES_NEW)
    public String createTransfer(MoneyTransferCommand moneyTransferCommand){
        requireNonNull(moneyTransferCommand, "moneyTransferCommand");

        MoneyTransferId newId = this.moneyTransferRepository.nextIdentifier();
        MoneyTransfer moneyTransfer = toMoneyTransfer(newId, moneyTransferCommand);

        AccountInfo accountInfo = this.accountInfoProvider.getAccountInfoFor(moneyTransfer.getAccountId());
		requireThatAccountIsActive(moneyTransfer, accountInfo);
		this.requireThatThereIsEnoughFundsOnAccount(moneyTransfer, accountInfo);
		this.requireThatTargetAccountIsNotOnBlacklist(moneyTransfer.getTargetAccountDetails());

		this.moneyTransferRepository.save(moneyTransfer);

        return newId.id();
    }

	private void requireThatTargetAccountIsNotOnBlacklist(TargetAccountDetails targetAccountDetails) {
		requireThat(!this.accountOnBlacklistProvider.isAccountOnBlacklist(targetAccountDetails.getAccountNumber()),
			() -> new AccountOnBlacklistException(targetAccountDetails.getAccountNumber()));
	}

	private void requireThatThereIsEnoughFundsOnAccount(MoneyTransfer moneyTransfer, AccountInfo accountInfo) {
		Amount availableFunds = this.amountCurrencyConversionService
			.convertToAmountWithCurrency(moneyTransfer.getAmount().getCurrency(), accountInfo.getAvailableFunds());
		requireThat(availableFunds.isGreateThanOrEqualTo(moneyTransfer.getAmount()),
			() -> new NotEnoughFundsException(moneyTransfer.getAmount(), availableFunds));
	}

	private static void requireThatAccountIsActive(MoneyTransfer moneyTransfer, AccountInfo accountInfo) {
		requireThat(accountInfo.isActive(),
			() -> new InactiveAccountException(moneyTransfer.getTargetAccountDetails().getAccountNumber()));
	}

    private static MoneyTransfer toMoneyTransfer(MoneyTransferId newId, MoneyTransferCommand moneyTransferCommand) {
        return new MoneyTransfer(
                newId,
                AccountId.of(moneyTransferCommand.getAccountId()),
                toTargetAccountDetails(moneyTransferCommand),
                Amount.pln(moneyTransferCommand.getAmount()),
                moneyTransferCommand.getDescription(),
                moneyTransferCommand.getDate()
        );
    }

    private static TargetAccountDetails toTargetAccountDetails(MoneyTransferCommand moneyTransferCommand) {
        return new TargetAccountDetails(
                AccountNumber.of(moneyTransferCommand.getTargetAccountNmber()),
                moneyTransferCommand.getRecipientName(),
                moneyTransferCommand.getAddress()
                    .map(address -> new TargetAccountDetails.Address(address.getStreet(), address.getCity(), address.getZipCode()))
                    .orElse(TargetAccountDetails.Address.NOT_SPECIFIED)
        );
    }
}
