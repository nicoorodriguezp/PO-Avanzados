package com.poa.POAvanzados.Database;

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
        user.setEmail(resultSet.getString("email"));
        user.setPosition(new Position() {
            @Override
            public void showHome() {

            }

            @Override
            public int getPosition() {
                return 1;
            }
        });
        user.setPassword(resultSet.getString("password"));
        user.setWorkplace(new Workplace(1,true,1,"dadada"));
        return user;
    }
}
