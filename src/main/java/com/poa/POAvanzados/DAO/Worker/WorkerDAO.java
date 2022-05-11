package com.poa.POAvanzados.DAO.Worker;

import java.util.ArrayList;

import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public interface WorkerDAO {

    public User getUser(User user) throws DAOException, LoginUserException;

    public ArrayList<Item> getItems() throws DAOException;

    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace);

    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem);

    public void createRepair(Repair repair);

    public void updateItemDetail(Item_Detail item); // Change state to "in use", "used", "discarded"

    public ArrayList<Repair> getAllRepairs(int idWorkplace);

    ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace) throws NotAllowedForWarehouse;
}
