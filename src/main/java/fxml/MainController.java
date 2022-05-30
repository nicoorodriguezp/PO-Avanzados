package fxml;

import java.io.IOException;

import com.poa.POAvanzados.controller.AdminController;
import com.poa.POAvanzados.controller.ManagerController;
import com.poa.POAvanzados.controller.WorkerController;
import com.poa.POAvanzados.exception.DAOException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.user_model.User;

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

    protected final AdminController ac = new AdminController();
    protected final ManagerController mc = new ManagerController();
    protected final WorkerController wc = new WorkerController();

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

        try {
            if(!user.getWorkplace().isWarehouse()) {
                getFXML("Repair");

                RepairController controller = this.fxmlLoader.getController();
                controller.init(this, wc.getAllInventoryByWorkplaceOnStock(user.getWorkplace(), user.getPosition().getIdPosition()));

                this.stage.setScene(new Scene(parent, 1263, 830));
                this.stage.setTitle("Repair Panel");

                this.stage.show();
            }
            else{
                throw new NotAllowedForWarehouse("No tiene los permisos para entrar al menu de reparaciones");
            }
        }
        catch (NotAllowedForWarehouse e){
            showAlert(e.getMessage(),2);
        }

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

            AlertController controller = this.fxmlLoader.<AlertController>getController();
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

        ReplenishController c = fxmlLoader.<ReplenishController>getController();
        c.init(this, false, wc.getItems(), mc.getWorkplaces());

        this.stage.setScene(new Scene(parent, 600, 400));
        this.stage.setTitle("Panel de Reposicion");
        this.stage.show();

    }

    /** @param type :(1) General -- (2) Warehouse -- (3) Item-Deposito */
    public void showReportGenerator(int type) {

        getFXML("ReportGenerator");

        ReportGeneratorController c = fxmlLoader.getController();
        c.init(this, type, wc.getItems(), mc.getWarehouse());

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Generador de Reportes");
        this.stage.show();

    }

    public void showInventory(){

        getFXML("ItemList");

        ItemListController c = fxmlLoader.<ItemListController>getController();
        c.init(this, wc.getItems(), mc.getWorkplaces(), wc.getAllInventoryByWorkplace(user.getWorkplace()));

        this.stage.setScene(new Scene(parent, 1263, 830));
        this.stage.setTitle("Lista de items");
        this.stage.show();

    }

    public void showItemUsageReport() {

        try {
            if (!user.getWorkplace().isWarehouse()) {
                getFXML("ItemListReport");

                ItemListReportController c = fxmlLoader.<ItemListReportController>getController();
                c.init(this, mc.getItemCountByWorkplace(user.getWorkplace()), mc.getWorkplaces());

                this.stage.setScene(new Scene(parent, 1263, 830));
                this.stage.setTitle("Lista de items");
                this.stage.show();
            } else {
                throw new NotAllowedForWarehouse("No tiene los permisos para entrar a esta opcion");
            }

        }
        catch (NotAllowedForWarehouse e){
            showAlert(e.getMessage(),2);
        }

    }
}
