package com.poa.POAvanzados.Database;

import com.poa.POAvanzados.Model.PositionModel.Admin;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class UserRepository {

    public static User getUser() {

        User u = new User(
                41476258,
                new Admin(),
                new Workplace(0, true, 0, "Mario Bravo 1050"),
                "Nicolas",
                "Rodriguez",
                "nicogrodriguezp@gmail.com");

        return u;
    }

    public static void createUser(User user) {
    }

    public static void deleteUser(int idUser) {
    }

    public static void updateUser(User user) {
    }

}
