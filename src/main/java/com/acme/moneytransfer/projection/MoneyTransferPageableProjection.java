package com.acme.moneytransfer.projection;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MoneyTransferPageableProjection {
	Page<BriefMoneyTransferDataView> getBriefMoneyTransferPagedDataView(AccountId accountId, Pageable pageable);
}
