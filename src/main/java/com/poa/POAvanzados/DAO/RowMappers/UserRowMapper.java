package com.poa.POAvanzados.DAO.RowMappers;

import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user= new User();
        user.setIdUser(resultSet.getInt("idUser"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("lastName"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setActive(resultSet.getBoolean("activo"));
        user.setPosition(new Position(resultSet.getInt("idPosition"),resultSet.getString("title"),resultSet.getString("category")));
        user.setWorkplace(new Workplace(resultSet.getInt("idWorkplace"),resultSet.getBoolean("warehouse"),resultSet.getInt("idManager"),resultSet.getString("address")));
        return user;
    }
}
