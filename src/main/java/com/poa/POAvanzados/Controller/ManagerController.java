package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Database.PositionRepository;
import com.poa.POAvanzados.Database.WorkplaceRepository;
import com.poa.POAvanzados.Model.DAO.ManagerDAO;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class ManagerController extends WorkerController implements ManagerDAO {

    @Override
    public void replenishWarehouse(int idWarehouse, int idItem, int quantity) {

    }

    @Override
    public void replenishLaboratory(int idLaboratory, int idItem, int quantity) {

    }

    @Override
    public ArrayList<Workplace> getWorkplaces() {
        return WorkplaceRepository.getWorkplaces();
    }

    @Override
    public ArrayList<Position> getPositions() {
        return PositionRepository.getPositions();
    }

}
