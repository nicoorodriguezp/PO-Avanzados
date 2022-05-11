package com.poa.POAvanzados.DAO.Manager;

import java.util.ArrayList;
import java.util.List;

import com.poa.POAvanzados.Exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public interface ManagerDAO {

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity,String checkIn) throws QuantityExceedsMaxSlots;

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity,String checkOut,int idWarehouse);

    public ArrayList<Workplace> getWorkplaces();

    public ArrayList<Position> getPositions();

    Workplace_Item checkStock(int idWarehouse, int idItem);

    List<Workplace> getWarehouses();
}
