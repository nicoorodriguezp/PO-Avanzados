package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.ItemState;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail_Inventory;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item_DetailInventoryRowMapper implements RowMapper<Item_Detail_Inventory> {
    @Override
    public Item_Detail_Inventory mapRow(ResultSet resultSet, int i) throws SQLException {
        Item_Detail_Inventory item_detail= new Item_Detail_Inventory();
        item_detail.setIdItemCode(resultSet.getInt("idItemCode"));
        item_detail.setCheckIn(resultSet.getString("check_in"));
        item_detail.setCheckOut(resultSet.getString("check_out"));
        item_detail.setState(new ItemState(resultSet.getInt("idState"),resultSet.getString("state_description")));
        item_detail.setItem(new Item(resultSet.getInt("idItem"),resultSet.getString("name"),resultSet.getBoolean("critical")));
        return item_detail;

    }
}
