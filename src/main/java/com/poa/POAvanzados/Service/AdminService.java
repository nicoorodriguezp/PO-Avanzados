package com.poa.POAvanzados.Service;

import java.util.ArrayList;

import com.poa.POAvanzados.DAO.Admin.AdminDAOImpl;
import com.poa.POAvanzados.Database.ItemDetailRepository;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("adminService")
public class AdminService extends ManagerService {

    //TODO Tratar de solucionar el autowired del service
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
