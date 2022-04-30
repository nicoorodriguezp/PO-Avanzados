package com.poa.POAvanzados;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;

/*

	Main Spring boot App
	
*/

@SpringBootApplication
public class PoAvanzadosApplication {

	ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		Application.launch(JavaFxMain.class, args);
	}

}
