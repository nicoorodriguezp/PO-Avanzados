package Model.RepairModel;

import java.sql.Date;
import java.util.ArrayList;

import Model.ItemModel.Item;

public class Repair {

    private int idRepair;
    private int idLaboratory;
    private int idTechnician;
    private Date reparationDate;
    private String repairDescription;
    private ArrayList<Item> items;

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

    public Date getReparationDate() {
        return reparationDate;
    }

    public void setReparationDate(Date reparationDate) {
        this.reparationDate = reparationDate;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

}
