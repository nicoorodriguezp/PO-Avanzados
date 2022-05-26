package com.poa.POAvanzados.Model.ItemModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item_Detail_Inventory {

    private int idItemCode;
    private Item item;
    private String checkIn; // Date of entry into Warehouse
    private String checkOut; // Date of entry into Laboratory
    private ItemState state;
}
