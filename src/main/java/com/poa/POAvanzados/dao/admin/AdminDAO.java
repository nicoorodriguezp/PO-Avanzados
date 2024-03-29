package com.poa.POAvanzados.dao.admin;

import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.user_model.User;

import java.util.ArrayList;

public interface AdminDAO {

    // User CRUD
    public void createUser(User user);

    public void updateUser(User user);

    public ArrayList<User> getUsers();

    public void addItem(Item item);
    public void updateItem(Item item);

    // Reports

    /** @return :Todas las salidas que hubo en un dia especifico. */
    public ArrayList<Item_Detail> getAllCheckOut(String date);

    /**
     * @return :Todas las salidas de items de un determinado deposito en un dia
     *         especifico.
     */
    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date);

    /** @return :Salidas de un item desde un deposito en un dia especifico. */
    public ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date);
}
