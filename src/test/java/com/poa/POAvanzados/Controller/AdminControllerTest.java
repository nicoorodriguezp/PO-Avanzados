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
    void getUsers() {
        adminController.getUsers();
        verify(adminService,atLeastOnce()).getUsers();
    }


}