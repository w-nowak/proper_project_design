package com.acme.moneytransfer.domain.model.basket;

import static com.acme.util.preconditions.Preconditions.requireNonEmpty;

public class OrderBasket {
    private final String name;

    public OrderBasket(String name) {
        this.name = requireNonEmpty(name, "name");
    }

    //public static
}
