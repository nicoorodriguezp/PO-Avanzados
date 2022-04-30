package com.poa.POAvanzados;

import java.io.IOException;

import com.poa.POAvanzados.JavaFxMain.StageReadyEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fxml.MainController;

@Component
public class StageInitializer implements ApplicationListener<JavaFxMain.StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent event) {

        MainController m = new MainController(event.getStage(), event.applicationContext());

        m.showLogin("Sistema de Gestion de Insumos");

    }

}
