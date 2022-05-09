package com.poa.POAvanzados.DAO.Worker;

import com.poa.POAvanzados.DAO.RowMappers.UserRowMapper;
import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;

public class WorkerDAOImpl implements WorkerDAO{
    @Override
    public User getUser(User user) throws DAOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        User userResponse= jt.queryForObject("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, password, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                        "\tFROM public.\"User\"\n" +
                        "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                        "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"" +
                "\tWHERE \"dni\"=? AND \"password\" LIKE ?;",new Object[]{user.getIdUser(),user.getPassword()},
                new UserRowMapper());
        return userResponse;
    }

    @Override
    public ArrayList<Item> gItems() throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Item_Detail> getAllInventory(int idWorkplace) {
        return null;
    }

    @Override
    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem) {
        return null;
    }

    @Override
    public void createRepair(Repair repair) {

    }

    @Override
    public void updateItem(Item_Detail item) {

    }

    @Override
    public ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return null;
    }
}
