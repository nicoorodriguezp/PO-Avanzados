
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ItemListController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item, Integer> idItemColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Boolean> criticalColumn;
    @FXML
    private TableView<Item_Detail> itemDetailTable;
    @FXML
    private TableColumn<Item_Detail, Integer> idItemStockColumn;
    @FXML
    private TableColumn<Item_Detail, String> nameStockColumn;
    @FXML
    private TableColumn<Item_Detail, Boolean> criticalStockColumn;
    @FXML
    private TableColumn<Item_Detail, Integer> itemCodeColumn;
    @FXML
    private TableColumn<Item_Detail, String> checkInColumn;
    @FXML
    private TableColumn<Item_Detail, String> checkOutColumn;
    @FXML
    private TableColumn<Item_Detail, Workplace> warehouseColumn;
    @FXML
    private Button filterButton;
    @FXML
    private Button showAllButton;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private Label workplaceLabel;
    @FXML
    private Pane crudPane;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextField itemNameTF;
    @FXML
    private CheckBox criticalCheck;

    private ArrayList<Item> items =  new ArrayList<>();
    private ArrayList<Item_Detail> itemsStock =  new ArrayList<>();
    private ObservableList<Item_Detail> itemsStockInTable =  FXCollections.observableArrayList();
    private ObservableList<Item> itemsInTable =  FXCollections.observableArrayList();

    private Item selectedItem;


    public void init(MainController m, ArrayList<Item> items, ArrayList<Workplace> workplaces, ArrayList<Item_Detail> itemsStock){

        this.m = m;
        this.items = items;
        this.itemsStock = itemsStock;

        if(this.m.user.getPosition().getIdPosition() == AdminPosition){
            //Si es admin, mostrar las opciones de alta, modificacion y baja de items.
            crudPane.setVisible(true);

            workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        }else{
            workplaceCB.valueProperty().setValue(this.m.user.getWorkplace());
        }


        this.itemsStockInTable.setAll(itemsStock);
        this.itemsInTable.setAll(items);

        itemTable.setItems(itemsInTable);
        itemDetailTable.setItems(itemsStockInTable);



    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tabla de Items
        idItemColumn.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        criticalColumn.setCellValueFactory(new PropertyValueFactory<>("critical"));

        // Tabla de Stock
        idItemStockColumn.setCellValueFactory(new PropertyValueFactory<>("idItem"));
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("idItemCode"));
        nameStockColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        criticalStockColumn.setCellValueFactory(new PropertyValueFactory<>("critical"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        warehouseColumn.setCellValueFactory(new PropertyValueFactory<>("warehouse"));

    }

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

    @FXML
    private void getItemSelected(MouseEvent event) {

        try {
            TablePosition pos = itemTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            selectedItem  = itemTable.getItems().get(row);

            itemNameTF.setText(selectedItem.getName());
            criticalCheck.selectedProperty().set(selectedItem.isCritical());

            System.out.println("Se ha seleccionado el item: " + selectedItem.getName());

        } catch (Exception e) {
            System.out.println("No hay nada seleccionado.");
        }
    }

    @FXML
    private void filter(ActionEvent event) {

        // Filtra unicamente por el item seleccionado teniendo en cuenta el workplace del usuario.

        itemsStockInTable.clear();

        for (Item_Detail item : itemsStock) {
            if (item.getIdItem() == selectedItem.getIdItem()) {
                itemsStockInTable.addAll(item);
            }
        }


        itemDetailTable.setItems(itemsStockInTable);

    }

    @FXML
    private void showAll(ActionEvent event) {

        itemsStockInTable.clear();
        itemsStockInTable.addAll(itemsStock);
        itemDetailTable.setItems(itemsStockInTable);

    }

    @FXML
    private void createItem(ActionEvent event) {
        Item i = new Item();
        i.setName(itemNameTF.getText());
        i.setCritical(criticalCheck.selectedProperty().getValue());
//        this.m.ac.createItem(i);
    }

    @FXML
    private void updateItem(ActionEvent event) {

        selectedItem.setName(itemNameTF.getText());
        selectedItem.setCritical(criticalCheck.selectedProperty().getValue());
//        this.m.ac.updateItemList(selectedItem);
    }

    @FXML
    private void filterByWorkplace(ActionEvent event) {

        if(workplaceCB.valueProperty().getValue() != null){

            itemsStockInTable.clear();

            if(this.m.user.getPosition().getIdPosition() == AdminPosition){
                itemsStock = this.m.wc.getAllInventory(workplaceCB.valueProperty().getValue().getIdWorkplace());
            }

            System.out.println("Filtrando por workplace: " + workplaceCB.valueProperty().getValue() + " con id " + workplaceCB.valueProperty().getValue().getIdWorkplace());

            for (Item_Detail item : itemsStock) {
                if (item.getWarehouse().getIdWorkplace() ==  workplaceCB.valueProperty().getValue().getIdWorkplace()) {
                    itemsStockInTable.addAll(item);
                }
            }
            
            itemDetailTable.setItems(itemsStockInTable);

        }else{
            m.showAlert("Debe seleccionar un lugar de trabajo para filtrar.", 1);
        }
    }

    @FXML
    private void filterByWorkplaceAndItem(ActionEvent event) {

        if(workplaceCB.valueProperty().getValue() != null){

            itemsStockInTable.clear();

            if(this.m.user.getPosition().getIdPosition() == AdminPosition){
                itemsStock = this.m.wc.getAllInventory(workplaceCB.valueProperty().getValue().getIdWorkplace());
            }

            System.out.println("Filtrando por workplace e item: " + workplaceCB.valueProperty().getValue() + " con id " + workplaceCB.valueProperty().getValue().getIdWorkplace());

            for (Item_Detail item : itemsStock) {
                if (item.getWarehouse().getIdWorkplace() ==  workplaceCB.valueProperty().getValue().getIdWorkplace()
                        &&item.getIdItem() == selectedItem.getIdItem()) {
                    itemsStockInTable.addAll(item);
                }
            }

            itemDetailTable.setItems(itemsStockInTable);

        }else{
            m.showAlert("Debe seleccionar un lugar de trabajo para filtrar.", 1);
        }
    }

}
