/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.ItemCount;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail_Inventory;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ItemListReportController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Pane adminPane;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private Label workplaceLabel;
    @FXML
    private TableView<ItemCount> itemTable;
    @FXML
    private TableColumn<ItemCount, Integer> idItemColumn;
    @FXML
    private TableColumn<ItemCount, String> nameColumn;
    @FXML
    private TableColumn<ItemCount, Boolean> criticalColumn;
    @FXML
    private TableColumn<ItemCount, Integer> usedColumn;
    @FXML
    private TableColumn<ItemCount, Item> discardedColumn;


    private ArrayList<ItemCount> items = new ArrayList<>();
    private ObservableList<ItemCount> itemsInTable = FXCollections.observableArrayList();

    private Item selectedItem;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tabla de Items
        idItemColumn.setCellValueFactory(itemCount -> new SimpleObjectProperty(itemCount.getValue().getIdItem()));
        nameColumn.setCellValueFactory(itemCount -> new SimpleObjectProperty(itemCount.getValue().getName()));
        criticalColumn.setCellValueFactory(itemCount -> new SimpleObjectProperty(itemCount.getValue().isCritical()));
        usedColumn.setCellValueFactory(itemCount -> new SimpleObjectProperty(itemCount.getValue().getUsedCount()));
        discardedColumn.setCellValueFactory(itemCount -> new SimpleObjectProperty(itemCount.getValue().getDiscardedCount()));

    }

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

    @FXML
    private void filterByWorkplace(ActionEvent event) {

        if (workplaceCB.valueProperty().getValue() != null) {


            itemsInTable.clear();

            if (this.m.user.getPosition().getIdPosition() == AdminPosition) {
                items = this.m.mc.getItemCountByWorkplace(workplaceCB.valueProperty().getValue());
            }

            System.out.println("Filtrando por workplace " + workplaceCB.valueProperty().getValue() + " con id " + workplaceCB.valueProperty().getValue().getIdWorkplace());

            for (ItemCount item : items) {
                itemsInTable.addAll(item);
            }

//            this.itemsInTable.setAll(items);
            itemTable.setItems(itemsInTable);


        } else {
            m.showAlert("Debe seleccionar un lugar de trabajo para filtrar.", 1);
        }
    }

    @FXML
    private void exportToExcel(ActionEvent event) {

        String date = getDate();
        String sheetName = "Reporte_Uso_Descarte_Items" + date;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.setInitialFileName(sheetName);
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {

            try (Workbook workbook = new HSSFWorkbook()) {
                Sheet spreadsheet = workbook.createSheet(sheetName);

                Row row = spreadsheet.createRow(0);

                for (int j = 0; j < itemTable.getColumns().size(); j++) {
                    row.createCell(j).setCellValue(itemTable.getColumns().get(j).getText());
                }

                for (int i = 0; i < itemTable.getItems().size(); i++) {
                    row = spreadsheet.createRow(i + 1);
                    for (int j = 0; j < itemTable.getColumns().size(); j++) {
                        if (itemTable.getColumns().get(j).getCellData(i) != null) {
                            row.createCell(j)
                                    .setCellValue(itemTable.getColumns().get(j).getCellData(i).toString());
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

            this.m.showAlert("Reporte de Uso/Descarte exportado correctamente.", 4);

        } else {
            this.m.showAlert("Debe seleccionar el directorio donde quiera exportar el documento.", 1);
        }

    }

    public void init(MainController mainController, ArrayList<ItemCount> items, ArrayList<Workplace> workplaces) {
    this.m = mainController;
    this.items = items;
            if (this.m.user.getPosition().getIdPosition() == AdminPosition) {
                //Si es admin, mostrar las opciones de alta, modificacion y baja de items.
                adminPane.setVisible(true);
                workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
            } else {
                adminPane.setVisible(false);
                workplaceCB.valueProperty().setValue(this.m.user.getWorkplace());
            }
    }
}
