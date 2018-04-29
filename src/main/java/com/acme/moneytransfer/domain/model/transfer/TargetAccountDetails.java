package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.ValueObject;
import com.acme.core.domain.model.account.AccountNumber;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.acme.util.preconditions.Preconditions.requireNonNull;


public class TargetAccountDetails extends ValueObject {
    private final AccountNumber accountNumber;
    private final String recipientName;
    private final Address address;

    public TargetAccountDetails(AccountNumber accountNumber, String recipientName, Address address) {
        this.accountNumber = accountNumber;
        this.recipientName = recipientName;
        this.address = address;
    }

    @ToString
    @EqualsAndHashCode
    public static class Address extends ValueObject {
        public static final Address NOT_SPECIFIED = Address.notSpecified();

        private final String addressLine;
        private final String city;
        private final String zipCode;

        public Address(String addressLine, String city, String zipCode) {
            this.addressLine = requireNonNull(addressLine, "addressLine");
            this.city = requireNonNull(city, "city");;
            this.zipCode = requireNonNull(zipCode, "zipCode");;
        }

        private static Address notSpecified(){
            return new Address("", "", "");
        }
    }
}
