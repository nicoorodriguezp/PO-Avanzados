package com.poa.POAvanzados.dao.manager;

import com.poa.POAvanzados.dao.row_mappers.ItemCountRowMapper;
import com.poa.POAvanzados.dao.row_mappers.ItemRowMapper;
import com.poa.POAvanzados.dao.row_mappers.PositionRowMapper;
import com.poa.POAvanzados.dao.row_mappers.WorkplaceRowMapper;
import com.poa.POAvanzados.dao.worker.WorkerDAOImpl;
import com.poa.POAvanzados.model.item_model.Item;
import com.poa.POAvanzados.model.item_model.ItemCount;
import com.poa.POAvanzados.model.item_model.Workplace_Item;
import com.poa.POAvanzados.model.position_model.Position;
import com.poa.POAvanzados.model.workplace_model.Workplace;
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
                "\tWHERE \"idItemCode\" in (SELECT \"idItemCode\" FROM \"Item_Detail\" WHERE \"idLaboratory\" is  null AND check_out is  null AND \"idWarehouse\"=? LIMIT ?)", idLaboratory, checkOut, idWarehouse,quantity);

        jt.update("UPDATE public.\"Workplace_Item\"\n" +
                "\tSET  stock= stock+?\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",quantity,idLaboratory,idItem);

        jt.update("UPDATE public.\"Workplace_Item\"\n" +
                "\tSET  stock= stock-?\n" +
                "\tWHERE \"idWorkplace\"=? AND \"idItem\"=?;",quantity,idWarehouse,idItem);

        Item item=jt.queryForObject("SELECT \"idItem\", name, critical\n" +
                "\tFROM public.\"Item\"\n" +
                "\tWHERE \"idItem\"=?;",new Object[]{idItem},new ItemRowMapper());

        Workplace_Item workplace_item= checkStock(idWarehouse,idItem);
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
    public List<Workplace> getWarehouses() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Workplace> workplaceList= jt.query("SELECT \"idWorkplace\", address, warehouse, \"idManager\"\n" +
                "\tFROM public.\"Workplace\"\n" +
                "\tWHERE warehouse is true;",new WorkplaceRowMapper());
        return workplaceList;
    }

    @Override
    public ArrayList<ItemCount> getItemCountByWorkplace(Workplace workplace) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        DataSource ds = (DataSource) applicationContext.getBean("dataSource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        ArrayList<ItemCount> itemCounts= new ArrayList<>();
        List<ItemCount> itemCountList= jt.query("SELECT \"Item\".\"idItem\",\"Item\".\"name\",\"Item\".\"critical\", sum(case when \"Item_Detail\".\"idState\" = 2 then 1 else 0 end) as usedCount, sum(case when \"Item_Detail\".\"idState\" = 3 then 1 else 0 end) as discardedCount\n" +
                "\tFROM public.\"Item_Detail\" AS \"Item_Detail\"\n" +
                "\tJOIN \"Item\" ON \"Item_Detail\".\"idItem\"=\"Item\".\"idItem\"\n" +
                "\tWHERE \"Item_Detail\".\"idLaboratory\" = ?\n" +
                "\tGROUP BY  \"Item\".\"idItem\",\"Item\".\"name\",\"Item\".\"critical\"",new Object[]{workplace.getIdWorkplace()},new ItemCountRowMapper());
        itemCounts.addAll(itemCountList);
        return itemCounts;
    }
}
