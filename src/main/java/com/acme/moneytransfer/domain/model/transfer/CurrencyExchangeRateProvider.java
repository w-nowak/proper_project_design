package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.money.Currency;

public interface CurrencyExchangeRateProvider {
	double getExchangeRateFor(Currency sourceCurrency, Currency targetCurrency);
}
