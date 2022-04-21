package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;

import java.sql.Date;

@Data
public class Item {

    // Traceability
    private int idItem;
    private int idWarehouse;
    private int idLaboratory;
    private Date checkIn; // Date of entry into Warehouse
    private Date checkOut; // Date of entry into Laboratory

    // Product Atributes
    private String name;
    private boolean critical;
    private int state; // 0: Stock 1: En uso 3: Usado 4:Descartado



}
