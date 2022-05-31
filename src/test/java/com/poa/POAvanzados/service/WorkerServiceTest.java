package com.poa.POAvanzados.service;

import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.exception.DAOException;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @Mock
    WorkerDAOImpl workerDAO;

    @InjectMocks
    WorkerService workerService;

    @Test
    void getUser() throws LoginUserException {
        User userExpected= TestUtils.createUser();
        User userMock= TestUtils.createUser();
        User userReceived;
        when(workerDAO.getUser(any())).thenReturn(userMock);
        userReceived=workerService.getUser(any());
        Assert.assertEquals(userExpected,userReceived);
        verify(workerDAO,atLeastOnce()).getUser(any());
    }

    @Test
    void getItems() throws DAOException {
        ArrayList<Item> items_expected= new ArrayList<>();
        ArrayList<Item>items_received= new ArrayList<>();
        ArrayList<Item>items_mock= new ArrayList<>();
        items_expected.add(TestUtils.createItem());
        items_mock.add(TestUtils.createItem());
        when(workerDAO.getItems()).thenReturn(items_mock);
        items_received.addAll(workerService.getItems());
        Assert.assertEquals(items_expected,items_received);
        verify(workerDAO,atLeastOnce()).getItems();
    }

    @Test
    void getAllInventoryByWorkplace() {
        ArrayList<Item_Detail_Inventory> items_inventory_expected= new ArrayList<>();
        ArrayList<Item_Detail_Inventory> items_inventory_mock= new ArrayList<>();
        ArrayList<Item_Detail_Inventory> items_inventory_received= new ArrayList<>();
        items_inventory_expected.add(TestUtils.createItemDetailInventory());
        items_inventory_mock.add(TestUtils.createItemDetailInventory());
        when(workerDAO.getAllInventoryByWorkplace(any())).thenReturn(items_inventory_mock);
        items_inventory_received.addAll(workerService.getAllInventoryByWorkplace(any()));
        Assert.assertEquals(items_inventory_expected,items_inventory_received);
        verify(workerDAO,atLeastOnce()).getAllInventoryByWorkplace(any());
    }

    @Test
    void createRepair() {
        workerService.createRepair(any());
        verify(workerDAO,atLeastOnce()).createRepair(any());
    }

    @Test
    void getAllInventoryByWorkplaceOnStock() throws NotAllowedForWarehouse {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(workerDAO.getAllInventoryByWorkplaceOnStock(any(),anyInt())).thenReturn(items_details_mock);
        items_details_received.addAll(workerService.getAllInventoryByWorkplaceOnStock(any(),anyInt()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(workerDAO,atLeastOnce()).getAllInventoryByWorkplaceOnStock(any(),anyInt());

    }

}