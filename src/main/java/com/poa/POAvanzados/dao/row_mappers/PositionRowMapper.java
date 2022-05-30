package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.position_model.Position;
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
