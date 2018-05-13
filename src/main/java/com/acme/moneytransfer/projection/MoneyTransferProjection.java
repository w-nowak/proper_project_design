package com.acme.moneytransfer.projection;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;

public interface MoneyTransferProjection  {
    Iterable<BriefMoneyTransferDataView> getBriefMoneyTransferDataView(AccountId accountId);
}
