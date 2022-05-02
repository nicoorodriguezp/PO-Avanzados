package com.poa.POAvanzados.Database;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.ItemBuilder;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;

public class ItemDetailRepository {

    public static ArrayList<Item_Detail> getAllInventory(int idWorkplace) {

        ArrayList<Item_Detail> itemsWorkplace = new ArrayList<>();

        Item_Detail iBuilder = new ItemBuilder()
                .addIdItem(0)
                .addName("Bateria")
                .isCritical(true)
                .addItemCode(0)
                .addWarehouse(0)
                .addLaboratory(0)
                .addCheckIn("14/03/21")
                .addCheckOut("18/03/21")
                .addState(0)
                .build();

        itemsWorkplace.add(iBuilder);

        iBuilder = new ItemBuilder()
                .addIdItem(1)
                .addName("Pantalla")
                .isCritical(true)
                .addItemCode(1)
                .addWarehouse(0)
                .addLaboratory(0)
                .addCheckIn("14/03/21")
                .addCheckOut("18/03/21")
                .addState(0)
                .build();

        itemsWorkplace.add(iBuilder);

        iBuilder = new ItemBuilder()
                .addIdItem(1)
                .addName("Pantalla")
                .isCritical(true)
                .addItemCode(2)
                .addWarehouse(0)
                .addLaboratory(0)
                .addCheckIn("14/03/21")
                .addCheckOut("18/03/21")
                .addState(0)
                .build();

        itemsWorkplace.add(iBuilder);

        return itemsWorkplace;
    }

}
