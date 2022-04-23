package com.poa.POAvanzados.Model.RepairModel;

import java.sql.Date;
import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Repair {

    private int idRepair;
    private int idLaboratory;
    private int idTechnician;
    private Date reparationDate;
    private String repairDescription;
    private ArrayList<Item_Detail> itemDetails;

}
