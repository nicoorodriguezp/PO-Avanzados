package com.poa.POAvanzados.exception;

public class QuantityExceedsMaxSlots extends Exception {
    public QuantityExceedsMaxSlots() {
    }

    public QuantityExceedsMaxSlots(String message) {
        super(message);
    }
}
