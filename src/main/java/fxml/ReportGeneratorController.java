package fxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Exception.InvalidDateFormat;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.beans.property.SimpleObjectProperty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
import javafx.stage.FileChooser;
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
        this.userNameLabel.setText(m.user.toString());
        switch (type) {
            case 1:
                // Reporte General
                workplaceCB.setEditable(false);
                workplaceCB.setDisable(true);
                itemsCB.setEditable(false);
                itemsCB.setDisable(true);
                generateButton.setOnAction(e -> {
                    try {


                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getAllCheckOut(String.valueOf(
                            datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
                    setItemsTable();
                    }
                    catch (NullPointerException exception){
                        m.showAlert("Introduzca una fecha que corresponda con el formato dd/MM/yyyy", 1);
                    }
                });

                break;
            case 2:
                // Reporte de salidas de un deposito

                itemsCB.setEditable(false);
                itemsCB.setDisable(true);
                workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
                generateButton.setOnAction(e -> {
                    try {
                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getCheckOutWarehouse(workplaceCB.valueProperty().getValue()
                                    .getIdWorkplace(),
                            String.valueOf(
                                    datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
                    setItemsTable();
                    }
                    catch (NullPointerException exception){
                        m.showAlert("Introduzca una fecha que corresponda con el formato dd/MM/yyyy", 1);
                    }
                });
                break;
            case 3:
                workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
                itemsCB.setItems(FXCollections.observableArrayList(items));
                // Reportes de salidas de un item desde un deposito especifico
                generateButton.setOnAction(e -> {
                    try {
                    checkOutTable.setItems(null);
                    itemsCheckedOut.clear();
                    itemsCheckedOut.addAll(ac.getCheckOutItemWarehouse(workplaceCB.valueProperty().getValue()
                                    .getIdWorkplace(),
                            itemsCB.valueProperty().getValue().getIdItem(), String.valueOf(
                                    datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
                    setItemsTable();
                    }
                    catch (NullPointerException exception){
                        m.showAlert("Introduzca una fecha que corresponda con el formato dd/MM/yyyy", 1);
                    }
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

        idItemColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().getIdItem()));
        nameColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().getName()));
        criticalColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().isCritical()));

        itemCodeColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getIdItemCode()));
        warehouseColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getWarehouse().getAddress()));
        checkInColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getCheckIn()));
        checkOutColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getCheckOut()));
        laboratoryColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getLaboratory().getAddress()));
        stateColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getState().getName()));

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

        if (datePicker.getValue() != null && checkOutTable.getColumns().get(0).getText() != null) {
            String date = datePicker.getValue()
                    .format(DateTimeFormatter.ofPattern("dd_MM_yy"));

            String sheetName = "Reporte_Egresos_" + date;

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Reporte");
            fileChooser.setInitialFileName(sheetName);
            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {

                try (Workbook workbook = new HSSFWorkbook()) {
                    Sheet spreadsheet = workbook.createSheet(sheetName);

                    Row row = spreadsheet.createRow(0);

                    for (int j = 0; j < checkOutTable.getColumns().size(); j++) {
                        row.createCell(j).setCellValue(checkOutTable.getColumns().get(j).getText());
                    }

                    for (int i = 0; i < checkOutTable.getItems().size(); i++) {
                        row = spreadsheet.createRow(i + 1);
                        for (int j = 0; j < checkOutTable.getColumns().size(); j++) {
                            if (checkOutTable.getColumns().get(j).getCellData(i) != null) {
                                row.createCell(j)
                                        .setCellValue(checkOutTable.getColumns().get(j).getCellData(i).toString());
                            } else {
                                row.createCell(j).setCellValue("");
                            }
                        }
                    }

                    FileOutputStream fileOut = new FileOutputStream(selectedFile.getAbsoluteFile() + ".xls");
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (Exception e) {
                    this.m.showAlert("Hubo un problema al exportar el documento.", 2);
                }
                this.m.showAlert("Reporte de Egresos exportado correctamente.", 4);
            } else {
                this.m.showAlert("Debe seleccionar el directorio donde quiera exportar el documento.", 1);
            }
        } else {
            this.m.showAlert(
                    "La tabla no tiene contenido. Por favor seleccione una fecha que tenga despachos asociados.", 1);
        }

    }

}
