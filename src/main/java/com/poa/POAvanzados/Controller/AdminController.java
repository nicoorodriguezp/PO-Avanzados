package com.poa.POAvanzados.Controller;

import java.util.List;

import com.poa.POAvanzados.Model.DAO.AdminDAO;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;

public class AdminController extends ManagerController implements AdminDAO {

    @Override
    public void createUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Item_Detail> getAllItemsByCheckOut(String date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Item_Detail> getItemsByIdWarehouseAndCheckOut(int idWarehouse, String date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Item_Detail> getItemsByIdWarehouseAndIdItemAndCheckOut(int idWarehouse, int idItem, String date) {
        // TODO Auto-generated method stub
        return null;
    }

}
