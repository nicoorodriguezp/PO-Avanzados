package com.poa.POAvanzados.Database;

import java.util.ArrayList;
import java.util.List;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;

public class ItemRepository {

    public static List<Item_Detail> getItemsDetails(int idWorkplace, int idItem, String checkOut) {
        return null;
    }

    public static void insert(Item_Detail item) {
    }

    public static ArrayList<Item> getItems() {

        ArrayList<Item> items = new ArrayList<>();

        Item i = new Item(0, "Bateria", false);
        items.add(i);

        i = new Item(1, "Pantalla", true);
        items.add(i);

        i = new Item(2, "Puerto Jack", false);
        items.add(i);

        i = new Item(3, "Puerto USB-C", true);
        items.add(i);

        i = new Item(4, "Camara", false);
        items.add(i);

        i = new Item(5, "Protector Pantalla", true);
        items.add(i);

        return items;
    }

}
