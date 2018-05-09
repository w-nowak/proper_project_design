package com.acme.moneytransfer.infrastructure;

import com.acme.moneytransfer.domain.model.transfer.MoneyTransfer;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import org.springframework.data.repository.CrudRepository;

public interface HibernateRepository extends CrudRepository<MoneyTransfer, MoneyTransferId> {
}
