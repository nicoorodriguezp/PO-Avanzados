package com.poa.POAvanzados.Controller;

import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import com.poa.POAvanzados.Service.WorkerService;
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
    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace) {
        return workerService.getAllInventoryByWorkplace(workplace);
    }


    public void createRepair(Repair repair) {
        workerService.createRepair(repair);
    }



    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        return workerService.getAllInventoryByWorkplaceOnStock(workplace, user_role);
    }
}
