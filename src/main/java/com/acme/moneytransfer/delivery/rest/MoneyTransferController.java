package com.acme.moneytransfer.delivery.rest;

import com.acme.moneytransfer.application.transfer.command.CancelMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.command.CreateMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.command.MoneyTransferCommand;
import com.acme.moneytransfer.application.transfer.command.CommitMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.query.MoneyTransferListUseCase;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.acme.util.time.ApplicationTime.dateNow;

@RestController
@RequestMapping("money-transfers")
class MoneyTransferController {
    private static final String ACCOUNT_ID = "CD-A-171017-15425452";

    @Autowired private CreateMoneyTransferUseCase createMoneyTransferUseCase;
    @Autowired private CommitMoneyTransferUseCase commitMoneyTransferUseCase;
    @Autowired private CancelMoneyTransferUseCase cancelMoneyTransferUseCase;
    @Autowired  private MoneyTransferListUseCase moneyTransferListUseCase;

    @GetMapping("/add-random")
    public String test() {
        MoneyTransferCommand moneyTransferCommand =
            MoneyTransferCommand.builder(ACCOUNT_ID, "465465464654", "My name", 100)
            .withDescription("some description of transfer")
            .withDate(dateNow())
            .withAddress("some street", "Wrocław", "53-212")
            .build();

        String newId = this.createMoneyTransferUseCase.createTransfer(moneyTransferCommand);

        return "success " + newId;
    }

    @PutMapping("/{moneyTransferId}/commit")
    public void commit(@PathVariable String moneyTransferId) {
        this.commitMoneyTransferUseCase.commitTransfer(moneyTransferId);
    }

    @PutMapping("/{moneyTransferId}/cancel")
    public void cancel(@PathVariable String moneyTransferId) {
        this.cancelMoneyTransferUseCase.cancelTransfer(moneyTransferId);
    }

    @GetMapping
    public Iterable<BriefMoneyTransferDataView> testget() {
        return this.moneyTransferListUseCase.getMoneyTransfersBy(ACCOUNT_ID);
    }

}
