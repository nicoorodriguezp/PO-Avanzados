package com.poa.POAvanzados.Model.ItemModel;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class ItemBuilder {

    private Item_Detail item;

    public ItemBuilder() {
        item = new Item_Detail();
        item.setState(new ItemState(1,"En Stock"));
    }

    public ItemBuilder addIdItem(int idItem) {
        item.setIdItemCode(idItem);
        return this;
    }

    public ItemBuilder addName(String name) {
        item.getItem().setName(name);
        return this;
    }

    public ItemBuilder isCritical(Boolean critical) {
        item.getItem().setCritical(critical);
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
        item.getState().setIdState(state);
        return this;
    }

    public Item_Detail build() {
        return item;
    }

}
