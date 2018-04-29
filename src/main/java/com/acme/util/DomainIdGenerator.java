package com.acme.util;

import java.time.LocalDate;
import java.util.UUID;

import static com.acme.util.preconditions.Preconditions.requireNonNull;
import static com.acme.util.preconditions.Preconditions.requireThat;
import static java.time.format.DateTimeFormatter.ofPattern;

public class DomainIdGenerator {
    public static final String FORMAT_MODEL = "XX-XX-XXXXXX-XXXXXXXX";
    private static final String SEGMENT_SEPARATOR = "-";
    private static final String ID_SYMBOL_PATTERN = "[a-zA-Z]{1,2}";
    private static final String ID_BASE_PATTERN = "\\d{6}-[\\w]{8}";
    private static final String ID_FORMAT_PATTERN = patternFrom(ID_SYMBOL_PATTERN, ID_SYMBOL_PATTERN, ID_BASE_PATTERN);

    public static String generateId(String domainSymbol, String entitySymbol) {
        requiresValidSymbol(domainSymbol, "domainSymbol");
        requiresValidSymbol(entitySymbol, "entitySymbol");

        String uuid = UUID.randomUUID().toString();

        return domainSymbol + SEGMENT_SEPARATOR +
                entitySymbol + SEGMENT_SEPARATOR +
                getSerializedCurrentDate() + SEGMENT_SEPARATOR +
                getFirstSegment(uuid);
    }

    public static boolean validateIdFormat(String domainId) {
        requireNonNull(domainId, "domainId");

        return domainId.matches(ID_FORMAT_PATTERN);
    }

    public static boolean validateIdFormatWithSymbols(String domainId, String domainSymbol, String entitySymbol) {
        requireNonNull(domainId, "domainId");
        requireNonNull(domainSymbol, "domainSymbol");
        requireNonNull(entitySymbol, "entitySymbol");

        return domainId.startsWith(concatenate(domainSymbol, entitySymbol)) &&
                domainId.matches(ID_FORMAT_PATTERN);
    }

    private static String patternFrom(String... segments) {
        return "^" + concatenate(segments) + "$";
    }

    private static String concatenate(String... segments) {
        return String.join(SEGMENT_SEPARATOR, segments);
    }

    private static void requiresValidSymbol(String symbol, String symbolName) {
        requireNonNull(symbol, symbolName);
        requireThat(symbol.matches(ID_SYMBOL_PATTERN), symbolName + " needs to match a pattern XX");
    }

    private static String getFirstSegment(String uuid) {
        return uuid.substring(uuid.indexOf(SEGMENT_SEPARATOR));
    }

    private static String getSerializedCurrentDate() {
        return LocalDate.now().format(ofPattern("yyMMdd"));
    }
}
