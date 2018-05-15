package com.acme.moneytransfer.application.transfer.command;

import com.acme.core.domain.model.money.Amount;
import com.acme.core.domain.model.money.Currency;
import com.acme.moneytransfer.domain.model.transfer.CurrencyExchangeRateProvider;
import org.springframework.stereotype.Service;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;

@Service
public class AmountCurrencyConversionService {
	private final CurrencyExchangeRateProvider currencyExchangeRateProvider;

	public AmountCurrencyConversionService(CurrencyExchangeRateProvider currencyExchangeRateProvider) {
		this.currencyExchangeRateProvider = requireNonNull(currencyExchangeRateProvider, "currencyExchangeRateProvider");
	}

	public Amount convertToAmountWithCurrency(Currency targetCurrency, Amount amountToConvert) {
		double exchangeRate = this.currencyExchangeRateProvider.getExchangeRateFor(amountToConvert.getCurrency(), targetCurrency);
		return amountToConvert.multiplyBy(exchangeRate);
	}
}
