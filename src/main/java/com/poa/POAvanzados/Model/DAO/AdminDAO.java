package com.poa.POAvanzados.Model.DAO;

import java.util.List;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;

public interface AdminDAO extends ManagerDAO {

    // User CRUD
    public void createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    // Reports

    // Todos los movimientos que hubo en un dia especifico.
    public List<Item_Detail> getAllItemsByCheckOut(String date);

    // Salidas que hubo por deposito en un dia especifico.
    public List<Item_Detail> getItemsByIdWarehouseAndCheckOut(int idWarehouse, String date);

    // Salidas de item desde un deposito en un dia especifico.
    public List<Item_Detail> getItemsByIdWarehouseAndIdItemAndCheckOut(int idWarehouse, int idItem, String date);
}
