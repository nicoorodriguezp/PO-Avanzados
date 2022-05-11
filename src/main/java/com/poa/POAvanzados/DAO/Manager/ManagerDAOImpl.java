package com.poa.POAvanzados.DAO.Manager;

import com.poa.POAvanzados.DAO.RowMappers.ItemRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.PositionRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.WorkplaceRowMapper;
import com.poa.POAvanzados.DAO.RowMappers.Workplace_ItemRowMapper;
import com.poa.POAvanzados.DAO.Worker.WorkerDAOImpl;
import com.poa.POAvanzados.Model.ItemModel.Item;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.ItemModel.Workplace_Item;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import javafx.geometry.Pos;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOImpl extends WorkerDAOImpl implements ManagerDAO {

    @Override
    public void replenishWarehouse(int idWarehouse, int idItem, int quantity, String checkIn) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        for (int i = 0; i < quantity; i++) {
            jt.update("INSERT INTO public.\"Item_Detail\"(\n" +
                    "\t\"idItem\", \"idWarehouse\", \"idLaboratory\", \"idState\", check_in, check_out)\n" +
                    "\tVALUES (?, ?, null, 1, ?, null);",idItem,idWarehouse,checkIn);
        }
        jt.update("UPDATE public.\"Workplace_Item\"\n" +
                "\tSET  stock= stock+?\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",quantity,idWarehouse,idItem);

    }

    @Override
    public void replenishLaboratory(int idLaboratory, int idItem, int quantity,String checkOut, int idWarehouse) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);

        jt.update("UPDATE public.\"Item_Detail\"\n" +
                    "\tSET \"idLaboratory\"=?, check_out=?\n" +
                    "\tWHERE \"idWarehouse\"=? AND \"idLaboratory\" is null AND check_out is null;", idLaboratory, checkOut, idWarehouse);

        jt.update("UPDATE public.\"Workplace_Item\"\n" +
                "\tSET  stock= stock+?\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",quantity,idLaboratory,idItem);

        jt.update("UPDATE public.\"Workplace_Item\"\n" +
                "\tSET  stock= stock-?\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",quantity,idWarehouse,idItem);

        Item item=jt.queryForObject("SELECT \"idItem\", name, critical\n" +
                "\tFROM public.\"Item\"\n" +
                "\tWHERE \"idItem\"=?;",new Object[]{idItem},new ItemRowMapper());

        Workplace_Item workplace_item= jt.queryForObject("SELECT \"idWorkplace\", \"idItem\", max_slots, stock\n" +
                "\tFROM public.\"Workplace_Item\"\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",new Object[]{idLaboratory,idItem} ,new Workplace_ItemRowMapper());
        checkingStockForMail(item,workplace_item);
    }

    @Override
    public ArrayList<Workplace> getWorkplaces() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Workplace> workplaces = new ArrayList<>();

        List<Workplace> workplaceList=jt.query("SELECT \"idWorkplace\", address, warehouse, \"idManager\"\n" +
                "\tFROM public.\"Workplace\";", new WorkplaceRowMapper());
        workplaces.addAll(workplaceList);
        return workplaces;
    }

    @Override
    public ArrayList<Position> getPositions() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<Position> positions = new ArrayList<>();
        List<Position> positionList=jt.query("SELECT \"idPosition\", title, category\n" +
                "\tFROM public.\"Position\";",new PositionRowMapper());
        positions.addAll(positionList);
        return positions;
    }

    @Override
    public Workplace_Item checkStock(int idWorkplace, int idItem) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Workplace_Item workplace_item= jt.queryForObject("SELECT max_slots, stock\n" +
                "\tFROM public.\"Workplace_Item\"\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",new Object[]{idWorkplace,idItem}, new Workplace_ItemRowMapper());

        return workplace_item;

    }

    @Override
    public List<Workplace> getWarehouses() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Workplace> workplaceList= jt.query("SELECT \"idWorkplace\", address, warehouse, \"idManager\"\n" +
                "\tFROM public.\"Workplace\"\n" +
                "\tWHERE warehouse is true;",new WorkplaceRowMapper());
        return workplaceList;
    }
}
