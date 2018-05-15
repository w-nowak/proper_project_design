package com.acme.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

import java.io.Serializable;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;

@ToString
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class Id<T extends Comparable<T>> extends ValueObject implements Serializable {
    private T id;

    protected Id() { // only for JPA
    }

    public Id(T id) {
        this.id = requireNonNull(id, "id");
    }

    public T id() {
        return id;
    }
}