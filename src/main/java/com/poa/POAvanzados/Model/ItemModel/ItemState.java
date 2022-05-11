package com.poa.POAvanzados.Model.ItemModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemState {

    private int idState;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
