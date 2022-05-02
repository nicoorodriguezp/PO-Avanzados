package com.poa.POAvanzados.Model.PositionModel;

public class Worker implements Position {

    private final int position = 2;
    private final String name = "Worker";

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
