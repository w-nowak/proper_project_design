package com.acme.core.domain.model.account;

import com.acme.core.domain.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

import static com.acme.core.util.preconditions.Preconditions.requireNonEmpty;

@Embeddable
@ToString
@EqualsAndHashCode(callSuper = false)
public class AccountNumber extends ValueObject {
    private String number;

    private AccountNumber() { // only for JPA
    }

    private AccountNumber(String accountNumber) {
        this.number = requireCorrectAccountNumber(accountNumber);;
    }

    public String asString() {
    	return number;
	}

    public static AccountNumber of(String accountNumber) {
        return new AccountNumber(accountNumber);
    }

    private static String requireCorrectAccountNumber(String accountNumber) {
        requireNonEmpty(accountNumber, "accountNumber");
        //TODO proper validation of account number
        return accountNumber;
    }
}
