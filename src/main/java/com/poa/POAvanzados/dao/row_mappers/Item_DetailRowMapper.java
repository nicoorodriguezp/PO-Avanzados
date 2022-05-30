package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.ItemState;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item_DetailRowMapper implements RowMapper<Item_Detail> {
    @Override
    public Item_Detail mapRow(ResultSet resultSet, int i) throws SQLException {
        Item_Detail item_detail= new Item_Detail();
        item_detail.setIdItemCode(resultSet.getInt("idItemCode"));
        item_detail.setCheckIn(resultSet.getString("check_in"));
        item_detail.setCheckOut(resultSet.getString("check_out"));
        item_detail.setState(new ItemState(resultSet.getInt("idState"),resultSet.getString("state_description")));
        item_detail.setLaboratory(new Workplace(resultSet.getInt("idLaboratory"),resultSet.getBoolean("warehouse"),resultSet.getInt("idManager"),resultSet.getString("addressLaboratorio")));
        item_detail.setWarehouse(new Workplace(resultSet.getInt("idWarehouse"),resultSet.getBoolean("warehouse"),resultSet.getInt("idManager"),resultSet.getString("addressDeposito")));
        item_detail.setItem(new Item(resultSet.getInt("idItem"),resultSet.getString("name"),resultSet.getBoolean("critical")));
        return item_detail;

    }
}
