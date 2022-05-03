package fxml;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class ReportGeneratorController extends Controller implements Initializable {

    @FXML
    private ImageView dateIcon;
    @FXML
    private DatePicker datePicker = new DatePicker(LocalDate.now());;
    @FXML
    private ComboBox<Item> itemsCB;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private Button generateButton;
    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TableView<Item_Detail> checkOutTable;
    @FXML
    private Button generateExcel;
    @FXML
    private TableColumn<Item_Detail, Integer> idItemColumn;
    @FXML
    private TableColumn<Item_Detail, String> nameColumn;
    @FXML
    private TableColumn<Item_Detail, Boolean> criticalColumn;
    @FXML
    private TableColumn<Item_Detail, Integer> itemCodeColumn;
    @FXML
    private TableColumn<Item_Detail, Integer> warehouseColumn;
    @FXML
    private TableColumn<Item_Detail, String> checkInColumn;
    @FXML
    private TableColumn<Item_Detail, String> checkOutColumn;
    @FXML
    private TableColumn<Item_Detail, Integer> laboratoryColumn;
    @FXML
    private TableColumn<Item_Detail, Integer> stateColumn;

    ObservableList<Item_Detail> itemsCheckedOut = FXCollections.observableArrayList();

    public void init(MainController m, int type, ArrayList<Item> items, ArrayList<Workplace> workplaces) {

        this.m = m;
        AdminController ac = new AdminController();

        switch (type) {
            case 0:
                // Reporte General
                workplaceCB.setEditable(false);
                workplaceCB.setDisable(true);
                itemsCB.setEditable(false);
                itemsCB.setDisable(true);
                generateButton.setOnAction(e -> {
                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getAllCheckOut(String.valueOf(
                            datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy")))));
                    setItemsTable();
                });

                break;
            case 1:
                // Reporte de salidas de un deposito
                itemsCB.setEditable(false);
                itemsCB.setDisable(true);
                generateButton.setOnAction(e -> {
                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getCheckOutWarehouse(workplaceCB.valueProperty().getValue()
                            .getIdWorkplace(),
                            String.valueOf(
                                    datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy")))));
                    setItemsTable();
                });
                break;
            case 2:
                // Reportes de salidas de un item desde un deposito especifico
                generateButton.setOnAction(e -> {
                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getCheckOutItemWarehouse(workplaceCB.valueProperty().getValue()
                            .getIdWorkplace(),
                            itemsCB.valueProperty().getValue().getIdItem(), String.valueOf(
                                    datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy")))));
                    setItemsTable();
                });

                break;

            default:
                break;
        }

    }

    private void setItemsTable() {

        if (!itemsCheckedOut.isEmpty()) {
            checkOutTable.setItems(itemsCheckedOut);
        } else {
            m.showAlert("No hubo despachos en el dia seleccionado.", 1);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        criticalColumn.setCellValueFactory(new PropertyValueFactory<>("critical"));

        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("idItemCode"));
        warehouseColumn.setCellValueFactory(new PropertyValueFactory<>("idWarehouse"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        laboratoryColumn.setCellValueFactory(new PropertyValueFactory<>("idLaboratory"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        datePicker.setConverter(new StringConverter<LocalDate>() {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

    }

    @FXML
    private void goBack(ActionEvent event) {
        m.showHome();
    }

    @FXML
    private void generateExcel(ActionEvent event) throws IOException {

        System.out.println("Creando excel del dia: " + datePicker.getValue()
                .format(DateTimeFormatter.ofPattern("dd/MM/yy")));

    }

}
