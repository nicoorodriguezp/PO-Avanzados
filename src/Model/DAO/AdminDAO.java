package Model.DAO;

import Model.UserModel.User;

public interface AdminDAO extends ManagerDAO {

    // User CRUD
    public void createUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    // Reports
    public void generateGeneralReport();

    public void generateWarehouseReport(int idWarehouse);

    public void generrateItemReport(int idWarehouse, int idItem);
}
