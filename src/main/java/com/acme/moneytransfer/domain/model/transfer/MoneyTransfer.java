package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.Entity;
import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;

import java.time.LocalDate;

import static com.acme.util.preconditions.Preconditions.requireNonNull;

public class MoneyTransfer extends Entity<MoneyTransferId> {
    private final AccountId accountId;
    private final TargetAccountDetails targetAccountDetails;
    private final Amount amount;
    private final String transferDescription;
    private final LocalDate transferDate;

    public MoneyTransfer(
            MoneyTransferId moneyTransferId,
            AccountId accountId,
            TargetAccountDetails targetAccountDetails,
            Amount amount,
            String transferDescription,
            LocalDate transferDate) {
        super(moneyTransferId);
        this.accountId = requireNonNull(accountId, "accountId");
        this.targetAccountDetails = requireNonNull(targetAccountDetails, "targetAccountDetails");
        this.amount = requireNonNull(amount, "amount");
        this.transferDescription = requireNonNull(transferDescription, "transferDescription");
        this.transferDate = requireNonNull(transferDate, "transferDate");
    }


}
