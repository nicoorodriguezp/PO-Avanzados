package com.poa.POAvanzados.Model.DAO;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;

public interface WorkerDAO {

    public User getUser(int idUser) throws DAOException;

    public ArrayList<Item> gItems() throws DAOException;

    public ArrayList<Item_Detail> getAllInventory(int idWorkplace);

    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem);

    public void createRepair(Repair repair);

    public void updateItem(Item_Detail item); // Change state to "in use", "used", "discarded"

    public ArrayList<Repair> getAllRepairs(int idWorkplace);
}
