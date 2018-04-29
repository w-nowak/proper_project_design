package com.acme.core.domain.model;

import lombok.Getter;

import static com.acme.util.preconditions.Preconditions.requireNonNull;

@Getter
public abstract class Entity<T extends Id> {
    private final T id;

    public Entity(T id) {
        this.id = requireNonNull(id, "id");
    }
}
