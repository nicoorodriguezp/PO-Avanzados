package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Exception.NoWarehouseWithEnoughStock;
import com.poa.POAvanzados.Exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import com.poa.POAvanzados.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

}
