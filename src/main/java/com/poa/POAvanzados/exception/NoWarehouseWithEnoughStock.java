package com.poa.POAvanzados.exception;

public class NoWarehouseWithEnoughStock extends Exception {
    public NoWarehouseWithEnoughStock() {
    }

    public NoWarehouseWithEnoughStock(String message) {
        super(message);
    }
}
