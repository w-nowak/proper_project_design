package com.acme.moneytransfer.infrastructure.persistence.projection;

import com.acme.core.domain.model.money.Amount;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import lombok.Value;

import java.time.LocalDate;

@Value
class BriefMoneyTransferDataViewImpl implements BriefMoneyTransferDataView {
	private String transferId;
	private String recipient;
	private double amount;
	private LocalDate date;

	public BriefMoneyTransferDataViewImpl(MoneyTransferId moneyTransferId, String recipient, Amount amount, LocalDate date) {
		this.transferId = moneyTransferId.id();
		this.recipient = recipient;
		this.amount = amount.doubleValue();
		this.date = date;
	}
}
