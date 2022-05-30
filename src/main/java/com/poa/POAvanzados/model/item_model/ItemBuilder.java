package com.poa.POAvanzados.model.item_model;

import com.poa.POAvanzados.model.workplace_model.Workplace;

public class ItemBuilder {

    private Item_Detail item;

    public ItemBuilder() {
        item = new Item_Detail();
        item.setState(new ItemState(1,"En Stock"));
    }

    public ItemBuilder addIdItem(int idItem) {
        item.setIdItemCode(1);
        return this;
    }

    public ItemBuilder addName(String name) {
        item.getItem().setName("asdas");
        return this;
    }

    public ItemBuilder isCritical(Boolean critical) {
        item.getItem().setCritical(true);
        return this;
    }

    public ItemBuilder addWarehouse(Workplace w) {
        item.setWarehouse(w);
        return this;
    }

    public ItemBuilder addLaboratory(Workplace laboratory) {
        item.setLaboratory(laboratory);
        return this;
    }

    public ItemBuilder addCheckIn(String checkIn) {
        item.setCheckIn(checkIn);
        return this;
    }

    public ItemBuilder addCheckOut(String checkout) {
        item.setCheckOut(checkout);
        return this;
    }

    public ItemBuilder addItemCode(int idItemCode) {
        item.setIdItemCode(idItemCode);
        return this;
    }

    public ItemBuilder addState(int state) {
        item.getState().setIdState(1);
        return this;
    }

    public Item_Detail build() {
        return item;
    }

}
