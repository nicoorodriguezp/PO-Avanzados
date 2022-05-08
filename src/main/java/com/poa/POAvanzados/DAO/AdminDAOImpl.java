package com.poa.POAvanzados.DAO;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.PositionModel.Admin;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository ("AdminDAOImpl")
public class AdminDAOImpl extends ManagerDAOImpl implements AdminDAO{

    @Override
    public void createUser(User user) {

    }

    @Override
    public void deleteUser(int idUser) {

    }

    @Override
    public void updateUser(User user) {

    }
    public ArrayList<User> getUsers() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<User> users = new ArrayList<>();

        List<User> userList= jt.query("select * from \"User\"", (rs, rowNum) -> new User(
                rs.getInt("idUser"),
                new Admin(),
                new Workplace(1,true,1,"asdas"),
                rs.getString("name"),
                rs.getString("lastName"),
                rs.getString("email"),

                rs.getBoolean("activo"),
                rs.getString("password")
        ));
        users.addAll(userList);

        return users;
    }


    @Override
    public List<Item_Detail> getAllCheckOut(String date) {
        return null;
    }

    @Override
    public List<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        return null;
    }

    @Override
    public List<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        return null;
    }
}
