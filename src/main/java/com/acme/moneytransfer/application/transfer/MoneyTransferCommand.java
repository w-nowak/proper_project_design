package com.acme.moneytransfer.application.transfer;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

import static com.acme.util.preconditions.Preconditions.requireNonEmpty;
import static com.acme.util.preconditions.Preconditions.requireNonNull;
import static java.util.Optional.ofNullable;

@Getter
public class MoneyTransferCommand {
    private final String accountId;
    private final String targetAccountNmber;
    private final String recipientName;
    private final Optional<Address> address;
    private final double amount;
    private final String description;
    private final LocalDate date;

    private MoneyTransferCommand(MoneyTransferCommandBuilder builder) {
        this.accountId = builder.accountId;
        this.targetAccountNmber = builder.targetAccountNmber;
        this.recipientName = builder.recipientName;
        this.address = ofNullable(builder.address);
        this.amount = builder.amount;
        this.description = builder.description;
        this.date = builder.date;
    }

    public static MoneyTransferCommandBuilder builder(String accountId, String targetAccountNmber,
                                                      String recipientName, double amount) {
        return new MoneyTransferCommandBuilder(accountId, targetAccountNmber, recipientName, amount);
    }

    @Getter
    public static class Address {
        private final String street;
        private final String city;
        private final String zipCode;

        Address(String street, String city, String zipCode) {
            this.street = street;
            this.city = city;
            this.zipCode = zipCode;
        }
    }

    public static class MoneyTransferCommandBuilder {
        private final String accountId;
        private final String targetAccountNmber;
        private final String recipientName;
        private final double amount;
        private Address address;
        private String description;
        private LocalDate date;

        private MoneyTransferCommandBuilder(String accountId, String targetAccountNmber, String recipientName, double amount) {
            this.accountId = accountId;
            this.targetAccountNmber = targetAccountNmber;
            this.recipientName = recipientName;
            this.amount = amount;
            this.description = "";
            this.date = LocalDate.now();
        }

        public MoneyTransferCommandBuilder withAddress(String street, String city, String zipCode) {
            requireNonEmpty(street, "street");
            requireNonEmpty(city, "city");
            requireNonEmpty(zipCode, "zipCode");

            this.address = new Address(street, city, zipCode);
            return this;
        }

        public MoneyTransferCommandBuilder withDescription(String description) {
            this.description = requireNonNull(description, "description");
            return this;
        }

        public MoneyTransferCommandBuilder withDate(LocalDate date) {
            this.date = requireNonNull(date, "date");
            return this;
        }

        public MoneyTransferCommand build() {
            return new MoneyTransferCommand(this);
        }
    }
}
