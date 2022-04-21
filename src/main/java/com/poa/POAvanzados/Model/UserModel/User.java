package com.poa.POAvanzados.Model.UserModel;

import Model.PositionModel.PositionInterface;
import Model.WorkplaceModel.Workplace;
import lombok.Data;

@Data
public class User {

    private int idUser;
    private PositionInterface position;
    private Workplace workplace;

    private String name;
    private String lastName;
    private String email;


}
