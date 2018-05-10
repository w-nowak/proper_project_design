package com.acme.moneytransfer.domain.model.transfer;

/**
 *
 */
public interface MoneyTransferRepository {
    MoneyTransferId nextIdentifier();
    MoneyTransfer getBy(MoneyTransferId moneyTransferId);
    void save(MoneyTransfer moneyTransfer);
}
