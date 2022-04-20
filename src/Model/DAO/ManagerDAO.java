package Model.DAO;

public interface ManagerDAO extends WorkerDAO {

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity);

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity);

}
