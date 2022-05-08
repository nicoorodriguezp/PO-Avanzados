package com.poa.POAvanzados;

import com.poa.POAvanzados.Model.PositionModel.Admin;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/*

	Main Spring boot App
	
*/

@SpringBootApplication
public class PoAvanzadosApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxMain.class, args);
	}

}
