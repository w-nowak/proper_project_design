package com.acme.moneytransfer.domain.model.transfer;

import com.acme.core.domain.model.DomainId;

import javax.persistence.Embeddable;

import static com.acme.moneytransfer.domain.model.MoneyTransferDomain.DOMAIN_SYMBOL;

@Embeddable
public class MoneyTransferId extends DomainId {
    private static final String ENTITY_SYMBOL = "T";

    private MoneyTransferId() { // only for JPA
    }

    private MoneyTransferId(String domainSymbol, String entitySymbol) {
        super(domainSymbol, entitySymbol);
    }

    private MoneyTransferId(String domainId) {
        super(domainId);
    }

    public static MoneyTransferId of(String domainId) {
        requireDomainIdWithSymbols(domainId, DOMAIN_SYMBOL, ENTITY_SYMBOL);
        return new MoneyTransferId(domainId);
    }

    public static MoneyTransferId newId() {
        return new MoneyTransferId(DOMAIN_SYMBOL, ENTITY_SYMBOL);
    }
}
