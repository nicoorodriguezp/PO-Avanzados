package fxml;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Controller {

    protected MainController m;

    public void init(MainController mainController) {
        this.m = mainController;
    }

    protected String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
