package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.workplace_model.Workplace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkplaceRowMapper implements RowMapper<Workplace> {


    @Override
    public Workplace mapRow(ResultSet resultSet, int i) throws SQLException {
        Workplace workplace= new Workplace();
        workplace.setIdWorkplace(resultSet.getInt("idWorkplace"));
        workplace.setIdManager(resultSet.getInt("idManager"));
        workplace.setAddress(resultSet.getString("address"));
        workplace.setWarehouse(resultSet.getBoolean("warehouse"));
        return workplace;
    }
}
