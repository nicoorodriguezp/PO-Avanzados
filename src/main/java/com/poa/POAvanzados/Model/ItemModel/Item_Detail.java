package com.poa.POAvanzados.Model.ItemModel;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item_Detail extends Item {

    // Traceability
    private int idItemCode;
    private Workplace warehouse;
    private Workplace laboratory;
    private String checkIn; // Date of entry into Warehouse
    private String checkOut; // Date of entry into Laboratory
    private ItemState state; // 0:Stock - 1:In Use - 2:Used - 3:Discarded


}
