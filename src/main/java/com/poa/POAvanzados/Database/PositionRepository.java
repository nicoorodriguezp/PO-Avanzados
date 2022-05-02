package com.poa.POAvanzados.Database;

import java.util.ArrayList;

import com.poa.POAvanzados.Model.PositionModel.Admin;
import com.poa.POAvanzados.Model.PositionModel.Manager;
import com.poa.POAvanzados.Model.PositionModel.Position;
import com.poa.POAvanzados.Model.PositionModel.Worker;

public class PositionRepository {

    public static ArrayList<Position> getPositions() {

        ArrayList<Position> positions = new ArrayList<>();

        Position p = new Admin();
        positions.add(p);
        p = new Manager();
        positions.add(p);
        p = new Worker();
        positions.add(p);

        return positions;
    }

}
