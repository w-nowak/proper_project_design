package com.acme.moneytransfer.infrastructure.persistence.projection;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransfer;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import com.acme.moneytransfer.projection.MoneyTransferPageableProjection;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

@org.springframework.stereotype.Repository
public interface MoneyTransferPageableJpaProjection extends MoneyTransferPageableProjection, Repository<MoneyTransfer, MoneyTransferId> {
	@Override
	@Query("SELECT new com.acme.moneytransfer.infrastructure.persistence.projection.BriefMoneyTransferDataViewImpl (" +
			"mt.id, mt.targetAccountDetails.recipientName, mt.amount, mt.transferDate) " +
			"FROM MoneyTransfer mt where mt.accountId = :accountId")
	public Page<BriefMoneyTransferDataView> getBriefMoneyTransferPagedDataView(@Param("accountId") AccountId accountId, Pageable pagable);
}
