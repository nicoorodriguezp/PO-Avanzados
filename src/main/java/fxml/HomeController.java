package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Exception.DAOException;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

@Component
public class HomeController extends Controller implements Initializable {

    @FXML
    public Label userNameLabel;
    @FXML
    private Button replenishButton;

    @FXML
    private ImageView workplaceIcon;
    @FXML
    private Pane adminPane;
    @FXML
    private Pane managerPane;
    @FXML
    private ImageView icon;

    public void init(MainController m) {
        this.m = m;
        this.userNameLabel.setText(m.user.toString());

        if (m.user.getWorkplace().isWarehouse()) {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/warehouseIconBlack.png")));
        } else {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/laboratoryIconBlack.png")));
        }

        initButtons();

    }

    private void initButtons() {


        System.out.println("Posicion del usuario" + m.user.getPosition().getIdPosition());
        if (m.user.getPosition().getIdPosition() != AdminPosition) {
            adminPane.setVisible(false);

            if (m.user.getPosition().getIdPosition() != ManagerPosition) {
                this.managerPane.setVisible(false);
                System.out.println("It's a worker.");
                icon.setVisible(true);
            }

        }
    }

    @FXML
    private void showReparationPanel(ActionEvent event) {
        m.showRepairPanel();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private void replenish(ActionEvent event) {
        m.showReplenishPanel();
    }

    @FXML
    private void showGeneralReportGenerator(ActionEvent event) {
        m.showReportGenerator(1);
    }

    @FXML
    private void showWarehouseReportGenerator(ActionEvent event) {
        m.showReportGenerator(2);
    }

    @FXML
    private void showItemWarehouseReportGenerator(ActionEvent event) {
        m.showReportGenerator(3);
    }

    @FXML
    private void goToUserListPanel(ActionEvent event) throws DAOException {
        m.showUserListPanel();
    }

    @FXML
    private void showInventory(ActionEvent event) {
        this.m.showInventory();
    }

    @FXML
    private void showLogin(ActionEvent event) {
        this.m.showLogin("Sistema de Control de Insumos");
    }

    @FXML
    private void showItemUsageReport(ActionEvent event) {
        this.m.showItemUsageReport();
    }

}
