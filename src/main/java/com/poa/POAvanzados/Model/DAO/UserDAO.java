package com.poa.POAvanzados.Model.DAO;

import com.poa.POAvanzados.Model.UserModel.User;

public interface UserDAO {

    public User getUser(int idUser) throws DAOException;

}
