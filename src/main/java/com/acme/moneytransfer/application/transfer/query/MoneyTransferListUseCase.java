package com.acme.moneytransfer.application.transfer.query;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.projection.MoneyTransferPageableProjection;
import com.acme.moneytransfer.projection.MoneyTransferProjection;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;

@Component
public class MoneyTransferListUseCase {
	private final MoneyTransferProjection moneyTransferProjection;
	private final MoneyTransferPageableProjection moneyTransferPagableProjection;

	public MoneyTransferListUseCase(MoneyTransferProjection moneyTransferProjection,
									MoneyTransferPageableProjection moneyTransferPagableProjection) {
		this.moneyTransferProjection = requireNonNull(moneyTransferProjection, "moneyTransferProjection");
		this.moneyTransferPagableProjection = requireNonNull(moneyTransferPagableProjection, "moneyTransferPagableProjection");
	}

	public Iterable<BriefMoneyTransferDataView> getMoneyTransfersBy(String accountId) {
		return this.moneyTransferProjection.getBriefMoneyTransferDataView(AccountId.of(accountId));
	}

	public Page<BriefMoneyTransferDataView> getMoneyTransfersBy(String accountId, Pageable pageable) {
		return this.moneyTransferPagableProjection.getBriefMoneyTransferPagedDataView(AccountId.of(accountId), pageable);
	}
}
