
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TableView<Item_Detail_Inventory> itemDetailTable;
    @FXML
    private TableColumn<Item_Detail_Inventory, Integer> idItemStockColumn;
    @FXML
    private TableColumn<Item_Detail_Inventory, String> nameStockColumn;
    @FXML
    private TableColumn<Item_Detail_Inventory, Boolean> criticalStockColumn;
    @FXML
    private TableColumn<Item_Detail_Inventory, Integer> itemCodeColumn;
    @FXML
    private TableColumn<Item_Detail_Inventory, String> checkInColumn;
    @FXML
    private TableColumn<Item_Detail_Inventory, String> checkOutColumn;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private Label workplaceLabel;
    @FXML
    private Pane crudPane;
    @FXML
    private TextField itemNameTF;
    @FXML
    private CheckBox criticalCheck;

    private ArrayList<Item> items =  new ArrayList<>();
    private ArrayList<Item_Detail_Inventory> itemsStock =  new ArrayList<>();
     // @itemStockInTable: Son los items que estan filtrados por algun parametro, listos para ser usados en las paginas.
    private ObservableList<Item_Detail_Inventory> itemsStockInTable =  FXCollections.observableArrayList();
    private ObservableList<Item> itemsInTable =  FXCollections.observableArrayList();

    private Item selectedItem;

    // Pagination
    private int lastPageIndex;
    private int maxPageIndex = 10;
    private int actualPage;

    private int from=-5;

    private ArrayList<Page> paginas = new ArrayList<>();
    @FXML
    private Label pageLabel;
    private Integer totalAmmount;

    private void setPaginas(){


        double cantMaxPerPagina = 5.0;
        maxPageIndex = (int) Math.ceil( itemsStockInTable.size() / cantMaxPerPagina);
        System.out.println("Se crearon " + maxPageIndex + " paginas.");

        paginas.clear();
        int pagina;
        lastPageIndex = 0;


        if(maxPageIndex!=0){
            for(pagina = 1; pagina<=maxPageIndex; pagina++){


                int index;
                for(index = lastPageIndex; index<lastPageIndex+4 && index<itemsStockInTable.size(); index++){}

                Page page = new Page(pagina, lastPageIndex, index);
                paginas.add(page);
                System.out.print("La pagina " + pagina + " tiene " + (page.getToItem() - page.getFromItem()) + " items.\n");
                System.out.println("El ultimo elemento es el: " +page.getToItem());


                lastPageIndex = index+1;
            }

            actualPage = -1;
            nextPage(null);
        }else{
            itemDetailTable.setItems(null);
            pageLabel.setText("Página " + (0) +"/" + maxPageIndex);
        }


    }


    public void init(MainController m, ArrayList<Item> items, ArrayList<Workplace> workplaces, ArrayList<Item_Detail_Inventory> itemsStock,Integer totalAmmount){
        this.totalAmmount= totalAmmount;
        this.m = m;
        this.items = items;
        this.itemsStock = itemsStock;
        this.userNameLabel.setText(m.user.toString());
        if(this.m.user.getPosition().getIdPosition() == AdminPosition){
            //Si es admin, mostrar las opciones de alta, modificacion y baja de items.
            crudPane.setVisible(true);

            workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        }else{
            crudPane.setVisible(false);
            workplaceCB.valueProperty().setValue(this.m.user.getWorkplace());
        }

        this.workplaceCB.valueProperty().setValue(this.m.user.getWorkplace());
        this.itemsStockInTable.setAll(itemsStock);
        this.itemsInTable.setAll(items);

        itemTable.setItems(itemsInTable);
//        itemDetailTable.setItems(itemsStockInTable);
        setPaginas();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tabla de Items
        idItemColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getIdItem()));
        nameColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getName()));
        criticalColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().isCritical()));

        // Tabla de Stock
        idItemStockColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().getIdItem()));
        itemCodeColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getIdItemCode()));
        nameStockColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().getName()));
        criticalStockColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getItem().isCritical()));
        checkInColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getCheckIn()));
        checkOutColumn.setCellValueFactory(itemDetail -> new SimpleObjectProperty(itemDetail.getValue().getCheckOut()));
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

        if(selectedItem!=null){
            itemsStockInTable.clear();

            for (Item_Detail_Inventory item : itemsStock) {
                if (item.getItem().getIdItem() == selectedItem.getIdItem()) {
                    itemsStockInTable.addAll(item);
                }
            }


            setPaginas();
        }else{
            m.showAlert("Debe seleccionar el item que desea filtrar.", 1);
        }


    }

    @FXML
    private void showAll(ActionEvent event) {

        itemsStockInTable.clear();
        itemsStockInTable.addAll(itemsStock);
        setPaginas();

    }

    @FXML
    private void createItem(ActionEvent event) {
        Item i = new Item();
        i.setName(itemNameTF.getText());
        i.setCritical(criticalCheck.selectedProperty().getValue());
        this.m.ac.addItem(i);
    }

    @FXML
    private void updateItem(ActionEvent event) {

        selectedItem.setName(itemNameTF.getText());
        selectedItem.setCritical(criticalCheck.selectedProperty().getValue());
        this.m.ac.updateItem(selectedItem);
    }

    @FXML
    private void filterByWorkplace(ActionEvent event) {

        if(workplaceCB.valueProperty().getValue() != null){
            this.from=-5;

            itemsStockInTable.clear();
            this.totalAmmount= this.m.wc.getAmmountItemsByWorkplace(workplaceCB.valueProperty().getValue());
            if(this.m.user.getPosition().getIdPosition() == AdminPosition){
                itemsStock = this.m.wc.getAllInventoryByWorkplace(workplaceCB.valueProperty().getValue(),from,5);
            }

            System.out.println("Filtrando por workplace " + workplaceCB.valueProperty().getValue() + " con id " + workplaceCB.valueProperty().getValue().getIdWorkplace());

            for (Item_Detail_Inventory item : itemsStock) {
                    itemsStockInTable.addAll(item);
            }
            
//            itemDetailTable.setItems(itemsStockInTable);
            setPaginas();

        }else{
            m.showAlert("Debe seleccionar un lugar de trabajo para filtrar.", 1);
        }
    }

    @FXML
    private void filterByWorkplaceAndItem(ActionEvent event) {

        if(workplaceCB.valueProperty().getValue() != null || selectedItem!=null){

            itemsStockInTable.clear();

            if(this.m.user.getPosition().getIdPosition() == AdminPosition){
                itemsStock = this.m.wc.getAllInventoryByWorkplace(workplaceCB.valueProperty().getValue());
            }

            System.out.println("Filtrando por workplace e item: " + workplaceCB.valueProperty().getValue() + " con id " + workplaceCB.valueProperty().getValue().getIdWorkplace());

            for (Item_Detail_Inventory item : itemsStock) {
                    itemsStockInTable.addAll(item);
            }

//            itemDetailTable.setItems(itemsStockInTable);
            setPaginas();

        }else{
            m.showAlert("Debe seleccionar un lugar de trabajo e item para filtrar.", 1);
        }
    }

    @FXML
    private void nextPage(MouseEvent event) {

        // Hago este auxiliar para trabajar con aquellos items que ya estan filtrados en el array "itemsStockInTable"
        System.out.println(from);
        if(from+5<=this.totalAmmount) {
            ObservableList<Item_Detail_Inventory> itemsAux = FXCollections.observableArrayList();
            from += 5;
            for (Item_Detail_Inventory item_detail_inventory : this.m.wc.getAllInventoryByWorkplace(this.workplaceCB.getValue(), from, 5)) {
                itemsAux.add(item_detail_inventory);
            }
            itemDetailTable.setItems(itemsAux);
        }
        else {
            System.out.println("Se encuentra en la ultima pagina.");
        }

        }


    @FXML
    private void formerPage(MouseEvent event) {
        System.out.println(from);
        if(this.from-5>=0) {
        ObservableList<Item_Detail_Inventory> itemsAux =  FXCollections.observableArrayList();

            this.from -= 5;
            for (Item_Detail_Inventory item_detail_inventory : this.m.wc.getAllInventoryByWorkplace(this.workplaceCB.getValue(), from, 5)) {
                itemsAux.add(item_detail_inventory);
            }
            itemDetailTable.setItems(itemsAux);
        }
        else {
            System.out.println("Se encuentra en la primera pagina.");
        }

    }


    @FXML
    private void deleteItem(ActionEvent event) {
    }

}
