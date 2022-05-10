package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WorkerController{


    @Autowired
    WorkerService workerService= new WorkerService();

    public User getUser(int dniUser,String password) throws DAOException {
        User user= new User();
        user.setIdUser(dniUser);
        user.setPassword(password);
        return workerService.getUser(user);
    }

    public ArrayList<Item> getItems() {
        return workerService.getItems();
    }

    public static ArrayList<Item_Detail> getAllInventory(int idWorkplace) {
        return WorkerService.getAllInventory(idWorkplace);
    }

    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem) {
        return WorkerService.getInvetoryItem();
    }

    public void createRepair(Repair repair) {
        WorkerService.createRepair(repair);
    }

    public void updateItem(Item_Detail item) {
        WorkerService.updateItem(item);

    }

    public void addItem(Item item){
        workerService.addItem(item);
    }
    public ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return WorkerService.getAllRepairs(idWorkplace);
    }



}
