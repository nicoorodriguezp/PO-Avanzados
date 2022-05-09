package com.poa.POAvanzados.Controller;

import java.util.ArrayList;

import com.poa.POAvanzados.Database.PositionRepository;
import com.poa.POAvanzados.Database.WorkplaceRepository;
import com.poa.POAvanzados.DAO.Manager.ManagerDAO;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import com.poa.POAvanzados.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ManagerController extends WorkerController implements ManagerDAO {

    @Autowired
    ManagerService managerService=new ManagerService();

    @Override
    public void replenishWarehouse(int idWarehouse, int idItem, int quantity) {

    }

    @Override
    public void replenishLaboratory(int idLaboratory, int idItem, int quantity) {

    }

    @Override
    public ArrayList<Workplace> getWorkplaces() {
        return managerService.getWorkplaces();
    }

    @Override
    public ArrayList<Position> getPositions() {
        return PositionRepository.getPositions();
    }

}
