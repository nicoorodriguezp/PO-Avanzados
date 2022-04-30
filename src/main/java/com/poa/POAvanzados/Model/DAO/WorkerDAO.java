package com.poa.POAvanzados.Model.DAO;

import java.util.List;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;

public interface WorkerDAO extends UserDAO {

    public List<Item_Detail> getAllInventory(int idWorkplace);

    public List<Item_Detail> getInventoryItem(int idWorkplace, int idItem);

    public void createRepair(Repair repair);

    public void updateItem(Item_Detail item); // Change state to "in use", "used", "discarded"

    public List<Repair> getAllRepairs(int idWorkplace);
}
