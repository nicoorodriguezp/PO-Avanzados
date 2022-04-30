package com.poa.POAvanzados.Model.ItemModel;

public class ItemBuilder {

    private Item_Detail item;

    public ItemBuilder() {
        item = new Item_Detail();
        item.setState(0);
    }

    public ItemBuilder addIdItem(int idItem) {
        item.setIdItem(idItem);
        return this;
    }

    public ItemBuilder addName(String name) {
        item.setName(name);
        return this;
    }

    public ItemBuilder isCritical(Boolean critical) {
        item.setCritical(critical);
        return this;
    }

    public ItemBuilder addWarehouse(int idWarehouse) {
        item.setIdWarehouse(idWarehouse);
        return this;
    }

    public ItemBuilder addLaboratory(int idLaboratory) {
        item.setIdLaboratory(idLaboratory);
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
        item.setState(state);
        return this;
    }

    public Item_Detail build() {
        return item;
    }

}
