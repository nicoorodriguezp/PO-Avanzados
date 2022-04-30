package com.poa.POAvanzados.Database.MongoDB.Entities;

import java.util.List;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkplaceRepository extends MongoRepository<Workplace, Integer> {

    List<Workplace> findByWarehouse(boolean isWarehouse);

}
