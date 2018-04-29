package com.acme.core.domain.model.money;

import java.math.BigDecimal;

import static com.acme.core.domain.model.money.Currency.ANY;
import static com.acme.util.preconditions.Preconditions.requireNonNull;

/**
 *
 */
public class Amount {
    private static final Amount ZERO = new Amount(0, ANY);

    private final BigDecimal amount;
    private final Currency currency;

    public Amount(double amount, Currency currency) {
        this.amount = new BigDecimal(amount);
        this.currency = requireNonNull(currency, "currency");
    }
}
