package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;

import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

@Component
public class RepairItemController extends Controller implements Initializable {

    @FXML
    private Label itemName;
    @FXML
    private CheckBox descardedCheck;
    @FXML
    private Button removeButton;
    @FXML
    private Label idItem;

    private Item_Detail item;
    private RepairController repairController;

    public void init(Item_Detail item_Detail, RepairController repairController) {

        this.item = item_Detail;
        this.repairController = repairController;

        idItem.setText(String.valueOf(item.getIdItemCode()));
        itemName.setText(item.getName());

        if (item.getState() == ItemDescarded) {
            descardedCheck.selectedProperty().set(true);
        } else {
            descardedCheck.selectedProperty().set(false);
        }

        descardedCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                repairController.changeItemFromRepairListStatus(item, ItemDescarded);
            }
        });
    }

    @FXML
    private void removeItem(ActionEvent event) {
        repairController.changeItemFromRepairListStatus(item, ItemInStock); // back to: "in stock"
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
