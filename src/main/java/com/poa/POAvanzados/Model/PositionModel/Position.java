package com.poa.POAvanzados.Model.PositionModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private int idPosition;
    private String title;
    private String category;

    @Override
    public String toString() {
        return  title;
    }
}
