package com.poa.POAvanzados.service;

import com.poa.POAvanzados.TestUtils;
import com.poa.POAvanzados.dao.admin.AdminDAOImpl;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.user_model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.junit.*;
import java.util.ArrayList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    AdminDAOImpl adminDAO;

    @InjectMocks
    AdminService adminService;


    @Test
    void getUsers() {
        ArrayList<User> usersExpected= new ArrayList<>();
        ArrayList<User> userReceived= new ArrayList<>();
        userReceived.add(TestUtils.createUser());
        usersExpected.add(TestUtils.createUser());
        when(adminDAO.getUsers()).thenReturn(userReceived);
        adminService.getUsers();
        Assert.assertEquals(usersExpected,userReceived);
        verify(adminDAO,atLeastOnce()).getUsers();
    }

    @Test
    void createUser() {
        adminService.createUser(new User());
        verify(adminDAO,atLeastOnce()).createUser(any());
    }

    @Test
    void updateUser() {
        adminService.updateUser(new User());
        verify(adminDAO,atLeastOnce()).updateUser(any());
    }

    @Test
    void getAllCheckOut() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminDAO.getAllCheckOut(any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminService.getAllCheckOut(any()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(adminDAO,atLeastOnce()).getAllCheckOut(any());
    }

    @Test
    void getCheckOutWarehouse() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminDAO.getCheckOutWarehouse(anyInt(),any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminService.getCheckOutWarehouse(anyInt(),any()));
        Assert.assertEquals(items_details_expected,items_details_mock);
        verify(adminDAO,atLeastOnce()).getCheckOutWarehouse(anyInt(),any());
    }

    @Test
    void getCheckOutItemWarehouse() {
        ArrayList<Item_Detail> items_details_expected= new ArrayList<>();
        ArrayList<Item_Detail> items_details_mock= new ArrayList<>();
        ArrayList<Item_Detail> items_details_received= new ArrayList<>();
        items_details_expected.add(TestUtils.createItemDetail());
        items_details_mock.add(TestUtils.createItemDetail());
        when(adminDAO.getCheckOutItemWarehouse(anyInt(),anyInt(),any())).thenReturn(items_details_mock);
        items_details_received.addAll(adminService.getCheckOutItemWarehouse(anyInt(),anyInt(),any()));
        Assert.assertEquals(items_details_expected,items_details_received);
        verify(adminDAO,atLeastOnce()).getCheckOutItemWarehouse(anyInt(),anyInt(),any());
    }

    @Test
    void updateItem() {
        adminService.updateItem(any());
        verify(adminDAO,atLeastOnce()).updateItem(any());
    }

    @Test
    void addItem() {
        adminService.addItem(any());
        verify(adminDAO,atLeastOnce()).addItem(any());
    }
}