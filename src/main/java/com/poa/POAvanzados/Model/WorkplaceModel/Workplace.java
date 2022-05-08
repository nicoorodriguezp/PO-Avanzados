package com.poa.POAvanzados.Model.WorkplaceModel;

import lombok.Data;

@Data
public class Workplace {

    private int idWorkplace;
    private boolean warehouse;
    private int idManager;
    private String address;

    public Workplace(int idWorkplace, boolean warehouse, int idManager, String address) {
        this.idWorkplace = idWorkplace;
        this.warehouse = warehouse;
        this.idManager = idManager;
        this.address = address;
    }

    public int getIdWorkplace() {
        return idWorkplace;
    }

    public void setIdWorkplace(int idWorkplace) {
        this.idWorkplace = idWorkplace;
    }

    public boolean isWarehouse() {
        return warehouse;
    }

    public void setWarehouse(boolean warehouse) {
        this.warehouse = warehouse;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        if (warehouse) {
            return "Deposito: " + this.address;
        } else {
            return "Laboratorio: " + this.address;
        }
    }

}
