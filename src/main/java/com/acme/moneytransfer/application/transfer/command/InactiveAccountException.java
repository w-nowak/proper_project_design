package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.account.AccountNumber;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class InactiveAccountException extends RuntimeException {
	private final String accountNumber;

	InactiveAccountException(AccountNumber accountNumber) {
		super(format("An account of number %s is inactive, hence no operaton can be excecuted on it", accountNumber.asString()));
		this.accountNumber = accountNumber.asString();
	}
}
