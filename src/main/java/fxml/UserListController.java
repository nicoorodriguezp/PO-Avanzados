package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserListController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;

    @FXML
    private Button newEmployeeButton;
    @FXML
    private Button changeEmployeeButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;

    // >>>>>>>>>>>>>>>>>>>>> TABLA DE USUARIOS <<<<<<<<<<<<<<<<<<<<<
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> idUserColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastnameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, Position> positionColumn;
    @FXML
    private TableColumn<User, Workplace> workplaceColumn;
    @FXML
    private TableColumn<User, Boolean> activeColumn;

    private ArrayList<User> users = new ArrayList<>();
    private ObservableList<User> usersInTable = FXCollections.observableArrayList();
    private User selectedUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getUsers();

        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        workplaceColumn.setCellValueFactory(new PropertyValueFactory<>("workplace"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            refreshUserTable();
        });

        refreshUserTable();

    }

    private void getUsers() {
        users.clear();
        AdminController ac = new AdminController();
        users.addAll(ac.getUsers());
    }

    void refreshUserTable() {
        usersInTable.clear();

        if (searchTF.getText().isEmpty()) {
            usersInTable.addAll(users);
            userTable.setItems(usersInTable);

        } else {

            for (User user : users) {
                if (user.getName().contains(searchTF.getText())) {
                    usersInTable.addAll(user);
                }
            }
            userTable.setItems(usersInTable);
        }

    }

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();

    }

    @FXML
    private void createUserPanel(ActionEvent event) throws DAOException {
        this.m.showUserPanel(false, null, this);

    }

    @FXML
    private void modifyUserPanel(ActionEvent event) throws DAOException {
        if (selectedUser != null) {
            this.m.showUserPanel(true, selectedUser, this);
        } else {
            this.m.showAlert("Debe elegir que usuario desea modificar.", 1);
        }

    }

    @FXML
    private void search(ActionEvent event) {

    }

    @FXML
    private void getSelected(MouseEvent event) {

        try {
            TablePosition pos = userTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            this.selectedUser = userTable.getItems().get(row);
        } catch (Exception e) {
            System.out.println("No hay nada seleccionado.");
        }

    }

    @FXML
    private void refreshData(MouseEvent event) {
        getUsers();
        refreshUserTable();
        this.m.showAlert("Se sincronizaron los datos con la Base de Datos correctamente.", 4);
        System.out.println("Se sincronizaron los datos con la base de datos.");
    }

}
