package com.acme.core.domain.model;

import com.acme.core.util.DomainIdGenerator;

import javax.persistence.MappedSuperclass;

import static com.acme.core.util.DomainIdGenerator.validateIdFormatWithSymbols;
import static com.acme.core.util.preconditions.Preconditions.requireNonNull;
import static com.acme.core.util.preconditions.Preconditions.requireThat;

@MappedSuperclass
public abstract class DomainId extends Id<String> {

    protected DomainId() { // only for JPA
    }

    protected DomainId(String domainSymbol, String entitySymbol) {
        super(DomainIdGenerator.generateId(domainSymbol, entitySymbol));
    }

    protected DomainId(String domainId) {
        super(requireCorrectDomainId(domainId));
    }

    protected static void requireDomainIdWithSymbols(String domainId, String domainSymbol, String entitySymbol) {
        requireThat(
                validateIdFormatWithSymbols(domainId, domainSymbol, entitySymbol),
                "domainId needs to to start with '" + domainSymbol + "-" + entitySymbol + "'"
        );
    }

    private static String requireCorrectDomainId(String domainId) {
        requireNonNull(domainId, "domainId");
        requireCorrectDomainIdFormat(domainId);

        return domainId;
    }

    private static void requireCorrectDomainIdFormat(String domainId) {
        requireThat(
                DomainIdGenerator.validateIdFormat(domainId),
                "domainId doesn't match required pattern " + DomainIdGenerator.FORMAT_MODEL
        );
    }
}