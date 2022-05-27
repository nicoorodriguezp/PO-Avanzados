package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.ItemCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCountRowMapper implements RowMapper<ItemCount> {
    @Override
    public ItemCount mapRow(ResultSet resultSet, int i) throws SQLException {
        ItemCount itemCount= new ItemCount();
        itemCount.setIdItem(resultSet.getInt("idItem"));
        itemCount.setName(resultSet.getString("name"));
        itemCount.setCritical(resultSet.getBoolean("critical"));
        itemCount.setUsedCount(resultSet.getInt("usedCount"));
        itemCount.setDiscardedCount(resultSet.getInt("discardedCount"));
        return itemCount;
    }
}
