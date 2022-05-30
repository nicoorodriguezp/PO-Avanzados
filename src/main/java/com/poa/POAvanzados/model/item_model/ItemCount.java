package com.poa.POAvanzados.model.item_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCount {
    private Integer idItem;
    private String name;
    private boolean critical;
    private int usedCount;
    private int discardedCount;

    @Override
    public boolean equals(Object o) {
        ItemCount itemCount = (ItemCount) o;
        return critical == itemCount.critical && usedCount == itemCount.usedCount && discardedCount == itemCount.discardedCount && Objects.equals(idItem, itemCount.idItem) && Objects.equals(name, itemCount.name);
    }

}
