package com.poa.POAvanzados.Database;

import com.poa.POAvanzados.Model.PositionModel.Position;

import java.util.ArrayList;

public class PositionRepository {

    public static ArrayList<Position> getPositions() {

        ArrayList<Position> positions = new ArrayList<>();

        Position p = new Position(1,"admin","admin");
        positions.add(p);
        Position p2 = new Position(2,"manager","manager");
        positions.add(p);
        Position p3 = new Position(3,"worker","worker");
        positions.add(p);

        return positions;
    }

}
