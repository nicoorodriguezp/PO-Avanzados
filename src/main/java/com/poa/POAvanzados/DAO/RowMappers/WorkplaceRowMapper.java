package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
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
