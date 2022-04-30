package com.poa.POAvanzados.Database.MongoDB.Entities;

import java.util.List;

import com.poa.POAvanzados.Model.RepairModel.Repair;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepairRepository extends MongoRepository<Repair, Integer> {

    List<Repair> findByIdLaboratory(int idLaboratory);

}
