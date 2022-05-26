package com.poa.POAvanzados.DAO.Admin;

import com.poa.POAvanzados.DAO.Manager.ManagerDAOImpl;
import com.poa.POAvanzados.DAO.RowMappers.Item_DetailRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.UserRowMapper;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
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
    public void updateUser(User user) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("UPDATE public.\"User\"\n" +
                "\tSET \"idPosition\"=?, name=?, \"lastName\"=?, email=?, password=?, activo=?, \"idWorkplace\"=?, dni=?\n" +
                "\tWHERE \"idUser\"=?;",user.getPosition().getIdPosition(),user.getName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getActive(),user.getWorkplace().getIdWorkplace(),user.getDni(),user.getIdUser());
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
    public ArrayList<Item_Detail> getAllCheckOut(String date) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> item_details = new ArrayList<>();
        List<Item_Detail> item_detailList= jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\", \"Item\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\" as addressDeposito,\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\" as addressLaboratorio,\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                "FROM public.\"Item_Detail\"\n" +
                "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                "WHERE check_out is not null AND \"idLaboratory\" is not null AND (\"Item_Detail\".\"idState\"=2 OR \"Item_Detail\".\"idState\"=3)AND check_out=?",new Object[]{date},new Item_DetailRowMapper());
        item_details.addAll(item_detailList);
        return item_details;
    }

    @Override
    public ArrayList<Item_Detail> getCheckOutWarehouse(int idWarehouse, String date) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> item_details = new ArrayList<>();
        List<Item_Detail> item_detailList= jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\", \"Item\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                "FROM public.\"Item_Detail\"\n" +
                "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                "WHERE check_out is not null AND \"idLaboratory\" is not null AND (\"Item_Detail\".\"idState\"=2 OR \"Item_Detail\".\"idState\"=3)AND check_out=? AND \"idWarehouse\"=?;",new Object[]{date,idWarehouse},new Item_DetailRowMapper());
        item_details.addAll(item_detailList);
        return item_details;
    }

    @Override
    public ArrayList<Item_Detail> getCheckOutItemWarehouse(int idWarehouse, int idItem, String date) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> item_details = new ArrayList<>();
        List<Item_Detail> item_detailList= jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\", \"Item\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\",\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\",\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                "FROM public.\"Item_Detail\"\n" +
                "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                "JOIN \"Workplace\" \"warehouse\" ON \"warehouse\".\"idWorkplace\"=\"Item_Detail\".\"idWarehouse\"\n" +
                "JOIN \"Workplace\" \"laboratory\" ON \"laboratory\".\"idWorkplace\"=\"Item_Detail\".\"idLaboratory\"\n" +
                "WHERE check_out is not null AND \"idLaboratory\" is not null AND (\"Item_Detail\".\"idState\"=2 OR \"Item_Detail\".\"idState\"=3)AND check_out=? AND \"idWarehouse\"=? AND \"Item\".\"idItem\"=?;",new Object[]{date,idWarehouse,idItem},new Item_DetailRowMapper());
        item_details.addAll(item_detailList);
        return item_details;
    }


    @Override
    public void addItem(Item item) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        int id_item;
        id_item=jt.queryForObject("INSERT INTO public.\"Item\"(\n" +
                "\t name, critical)\n" +
                "\tVALUES ( ?, ?)\n" +
                "\tRETURNING \"idItem\";",new Object[]{item.getName(),item.isCritical()},Integer.class);
        List<Workplace> workplaceList=getWorkplaces();
        for (Workplace workplace:
             workplaceList) {
            Integer id=jt.queryForObject("SELECT max(\"idItem\") From \"Item\";",Integer.class);
            jt.update("INSERT INTO public.\"Workplace_Item\"(\n" +
                    "\t\"idWorkplace\", \"idItem\", max_slots, stock)\n" +
                    "\tVALUES (?, ?, ?, ?);",workplace.getIdWorkplace(),id_item,50,0);
        }
    }
    public void updateItem(Item item) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("UPDATE public.\"Item\"\n" +
                "\tSET  name=?, critical=?\n" +
                "\tWHERE \"idItem\"=? ;",item.getName(),item.isCritical(),item.getIdItem());

    }
}
