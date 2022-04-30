package com.poa.POAvanzados.Database.MongoDB.Entities;

import java.util.List;

import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item_Detail, Integer> {

    // Laboratory
    List<Item_Detail> findByIdLaboratoryAndState(int idLaboratory, int stockState);

    List<Item_Detail> findByIdLaboratoryAndIdItemAndState(int idLaboratory, int idItem, int stockState);//

    List<Item_Detail> findByIdLaboratoryAndIdItemAndCheckOut(int idLaboratory, int idItem, String checkout);

    // warehouse
    List<Item_Detail> findByIdWarehouseAndState(int idLaboratory, int stockState);

    List<Item_Detail> findByIdWarehouseAndIdItemAndState(int idLaboratory, int idItem, int stockState);//

    List<Item_Detail> findByIdWarehouseAndIdItemAndCheckOut(int idLaboratory, int idItem, String checkout);

    // CheckOut

    List<Item_Detail> findByCheckOut(String startdate, String endDate);

    List<Item_Detail> getAllItemsByCheckOut(String date);

    List<Item_Detail> getItemsByIdWarehouseAndCheckOut(int idWarehouse, String date);

    List<Item_Detail> getItemsByIdWarehouseAndIdItemAndCheckOut(int idWarehouse, int idItem, String date);

}
