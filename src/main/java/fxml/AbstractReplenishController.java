/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
public class AbstractReplenishController extends Controller implements Initializable {

    @FXML
    private Button rButton;
    @FXML
    private TextField quantityTF;
    @FXML
    private ComboBox<Item> itemsCB;
    @FXML
    private ComboBox<Workplace> workplaceCB;
    @FXML
    private Button backButton;

    private int itemSelected;
    private int selectedWorkplace;

    public void init(MainController m, boolean warehouse, ArrayList<Item> items, ArrayList<Workplace> workplaces) {

        this.m = m;

        itemsCB.setItems(FXCollections.observableArrayList(items));
        itemsCB.valueProperty().addListener(
                (observable, oldItem, newItem) -> itemSelected = newItem.getIdItem());

        if (m.user.getPosition().getPosition() == 0) {
            // Si es admin puede reponer cualquier workplace.
            workplaceCB.setItems(FXCollections.observableArrayList(workplaces));
        } else {
            // Si es gerente solo puede reponer su workplace.
            workplaceCB.setItems(FXCollections.observableArrayList(m.user.getWorkplace()));
        }

        workplaceCB.valueProperty().addListener(
                (observable, oldWorkplace, newWorkplace) -> {
                    selectedWorkplace = newWorkplace.getIdWorkplace();
                    setButtonAction(newWorkplace.isWarehouse());
                });

    }

    private void setButtonAction(boolean warehouse) {

        if (warehouse) {
            rButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Soy w");

                }
            });
        } else {
            rButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("Soy Labo");
                }
            });

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void replenish(ActionEvent event) {

    }

    @FXML
    private void goBack(ActionEvent event) {
        m.showHome();
    }

}
