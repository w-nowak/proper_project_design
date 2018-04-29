package com.acme.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.acme.util.preconditions.Preconditions.requireNonNull;

@ToString
@EqualsAndHashCode
public abstract class Id<T extends Comparable<T>> extends ValueObject{
    private final T id;

    public Id(T id) {
        this.id = requireNonNull(id, "id");
    }

    public T id() {
        return id;
    }
}