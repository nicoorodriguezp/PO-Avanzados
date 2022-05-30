package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.service.ManagerService;
import com.poa.POAvanzados.service.WorkerService;
import javafx.concurrent.Worker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerControllerTest {

    @Mock
    WorkerService workerService;

    @InjectMocks
    WorkerController workerController;
    @Test
    void getUser() throws LoginUserException {
        User userExpected= TestUtils.createUser();
        User userMock= TestUtils.createUser();
        User userReceived;
        when(workerService.getUser(any())).thenReturn(userMock);
        userReceived=workerController.getUser(anyInt(), "123");
        Assert.assertEquals(userExpected,userReceived);
        verify(workerService,atLeastOnce()).getUser(any());
    }

    @Test
    void getItems() {
        ArrayList<Item>items_expected= new ArrayList<>();
        ArrayList<Item>items_received= new ArrayList<>();
        ArrayList<Item>items_mock= new ArrayList<>();
        items_expected.add(TestUtils.createItem());
        items_mock.add(TestUtils.createItem());
        when(workerService.getItems()).thenReturn(items_mock);
        items_received.addAll(workerController.getItems());
        Assert.assertEquals(items_expected,items_received);
        verify(workerService,atLeastOnce()).getItems();
    }

    @Test
    void getAllInventoryByWorkplace() {
        ArrayList<Item_Detail_Inventory> items_inventory_expected= new ArrayList<>();
        ArrayList<Item_Detail_Inventory> items_inventory_mock= new ArrayList<>();
        ArrayList<Item_Detail_Inventory> items_inventory_received= new ArrayList<>();
        items_inventory_expected.add(TestUtils.createItemDetailInventory());
        items_inventory_mock.add(TestUtils.createItemDetailInventory());
        when(workerService.getAllInventoryByWorkplace(any())).thenReturn(items_inventory_mock);
        items_inventory_received.addAll(workerController.getAllInventoryByWorkplace(any()));
        Assert.assertEquals(items_inventory_expected,items_inventory_received);
        verify(workerService,atLeastOnce()).getAllInventoryByWorkplace(any());
    }

    @Test
    void createRepair() {
        workerController.createRepair(any());
        verify(workerService,atLeastOnce()).createRepair(any());
    }

    @Test
    void getAllInventoryByWorkplaceOnStock() throws NotAllowedForWarehouse {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(workerService.getAllInventoryByWorkplaceOnStock(any(),anyInt())).thenReturn(items_details_mock);
        items_details_received.addAll(workerController.getAllInventoryByWorkplaceOnStock(any(),anyInt()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(workerService,atLeastOnce()).getAllInventoryByWorkplaceOnStock(any(),anyInt());

    }
}