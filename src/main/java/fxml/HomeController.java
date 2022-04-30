package fxml;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button generalReportButton;
    @FXML
    private Button warehouseReportButton;
    @FXML
    private Button itemWarehouseReportButton;
    @FXML
    private Button replenishButton;
    @FXML
    private Button showInventaryButton;
    @FXML
    private Button newEmployeeButton;
    @FXML
    private Button downEmployeeButton;
    @FXML
    private Button changeEmployeeButton;
    @FXML
    private ImageView workplaceIcon;

    public void init(MainController m) {
        this.m = m;
        this.userNameLabel.setText(m.user.toString());

        Boolean warehouse = false;

        if (warehouse) {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/warehouseIconBlack.png")));
        } else {
            this.workplaceIcon.setImage(
                    new Image(getClass().getResourceAsStream("/static/icons/laboratoryIconBlack.png")));
        }

    }

    @FXML
    private void showReparationPanel(ActionEvent event) {

        m.showRepairPanel();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
