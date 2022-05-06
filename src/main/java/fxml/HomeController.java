package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.DAO.DAOException;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Component
public class HomeController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    public Label userNameLabel;
    @FXML
    private Button newRepairButton;
    @FXML
    private Button replenishButton;
    @FXML
    private Button showInventaryButton;
    @FXML
    private Button generalReportButton;
    @FXML
    private Button warehouseReportButton;
    @FXML
    private Button itemWarehouseReportButton;
    private Button newEmployeeButton;
    private Button downEmployeeButton;
    private Button changeEmployeeButton;
    @FXML
    private ImageView workplaceIcon;
    @FXML
    private Button userListButton;

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

        if (m.user.getPosition().getPosition() != 0) {

            generalReportButton.setVisible(false);
            warehouseReportButton.setVisible(false);
            itemWarehouseReportButton.setVisible(false);
            newEmployeeButton.setVisible(false);
            downEmployeeButton.setVisible(false);
            changeEmployeeButton.setVisible(false);
        }

        if (m.user.getPosition().getPosition() == 2) {
            replenishButton.setVisible(false);
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
        m.showReportGenerator(0);
    }

    @FXML
    private void showWarehouseReportGenerator(ActionEvent event) {
        m.showReportGenerator(1);
    }

    @FXML
    private void showItemWarehouseReportGenerator(ActionEvent event) {
        m.showReportGenerator(2);
    }

    @FXML
    private void goToUserListPanel(ActionEvent event) throws DAOException {
        m.showUserListPanel();
    }

}
