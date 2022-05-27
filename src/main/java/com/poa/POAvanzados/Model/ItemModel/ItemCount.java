package com.poa.POAvanzados.Model.ItemModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
