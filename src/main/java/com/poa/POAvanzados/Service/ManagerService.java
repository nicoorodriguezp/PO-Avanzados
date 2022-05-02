package com.poa.POAvanzados.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.poa.POAvanzados.Database.ItemRepository;
import com.poa.POAvanzados.Database.WorkplaceRepository;
import com.poa.POAvanzados.Model.ItemModel.ItemBuilder;
import com.poa.POAvanzados.Model.ItemModel.Item_Detail;
import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class ManagerService extends WorkerService {

    public void replenishWarehouse(int idWarehouse, int idItem, int quantity) {

        for (int q = 0; q < quantity; q++) {

            Item_Detail item = new ItemBuilder()
                    .addIdItem(idItem)
                    .addWarehouse(idWarehouse)
                    .addCheckIn(getDate())
                    .build();

            ItemRepository.insert(item);
        }

    }

    public void replenishLaboratory(int idLaboratory, int idItem, int quantity) {
        List<Workplace> warehouses = WorkplaceRepository.getWarehouses();

        int remaining = quantity;
        List<Integer> last_warehouses = new ArrayList<Integer>();
        int contador = 0;

        while (remaining != 0 && contador < warehouses.size()) {

            Random random = new Random();
            int w = random.nextInt(warehouses.size() + 0) + 0;
            contador++;

            if (!last_warehouses.contains(w)) {

                last_warehouses.add(w);

                List<Item_Detail> availableItemsWarehouse = ItemRepository
                        .getItemsDetails(
                                warehouses.get(w).getIdWorkplace(),
                                idItem,
                                null);

                if (availableItemsWarehouse.size() == 0) {

                    // Remplazar esto por un throw business exception
                    System.out.println("No hay stock disponible de ese item en ningun deposito.");

                } else if (availableItemsWarehouse.size() < remaining) {

                    remaining = updateItemState(remaining, idLaboratory, availableItemsWarehouse,
                            availableItemsWarehouse.size());
                } else {

                    remaining = updateItemState(remaining, idLaboratory, availableItemsWarehouse, remaining);

                }
            }

        }

        if (contador < warehouses.size()) {
            System.out.println(
                    "Se abastecio el stock del item " + idItem + " en el laboratorio: " + idLaboratory
                            + " correctamente. \n\n");
        } else {
            // Si ya verifico el stock en todos los warehouses, entonces no hay mas stock
            // disponible.
            // Remplazar esto por un throw business exception
            System.out.println(
                    "No se pudo abastecer por completo por falta de stock en los depositos.\n " +
                            "Aun quedan " + remaining + " unidades restantes que no se han podido reabastecer. \n\n");

        }

    }

    private int updateItemState(int remaining, int idLaboratory, List<Item_Detail> availableItemsWarehouse, int max) {
        for (int i = 0; i < max; i++) {
            Item_Detail item = availableItemsWarehouse.get(i);
            item.setCheckOut(getDate());
            item.setIdLaboratory(idLaboratory);
            // ItemRepository.save(item);
            remaining -= 1;
        }

        return remaining;
    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);

    }
}
