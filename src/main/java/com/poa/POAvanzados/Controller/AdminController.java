package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.DAO.AdminDAO;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.AdminService;

public class AdminController extends ManagerController implements AdminDAO {

    @Override
    public void createUser(User user) {
        AdminService.createUser(user);

    }

    @Override
    public void deleteUser(int idUser) {
        AdminService.deleteUser(idUser);

    }

    @Override
    public void updateUser(User user) {
        AdminService.updateUser(user);

    }

    @Override
    public ArrayList<Item_Detail> getAllCheckOut(String date) {
        return AdminService.getAllCheckOut(date);
    }

    @Override
    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return AdminService.getCheckOutWarehouse(idWarehouse, date);
    }

    @Override
    public ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return AdminService.getCheckOutItemWarehouse(idWarehouse, idItem, date);
    }

    public static ArrayList<User> getUsers() {
        return AdminService.getUsers();
    }

}
