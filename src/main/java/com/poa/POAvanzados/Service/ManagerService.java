package com.poa.POAvanzados.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.poa.POAvanzados.DAO.Manager.ManagerDAOImpl;
import com.poa.POAvanzados.Database.ItemRepository;
import com.poa.POAvanzados.Database.WorkplaceRepository;
import com.poa.POAvanzados.Exception.NoWarehouseWithEnoughStock;
import com.poa.POAvanzados.Exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class ManagerService extends WorkerService {

    private ManagerDAOImpl managerDAO = new ManagerDAOImpl();

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity, String checkIn) throws QuantityExceedsMaxSlots {

        Workplace_Item workplace_item = managerDAO.checkStock(idWarehouse, idItem);
        if (workplace_item.getMax_slots() < workplace_item.getStock() + quantity) {
            throw new QuantityExceedsMaxSlots("La cantidad de items que quiere ingresar excede la capacidad maxima del inventario del item, cantidad actual: " + workplace_item.getStock());
        }
        managerDAO.replenishWarehouse(idWarehouse, idItem, quantity, checkIn);


    }

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity, String checkout) throws QuantityExceedsMaxSlots, NoWarehouseWithEnoughStock {
        Workplace_Item workplace_item = managerDAO.checkStock(idLaboratory, idItem);
        if (workplace_item.getMax_slots() < workplace_item.getStock() + quantity) {
            throw new QuantityExceedsMaxSlots("La cantidad de items que quiere ingresar excede la capacidad maxima del inventario del item, cantidad actual: " + workplace_item.getStock());
        }
        List<Workplace> warehouses = managerDAO.getWarehouses();
        Boolean isSent = false;
        int highestStock=0;
        for (Workplace warehouse : warehouses) {
            if(!isSent) {
                Workplace_Item workplace_itemAux= managerDAO.checkStock(warehouse.getIdWorkplace(), idItem);
                if ( workplace_itemAux.getStock()>= quantity) {
                    managerDAO.replenishLaboratory(idLaboratory,idItem,quantity,checkout,warehouse.getIdWorkplace());
                    isSent = true;
                }
                else {
                    if(highestStock<workplace_itemAux.getStock()){
                        highestStock=workplace_itemAux.getStock();
                    }
                }
            }
        }
        if (!isSent){
            throw new NoWarehouseWithEnoughStock("No hay warehouse con stock suficiente para cubrir el pedido, el mayor stock registrado es "+highestStock);
        }
    }

    public ArrayList<Workplace> getWorkplaces() {
        return managerDAO.getWorkplaces();
    }

    public ArrayList<Position> getPositions() {
        return managerDAO.getPositions();
    }
}
