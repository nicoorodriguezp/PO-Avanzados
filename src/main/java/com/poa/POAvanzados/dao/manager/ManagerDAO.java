package com.poa.POAvanzados.dao.manager;

import com.poa.POAvanzados.exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.model.item_model.ItemCount;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;

import java.util.ArrayList;
import java.util.List;

public interface ManagerDAO {

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity,String checkIn) throws QuantityExceedsMaxSlots;

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity,String checkOut,int idWarehouse);

    public ArrayList<Workplace> getWorkplaces();

    public ArrayList<Position> getPositions();

    List<Workplace> getWarehouses();

    ArrayList<ItemCount> getItemCountByWorkplace(Workplace workplace);
}
