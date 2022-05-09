
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    @FXML
    private CheckBox activeCheck;
    @FXML
    private Label activeLabel;
    @FXML
    private ImageView iconAlert;

    private boolean update;

    private User selectedUser;

    private UserListController ulc;

    public void init(MainController m, Boolean update, ArrayList<Workplace> workplaces,
                     ArrayList<Position> positions, User selectedUser, UserListController ulc) {
        this.m = m;
        this.ulc = ulc;
        this.update = update;
        this.selectedUser = selectedUser;

        if (update) {
            if (m.user.getPosition().getIdPosition() != 1) {
                // Si no es un admin, entonces deshabilitar todo esto.
                workplaceCB.setEditable(false);
                positionCB.setEditable(false);
                activeCheck.disableProperty().set(true);
            }
        }

        workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        // workplaceCB.valueProperty().addListener(
        // (observable, oldWorkplace, newWorkplace) -> selectedWorkplace =
        // newWorkplace.getIdWorkplace());

        positionCB.setItems(FXCollections.observableArrayList(positions));
        // positionCB.valueProperty().addListener(
        // (observable, oldPosition, newPos) -> selectedPosition =
        // newPos.getPosition());

        // Cargo los datos del usuario seleccionado.
        if (this.selectedUser != null) {
            idUserTF.setText(String.valueOf(selectedUser.getIdUser()));
            usernameTF.setText(selectedUser.getName());
            lastnameTF.setText(selectedUser.getLastName());
            emailTF.setText(selectedUser.getEmail());
            passwordTF.setText(selectedUser.getPassword());
            workplaceCB.valueProperty().setValue(selectedUser.getWorkplace());
            positionCB.valueProperty().setValue(selectedUser.getPosition());
            activeCheck.selectedProperty().set(selectedUser.getActive());
        }

        // Fuerzo a que el contenido del tf sea solo numerico.
        idUserTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    idUserTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void saveUser(ActionEvent event) {

        if (checkUserData()) {

            this.selectedUser.setIdUser(Integer.valueOf(idUserTF.getText()));
            this.selectedUser.setName(usernameTF.getText());
            this.selectedUser.setLastName(lastnameTF.getText());
            this.selectedUser.setActive(activeCheck.selectedProperty().getValue());
            this.selectedUser.setEmail(emailTF.getText());
            this.selectedUser.setPosition(positionCB.valueProperty().getValue());
            this.selectedUser.setWorkplace(workplaceCB.valueProperty().getValue());
            this.selectedUser.setPassword(passwordTF.getText());

            if (!update) {
                m.ac.createUser(this.selectedUser);
                m.showAlert("Se agrego un " + positionCB.getSelectionModel().getSelectedItem() + " al workplace: "
                        + workplaceCB.valueProperty().getValue(), 1);
            } else {
                m.ac.updateUser(this.selectedUser);
                m.showAlert("Se actualizaron los datos del usuario: " + selectedUser.getName(), 4);
            }

            labelUsernotUpdated.setVisible(false);
            labelUserUpdated.setVisible(true);

            ulc.refreshUserTable();
        }

    }

    private boolean checkUserData() {

        if (idUserTF.getText().isEmpty()) {
            m.showAlert("Debe introducir la identificación del usuario.", 1);
            return false;
        } else if (usernameTF.getText().isEmpty()) {
            m.showAlert("El nombre del usuario está vacío.", 1);
            return false;
        } else if (lastnameTF.getText().isEmpty()) {
            m.showAlert("El apellido del usuario está vacío.", 1);
            return false;
        } else if (emailTF.getText().isEmpty()) {
            m.showAlert("El email del usuario está vacío.", 1);
            return false;
        } else if (passwordTF.getText().isEmpty()) {
            m.showAlert("La contraseña del usuario está vacía.", 1);
            return false;
        } else if (workplaceCB.valueProperty().getValue() == null) {
            m.showAlert("Debe seleccionar el lugar de trabajo del usuario.", 1);
            return false;
        } else if (positionCB.valueProperty().getValue() == null) {
            m.showAlert("Debe seleccionar el puesto de trabajo del usuario.", 1);
            return false;
        }

        return true;

    }

    @FXML
    private void goBack(ActionEvent event) {
        Stage stageClose = (Stage) iconAlert.getScene().getWindow();
        stageClose.close();
    }

}
