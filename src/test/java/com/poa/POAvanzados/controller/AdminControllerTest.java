package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.junit.*;
import java.util.ArrayList;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    AdminService adminService;

    @InjectMocks
    AdminController adminController;




    @Test
    void getUsers() {
        ArrayList<User> usersExpected= new ArrayList<>();
        ArrayList<User> userReceived= new ArrayList<>();
        userReceived.add(TestUtils.createUser());
        usersExpected.add(TestUtils.createUser());
        when(adminService.getUsers()).thenReturn(userReceived);
        adminController.getUsers();
        Assert.assertEquals(usersExpected,userReceived);
        verify(adminService,atLeastOnce()).getUsers();
    }

    @Test
    void createUser() {
        adminController.createUser(new User());
        verify(adminService,atLeastOnce()).createUser(any());
    }

    @Test
    void updateUser() {
        adminController.updateUser(new User());
        verify(adminService,atLeastOnce()).updateUser(any());
    }

    @Test
    void getAllCheckOut() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminService.getAllCheckOut(any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminController.getAllCheckOut(any()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(adminService,atLeastOnce()).getAllCheckOut(any());
    }

    @Test
    void getCheckOutWarehouse() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminService.getCheckOutWarehouse(anyInt(),any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminController.getCheckOutWarehouse(anyInt(),any()));
        Assert.assertEquals(items_details_expected,items_details_mock);
        verify(adminService,atLeastOnce()).getCheckOutWarehouse(anyInt(),any());
    }

    @Test
    void getCheckOutItemWarehouse() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminService.getCheckOutItemWarehouse(anyInt(),anyInt(),any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminController.getCheckOutItemWarehouse(anyInt(),anyInt(),any()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(adminService,atLeastOnce()).getCheckOutItemWarehouse(anyInt(),anyInt(),any());
    }

    @Test
    void updateItem() {
        adminController.updateItem(any());
        verify(adminService,atLeastOnce()).updateItem(any());
    }

    @Test
    void addItem() {
        adminController.addItem(any());
        verify(adminService,atLeastOnce()).addItem(any());
    }


}