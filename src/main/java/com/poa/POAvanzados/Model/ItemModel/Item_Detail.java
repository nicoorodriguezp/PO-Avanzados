package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@Document
public class Item_Detail extends Item {

    // Traceability
    private int idItemCode;
    private int idWarehouse;
    private int idLaboratory;
    private Date checkIn; // Date of entry into Warehouse
    private Date checkOut; // Date of entry into Laboratory
    private ItemState state; // 0: Stock 1: In Use 3: Used 4:Discarded

}
