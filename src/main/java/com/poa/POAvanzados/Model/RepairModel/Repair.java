package com.poa.POAvanzados.Model.RepairModel;

import java.sql.Date;
import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.Item;
import lombok.Data;

@Data
public class Repair {

    private int idRepair;
    private int idLaboratory;
    private int idTechnician;
    private Date reparationDate;
    private String repairDescription;
    private ArrayList<Item> items;


}
