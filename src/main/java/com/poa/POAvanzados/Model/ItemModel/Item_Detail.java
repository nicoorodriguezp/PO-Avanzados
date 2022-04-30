package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Item_Detail extends Item {

    // Traceability
    @Id
    private int idItemCode;
    private int idWarehouse;
    private int idLaboratory;
    private String checkIn; // Date of entry into Warehouse
    private String checkOut; // Date of entry into Laboratory
    private int state; // 0:Stock - 1:In Use - 2:Used - 3:Discarded

    public int getIdItemCode() {
        return idItemCode;
    }

    public void setIdItemCode(int idItemCode) {
        this.idItemCode = idItemCode;
    }

    public int getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(int idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public int getIdLaboratory() {
        return idLaboratory;
    }

    public void setIdLaboratory(int idLaboratory) {
        this.idLaboratory = idLaboratory;
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

}
