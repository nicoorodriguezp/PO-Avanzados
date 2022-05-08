package fxml;

import java.io.IOException;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Controller.ManagerController;
import com.poa.POAvanzados.Controller.WorkerController;
import com.poa.POAvanzados.DAO.DAOException;
import com.poa.POAvanzados.Model.UserModel.User;

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

    protected AdminController ac = new AdminController();
    protected ManagerController mc = new ManagerController();
    protected WorkerController wc = new WorkerController();

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

    protected void showUserPanel(Boolean update, User selectedUser, UserListController ulc) throws DAOException {

        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/User.fxml"));
            Parent userParent = (Parent) fxmlLoader.load();

            AdminController ac = new AdminController();

            UserPanelController controller = this.fxmlLoader.getController();
            controller.init(this, update, ac.getWorkplaces(), ac.getPositions(), selectedUser, ulc);

            Stage userStage = new Stage();
            userStage.setScene(new Scene(userParent, 1263, 830));
            userStage.setTitle("User Panel");
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Hubo un problema al cargar la interfaz de usuarios.", 2);
        }
    }

    protected void showUserListPanel() throws DAOException {

        getFXML("UserList");

        UserListController controller = this.fxmlLoader.<UserListController>getController();
        controller.init(this);

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("User List Panel");

        this.stage.show();
    }

    /**
     * @param alertDesc : Contenido de la alerta. Mensaje a mostrar.
     * @param type      :(0) Alerta -- (1) Informacion -- (2) Error -- (3) Database
     *                  Error -- (4) Success
     */
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
        this.stage.setTitle("Panel de Reposicion");
        this.stage.show();

    }

    /** @param type :(0) General -- (1) Warehouse -- (2) Item-Deposito */
    public void showReportGenerator(int type) {

        getFXML("ReportGenerator");

        ReportGeneratorController c = fxmlLoader.getController();
        c.init(this, type, wc.getItems(), mc.getWorkplaces());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Generador de Reportes");
        this.stage.show();

    }
}
