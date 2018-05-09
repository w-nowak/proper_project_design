package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.Entity;
import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;

import javax.persistence.*;
import java.time.LocalDate;

import static com.acme.moneytransfer.domain.model.transfer.Status.CANCELED;
import static com.acme.moneytransfer.domain.model.transfer.Status.CREATED;
import static com.acme.moneytransfer.domain.model.transfer.Status.ORDERED;
import static com.acme.util.preconditions.Preconditions.requireNonNull;
import static com.acme.util.preconditions.Preconditions.requireStateThat;
import static java.time.LocalDate.now;
import static javax.persistence.EnumType.STRING;

@javax.persistence.Entity
public class MoneyTransfer extends Entity<MoneyTransferId> {
    @Embedded
    private AccountId accountId;
    @Embedded
    private TargetAccountDetails targetAccountDetails;
    @Embedded
    private Amount amount;
    private String transferDescription;
    private LocalDate transferDate;
    @Enumerated(STRING)
    private Status status;
    private LocalDate lastStatusChangeDate;
    @Version
    private int version;

    private MoneyTransfer() { // only for JPA
    }

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
        this.status = CREATED;
    }

    public void realize(){
        requireStateThat(this.status == CREATED, "A transfer cannot be realized because it's already been ordered");
        this.changeStatusTo(ORDERED);
    }

    public void cancel(){
        requireStateThat(this.status == CREATED, "A transfer cannot be cancelled because it's already been ordered");
        this.changeStatusTo(CANCELED);
    }

    private void changeStatusTo(Status newStatus) {
        this.status = newStatus;
        this.lastStatusChangeDate = now();
    }
}
