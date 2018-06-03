package com.acme.core.domain.model;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import static com.acme.core.util.preconditions.Preconditions.requireNonNull;

@Getter
@MappedSuperclass
public abstract class Entity<T extends Id, S> implements Equalable<S>{
    @EmbeddedId
    private T id;

    protected Entity() { // only for JPA
    }

    public Entity(T id) {
        this.id = requireNonNull(id, "id");
    }
}
