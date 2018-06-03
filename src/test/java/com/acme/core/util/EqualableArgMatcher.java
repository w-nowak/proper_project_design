package com.acme.core.util;

import com.acme.core.domain.model.Equalable;
import org.mockito.ArgumentMatcher;

import static org.mockito.ArgumentMatchers.argThat;


public class EqualableArgMatcher {
	public static <T extends Equalable<T>> T argIsEqualTo(final T referenceObject) {
		return argThat(
			new ArgumentMatcher<T>() {
				@Override
				public boolean matches(T object) {
					return object.isEqualTo(referenceObject);
				}
			}
		);
	}
}
