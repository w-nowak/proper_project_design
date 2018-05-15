package com.acme.core.domain.model.money;

import com.acme.core.domain.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

import static com.acme.core.domain.model.money.Currency.ANY;
import static com.acme.core.domain.model.money.Currency.PLN;
import static com.acme.core.util.preconditions.Preconditions.requireNonNull;
import static com.acme.core.util.preconditions.Preconditions.requireThat;
import static java.math.BigDecimal.valueOf;
import static javax.persistence.EnumType.STRING;

@Embeddable
@ToString
@EqualsAndHashCode(callSuper = false)
public class Amount extends ValueObject {
    private static final Amount ZERO = new Amount(0, ANY);

    private BigDecimal amount;
    @Getter
    @Enumerated(STRING)
    private Currency currency;

    private Amount() { // only for JPA
    }

    public Amount(double amount, Currency currency) {
        this.amount = new BigDecimal(amount);
        this.currency = requireNonNull(currency, "currency");
    }

    public double doubleValue() {
        return this.amount.doubleValue();
    }

    public boolean isGreaterThan(Amount amount) {
    	requireSameCurrency(amount);
    	return this.amount.compareTo(amount.amount) > 0;
	}

	public boolean isGreateThanOrEqualTo(Amount amount) {
		requireSameCurrency(amount);
    	return isGreaterThan(amount) || equals(amount);
	}

	public Amount multiplyBy(double multiplier) {
    	return new Amount(this.amount.multiply(valueOf(multiplier)).doubleValue(), this.currency);
	}

	public boolean isZero() {
    	return this.amount.equals(ZERO.amount);
	}

	private void requireSameCurrency(Amount amount) {
    	requireThat(
    		this.isZero() && amount.isZero() || this.currency == amount.currency,
			"Amounts needs to be of the same currency"
		);
	}

	//TODO other amount modification methods

    public static Amount pln(double amount) {
        return new Amount(amount, PLN);
    }
}
