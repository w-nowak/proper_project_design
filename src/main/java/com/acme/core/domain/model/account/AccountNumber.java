package com.acme.core.domain.model.account;

import com.acme.core.domain.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

import static com.acme.util.preconditions.Preconditions.requireNonEmpty;

@ToString
@EqualsAndHashCode
@Embeddable
public class AccountNumber extends ValueObject {
    private String number;

    private AccountNumber() { // only for JPA
    }

    public AccountNumber(String accountNumber) {
        this.number = requireCorrectAccountNumber(accountNumber);;
    }

    private static String requireCorrectAccountNumber(String accountNumber) {
        requireNonEmpty(accountNumber, "accountNumber");
        //TODO proper validation of account number
        return accountNumber;
    }
}
