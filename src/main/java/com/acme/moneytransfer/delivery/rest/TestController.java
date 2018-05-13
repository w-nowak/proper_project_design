package com.acme.moneytransfer.delivery.rest;

import com.acme.moneytransfer.application.transfer.CreateMoneyTransferUseCase;
import com.acme.moneytransfer.application.transfer.MoneyTransferCommand;
import com.acme.moneytransfer.domain.model.transfer.MoneyTransfer;
import com.acme.moneytransfer.infrastructure.HibernateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TestController {
    @Autowired
    private CreateMoneyTransferUseCase createMoneyTransferUseCase;

    @GetMapping("/test")
    public String test() {
        MoneyTransferCommand moneyTransferCommand =
            MoneyTransferCommand.builder("CD-A-171017-15425452", "465465464654", "My name", 100)
            .withDescription("some description of transfer")
            .withDate(LocalDate.now())
            .withAddress("some street", "Wrocław", "53-212")
            .build();

        String newId = this.createMoneyTransferUseCase.createTransfer(moneyTransferCommand);

        return "success " + newId;
    }
}
