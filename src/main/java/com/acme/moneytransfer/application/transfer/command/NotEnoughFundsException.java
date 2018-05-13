package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.money.Amount;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class NotEnoughFundsException extends RuntimeException {
    private final double requiredFunds;
    private final double availableFunds;

    NotEnoughFundsException(Amount requiredFunds, Amount availableFunds) {
        super(
            format(
                    "Not enough funds to successfully execute a request. Required funds %.2f, available funds: %.2f",
                    requiredFunds.doubleValue(), availableFunds.doubleValue()
            )
        );

        this.requiredFunds = requiredFunds.doubleValue();
        this.availableFunds = availableFunds.doubleValue();
    }
}
