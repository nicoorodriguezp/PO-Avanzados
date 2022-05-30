package com.poa.POAvanzados.dao.worker;

import com.poa.POAvanzados.exception.DAOException;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.item_model.Workplace_Item;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;

import java.util.ArrayList;

public interface WorkerDAO {

    public User getUser(User user) throws DAOException, LoginUserException;

    public ArrayList<Item> getItems() throws DAOException;

    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace);


    public void createRepair(Repair repair);

    public void updateItemDetail(Item_Detail item); // Change state to "in use", "used", "discarded"

    public Workplace_Item checkStock(int idWorkplace, int idItem);

    ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse;
}
