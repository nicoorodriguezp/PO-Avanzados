package com.poa.POAvanzados.Database.MongoDB.Entities;

import com.poa.POAvanzados.Model.UserModel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    User findUserByIdUser(int idUser);
}
