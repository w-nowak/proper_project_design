package com.acme.moneytransfer.infrastructure.provider;

import com.acme.core.domain.model.account.AccountId;
import com.acme.core.domain.model.money.Amount;
import com.acme.moneytransfer.domain.model.transfer.AccountInfo;
import com.acme.moneytransfer.domain.model.transfer.AccountInfoProvider;
import org.springframework.stereotype.Component;

@Component
public class AccountInfoMockProvider implements AccountInfoProvider {
    @Override
    public AccountInfo getAccountInfoFor(AccountId accountId) {
        final double availableFounds = 1500;
        final boolean accountIsActive = true;

        return new AccountInfo(accountId, Amount.pln(availableFounds), accountIsActive);
    }
}
