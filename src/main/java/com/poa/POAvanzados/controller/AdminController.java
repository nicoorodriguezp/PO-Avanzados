package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class AdminController extends ManagerController {

    @Autowired
    private AdminService adminService= new AdminService();

    public void createUser(User user) {
        adminService.createUser(user);

    }

    public void updateUser(User user) {
        adminService.updateUser(user);

    }

    public ArrayList<Item_Detail> getAllCheckOut(String date) {
        return adminService.getAllCheckOut(date);
    }


    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return adminService.getCheckOutWarehouse(idWarehouse, date);
    }

    public ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return adminService.getCheckOutItemWarehouse(idWarehouse, idItem, date);
    }

    public ArrayList<User> getUsers() {
        return adminService.getUsers();
    }

    public void updateItem(Item item) {
        adminService.updateItem(item);

    }
    public void addItem(Item item){
        adminService.addItem(item);
    }
}
