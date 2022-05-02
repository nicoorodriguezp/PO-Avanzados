package com.poa.POAvanzados.Model.PositionModel;

public class Admin implements Position {

    private final int position = 0;
    private final String name = "Admin";

    @Override
    public void showHome() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
