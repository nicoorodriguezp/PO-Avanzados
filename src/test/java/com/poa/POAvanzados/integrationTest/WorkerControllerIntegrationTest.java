package com.poa.POAvanzados.integrationTest;

import com.poa.POAvanzados.controller.WorkerController;
import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import com.poa.POAvanzados.service.WorkerService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class WorkerControllerIntegrationTest {

    WorkerController workerController= new WorkerController();


    @DisplayName("Login Succesful")
    @Test
    public void loginTest() throws LoginUserException {
        User userExpected= new User(1,new Position(1,"ADMIN","ADMIN"),new Workplace(3,false,6,"Doofenshmirtz Malvados y Asociados"),"Roberto","Galati","mailfalso123@gmail.com",true,null,1);
        User userReceived=workerController.getUser(1,"admin");
        Assert.assertEquals(userExpected.getIdUser(),userReceived.getIdUser());
    }

    @DisplayName("Login Failed")
    @Test
    public void loginFailedTest() throws LoginUserException {
        Assert.assertThrows(LoginUserException.class,()->workerController.getUser(1,"zvczv"));
    }

    @DisplayName("Get Items")
    @Test
    public void getItems() throws LoginUserException {
        ArrayList<Item> items= new ArrayList<Item>();
        items.addAll(workerController.getItems());
        Assert.assertEquals(false,items.isEmpty());
    }

}
