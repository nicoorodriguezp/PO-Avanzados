package com.poa.POAvanzados.Model.DAO;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public interface ManagerDAO extends WorkerDAO {

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity);

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity);

    public ArrayList<Workplace> getWorkplaces();

    public ArrayList<Position> getPositions();

}
