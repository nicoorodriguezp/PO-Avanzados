package com.poa.POAvanzados.Service;

import java.util.List;

import com.poa.POAvanzados.Database.MongoDB.Entities.ItemRepository;
import com.poa.POAvanzados.Database.MongoDB.Entities.RepairRepository;
import com.poa.POAvanzados.Database.MongoDB.Entities.UserRepository;
import com.poa.POAvanzados.Model.DAO.WorkerDAO;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.RepairModel.Repair;

import org.springframework.stereotype.Service;

@Service
public class WorkerService extends UserService implements WorkerDAO {

    protected final ItemRepository itemRepository;
    private final RepairRepository repairRepository;

    public WorkerService(UserRepository userRepository, ItemRepository itemRepository,
            RepairRepository repairRepository) {
        super(userRepository);
        this.itemRepository = itemRepository;
        this.repairRepository = repairRepository;
    }

    // >>>>>>>>>>>>>>>>>>>>> Inventory methods <<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public List<Item_Detail> getAllInventory(int idWorkplace) {
        return itemRepository.findByIdLaboratoryAndState(idWorkplace, 0);

    }

    @Override
    public List<Item_Detail> getInventoryItem(int idWorkplace, int idItem) {
        return itemRepository.findByIdLaboratoryAndIdItemAndState(idWorkplace, idItem, 0);
    }

    @Override
    public void updateItem(Item_Detail item) {
        itemRepository.save(item);

    }

    // >>>>>>>>>>>>>>>>>>>>> Repair methods <<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Override
    public List<Repair> getAllRepairs(int idWorkplace) {
        return repairRepository.findByIdLaboratory(idWorkplace);
    }

    @Override
    public void createRepair(Repair repair) {
        repairRepository.insert(repair);

    }

}
