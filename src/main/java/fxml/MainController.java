package fxml;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.ItemBuilder;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class MainController {

    private ApplicationContext applicationContext;

    public User user;
    public Stage stage;
    public FXMLLoader fxmlLoader;

    public Parent parent;

    public MainController(Stage stage, ApplicationContext applicationContext) {
        this.stage = stage;
        this.applicationContext = applicationContext;
    }

    protected void getFXML(String name) {
        try {
            this.fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + name + ".fxml"));
            this.parent = this.fxmlLoader.load();

        } catch (IOException e) {
            System.out.println("No se pudo cargar el FXML: " + name);
            e.printStackTrace();
        }
    }

    public void showLogin(String appTitle) {

        getFXML("Login");

        Controller controller = this.fxmlLoader.getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle(appTitle);

        this.stage.show();
    }

    protected void showHome() {

        getFXML("Home");

        Controller controller = this.fxmlLoader.getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Home Panel - " + user.toString());

        this.stage.show();
    }

    protected void showRepairPanel() {

        getFXML("Repair");

        ArrayList<Item_Detail> itemsLaboratory = new ArrayList<>();

        Item_Detail iBuilder = new ItemBuilder()
                .addIdItem(0)
                .addName("Bateria")
                .isCritical(true)
                .addItemCode(0)
                .addWarehouse(0)
                .addLaboratory(0)
                .addCheckIn("14/03/21")
                .addCheckOut("18/03/21")
                .addState(0)
                .build();

        itemsLaboratory.add(iBuilder);

        RepairController controller = this.fxmlLoader.getController();
        controller.init(this, itemsLaboratory);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Home Panel - " + user.toString());

        this.stage.show();

    }

    protected String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);

    }
}
