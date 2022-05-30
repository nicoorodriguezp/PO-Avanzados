package com.poa.POAvanzados.controller;


import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.exception.NoWarehouseWithEnoughStock;
import com.poa.POAvanzados.exception.QuantityExceedsMaxSlots;
import com.poa.POAvanzados.model.item_model.ItemCount;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import com.poa.POAvanzados.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ManagerControllerTest {

    @Mock
    ManagerService managerService;

    @InjectMocks
    ManagerController managerController;

    @Test
    void replenishWarehouse() throws QuantityExceedsMaxSlots {
        managerController.replenishWarehouse(anyInt(),anyInt(),anyInt(),any());
        verify(managerService,atLeastOnce()).replenishWarehouse(anyInt(),anyInt(),anyInt(),any());
    }

    @Test
    void replenishLaboratory() throws NoWarehouseWithEnoughStock, QuantityExceedsMaxSlots {
        managerController.replenishLaboratory(anyInt(),anyInt(),anyInt(),any());
        verify(managerService,atLeastOnce()).replenishLaboratory(anyInt(),anyInt(),anyInt(),any());
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
        when(managerService.getWorkplaces()).thenReturn(workplaces_mock);
        workplaces_received.addAll(managerController.getWorkplaces());
        Assert.assertEquals(workplaces_expected,workplaces_received);
        verify(managerService,atLeastOnce()).getWorkplaces();
    }

    @Test
    void getPositions() {

        ArrayList<Position>positions_expected= new ArrayList<>();
        ArrayList<Position>positions_received= new ArrayList<>();
        ArrayList<Position>positions_mock= new ArrayList<>();
        positions_expected.add(TestUtils.createPosition());
        positions_mock.add(TestUtils.createPosition());
        when(managerService.getPositions()).thenReturn(positions_mock);
        positions_received.addAll(managerController.getPositions());
        Assert.assertEquals(positions_expected,positions_received);
        verify(managerService,atLeastOnce()).getPositions();
    }

    @Test
    void getWarehouse() {
        ArrayList<Workplace>workplaces_expected= new ArrayList<>();
        ArrayList<Workplace>workplaces_received= new ArrayList<>();
        ArrayList<Workplace>workplaces_mock= new ArrayList<>();
        workplaces_expected.add(TestUtils.createWarehouse());
        workplaces_mock.add(TestUtils.createWarehouse());
        when(managerService.getWarehouse()).thenReturn(workplaces_mock);
        workplaces_received.addAll(managerController.getWarehouse());
        Assert.assertEquals(workplaces_expected,workplaces_received);
        verify(managerService,atLeastOnce()).getWarehouse();
    }

    @Test
    void getItemCountByWorkplace() {
        ArrayList<ItemCount>itemCount_expected= new ArrayList<>();
        ArrayList<ItemCount>itemCount_received= new ArrayList<>();
        ArrayList<ItemCount>itemCount_mock= new ArrayList<>();
        itemCount_expected.add(TestUtils.createItemCount());
        itemCount_mock.add(TestUtils.createItemCount());
        when(managerService.getItemCountByWorkplace(any())).thenReturn(itemCount_mock);
        itemCount_received.addAll(managerController.getItemCountByWorkplace(any()));
        Assert.assertEquals(itemCount_expected,itemCount_received);
        verify(managerService,atLeastOnce()).getItemCountByWorkplace(any());
    }
}