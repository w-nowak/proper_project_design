package com.acme.core.domain.model;

public interface Equalable<T> {
	boolean isEqualTo(T object);
}
