package com.acme.core.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DomainIdTests {
    private static final String DOMAIN_SYMBOL = "DS";
    private static final String ENTITY_SYMBOL = "E";
    private static final String DOMAIN_ID = "DS-E-182004-XUS25IE9";

    @Test
    void createsDomainIdFromDomainAndEntitySymbols() {
        DomainId domainId = new TestDomainId(DOMAIN_SYMBOL, ENTITY_SYMBOL);

        assertThat(domainId).isNotNull();
        assertThat(domainId.id()).startsWith(DOMAIN_SYMBOL + "-" + ENTITY_SYMBOL + "-");
    }

    @Test
    void throwsIllegalArgumentExceptionWhenDomainSymbolHasIncorrectFormat() {
        String invalidDomainSymbol = "23";

        Throwable caughtException = catchThrowable(() -> new TestDomainId(invalidDomainSymbol, ENTITY_SYMBOL));
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwsIllegalArgumentExceptionWhenEntitySymbolHasIncorrectFormat() {
        String invalidEntitySymbol = "XXX";

        Throwable caughtException = catchThrowable(() -> new TestDomainId(DOMAIN_SYMBOL, invalidEntitySymbol));
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createsDomainIdFromStringRepresentation() {
        DomainId domainId = new TestDomainId(DOMAIN_ID);

        assertThat(domainId).isNotNull();
        assertThat(domainId.id()).isEqualTo(DOMAIN_ID);
    }

    @Test
    void throwsIllegalArgumentExceptionWhenDomainIdHasIncorrectFormat() {
        String invalidDomainId = "DS-E-182X04-XUS25IE9";

        Throwable caughtException = catchThrowable(() -> new TestDomainId(invalidDomainId));
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwsNoExceptionWhenDomainIdContainsCorrectSymbols() {
        String domainId = "PP-Y-123456-XUS25IE9";
        String domainSymbol = "PP";
        String entitySymbol = "Y";

        DomainId.requireDomainIdWithSymbols(domainId, domainSymbol, entitySymbol);
    }

    @Test
    void throwsIllegalArgumentExceptionWhenDomainIdDoesntContainCorrectSymbols() {
        String domainId = "PP-Y-123456-XUS25IE9";
        String domainSymbol = "PR";
        String entitySymbol = "Y";

        Throwable caughtException = catchThrowable(() -> DomainId.requireDomainIdWithSymbols(domainId, domainSymbol, entitySymbol));
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
//        assertThrows(
//                IllegalArgumentException.class,
//                () -> DomainId.requireDomainIdWithSymbols(domainId, domainSymbol, entitySymbol)
//        );
    }

    class TestDomainId extends DomainId {
        TestDomainId(String domainSymbol, String entitySymbol) {
            super(domainSymbol, entitySymbol);
        }

        public TestDomainId(String domainId) {
            super(domainId);
        }
    }
}