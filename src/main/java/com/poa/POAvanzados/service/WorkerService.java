package com.poa.POAvanzados.service;

import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.exception.DAOException;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkerService {

    @Autowired
    WorkerDAOImpl workerDAO=new WorkerDAOImpl();

    public User getUser(User userLogin) throws LoginUserException {

        return workerDAO.getUser(userLogin);
    }

    public ArrayList<Item> getItems() {
        try{
            return workerDAO.getItems();
        }catch (DAOException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace) {
        return workerDAO.getAllInventoryByWorkplace(workplace);
    }

    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace,Integer from,Integer ammount) {
        return workerDAO.getAllInventoryByWorkplace(workplace,from,ammount);
    }

    public void createRepair(Repair repair) {

        workerDAO.createRepair(repair);
    }

    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        return workerDAO.getAllInventoryByWorkplaceOnStock(workplace,user_role);
    }

    public Integer getAmmountItemsByWorkplace(Workplace workplace) {
        return workerDAO.getAmmountItemsByWorkplace(workplace);
    }
}
