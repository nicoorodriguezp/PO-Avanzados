package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.item_model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setIdItem(resultSet.getInt("idItem"));
        item.setCritical(resultSet.getBoolean("critical"));
        item.setName(resultSet.getString("name"));
        return item;
    }
}
