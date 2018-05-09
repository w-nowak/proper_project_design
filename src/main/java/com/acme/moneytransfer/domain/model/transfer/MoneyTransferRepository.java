package com.acme.moneytransfer.domain.model.transfer;

/**
 *
 */
public interface MoneyTransferRepository {
    MoneyTransferId nextIdentifier();
    MoneyTransfer getMoneyTransfer(MoneyTransferId moneyTransferId);
    void save(MoneyTransfer moneyTransfer);
}
