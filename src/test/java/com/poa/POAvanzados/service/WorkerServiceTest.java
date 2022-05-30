package com.poa.POAvanzados.service;

import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerServiceTest {

    @Mock
    WorkerDAOImpl workerDAO;

    @InjectMocks
    WorkerService workerService;

    @SneakyThrows
    @Test
    @DisplayName("Verifica si se llama a la funcion getUser")
    void getUser() {

        workerService.getUser(new User());
        verify(workerDAO,atLeastOnce()).getUser(any());

    }

    @SneakyThrows
    @Test
    void getItems() {
        workerService.getItems();
        verify(workerDAO,atLeastOnce()).getItems();
    }

    @Test
    void getAllInventoryByWorkplace() {
        workerService.getAllInventoryByWorkplace(new Workplace());
        verify(workerDAO,atLeastOnce()).getAllInventoryByWorkplace(any());
    }

    @Test
    void createRepair() {
        workerDAO.createRepair(new Repair());
        verify(workerDAO,atLeastOnce()).createRepair(any());
    }

}