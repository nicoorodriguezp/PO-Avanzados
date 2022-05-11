package com.poa.POAvanzados.Exception;

public class NoWarehouseWithEnoughStock extends Exception {
    public NoWarehouseWithEnoughStock() {
    }

    public NoWarehouseWithEnoughStock(String message) {
        super(message);
    }
}
