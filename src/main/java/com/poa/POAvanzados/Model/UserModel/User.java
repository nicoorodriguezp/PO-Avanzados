package com.poa.POAvanzados.Model.UserModel;

import com.poa.POAvanzados.Model.PositionModel.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private int idUser;

    private Position position;

    private Workplace workplace;

    private String name;
    private String lastName;

    private String email;
    private Boolean active;
    private String password;
    private int dni;

    @Override
    public String toString() {
        String fullname = name + " " + lastName;
        return fullname;

    }


}
