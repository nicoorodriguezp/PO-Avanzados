package com.poa.POAvanzados.Controller;

import com.poa.POAvanzados.Database.MongoDB.Entities.UserRepository;
import com.poa.POAvanzados.Model.DAO.DAOException;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Permite hacer peticiones REST desde un cliente
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserRepository userRepo) {
        this.userService = new UserService(userRepo);
    }

    @GetMapping // Le agrega los metodos POST, DELETE, UPDATE, GET, ETC
    public User getUser(int idUser) throws DAOException {
        return userService.getUser(idUser);
    }

}
