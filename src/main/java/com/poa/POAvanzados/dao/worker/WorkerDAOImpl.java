package com.poa.POAvanzados.dao.worker;


import com.poa.POAvanzados.dao.row_mappers.*;
import com.poa.POAvanzados.controller.EmailController;
import com.poa.POAvanzados.model.email.EmailDetails;
import com.poa.POAvanzados.exception.DAOException;
import com.poa.POAvanzados.exception.LoginUserException;
import com.poa.POAvanzados.exception.NotAllowedForWarehouse;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.Item_Detail;
import com.poa.POAvanzados.model.item_model.Item_Detail_Inventory;
import com.poa.POAvanzados.model.item_model.Workplace_Item;
import com.poa.POAvanzados.model.repair_model.Repair;
import com.poa.POAvanzados.model.user_model.User;
import com.poa.POAvanzados.model.workplace_model.Workplace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
            User userResponse = jt.queryForObject("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                            "\tFROM public.\"User\"\n" +
                            "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                            "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"" +
                            "\tWHERE \"dni\"=? AND \"password\" LIKE ?;", new Object[]{user.getIdUser(), user.getPassword()}, new UserNoPasswordRowMapper());
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
    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail_Inventory> items = new ArrayList<>();
        List<Item_Detail_Inventory> item_detailList;
        if(workplace.isWarehouse()){
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idWarehouse\"=? and \"idLaboratory\" is null;",new Object[]{workplace.getIdWorkplace()},new Item_DetailInventoryRowMapper());
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idLaboratory\"=? and \"Item_Detail\".\"idState\" = 1;",new Object[]{workplace.getIdWorkplace()},new Item_DetailInventoryRowMapper());
        }

        items.addAll(item_detailList);
        return items;
    }
    public ArrayList<Item_Detail_Inventory> getAllInventoryByWorkplace(Workplace workplace,Integer from,Integer ammount) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail_Inventory> items = new ArrayList<>();
        List<Item_Detail_Inventory> item_detailList;
        if(workplace.isWarehouse()){
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idWarehouse\"=? and \"idLaboratory\" is null " +
                    "OFFSET ? LIMIT ?;",new Object[]{workplace.getIdWorkplace(),from,ammount},new Item_DetailInventoryRowMapper());
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idLaboratory\"=? and \"Item_Detail\".\"idState\" = 1 " +
                    "OFFSET ? LIMIT ?;",new Object[]{workplace.getIdWorkplace(),from,ammount},new Item_DetailInventoryRowMapper());
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
        }
        int itemIdAux=0;
        for (Item_Detail itemDetail : repair.getItemDetails()) {
            if(itemDetail.getItem().getIdItem()!=itemIdAux){
                itemIdAux=itemDetail.getItem().getIdItem();
                Item item=jt.queryForObject("SELECT \"idItem\", name, critical\n" +
                        "\tFROM public.\"Item\"\n" +
                        "\tWHERE \"idItem\"=?;",new Object[]{itemDetail.getItem().getIdItem()},new ItemRowMapper());
                Workplace_Item workplace_item= checkStock(repair.getIdLaboratory(),item.getIdItem());
                checkingStockForMail(item,workplace_item);
            }

        }
    }

    @Override
    public void updateItemDetail(Item_Detail item) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.update("UPDATE public.\"Item_Detail\"\n" +
                "\tSET \"idState\"=?\n" +
                "\tWHERE \"idItemCode\"=(SELECT \"idItemCode\"\n" +
                "\t\t\t\t\t   FROM \"Item_Detail\"\n" +
                "\t\t\t\t\t   WHERE \"idItemCode\"=?\n" +
                "\t\t\t\t\t   LIMIT 1);",item.getState().getIdState(),item.getIdItemCode());

    }


    @Override
    public ArrayList<Item_Detail> getAllInventoryByWorkplaceOnStock(Workplace workplace,int user_role) throws NotAllowedForWarehouse {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail> items = new ArrayList<>();
        List<Item_Detail> item_detailList;
        if(user_role==3 && workplace.isWarehouse()){
            throw new NotAllowedForWarehouse("Solo los usuarios de laboratorio pueden hacer una reparacion");
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"warehouse\".\"address\" as addressDeposito,\"warehouse\".\"warehouse\",\"warehouse\".\"idManager\", \"idLaboratory\",\"laboratory\".\"address\" as addressLaboratorio,\"laboratory\".\"warehouse\",\"laboratory\".\"idManager\", \"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
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
    public Workplace_Item checkStock(int idWorkplace, int idItem) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Workplace_Item workplace_item = jt.queryForObject("SELECT \"idWorkplace\", \"idItem\", max_slots, stock\n" +
                "\tFROM public.\"Workplace_Item\"\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;", new Object[]{idWorkplace, idItem}, new Workplace_ItemRowMapper());

        return workplace_item;
    }
    public void checkingStockForMail(Item item, Workplace_Item workplace_item){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        User manager= jt.queryForObject("SELECT \"idUser\", \"User\".\"idPosition\", name, \"lastName\", email, activo, \"User\".\"idWorkplace\", dni,\"Position\".title,\"Position\".category,\"Workplace\".address,\"Workplace\".warehouse,\"Workplace\".\"idManager\"\n" +
                "\tFROM public.\"User\"\n" +
                "\tJOIN \"Position\" ON \"User\".\"idPosition\"=\"Position\".\"idPosition\"\n" +
                "\tJOIN \"Workplace\" ON \"Workplace\".\"idWorkplace\"=\"User\".\"idWorkplace\"" +
                "\tWHERE \"Workplace\".\"idWorkplace\" = ? AND \"User\".\"idPosition\"=2 AND activo is true;", new Object[]{workplace_item.getIdWorkplace()}, new UserNoPasswordRowMapper());


        int max_slots = workplace_item.getMax_slots();
        int fifty = (int) Math.ceil( max_slots / 2.0);
        int twenty= (int) Math.ceil( max_slots * 0.2);
        String message = "El stock del item se encuentra dentro del nivel de tolerancia.";
        String email = manager.getEmail().trim();
        System.out.println(email);
        if(item.isCritical() && workplace_item.getStock() <= fifty){
            message = " El insumo critico "+ item.getName() + " está por debajo del nivel de tolerancia en su lugar de trabajo. \n\n" +
                    "Por favor, solicite más suministros del item mencionado LO ANTES POSIBLE.\n\n";
            sendEmail(email, message);

        }else if(!item.isCritical() && workplace_item.getStock() <= twenty){
            message = " El insumo secundario "+ item.getName() + " está por debajo del nivel de tolerancia en su lugar de trabajo. \n\n" +
                    "Por favor, solicite más suministros del item mencionado.\n\n";
            sendEmail(email, message);

        }else{
            System.out.println(message);
        }


    }

    private void sendEmail(String addressee, String message){


        Thread emailThread = new Thread(() -> {
            System.out.println("Enviando email de alerta.");

            EmailController e = new EmailController();
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(addressee);
            emailDetails.setMsgBody(message);
            emailDetails.setSubject("Alerta de Reposición");
            e.sendMail(emailDetails);

            System.out.println("sendEmail() terminó su ejecución.");
        });

        emailThread.start();

    }

    public Integer getAmmountItemsByWorkplace(Workplace workplace) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Item_Detail_Inventory> items = new ArrayList<>();
        List<Item_Detail_Inventory> item_detailList;
        if(workplace.isWarehouse()){
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idWarehouse\"=? and \"idLaboratory\" is null;",new Object[]{workplace.getIdWorkplace()},new Item_DetailInventoryRowMapper());
        }
        else {
            item_detailList=jt.query("SELECT \"idItemCode\", \"Item_Detail\".\"idItem\",\"idWarehouse\",\"idLaboratory\",\"Item_Detail\".\"idState\", check_in, check_out,\"State\".\"state_description\",\"Item\".\"name\",\"Item\".\"critical\"\n" +
                    "FROM public.\"Item_Detail\"\n" +
                    "JOIN \"Item\" ON \"Item\".\"idItem\"=\"Item_Detail\".\"idItem\"\n" +
                    "JOIN \"State\" ON \"Item_Detail\".\"idState\"=\"State\".\"idState\"\n" +
                    "WHERE \"idLaboratory\"=? and \"Item_Detail\".\"idState\" = 1;",new Object[]{workplace.getIdWorkplace()},new Item_DetailInventoryRowMapper());
        }

        items.addAll(item_detailList);
        return items.size();
    }
}
