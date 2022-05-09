package com.poa.POAvanzados.DAO.Admin;

import com.poa.POAvanzados.DAO.Manager.ManagerDAOImpl;
import com.poa.POAvanzados.DAO.RowMappers.UserRowMapper;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("INSERT INTO public.\"User\"(\n" +
                "\t\"idPosition\", name, \"lastName\", email, password, activo, \"idWorkplace\", dni)\n" +
                "\tVALUES ( ?, ?, ?, ?, ?, ?, ?, ?);",user.getPosition().getIdPosition(),user.getName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getActive(),user.getWorkplace().getIdWorkplace(),user.getDni());
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

        List<User> userList= jt.query("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, password, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                "\tFROM public.\"User\"\n" +
                "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"", new UserRowMapper());
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
