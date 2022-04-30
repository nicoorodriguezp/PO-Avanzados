package com.poa.POAvanzados.Model.RepairModel;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;

public class RepairBuilder {

    private Repair repair;

    public RepairBuilder() {
        repair = new Repair();
    }

    public RepairBuilder addIdRepair(int idRepair) {
        repair.setIdRepair(idRepair);
        return this;
    }

    public RepairBuilder addLaboratory(int idLaboratory) {
        repair.setIdLaboratory(idLaboratory);
        return this;
    }

    public RepairBuilder addTechnician(int idTechnician) {
        repair.setIdTechnician(idTechnician);
        return this;
    }

    public RepairBuilder addReparationDate(String reparationDate) {
        repair.setReparationDate(reparationDate);
        return this;
    }

    public RepairBuilder addReparationDesc(String repairDescription) {
        repair.setRepairDescription(repairDescription);
        return this;
    }

    public RepairBuilder addItems(ArrayList<Item_Detail> itemDetails) {
        repair.setItemDetails(itemDetails);
        return this;
    }

    public Repair build() {
        return repair;
    }

}
