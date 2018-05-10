package com.acme.moneytransfer.infrastructure.provider;

import com.acme.core.domain.model.account.AccountNumber;
import com.acme.moneytransfer.domain.model.transfer.AccountOnBlacklistProvider;
import org.springframework.stereotype.Component;

@Component
public class AccountOnBlacklistMockProvider implements AccountOnBlacklistProvider {
	@Override
	public boolean isAccountOnBlacklist(AccountNumber accountNumber) {
		return false;
	}
}
