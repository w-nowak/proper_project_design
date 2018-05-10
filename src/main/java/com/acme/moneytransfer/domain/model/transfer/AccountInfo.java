package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.ValueObject;
import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.acme.util.preconditions.Preconditions.requireNonNull;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class AccountInfo extends ValueObject {
    private final AccountId accountId;
    private final Amount availableFunds;
    private final boolean active;

    public AccountInfo(AccountId accountId, Amount availableFunds, boolean active) {
        this.accountId = requireNonNull(accountId, "accountId");
        this.availableFunds = requireNonNull(availableFunds, "availableFunds");
        this.active = active;
    }
}
