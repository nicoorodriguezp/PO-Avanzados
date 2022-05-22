package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.ItemCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCountRowMapper implements RowMapper<ItemCount> {
    @Override
    public ItemCount mapRow(ResultSet resultSet, int i) throws SQLException {
        ItemCount item = new ItemCount();
        item.setIdItem(resultSet.getInt("idItem"));
        item.setCritical(resultSet.getBoolean("critical"));
        item.setName(resultSet.getString("name"));
        item.setUsedCount(resultSet.getInt("usedCount"));
        item.setDiscardedCount(resultSet.getInt("discardedCount"));

        return item;
    }
}
