package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.ValueObject;
import com.acme.core.domain.model.account.AccountNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

import static com.acme.util.preconditions.Preconditions.requireNonEmpty;
import static com.acme.util.preconditions.Preconditions.requireNonNull;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class TargetAccountDetails extends ValueObject {
    private AccountNumber accountNumber;
    private String recipientName;
    private Address address;

    private TargetAccountDetails() { // only for JPA
    }

    public TargetAccountDetails(AccountNumber accountNumber, String recipientName, Address address) {
        this.accountNumber = requireNonNull(accountNumber, "accountNumber");
        this.recipientName = requireNonEmpty(recipientName, "recipientName");
        this.address = requireNonNull(address, "address");
    }

    @ToString
    @EqualsAndHashCode
    @Embeddable
    public static class Address extends ValueObject {
        public static final Address NOT_SPECIFIED = Address.notSpecified();

        private String addressLine;
        private String city;
        private String zipCode;

        private Address() { // only for JPA
        }

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
