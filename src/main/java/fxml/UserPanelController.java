
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserPanelController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField idUserTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private ComboBox<Position> positionCB;
    @FXML
    private Button saveButton;
    @FXML
    private Label labelUserUpdated;
    @FXML
    private Label labelUsernotUpdated;

    private boolean update;

    private int selectedWorkplace;
    private int selectedPosition;

    public void init(MainController m, Boolean update, ArrayList<Workplace> workplaces,
            ArrayList<Position> positions) {
        this.m = m;
        this.update = update;

        if (update) {
            if (m.user.getPosition().getPosition() != 1) {
                // Si no es un admin, entonces deshabilitar todo esto.
                searchTF.setVisible(false);
                searchButton.setVisible(false);
                searchButton.setVisible(false);
                workplaceCB.setEditable(false);
                // Cargar todos los datos de usuario por default.
            }
        }

        workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        workplaceCB.valueProperty().addListener(
                (observable, oldWorkplace, newWorkplace) -> selectedWorkplace = newWorkplace.getIdWorkplace());

        positionCB.setItems(FXCollections.observableArrayList(positions));
        positionCB.valueProperty().addListener(
                (observable, oldPosition, newPos) -> selectedPosition = newPos.getPosition());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void saveUser(ActionEvent event) {

        labelUsernotUpdated.setVisible(false);
        labelUserUpdated.setVisible(true);

        String msg = "Se agrego un " + positionCB.getSelectionModel().getSelectedItem() + " al workplace: "
                + selectedWorkplace;

        m.showAlert(msg, 1);
        System.out.println(msg);
    }

    @FXML
    private void goBack(ActionEvent event) {
        m.showHome();
    }

}
