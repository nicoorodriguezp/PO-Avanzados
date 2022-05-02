package com.poa.POAvanzados.Model.PositionModel;

public class Manager implements Position {

    private final int position = 1;
    private final String name = "Manager";

    @Override
    public void showHome() {

    }

    @Override
    public int getPosition() {
        return position;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
