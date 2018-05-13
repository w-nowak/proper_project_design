package com.acme.moneytransfer.delivery.rest;

import com.acme.moneytransfer.application.transfer.command.CreateMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.command.MoneyTransferCommand;
import com.acme.moneytransfer.application.transfer.query.MoneyTransferListUseCase;
import com.acme.moneytransfer.projection.view.BriefMoneyTransferDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("money-transfers")
public class MoneyTransferController {
    private static final String ACCOUNT_ID = "CD-A-171017-15425452";
    @Autowired
    private CreateMoneyTransferUseCase createMoneyTransferUseCase;
    @Autowired
    private MoneyTransferListUseCase moneyTransferListUseCase;

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

    @GetMapping
    public Iterable<BriefMoneyTransferDataView> testget() {
        return this.moneyTransferListUseCase.getMoneyTransfersBy(ACCOUNT_ID);
    }

}
