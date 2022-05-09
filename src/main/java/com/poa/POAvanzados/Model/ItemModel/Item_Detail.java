package com.poa.POAvanzados.Model.ItemModel;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import lombok.Data;

@Data
public class Item_Detail extends Item {

    // Traceability
    private int idItemCode;
    private Workplace warehouse;
    private Workplace laboratory;
    private String checkIn; // Date of entry into Warehouse
    private String checkOut; // Date of entry into Laboratory
    private int state; // 0:Stock - 1:In Use - 2:Used - 3:Discarded

    public int getIdItemCode() {
        return idItemCode;
    }

    public void setIdItemCode(int idItemCode) {
        this.idItemCode = idItemCode;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Workplace getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Workplace warehouse) {
        this.warehouse = warehouse;
    }

    public Workplace getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Workplace laboratory) {
        this.laboratory = laboratory;
    }
}
