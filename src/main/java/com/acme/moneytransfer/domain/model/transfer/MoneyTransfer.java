package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.Entity;
import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.acme.moneytransfer.domain.model.transfer.Status.CANCELED;
import static com.acme.moneytransfer.domain.model.transfer.Status.CREATED;
import static com.acme.moneytransfer.domain.model.transfer.Status.COMMITTED;
import static com.acme.core.util.preconditions.Preconditions.requireNonNull;
import static com.acme.core.util.preconditions.Preconditions.requireStateThat;
import static com.acme.core.util.time.ApplicationTime.dateTimeNow;
import static javax.persistence.EnumType.STRING;

@javax.persistence.Entity
@Getter
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
    private LocalDateTime lastStatusChangeDate;
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

    public void commit(){
        requireStateThat(this.status == CREATED, "A transfer has already been committed");
        this.changeStatusTo(COMMITTED);
    }

    public void cancel(){
        requireStateThat(this.status == CREATED, "A transfer cannot be cancelled because it's already been committed");
        this.changeStatusTo(CANCELED);
    }

    private void changeStatusTo(Status newStatus) {
        this.status = newStatus;
        this.lastStatusChangeDate = dateTimeNow();
    }
}
