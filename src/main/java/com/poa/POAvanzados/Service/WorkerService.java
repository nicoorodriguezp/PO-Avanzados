package com.poa.POAvanzados.Service;

import java.util.ArrayList;

import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.DAO.Worker.WorkerDAOImpl;
import com.poa.POAvanzados.Database.ItemDetailRepository;
import com.poa.POAvanzados.Database.ItemRepository;
import com.poa.POAvanzados.Database.RepairRepository;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    @Autowired
    WorkerDAOImpl workerDAO=new WorkerDAOImpl();

    public User getUser(User userLogin) throws DAOException {

        return workerDAO.getUser(userLogin);
    }

    public static ArrayList<Item> getItems() {
        return ItemRepository.getItems();
    }

    public static ArrayList<Item_Detail> getAllInventory(int idWorkplace) {
        return ItemDetailRepository.getAllInventory(idWorkplace);
    }

    public static ArrayList<Item_Detail> getInvetoryItem() {
        return null;
    }

    public static void createRepair(Repair repair) {
        RepairRepository.createRepair(repair);
    }

    public static void updateItem(Item_Detail item) {
        ItemDetailRepository.updateItem(item);
    }

    public static ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return RepairRepository.getAllRepairs(idWorkplace);
    }

}
