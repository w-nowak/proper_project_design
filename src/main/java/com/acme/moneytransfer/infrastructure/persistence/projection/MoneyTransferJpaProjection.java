package com.acme.moneytransfer.infrastructure.persistence.projection;

import com.acme.core.domain.model.account.AccountId;
import com.acme.moneytransfer.projection.MoneyTransferProjection;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@ConditionalOnMissingBean(MoneyTransferProjection.class)
public class MoneyTransferJpaProjection implements MoneyTransferProjection  {
    @PersistenceContext
	private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	public Iterable<BriefMoneyTransferDataView> getBriefMoneyTransferDataView(AccountId accountId) {
		return entityManager.createQuery(
				"SELECT new com.acme.moneytransfer.infrastructure.persistence.projection.BriefMoneyTransferDataViewImpl (" +
						"mt.id, mt.targetAccountDetails.recipientName, mt.amount, mt.transferDate) " +
						"FROM MoneyTransfer mt where mt.accountId = :accountId"
				)
				.setParameter("accountId", accountId)
				.getResultList();
	}
}
