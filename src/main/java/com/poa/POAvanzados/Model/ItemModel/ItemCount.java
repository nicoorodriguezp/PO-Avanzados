package com.poa.POAvanzados.Model.ItemModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCount extends Item {

    private int discardedCount; //Recuento de descartados
    private int usedCount; // Recuento de usados

    public ItemCount(int idItem, String name, boolean critical, int usedCount, int discardedCount) {
        setIdItem(idItem);
        setName(name);
        setCritical(critical);
        this.usedCount = usedCount;
        this.discardedCount = discardedCount;
    }

}
