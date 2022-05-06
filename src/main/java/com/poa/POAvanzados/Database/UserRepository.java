package com.poa.POAvanzados.Database;

import java.util.ArrayList;

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
                "nicogrodriguezp@gmail.com",
                true,
                "0110703");

        return u;
    }

    public static void createUser(User user) {
    }

    public static void deleteUser(int idUser) {
    }

    public static void updateUser(User user) {
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        User u = new User(
                41476258,
                new Admin(),
                new Workplace(0, true, 0, "Mario Bravo 1050"),
                "Nicolas Gaston",
                "Rodriguez Perez",
                "nicogrodriguezp@gmail.com",
                true,
                "0110703");
        users.add(u);

        u = new User(
                42384929,
                new Admin(),
                new Workplace(0, true, 0, "Mario Bravo 1050"),
                "Matias Ezequiel",
                "Romero",
                "matias301020000@gmail.com",
                true,
                "1234567");

        users.add(u);

        return users;
    }

}
