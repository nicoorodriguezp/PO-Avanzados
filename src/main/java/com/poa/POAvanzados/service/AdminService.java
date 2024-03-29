package com.poa.POAvanzados.service;

import com.poa.POAvanzados.dao.admin.AdminDAOImpl;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.user_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService extends ManagerService {

    @Autowired
    private AdminDAOImpl adminDAO=new AdminDAOImpl();

    public void createUser(User user) {
        adminDAO.createUser(user);
    }

    public  void updateUser(User user) {
        adminDAO.updateUser(user);
    }

    public  ArrayList<Item_Detail> getAllCheckOut(String date) {
        return adminDAO.getAllCheckOut(date);
    }

    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return adminDAO.getCheckOutWarehouse(idWarehouse, date);
    }

    public  ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return adminDAO.getCheckOutItemWarehouse(idWarehouse, idItem, date);
    }

    public ArrayList<User> getUsers() {
        return adminDAO.getUsers();
    }

    public void addItem(Item item){
        adminDAO.addItem(item);
    }
    public void updateItem(Item item) {

        adminDAO.updateItem(item);

    }
}
