package com.poa.POAvanzados.DAO;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import java.util.ArrayList;

public class ManagerDAOImpl extends WorkerDAOImpl implements ManagerDAO {
    @Override
    public void replenishWarehouse(int idWarehouse, int idItem, int quantity) {

    }

    @Override
    public void replenishLaboratory(int idLaboratory, int idItem, int quantity) {

    }

    @Override
    public ArrayList<Workplace> getWorkplaces() {
        return null;
    }

    @Override
    public ArrayList<Position> getPositions() {
        return null;
    }
}
