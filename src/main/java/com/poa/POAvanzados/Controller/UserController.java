package com.poa.POAvanzados.Controller;

import com.poa.POAvanzados.Model.DAO.DAOException;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User fetchUser(int idUser) throws DAOException {
        return userService.getUser(idUser);
    }
}
