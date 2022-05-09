package com.poa.POAvanzados.DAO.Manager;

import com.poa.POAvanzados.DAO.RowMappers.WorkplaceRowMapper;
import com.poa.POAvanzados.DAO.Worker.WorkerDAOImpl;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOImpl extends WorkerDAOImpl implements ManagerDAO {
    @Override
    public void replenishWarehouse(int idWarehouse, int idItem, int quantity) {

    }

    @Override
    public void replenishLaboratory(int idLaboratory, int idItem, int quantity) {

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
        return null;
    }
}
