package com.poa.POAvanzados.service;

import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.dao.manager.ManagerDAOImpl;
import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.exception.NoWarehouseWithEnoughStock;
import com.poa.POAvanzados.exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.item_model.Workplace_Item;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
    @Mock
    ManagerDAOImpl managerDAO;
    @Mock
    WorkerDAOImpl workerDAO;

    @InjectMocks
    ManagerService managerService;
    @Test
    void replenishWarehouse() throws QuantityExceedsMaxSlots {
        Workplace_Item workplaceItemMock= TestUtils.createWorkPlaceItem();
        when(managerDAO.checkStock(anyInt(),anyInt())).thenReturn(workplaceItemMock);
        managerService.replenishWarehouse(1,1,1,"adsda");
        verify(managerDAO,atLeastOnce()).replenishWarehouse(anyInt(),anyInt(),anyInt(),anyString());

    }
    @Test
    void replenishWarehouseQuantityGreaterThanMaxSlot() throws QuantityExceedsMaxSlots {
        Workplace_Item workplaceItemMock= TestUtils.createWorkPlaceItem();
        when(managerDAO.checkStock(anyInt(),anyInt())).thenReturn(workplaceItemMock);
        Assert.assertThrows(QuantityExceedsMaxSlots.class,()->managerService.replenishWarehouse(1,1,1000,"adsda"));
    }

    @Test
    void replenishLaboratory() throws NoWarehouseWithEnoughStock, QuantityExceedsMaxSlots {
        Workplace_Item workplaceItemMock= TestUtils.createWorkPlaceItem();
        List<Workplace> workplaceListMock = new ArrayList<>();
        workplaceListMock.add(TestUtils.createWarehouse());
        when(managerDAO.checkStock(anyInt(),anyInt())).thenReturn(workplaceItemMock);
        when(managerDAO.getWarehouses()).thenReturn(workplaceListMock);
        managerService.replenishLaboratory(1,1,1,"dasdas");
        verify(managerDAO,atLeastOnce()).replenishLaboratory(anyInt(),anyInt(),anyInt(),any(),anyInt());
    }

    @Test
    void replenishLaboratoryQuantityGreaterThanMaxSlot() throws NoWarehouseWithEnoughStock, QuantityExceedsMaxSlots {
        Workplace_Item workplaceItemMock= TestUtils.createWorkPlaceItem();
        List<Workplace> workplaceListMock = new ArrayList<>();
        workplaceListMock.add(TestUtils.createWarehouse());
        when(managerDAO.checkStock(anyInt(),anyInt())).thenReturn(workplaceItemMock);
        Assert.assertThrows(QuantityExceedsMaxSlots.class,()->managerService.replenishWarehouse(1,1,1000,"adsda"));
    }
    @Test
    void replenishLaboratoryNotEnoughStock() throws NoWarehouseWithEnoughStock, QuantityExceedsMaxSlots {
        Workplace_Item workplaceItemMock= TestUtils.createWorkPlaceItemNotEnoughStock();
        List<Workplace> workplaceListMock = new ArrayList<>();
        workplaceListMock.add(TestUtils.createWarehouse());
        when(managerDAO.checkStock(anyInt(),anyInt())).thenReturn(workplaceItemMock);
        when(managerDAO.getWarehouses()).thenReturn(workplaceListMock);
        Assert.assertThrows(NoWarehouseWithEnoughStock.class,()->managerService.replenishLaboratory(1,1,55,"dasdas"));
    }
    @Test
    void getWorkplaces() {
        ArrayList<Workplace>workplaces_expected= new ArrayList<>();
        ArrayList<Workplace>workplaces_received= new ArrayList<>();
        ArrayList<Workplace>workplaces_mock= new ArrayList<>();
        workplaces_expected.add(TestUtils.createWarehouse());
        workplaces_expected.add(TestUtils.createLaboratory());
        workplaces_mock.add(TestUtils.createWarehouse());
        workplaces_mock.add(TestUtils.createLaboratory());
        when(managerDAO.getWorkplaces()).thenReturn(workplaces_mock);
        workplaces_received.addAll(managerService.getWorkplaces());
        Assert.assertEquals(workplaces_expected,workplaces_received);
        verify(managerDAO,atLeastOnce()).getWorkplaces();
    }

    @Test
    void getPositions() {
        ArrayList<Position>positions_expected= new ArrayList<>();
        ArrayList<Position>positions_received= new ArrayList<>();
        ArrayList<Position>positions_mock= new ArrayList<>();
        positions_expected.add(TestUtils.createPosition());
        positions_mock.add(TestUtils.createPosition());
        when(managerDAO.getPositions()).thenReturn(positions_mock);
        positions_received.addAll(managerService.getPositions());
        Assert.assertEquals(positions_expected,positions_received);
        verify(managerDAO,atLeastOnce()).getPositions();
    }

    @Test
    void getWarehouse() {
        List<Workplace>workplaces_expected= new ArrayList<>();
        List<Workplace>workplaces_received= new ArrayList<>();
        List<Workplace>workplaces_mock= new ArrayList<>();
        workplaces_expected.add(TestUtils.createWarehouse());
        workplaces_mock.add(TestUtils.createWarehouse());
        when(managerDAO.getWarehouses()).thenReturn(workplaces_mock);
        workplaces_received.addAll(managerService.getWarehouse());
        Assert.assertEquals(workplaces_expected,workplaces_received);
        verify(managerDAO,atLeastOnce()).getWarehouses();
    }
}