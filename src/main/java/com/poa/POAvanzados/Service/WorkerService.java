package com.poa.POAvanzados.Service;

import java.util.ArrayList;

import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.DAO.Worker.WorkerDAOImpl;
import com.poa.POAvanzados.Database.RepairRepository;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    @Autowired
    WorkerDAOImpl workerDAO=new WorkerDAOImpl();

    public User getUser(User userLogin) throws DAOException, LoginUserException {

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

    public void addItem(Item item){
        workerDAO.addItem(item);
    }

    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace) {
        return workerDAO.getAllInventoryByWorkplace(workplace);
    }

    public static ArrayList<Item_Detail> getInvetoryItem() {
        return null;
    }

    public static void createRepair(Repair repair) {
        RepairRepository.createRepair(repair);
    }

    public void updateItemDetail(Item_Detail item) {
        workerDAO.updateItemDetail(item);
    }

    public static ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return RepairRepository.getAllRepairs(idWorkplace);
    }

}
