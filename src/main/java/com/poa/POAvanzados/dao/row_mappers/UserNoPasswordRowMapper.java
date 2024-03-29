package com.poa.POAvanzados.dao.row_mappers;

import com.poa.POAvanzados.model.user_model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNoPasswordRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user= new User();
        user.setIdUser(resultSet.getInt("idUser"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));
        user.setActive(resultSet.getBoolean("activo"));
        user.setDni(resultSet.getInt("dni"));
        user.setPosition(new PositionRowMapper().mapRow(resultSet,i));
        user.setWorkplace(new WorkplaceRowMapper().mapRow(resultSet,i));
        return user;
    }
}
