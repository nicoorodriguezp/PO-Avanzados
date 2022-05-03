package com.poa.POAvanzados.Model.PositionModel;

public interface Position {
    public void showHome();

    /**
     * @return : Devuelve el id de Posicion/Rango Laboral. (0) Admin -- (1) Manager
     *         -- (2) Worker
     */
    public int getPosition();
}
