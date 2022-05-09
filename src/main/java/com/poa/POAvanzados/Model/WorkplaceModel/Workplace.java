package com.poa.POAvanzados.Model.WorkplaceModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workplace {

    private int idWorkplace;
    private boolean warehouse;
    private int idManager;
    private String address;


    @Override
    public String toString() {
        if (warehouse) {
            return "Deposito: " + this.address;
        } else {
            return "Laboratorio: " + this.address;
        }
    }

}
