package com.poa.POAvanzados.DAO.Worker;

import com.poa.POAvanzados.DAO.RowMappers.*;
import com.poa.POAvanzados.Exception.DAOException;
import com.poa.POAvanzados.Exception.LoginUserException;
import com.poa.POAvanzados.Exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
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
    public User getUser(User user) throws LoginUserException {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
            DataSource ds = (DataSource) applicationContext.getBean("dataSource");
            JdbcTemplate jt = new JdbcTemplate(ds);
            User userResponse = jt.queryForObject("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, password, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                            "\tFROM public.\"User\"\n" +
                            "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                            "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"" +
                            "\tWHERE \"dni\"=? AND \"password\" LIKE ?;", new Object[]{user.getIdUser(), user.getPassword()}, new UserRowMapper());
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
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                    "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                    "WHERE \"idWarehouse\"=?;",new Object[]{workplace.getIdWorkplace()},new Item_DetailRowMapper());
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                    "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                    "WHERE \"idLaboratory\"=?;",new Object[]{workplace.getIdWorkplace()},new Item_DetailRowMapper());
        }

        items.addAll(item_detailList);
        return items;
    }


    @Override
    public void createRepair(Repair repair) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        int idRepair;
        idRepair=jt.queryForObject("INSERT INTO public.\"Repair\"(\n" +
                    "\t \"idUser\", \"idWorkplace\", date, description)\n" +
                    "\tVALUES (?, ?, ?, ?) RETURNING \"idRepair\";",new Object[]{repair.getIdTechnician(),repair.getIdLaboratory(),repair.getReparationDate(),repair.getRepairDescription()},Integer.class);
        repair.setIdRepair(idRepair);
        for (Item_Detail itemDetail :
                repair.getItemDetails()) {
            jt.update("INSERT INTO public.\"Repair_Item\"(\n" +
                    "\t\"idRepair\", \"idItemCode\")\n" +
                    "\tVALUES (?, ?);",repair.getIdRepair(), itemDetail.getIdItemCode());
            updateItemDetail(itemDetail);
            Item item=jt.queryForObject("SELECT \"idItem\", name, critical\n" +
                    "\tFROM public.\"Item\"\n" +
                    "\tWHERE \"idItem\"=?;",new Object[]{itemDetail.getItem().getIdItem()},new ItemRowMapper());
            Workplace_Item workplace_item= jt.queryForObject("SELECT \"idWorkplace\", \"idItem\", max_slots, stock\n" +
                    "\tFROM public.\"Workplace_Item\"\n" +
                    "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",new Object[]{itemDetail.getLaboratory(),itemDetail.getItem().getIdItem()} ,new Workplace_ItemRowMapper());
            checkingStockForMail(item,workplace_item);
        }

    }

    @Override
    public void updateItemDetail(Item_Detail item) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("UPDATE public.\"Item_Detail\"\n" +
                "\tSET \"idState\"=?\n" +
                "\tWHERE \"idItemCode\"=?;",item.getState().getIdState(),item.getIdItemCode());

    }


    @Override
    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> items = new ArrayList<>();
        List<Item_Detail> item_detailList;
        if(workplace.isWarehouse() || user_role==1){
            throw new NotAllowedForWarehouse("Solo los usuarios de laboratorio pueden hacer una reparacion");
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                    "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                    "WHERE \"idLaboratory\"=? AND \"Item_Detail\".\"idState\"=1;",new Object[]{workplace.getIdWorkplace()},new Item_DetailRowMapper());
        }

        items.addAll(item_detailList);
        return items;
    }

    public void checkingStockForMail(Item item, Workplace_Item workplace_item){

    }
}
