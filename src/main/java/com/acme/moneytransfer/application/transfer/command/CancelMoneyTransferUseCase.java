package com.acme.moneytransfer.application.transfer.command;

import com.acme.moneytransfer.domain.model.transfer.MoneyTransfer;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferId;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransferRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

@Component
@Transactional(REQUIRES_NEW)
public class CancelMoneyTransferUseCase {
	private MoneyTransferRepository moneyTransferRepository;

	public CancelMoneyTransferUseCase(MoneyTransferRepository moneyTransferRepository) {
		this.moneyTransferRepository = requireNonNull(moneyTransferRepository, "moneyTransferRepository");
	}

	public void cancelTransfer(String moneyTransferId) {
		MoneyTransferId transferId = MoneyTransferId.of(moneyTransferId);

		MoneyTransfer moneyTransfer = this.moneyTransferRepository.getBy(transferId);
		moneyTransfer.cancel();
	}
}
