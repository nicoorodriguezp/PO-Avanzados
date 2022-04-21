package com.poa.POAvanzados.Model.DAO;

import com.poa.POAvanzados.Model.RepairModel.Repair;

public interface WorkerDAO extends UserDAO {

    public void getAllInventory(int idWorkplace);

    public void getInventoryItem(int idWorkplace, int idItem);

    public void repair(Repair repair);

    public void changeItemState(int idItem, int state); // Change state to "in use", "used", "discarded"
}
