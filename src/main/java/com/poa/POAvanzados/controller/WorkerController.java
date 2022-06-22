package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import com.poa.POAvanzados.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class WorkerController{


    @Autowired
    WorkerService workerService= new WorkerService();

    public User getUser(int dniUser,String password) throws LoginUserException {
        User user= new User();
        user.setIdUser(dniUser);
        user.setPassword(password);
        return workerService.getUser(user);
    }

    public ArrayList<Item> getItems() {
        return workerService.getItems();
    }
    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace) {
        return workerService.getAllInventoryByWorkplace(workplace);
    }
    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace,Integer from,Integer ammount) {
        return workerService.getAllInventoryByWorkplace(workplace,from,ammount);
    }

    public void createRepair(Repair repair) {
        workerService.createRepair(repair);
    }



    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        return workerService.getAllInventoryByWorkplaceOnStock(workplace, user_role);
    }


    public Integer getAmmountItemsByWorkplace(Workplace workplace) {
        return workerService.getAmmountItemsByWorkplace(workplace);
    }
}
