package com.acme.core.domain.model.account;

import com.acme.core.domain.model.DomainId;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID")))
public class AccountId extends DomainId {
    private static String ENTITY_SYMBOL = "A";

    private AccountId() { // only for JPA
    }

    private AccountId(String domainSymbol, String entitySymbol) {
        super(domainSymbol, entitySymbol);
    }

    private AccountId(String domainId) {
        super(domainId);
    }

    public static AccountId of(String domianId) {
        requireDomainIdWithSymbols(domianId, "CD", ENTITY_SYMBOL);
        return new AccountId(domianId);
    }
}
