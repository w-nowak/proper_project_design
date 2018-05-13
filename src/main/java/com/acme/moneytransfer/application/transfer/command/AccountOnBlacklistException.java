package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.account.AccountNumber;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class AccountOnBlacklistException extends RuntimeException {
	private final String accountNumber;

	AccountOnBlacklistException(AccountNumber accountNumber) {
		super(
			format(
				"A given target account with number '%s' is on blacklist so no operation can be made upon it",
				accountNumber.asString()
			)
		);
		this.accountNumber = accountNumber.asString();
	}
}
