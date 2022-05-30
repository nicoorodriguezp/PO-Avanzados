package com.poa.POAvanzados.model.item_model;

import com.poa.POAvanzados.model.workplace_model.Workplace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item_Detail {

    // Traceability
    private int idItemCode;
    private Item item;
    private Workplace warehouse;
    private Workplace laboratory;
    private String checkIn; // Date of entry into Warehouse
    private String checkOut; // Date of entry into Laboratory
    private ItemState state;

    @Override
    public String toString() {
        return  item.getName();
    }
}
