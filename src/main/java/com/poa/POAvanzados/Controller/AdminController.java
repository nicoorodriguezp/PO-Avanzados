package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.DAO.AdminDAO;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController extends ManagerController {

//TODO Tratar de solucionar el autowired del service
    @Autowired
    private AdminService adminService= new AdminService();

    public void createUser(User user) {
        AdminService.createUser(user);

    }

    public void deleteUser(int idUser) {
        AdminService.deleteUser(idUser);

    }

    public void updateUser(User user) {
        AdminService.updateUser(user);

    }

    public ArrayList<Item_Detail> getAllCheckOut(String date) {
        return AdminService.getAllCheckOut(date);
    }


    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return AdminService.getCheckOutWarehouse(idWarehouse, date);
    }

    public ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return AdminService.getCheckOutItemWarehouse(idWarehouse, idItem, date);
    }

    public ArrayList<User> getUsers() {
        return adminService.getUsers();
    }


}
