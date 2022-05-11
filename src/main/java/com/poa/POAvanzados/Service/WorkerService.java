package com.poa.POAvanzados.Service;

import com.poa.POAvanzados.DAO.Worker.WorkerDAOImpl;
import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
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

    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace) {
        return workerDAO.getAllInventoryByWorkplace(workplace);
    }

    public void createRepair(Repair repair) {

        workerDAO.createRepair(repair);
    }

    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        return workerDAO.getAllInventoryByWorkplaceOnStock(workplace,user_role);
    }
}
