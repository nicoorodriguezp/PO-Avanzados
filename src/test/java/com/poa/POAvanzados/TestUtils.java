package com.poa.POAvanzados;

import com.poa.POAvanzados.model.item_model.*;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;

public class TestUtils {
    public static Item_Detail createItemDetail(){
        Item_Detail itemDetail= new Item_Detail();
        itemDetail.setItem(createItem());
        itemDetail.setIdItemCode(1);
        itemDetail.setCheckIn("29/05/2022");
        itemDetail.setCheckOut("29/05/2022");
        itemDetail.setState(createItemState());
        itemDetail.setLaboratory(createLaboratory());
        itemDetail.setWarehouse(createWarehouse());
        return itemDetail;
    }

    public static Item createItem(){
        Item item= new Item();
        item.setIdItem(1);
        item.setCritical(false);
        item.setName("Pantalla");
        return item;
    }


    public static ItemState createItemState(){
        ItemState itemState= new ItemState();
        itemState.setIdState(1);
        itemState.setName("En stock");
        return itemState;
    }
    public static Workplace createLaboratory(){
        Workplace workplace= new Workplace();
        workplace.setIdWorkplace(3);
        workplace.setWarehouse(false);
        workplace.setAddress("Doofenshmirtz Malvados y Asociados");
        workplace.setIdManager(5);
        return workplace;
    }

    public static Workplace createWarehouse(){
        Workplace workplace= new Workplace();
        workplace.setIdWorkplace(1);
        workplace.setWarehouse(true);
        workplace.setAddress("callefalsa 123");
        workplace.setIdManager(2);
        return workplace;
    }
    public static Position createPosition(){
        return new Position(2,"manager","manager");
    }
    public static User createUser(){
        return new User(2,createPosition(),createLaboratory(),"Matias","Romero","matias301020000@gmail.com",true,"123",2);
    }

    public static ItemCount createItemCount() {
        return new ItemCount(1,"Pantalla",false,13,2);
    }

    public static Item_Detail_Inventory createItemDetailInventory() {
        return new Item_Detail_Inventory(1,createItem(),"29/05/2022","29/05/2022",createItemState());
    }
}
