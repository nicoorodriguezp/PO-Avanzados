package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.exception.NoWarehouseWithEnoughStock;
import com.poa.POAvanzados.exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.model.item_model.ItemCount;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import com.poa.POAvanzados.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class ManagerController extends WorkerController{

    @Autowired
    ManagerService managerService=new ManagerService();

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity,String checkIn) throws QuantityExceedsMaxSlots {
        managerService.replenishWarehouse(idWarehouse,  idItem,  quantity,checkIn);

    }

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity,String checkout) throws QuantityExceedsMaxSlots, NoWarehouseWithEnoughStock {
        managerService.replenishLaboratory(idLaboratory,idItem,quantity,checkout);

    }

    public ArrayList<Workplace> getWorkplaces() {
        return managerService.getWorkplaces();
    }

    public ArrayList<Position> getPositions() {
        return managerService.getPositions();
    }

    public ArrayList<Workplace> getWarehouse() {
        return managerService.getWarehouse();
    }

    public ArrayList<ItemCount> getItemCountByWorkplace(Workplace workplace) {
        return managerService.getItemCountByWorkplace(workplace);
    }
}
