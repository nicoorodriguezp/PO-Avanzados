package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.PositionModel.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionRowMapper implements RowMapper<Position> {

    @Override
    public Position mapRow(ResultSet resultSet, int i) throws SQLException {
        Position position= new Position();
        position.setIdPosition(resultSet.getInt("idPosition"));
        position.setTitle(resultSet.getString("title"));
        position.setCategory(resultSet.getString("category"));
        return position;
    }
}
