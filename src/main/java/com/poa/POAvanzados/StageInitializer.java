package com.poa.POAvanzados;

import com.poa.POAvanzados.JavaFxMain.StageReadyEvent;
import fxml.MainController;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<JavaFxMain.StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent event) {

        MainController m = new MainController(event.getStage(), event.applicationContext());

        m.showLogin("Sistema de Gestion de Insumos");

    }

}
