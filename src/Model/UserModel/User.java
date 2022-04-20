package Model.UserModel;

import Model.PositionModel.PositionInterface;
import Model.WorkplaceModel.Workplace;

public class User {

    private int idUser;
    private PositionInterface position;
    private Workplace workplace;

    private String name;
    private String lastName;
    private String email;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public PositionInterface getPosition() {
        return position;
    }

    public void setPosition(PositionInterface position) {
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

}
