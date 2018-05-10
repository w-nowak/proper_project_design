package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.account.AccountId;

public interface AccountInfoProvider {
    AccountInfo getAccountInfoFor(AccountId accountId);
}
