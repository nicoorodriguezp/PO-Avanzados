package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;

@Data
public class Item {
    // Product Attributes

    private int idItem;
    private String name;
    private boolean critical;

    public Item(int idItem, String name, boolean critical) {
        this.idItem = idItem;
        this.name = name;
        this.critical = critical;
    }

    @Override
    public String toString() {
        return name;
    }

    public Item() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

}
