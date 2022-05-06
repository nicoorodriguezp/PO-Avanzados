package com.poa.POAvanzados.Model.UserModel;

import lombok.Data;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private int idUser;

    private Position position;

    private Workplace workplace;

    private String name;
    private String lastName;
    @Indexed(unique = true) // Unique email
    private String email;
    private Boolean active;
    private String password;

    public User(int idUser, Position position, Workplace workplace, String name, String lastName,
            String email, Boolean active, String password) {
        this.idUser = idUser;
        this.position = position;
        this.workplace = workplace;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.password = password;
    }

    @Override
    public String toString() {
        String fullname = name + " " + lastName;
        return fullname;

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
