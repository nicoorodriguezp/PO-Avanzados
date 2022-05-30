package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.repair_model.Repair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRowMapper implements RowMapper<Repair> {
    @Override
    public Repair mapRow(ResultSet resultSet, int i) throws SQLException {
        Repair repair= new Repair();
        repair.setIdTechnician(resultSet.getInt("idUser"));
        repair.setIdLaboratory(resultSet.getInt("idWorkplace"));
        repair.setReparationDate(resultSet.getString("date"));
        repair.setRepairDescription(resultSet.getString("description"));
        return repair;
    }
}
