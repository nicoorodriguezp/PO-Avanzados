/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Controller.AdminController;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ngrp
 */
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

    private ArrayList<Item_Detail> itemsCheckedOut = new ArrayList<>();
    private int itemSelected;

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
                    itemsCheckedOut = ac.getAllCheckOut(String.valueOf(datePicker.getValue()));
                    generateReport();
                });

                break;
            case 1:
                // Reporte de salidas de un deposito
                itemsCB.setEditable(false);
                itemsCB.setDisable(true);
                generateButton.setOnAction(e -> {
                    itemsCheckedOut = ac.getCheckOutWarehouse(workplaceCB.valueProperty().getValue()
                            .getIdWorkplace(), String.valueOf(datePicker.getValue()));
                    generateReport();
                });
                break;
            case 2:
                // Reportes de salidas de un item desde un deposito especifico
                generateButton.setOnAction(e -> {
                    itemsCheckedOut = ac.getCheckOutItemWarehouse(workplaceCB.valueProperty().getValue()
                            .getIdWorkplace(),
                            itemsCB.valueProperty().getValue().getIdItem(), String.valueOf(datePicker.getValue()));
                    generateReport();
                });

                break;

            default:
                break;
        }

    }

    private void generateReport() {

        System.out.println(datePicker.getValue());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancel(ActionEvent event) {

        m.showHome();
    }

}
