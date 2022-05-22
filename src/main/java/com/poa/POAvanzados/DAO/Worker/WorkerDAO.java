package com.poa.POAvanzados.DAO.Worker;

import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.ItemCount;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import java.util.ArrayList;

public interface WorkerDAO {

    public User getUser(User user) throws DAOException, LoginUserException;

    public ArrayList<Item> getItems() throws DAOException;

    ArrayList<ItemCount> getItemsCount() throws DAOException;

    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace);


    public void createRepair(Repair repair);

    public void updateItemDetail(Item_Detail item); // Change state to "in use", "used", "discarded"

    public Workplace_Item checkStock(int idWorkplace, int idItem);

    ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse;
}
