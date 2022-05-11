package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Workplace_ItemRowMapper implements RowMapper<Workplace_Item> {


    @Override
    public Workplace_Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Workplace_Item workplace_item=new Workplace_Item();
        workplace_item.setIdWorkplace(resultSet.getInt("idWorkplace"));
        workplace_item.setIdItem(resultSet.getInt("idItem"));
        workplace_item.setStock(resultSet.getInt("stock"));
        workplace_item.setMax_slots(resultSet.getInt("max_slots"));
        return workplace_item;
    }
}
