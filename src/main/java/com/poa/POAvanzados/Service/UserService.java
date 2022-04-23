package com.poa.POAvanzados.Service;

import com.poa.POAvanzados.Database.MongoDB.Entities.UserRepository;
import com.poa.POAvanzados.Model.DAO.DAOException;
import com.poa.POAvanzados.Model.DAO.UserDAO;
import com.poa.POAvanzados.Model.UserModel.User;

import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDAO {

    private final UserRepository repository; // MongoDB

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUser(int idUser) throws DAOException {
        return repository.findUserByIdUser(idUser);
    }

}
