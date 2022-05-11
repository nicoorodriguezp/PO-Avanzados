package com.poa.POAvanzados.DAO.Worker;

import com.poa.POAvanzados.DAO.RowMappers.ItemRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.Item_DetailRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.UserRowMapper;
import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO{
    @Override
    public User getUser(User user) throws DAOException, LoginUserException {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
            DataSource ds = (DataSource) applicationContext.getBean("dataSource");
            JdbcTemplate jt = new JdbcTemplate(ds);
            User userResponse = jt.queryForObject("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, password, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                            "\tFROM public.\"User\"\n" +
                            "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                            "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"" +
                            "\tWHERE \"dni\"=? AND \"password\" LIKE ?;", new Object[]{user.getIdUser(), user.getPassword()},
                    new UserRowMapper());
            return userResponse;
        }
        catch (EmptyResultDataAccessException e){
            throw new LoginUserException("Error: Dni y contraseña incorrectos");
        }
    }

    @Override
    public ArrayList<Item> getItems() throws DAOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item> items = new ArrayList<>();

        List<Item> itemList=jt.query("SELECT \"idItem\", name, critical\n" +
                "\tFROM public.\"Item\";",new ItemRowMapper());
        items.addAll(itemList);
        for (Item item :
                items) {
            System.out.println(item.getName());
        }
        return items;
    }

    @Override
    public ArrayList<Item_Detail> getAllInventoryByWorkplace(Workplace workplace) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> items = new ArrayList<>();
        List<Item_Detail> item_detailList;
        if(workplace.isWarehouse()){
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\", \"Item\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                    "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                    "WHERE \"idWarehouse\"=?;",new Object[]{workplace.getIdWorkplace()},new Item_DetailRowMapper());
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\", \"Item\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                    "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                    "WHERE \"idWarehouse\"=?;",new Object[]{workplace.getIdWorkplace()},new Item_DetailRowMapper());
        }

        items.addAll(item_detailList);
        return items;
    }

    @Override
    public ArrayList<Item_Detail> getInventoryItem(int idWorkplace, int idItem) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        return null;
    }

    @Override
    public void addItem(Item item) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("INSERT INTO public.\"Item\"(\n" +
                "\t name, critical)\n" +
                "\tVALUES ( ?, ?);",item.getName(),item.isCritical());
    }

    @Override
    public void createRepair(Repair repair) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("INSERT INTO public.\"Repair\"(\n" +
                "\t \"idUser\", \"idWorkplace\", date, description)\n" +
                "\tVALUES (?, ?, ?, ?);",repair.getIdTechnician(),repair.getIdLaboratory(),repair.getReparationDate(),repair.getRepairDescription());
    }

    @Override
    public void updateItemDetail(Item_Detail item) {

    }


    @Override
    public ArrayList<Repair> getAllRepairs(int idWorkplace) {
        return null;
    }
}
