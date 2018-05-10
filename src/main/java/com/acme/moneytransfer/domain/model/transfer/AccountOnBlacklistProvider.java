package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.account.AccountNumber;

public interface AccountOnBlacklistProvider {
	boolean isAccountOnBlacklist(AccountNumber accountNumber);
}
