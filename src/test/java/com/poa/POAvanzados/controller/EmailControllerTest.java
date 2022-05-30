package com.poa.POAvanzados.controller;

import com.poa.POAvanzados.service.EmailService;
import com.poa.POAvanzados.service.EmailServiceImpl;
import com.poa.POAvanzados.service.WorkerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
class EmailControllerTest {

    @Mock
    EmailServiceImpl workerService;

    @InjectMocks
    EmailController workerController;

    @Test
    void sendMail() {
        String statusExpected= "Mail Sent Successfully...";
        String statusMock= "Mail Sent Successfully...";
        when(workerService.sendSimpleMail(any())).thenReturn(statusMock);
        String statusReceived= workerController.sendMail(any());
        assertEquals(statusExpected,statusReceived);
    }
}