package com.poa.POAvanzados;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import fxml.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavaFxMain extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(PoAvanzadosApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {

        applicationContext.publishEvent(new StageReadyEvent(stage, applicationContext));

    }

    static class StageReadyEvent extends ApplicationEvent {

        ConfigurableApplicationContext applicationContext;

        public StageReadyEvent(Stage stage, ConfigurableApplicationContext applicationContext) {
            super(stage);
            this.applicationContext = applicationContext;

        }

        public ConfigurableApplicationContext applicationContext() {
            return this.applicationContext;
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}
