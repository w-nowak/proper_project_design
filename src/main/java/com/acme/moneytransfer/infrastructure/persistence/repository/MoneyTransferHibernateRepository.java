package com.acme.moneytransfer.infrastructure.persistence.repository;

import com.acme.moneytransfer.domain.model.transfer.MoneyTransfer;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MoneyTransferHibernateRepository implements MoneyTransferRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MoneyTransferId nextIdentifier() {
		return MoneyTransferId.newId();
	}

	@Override
	public MoneyTransfer getBy(MoneyTransferId moneyTransferId) {
		return this.entityManager.find(MoneyTransfer.class, moneyTransferId);
	}

	@Override
	public void save(MoneyTransfer moneyTransfer) {
		this.entityManager.persist(moneyTransfer);
	}
}
