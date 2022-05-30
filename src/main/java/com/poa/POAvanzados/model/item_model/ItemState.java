package com.poa.POAvanzados.model.item_model;

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
