package com.acme.core.domain.model;

import static java.lang.String.format;

public abstract class ValueObject implements Equalable<ValueObject> {

    protected ValueObject() { // only for JPA
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException(
                format("toString() method needs to be implemented in a concrete class '%s'", this.getClass().getName())
        );
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(
                format("hashCode() method needs to be implemented in a concrete class '%s'", this.getClass().getName())
        );
    }

    @Override
    public boolean equals(Object object) {
        throw new UnsupportedOperationException(
                format("equals() method needs to be implemented in a concrete class '%s'", this.getClass().getName())
        );
    }

    @Override
    public boolean isEqualTo(ValueObject valueObject) {
        return this.equals(valueObject);
    }
}
