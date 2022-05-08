package com.poa.POAvanzados.Model.RepairModel;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;

import lombok.Data;


@Data

public class Repair {


    private int idRepair;
    private int idLaboratory;
    private int idTechnician;
    private String reparationDate;
    private String repairDescription;
    private ArrayList<Item_Detail> itemDetails;

    public int getIdRepair() {
        return idRepair;
    }

    public void setIdRepair(int idRepair) {
        this.idRepair = idRepair;
    }

    public int getIdLaboratory() {
        return idLaboratory;
    }

    public void setIdLaboratory(int idLaboratory) {
        this.idLaboratory = idLaboratory;
    }

    public int getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(int idTechnician) {
        this.idTechnician = idTechnician;
    }

    public String getReparationDate() {
        return reparationDate;
    }

    public void setReparationDate(String reparationDate) {
        this.reparationDate = reparationDate;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public ArrayList<Item_Detail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<Item_Detail> itemDetails) {
        this.itemDetails = itemDetails;
    }

}
