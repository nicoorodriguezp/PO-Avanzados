package com.poa.POAvanzados.model.user_model;

import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import lombok.AllArgsConstructor;
import lombok.Data;
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
