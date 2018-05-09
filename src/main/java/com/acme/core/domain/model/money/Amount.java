package com.acme.core.domain.model.money;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

import static com.acme.core.domain.model.money.Currency.ANY;
import static com.acme.core.domain.model.money.Currency.PLN;
import static com.acme.util.preconditions.Preconditions.requireNonNull;
import static javax.persistence.EnumType.STRING;

@Embeddable
public class Amount {
    private static final Amount ZERO = new Amount(0, ANY);

    private BigDecimal amount;
    @Enumerated(STRING)
    private Currency currency;

    private Amount() { // only for JPA
    }

    public Amount(double amount, Currency currency) {
        this.amount = new BigDecimal(amount);
        this.currency = requireNonNull(currency, "currency");
    }

    public static Amount pln(double amount) {
        return new Amount(amount, PLN);
    }

    //TODO other VO modification methods
}
