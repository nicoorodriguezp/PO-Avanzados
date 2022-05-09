package fxml;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Controller {

    protected MainController m;

    // User Positions
    protected final int AdminPosition = 1;
    protected final int ManagerPosition = 2;
    protected final int WorkerPosition = 3;

    // Item States
    protected final int ItemInStock = 0;
    protected final int ItemInUse = 1;
    protected final int ItemUsed = 2;
    protected final int ItemDescarded = 3;

    public void init(MainController mainController) {
        this.m = mainController;
    }

    protected String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
