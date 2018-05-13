package com.acme.moneytransfer.delivery.rest;

import com.acme.moneytransfer.application.transfer.command.CancelMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.command.CreateMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.command.MoneyTransferCommand;
import com.acme.moneytransfer.application.transfer.command.RealizeMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.query.MoneyTransferListUseCase;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("money-transfers")
class MoneyTransferController {
    private static final String ACCOUNT_ID = "CD-A-171017-15425452";

    @Autowired private CreateMoneyTransferUseCase createMoneyTransferUseCase;
    @Autowired private RealizeMoneyTransferUseCase realizeMoneyTransferUseCase;
    @Autowired private CancelMoneyTransferUseCase cancelMoneyTransferUseCase;
    @Autowired  private MoneyTransferListUseCase moneyTransferListUseCase;

    @GetMapping("/add-random")
    public String test() {
        MoneyTransferCommand moneyTransferCommand =
            MoneyTransferCommand.builder(ACCOUNT_ID, "465465464654", "My name", 100)
            .withDescription("some description of transfer")
            .withDate(LocalDate.now())
            .withAddress("some street", "Wrocław", "53-212")
            .build();

        String newId = this.createMoneyTransferUseCase.createTransfer(moneyTransferCommand);

        return "success " + newId;
    }

    @PutMapping("/{moneyTransferId}/realize")
    public void realize(@PathVariable String moneyTransferId) {
        this.realizeMoneyTransferUseCase.realizeTransfer(moneyTransferId);
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
