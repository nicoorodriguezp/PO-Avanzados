package com.poa.POAvanzados.Exception;

public class QuantityExceedsMaxSlots extends Exception {
    public QuantityExceedsMaxSlots() {
    }

    public QuantityExceedsMaxSlots(String message) {
        super(message);
    }
}
