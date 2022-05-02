package com.poa.POAvanzados.Database;

import java.util.ArrayList;
import java.util.List;

import com.poa.POAvanzados.Model.WorkplaceModel.Workplace;

public class WorkplaceRepository {

    public static ArrayList<Workplace> getWorkplaces() {

        ArrayList<Workplace> workplaces = new ArrayList<>();
        Workplace w = new Workplace(0, true, 0, "Mario Bravo 1050");
        workplaces.add(w);
        w = new Workplace(1, true, 0, "Larrea 1050");
        workplaces.add(w);

        w = new Workplace(2, false, 0, "Mario Bravo 1122");
        workplaces.add(w);
        w = new Workplace(3, false, 0, "Jean Jaures 932/6");
        workplaces.add(w);
        w = new Workplace(4, false, 0, "Av. Madero 942/8");
        workplaces.add(w);

        return workplaces;
    }

    public static List<Workplace> getWarehouses() {

        ArrayList<Workplace> workplaces = new ArrayList<>();
        Workplace w = new Workplace(0, true, 0, "Mario Bravo 1050");
        workplaces.add(w);
        w = new Workplace(1, true, 0, "Larrea 1050");
        workplaces.add(w);

        return workplaces;
    }

}
