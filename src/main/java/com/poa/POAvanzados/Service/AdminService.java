package com.poa.POAvanzados.Service;

import java.util.List;

import com.poa.POAvanzados.Database.MongoDB.Entities.ItemRepository;
import com.poa.POAvanzados.Database.MongoDB.Entities.RepairRepository;
import com.poa.POAvanzados.Database.MongoDB.Entities.UserRepository;
import com.poa.POAvanzados.Model.DAO.AdminDAO;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;

public class AdminService extends ManagerService implements AdminDAO {

    public AdminService(UserRepository userRepository, ItemRepository itemRepository,
            RepairRepository repairRepository) {
        super(userRepository, itemRepository, repairRepository);
    }

    @Override
    public void createUser(User user) {
        userRepository.insert(user);

    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);

    }

    @Override
    public List<Item_Detail> getAllItemsByCheckOut(String date) {
        return itemRepository.getAllItemsByCheckOut(date);
    }

    @Override
    public List<Item_Detail> getItemsByIdWarehouseAndCheckOut(int idWarehouse, String date) {
        return itemRepository.getItemsByIdWarehouseAndCheckOut(idWarehouse, date);
    }

    @Override
    public List<Item_Detail> getItemsByIdWarehouseAndIdItemAndCheckOut(int idWarehouse, int idItem,
            String date) {
        return itemRepository.getItemsByIdWarehouseAndIdItemAndCheckOut(idWarehouse, idItem, date);
    }

}
