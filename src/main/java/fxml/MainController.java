package fxml;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Controller.ManagerController;
import com.poa.POAvanzados.Controller.WorkerController;
import com.poa.POAvanzados.Model.BusinessMessage;
import com.poa.POAvanzados.Model.DAO.DAOException;
import com.poa.POAvanzados.Model.ItemModel.ItemBuilder;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@Component
public class MainController {

    private ApplicationContext applicationContext;

    private AdminController ac = new AdminController();
    private ManagerController mc = new ManagerController();
    private WorkerController wc = new WorkerController();

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
            showAlert("No se pudo cargar la interfaz grafica: " + name, 2);
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

        RepairController controller = this.fxmlLoader.getController();
        controller.init(this, wc.getAllInventory(user.getWorkplace().getIdWorkplace()));

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Repair Panel");

        this.stage.show();

    }

    protected void showUserPanel(Boolean update) throws DAOException {

        getFXML("User");

        AdminController ac = new AdminController();

        UserPanelController controller = this.fxmlLoader.getController();
        controller.init(this, update, ac.getWorkplaces(), ac.getPositions());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("User Panel");
        this.stage.show();
    }

    public void showAlert(String alertDesc, int type) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Alert.fxml"));
            Parent alert = (Parent) fxmlLoader.load();

            AlertController controller = this.fxmlLoader.getController();
            controller.init(alertDesc, type);

            Stage alertStage = new Stage();
            alertStage.setScene(new Scene(alert, 500, 250));
            alertStage.initStyle(StageStyle.UNDECORATED);
            alertStage.setTitle("");

            alertStage.show();

        } catch (IOException e) {
        }
    }

    public void showReplenishPanel() {

        getFXML("AbstractReplenish");

        AbstractReplenishController c = fxmlLoader.<AbstractReplenishController>getController();
        c.init(this, false, wc.getItems(), mc.getWorkplaces());

        this.stage.setScene(new Scene(parent, 600, 400));
        this.stage.setTitle("ReplenishPanel");
        this.stage.show();

    }

}
