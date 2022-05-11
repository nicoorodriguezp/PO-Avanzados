package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.RepairModel.RepairBuilder;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

@Component
public class RepairController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button newRepairButton;
    @FXML
    private TextField searchTF;
    @FXML
    private Button searchButton;
    @FXML
    private VBox listRepair;
    @FXML
    private VBox listSearch;
    @FXML
    private Button addItemButton;

    // Search attributes
    private ArrayList<Item_Detail> itemsLaboratory;
    private SearchItemController itemSearchSelected;

    // Repair attributes
    protected ArrayList<Item_Detail> itemsListRepair = new ArrayList<>();

    public void setItemSearchSelected(SearchItemController itemSearchSelected) {
        this.itemSearchSelected = itemSearchSelected;
    }

    public void init(MainController m, ArrayList<Item_Detail> itemsLaboratory) {
        this.m = m;
        this.itemsLaboratory = itemsLaboratory;
        this.userNameLabel.setText(m.user.toString());
        refreshSearchList();

        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshSearchList();
        });
    }

    /*
     * >>>>>>>>>>>>>>>>>>>>>>>>> REPAIR LIST METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */

    protected void refreshRepairList() {

        try {
            listRepair.getChildren().clear();
        } catch (Exception e) {
            System.out.println("No se utilizaron items en esta reparacion todavia, la lista ya esta vacia: " + e);
        }

        System.out.println("\n\n\nRefrescando lista de items utilizados en la reparacion.");
        try {
            for (int i = 0; i < itemsListRepair.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RepairItem.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                RepairItemController controller = fxmlLoader.<RepairItemController>getController();
                controller.init(itemsListRepair.get(i), this);

                listRepair.getChildren().add((Node) root);

            }
        } catch (IOException ex) {
            m.showAlert("Hubo un problema al intentar refrescar la lista de reparacion.", 2);
            System.out.println("\n\nEl problema fue: " + ex);
        }

    }// fin refreshRepairList()

    @FXML
    private void addItemToRepairList(ActionEvent event) {

        if (itemSearchSelected != null) {

            boolean exist = false;

            for (Item_Detail i : itemsListRepair) {
                if (i == itemSearchSelected.getItem()){
                    exist = true;
                    System.out.println("Ya existe en la lista.");
                }
            }

            if (!exist){
                changeItemFromRepairListStatus(itemSearchSelected.getItem(), ItemInUse);

                System.out
                        .println("\nSe agrego a la lista de reparacion el item: " + itemSearchSelected.getItem().getItem().getName());
            }


        } else {
            m.showAlert("Debe seleccionar un item de la busqueda.", 0);
            System.out.println("\n\nDebe seleccionar un item de la busqueda.");
        }

    }

    public void changeItemFromRepairListStatus(Item_Detail i, int state) {

        if (state == ItemInStock) {

            // En caso de que se quiera sacar de la lista de reparacion.
            itemsListRepair.remove(itemsListRepair.indexOf(i));
            i.getState().setIdState(state);

        } else if (state == ItemInUse) {

            // En caso de que el item se quiera agregar a la lista de reparacion.
            i.getState().setIdState(state);
            itemsListRepair.add(i);

        } else if (state == ItemDescarded) {

            if (i.getState().getIdState() == ItemDescarded) {
                i.getState().setIdState(ItemInUse); // Si ya estaba marcado como descartado, entonces se volvio al estado en uso
            } else {
                i.getState().setIdState(state);
            }
            // En caso de que se quiera mantener pero cambiar el estado a descartado.

            System.out.println(itemsListRepair.get(itemsListRepair.indexOf(i)));

        }

        refreshRepairList();
        refreshSearchList();
    }

    /*
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>> SEARCH METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     */

    @FXML
    private void search(ActionEvent event) {
        refreshSearchList();
        checkStatusSearch();
    }

    protected void refreshSearchList() {

        try {
            listSearch.getChildren().clear();
        } catch (Exception e) {
            System.out.println("La lista ya estaba vacia: " + e);
        }

        System.out.println("\n\n\nRefrescando lista de items.");

        for (int i = 0; i < itemsLaboratory.size(); i++) {

            // verifico si esta en estado de stock
            if (searchTF.getText() == "" && itemsLaboratory.get(i).getState().getIdState() == ItemInStock) {
                addSearchItem(i);

            } else if (itemsLaboratory.get(i).getItem().getName().contains(
                    searchTF.getText()) && itemsLaboratory.get(i).getState().getIdState() == ItemInStock) {
                addSearchItem(i);
            }

        }

    }// fin refreshSearchList()

    private void checkStatusSearch() {

        if (listSearch.getChildren().isEmpty()) {

            m.showAlert("No se encontraron items que coincidan.", 1);
            System.out.println("No se encontraron items que coincidan.");

        }

    }

    private void addSearchItem(int i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SearchItem.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            SearchItemController controller = fxmlLoader.<SearchItemController>getController();
            controller.init(itemsLaboratory.get(i), this);

            listSearch.getChildren().add((Node) root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void createRepair(ActionEvent event) {

        for (Item_Detail i : itemsListRepair) {
            if (i.getState().getIdState() != ItemDescarded) {
                i.getState().setIdState(ItemInUse);
            }
            if (i.getItem().isCritical()) {
                checkStockStatus();
            }

        }
        Repair repair = new RepairBuilder()
                .addItems(itemsListRepair)
                .addLaboratory(m.user.getWorkplace().getIdWorkplace())
                .addReparationDate(getDate())
                .addReparationDesc("Just a repair.")
                .addTechnician(m.user.getIdUser())
                .build();

        m.wc.createRepair(repair);

        // upload the repair to db

        m.showAlert("Se dio de alta la reparacion", 1);
    }

    private void checkStockStatus() {
        /*
         * Obtener el stock restante y si es menos que el soportado, entonces enviar
         * email al gerente del laboratorio.
         * 
         * Lo ideal seria guardar en un array el idItem y la cantidad asociada a ese
         * idItem que se agregaron a la reparacion.
         * Entonces depsues este check se hace desde ese array.
         */
    }

    @FXML
    private void goBack(ActionEvent event) {
        this.m.showHome();
    }

}
