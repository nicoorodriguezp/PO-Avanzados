package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.DAO.DAOException;
import com.poa.POAvanzados.Model.DAO.WorkerDAO;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.WorkerService;

public class WorkerController implements WorkerDAO {

    @Override
    public User getUser(int idUser) throws DAOException {
        return WorkerService.getUser(idUser);
    }

    @Override
    public ArrayList<Item> gItems() throws DAOException {
        return WorkerService.getItems();
    }

    @Override
    public ArrayList<Item_Detail> getAllInventory(int idWorkplace) {
        return WorkerService.getAllInventory(idWorkplace);
    }

    @Override
    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem) {
        return WorkerService.getInvetoryItem();
    }

    @Override
    public void createRepair(Repair repair) {
        WorkerService.createRepair(repair);
    }

    @Override
    public void updateItem(Item_Detail item) {
        WorkerService.updateItem(item);

    }

    @Override
    public ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return WorkerService.getAllRepairs(idWorkplace);
    }

    public ArrayList<Item> getItems() {
        return WorkerService.getItems();
    }

}
