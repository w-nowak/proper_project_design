package com.acme.moneytransfer.infrastructure.provider;

import com.acme.core.domain.model.money.Currency;
import com.acme.moneytransfer.domain.model.transfer.CurrencyExchangeRateProvider;
import org.springframework.stereotype.Component;

@Component
class CurrencyExchangeRateIdentityMockProvider implements CurrencyExchangeRateProvider {
	@Override
	public double getExchangeRateFor(Currency sourceCurrency, Currency targetCurrency) {
		return 1;
	}
}
