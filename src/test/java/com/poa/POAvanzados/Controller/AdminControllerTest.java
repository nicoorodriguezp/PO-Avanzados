package com.poa.POAvanzados.Controller;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    AdminService adminService;

    @InjectMocks
    AdminController adminController;


    @Test
    void createUser() {
        adminController.createUser(new User());
        verify(adminService,atLeastOnce()).createUser(any());
    }

    @Test
    void updateUser() {
        adminController.updateUser(new User());
        verify(adminService,atLeastOnce()).createUser(any());
    }

    @Test
    void getAllCheckOut() {
        adminController.getAllCheckOut("");
        verify(adminService,atLeastOnce()).getAllCheckOut(any());
    }

    @Test
    void getCheckOutWarehouse() {
        adminController.getCheckOutWarehouse(1,"");
        verify(adminService,atLeastOnce()).getCheckOutWarehouse(any(),any());
    }

    @Test
    void getCheckOutItemWarehouse() {
        adminController.getCheckOutItemWarehouse(1,1,"");
        verify(adminService,atLeastOnce()).getCheckOutItemWarehouse(any(),any(),any());

    }

    @Test
    void getUsers() {
        adminController.getUsers();
        verify(adminService,atLeastOnce()).getUsers();
    }

    @Test
    void updateItem() {
        adminController.updateItem(new Item());
        verify(adminService,atLeastOnce()).updateItem(any());
    }

    @Test
    void addItem() {
        adminController.addItem(new Item());
        verify(adminService,atLeastOnce()).addItem(any());
    }
}