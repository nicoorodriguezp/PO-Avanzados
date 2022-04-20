package Model.DAO;

import Model.UserModel.User;

public interface UserDAO {

    public User getUser(int idUser) throws DAOException;

}
