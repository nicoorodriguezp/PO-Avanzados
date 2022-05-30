
package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.model.item_model.Item_Detail;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

@Component
public class SearchItemController implements Initializable {

    @FXML
    private Label itemName;
    @FXML
    private Label idItem;

    private Item_Detail item;
    private RepairController repairController;

    public void init(Item_Detail item, RepairController repairController) {
        this.item = item;
        this.repairController = repairController;

        this.itemName.setText(item.getItem().getName());

        this.idItem.setText(String.valueOf(item.getIdItemCode()));

    }

    public Item_Detail getItem() {
        return this.item;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void getSelected(MouseEvent event) {

        System.out.println(this.itemName);
        repairController.setItemSearchSelected(this);
    }

}
