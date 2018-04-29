package com.acme.core.domain.model.account;

import com.acme.core.domain.model.DomainId;

/**
 *
 */
public class AccountId extends DomainId {
    private static final String ENTITY_SYMBOL = "A";

    private AccountId(String domainSymbol, String entitySymbol) {
        super(domainSymbol, entitySymbol);
    }

    private AccountId(String domainId) {
        super(domainId);
    }

    public static AccountId of(String domianId){
        requireDomainIdWithSymbols(domianId,"CD", ENTITY_SYMBOL);
        return new AccountId(domianId);
    }
}
