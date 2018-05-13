package com.acme.moneytransfer.projection.view;

import java.time.LocalDate;

public interface BriefMoneyTransferDataView {
    String getTransferId();
    String getRecipient();
    double getAmount();
    LocalDate getDate();
}
