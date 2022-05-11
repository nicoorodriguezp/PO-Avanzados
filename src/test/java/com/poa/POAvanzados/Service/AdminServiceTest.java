package com.poa.POAvanzados.Service;

import com.poa.POAvanzados.DAO.Admin.AdminDAOImpl;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.UserModel.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    AdminDAOImpl adminDAO;

    @InjectMocks
    AdminService adminService;

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
        adminService.getAllCheckOut("");
        verify(adminDAO,atLeastOnce()).getAllCheckOut(any());
    }

    @Test
    void getCheckOutWarehouse() {
        adminService.getCheckOutWarehouse(1,"");
        verify(adminDAO,atLeastOnce()).getCheckOutWarehouse(any(),any());
    }

    @Test
    void getCheckOutItemWarehouse() {
        adminService.getCheckOutItemWarehouse(1,1,"");
        verify(adminDAO,atLeastOnce()).getCheckOutItemWarehouse(any(),any(),any());

    }

    @Test
    void getUsers() {
        adminService.getUsers();
        verify(adminDAO,atLeastOnce()).getUsers();

    }

    @Test
    void addItem() {
        adminService.addItem( new Item());
        verify(adminDAO,atLeastOnce()).getUsers();
    }

    @Test
    void updateItem() {
        adminService.updateItem( new Item());
        verify(adminDAO,atLeastOnce()).updateItem(any());
    }
}