package com.poa.POAvanzados.model.item_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Workplace_Item {
    int idWorkplace;
    int idItem;
    int max_slots;
    int stock;
}
