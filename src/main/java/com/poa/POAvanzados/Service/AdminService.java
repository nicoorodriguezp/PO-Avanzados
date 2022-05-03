package com.poa.POAvanzados.Service;

import java.util.ArrayList;
import java.util.List;

import com.poa.POAvanzados.Database.ItemDetailRepository;
import com.poa.POAvanzados.Database.UserRepository;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;

public class AdminService extends ManagerService {

    public static void createUser(User user) {
        UserRepository.createUser(user);
    }

    public static void deleteUser(int idUser) {
        UserRepository.deleteUser(idUser);
    }

    public static void updateUser(User user) {
        UserRepository.updateUser(user);
    }

    public static ArrayList<Item_Detail> getAllCheckOut(String date) {
        return ItemDetailRepository.getAllCheckOut(date);
    }

    public static ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return ItemDetailRepository.getCheckOutWarehouse(idWarehouse, date);
    }

    public static ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return ItemDetailRepository.getCheckOutItemWarehouse(idWarehouse, idItem, date);
    }

}
