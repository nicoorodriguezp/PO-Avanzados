package com.poa.POAvanzados.Database;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.ItemBuilder;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class ItemDetailRepository {

        public static ArrayList<Item_Detail> getAllInventory(int idWorkplace) {

                ArrayList<Item_Detail> itemsWorkplace = new ArrayList<>();

                Item_Detail iBuilder = new ItemBuilder()
                                .addIdItem(0)
                                .addName("Bateria")
                                .isCritical(true)
                                .addItemCode(0)
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
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
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
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
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                                .addCheckIn("14/03/21")
                                .addCheckOut("18/03/21")
                                .addState(0)
                                .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(1)
                        .addName("Puerto Jack")
                        .isCritical(true)
                        .addItemCode(3)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(1)
                        .addName("Pantalla")
                        .isCritical(true)
                        .addItemCode(4)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(5)
                        .addName("Protector Pantalla")
                        .isCritical(true)
                        .addItemCode(5)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(4)
                        .addName("Camara")
                        .isCritical(true)
                        .addItemCode(6)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(3)
                        .addName("Puerto USB-C")
                        .isCritical(true)
                        .addItemCode(7)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(3)
                        .addName("Puerto USB-C")
                        .isCritical(true)
                        .addItemCode(8)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(5)
                        .addName("Protector Pantalla")
                        .isCritical(true)
                        .addItemCode(9)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(4)
                        .addName("Camara")
                        .isCritical(true)
                        .addItemCode(10)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(3)
                        .addName("Puerto USB-C")
                        .isCritical(true)
                        .addItemCode(11)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                iBuilder = new ItemBuilder()
                        .addIdItem(3)
                        .addName("Puerto USB-C")
                        .isCritical(true)
                        .addItemCode(12)
                        .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                        .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                        .addCheckIn("14/03/21")
                        .addCheckOut("18/03/21")
                        .addState(0)
                        .build();

                itemsWorkplace.add(iBuilder);

                return itemsWorkplace;
        }

        public static void updateItem(Item_Detail item) {

        }

        public static ArrayList<Item_Detail> getAllCheckOut(String date) {

                ArrayList<Item_Detail> allCheckOuts = new ArrayList<>();

                Item_Detail iBuilder = new ItemBuilder()
                                .addIdItem(0)
                                .addName("Bateria")
                                .isCritical(true)
                                .addItemCode(0)
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                                .addCheckIn("14/03/21")
                                .addCheckOut("18/03/22")
                                .addState(0)
                                .build();

                allCheckOuts.add(iBuilder);

                iBuilder = new ItemBuilder()
                                .addIdItem(1)
                                .addName("Pantalla")
                                .isCritical(true)
                                .addItemCode(2)
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                                .addCheckIn("14/03/21")
                                .addCheckOut("18/03/22")
                                .addState(0)
                                .build();
                allCheckOuts.add(iBuilder);

                iBuilder = new ItemBuilder()
                                .addIdItem(1)
                                .addName("Pantalla")
                                .isCritical(true)
                                .addItemCode(2)
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                                .addCheckIn("14/03/21")
                                .addCheckOut("18/03/22")
                                .addState(0)
                                .build();
                allCheckOuts.add(iBuilder);

                iBuilder = new ItemBuilder()
                                .addIdItem(3)
                                .addName("Puerto USB-C")
                                .isCritical(true)
                                .addItemCode(2)
                                .addWarehouse(new Workplace(0, true, 0, "Mario Bravo 1050"))
                                .addLaboratory(new Workplace(3, false, 0, "Jean Jaures 932/6"))
                                .addCheckIn("14/03/21")
                                .addCheckOut("18/03/22")
                                .addState(0)
                                .build();
                allCheckOuts.add(iBuilder);

                return allCheckOuts;
        }

        public static ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
                return null;
        }

        public static ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
                return null;
        }

}
