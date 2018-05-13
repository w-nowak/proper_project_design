package com.acme.moneytransfer.application.transfer.query;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.projection.MoneyTransferProjection;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.stereotype.Component;


import static com.acme.util.preconditions.Preconditions.requireNonNull;

@Component
public class MoneyTransferListUseCase {
	private final MoneyTransferProjection moneyTransferProjection;

	public MoneyTransferListUseCase(MoneyTransferProjection moneyTransferProjection) {
		this.moneyTransferProjection = requireNonNull(moneyTransferProjection, "moneyTransferProjection");
	}

	public Iterable<BriefMoneyTransferDataView> getMoneyTransfersBy(String accountId) {
		return this.moneyTransferProjection.getBriefMoneyTransferDataView(AccountId.of(accountId));
	}
}
