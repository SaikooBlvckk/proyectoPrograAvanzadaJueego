package com.mygdx.game;

public class MovimientoLineal implements Movimiento{
    @Override
    public void mover(NaveEnemiga naveEnemiga) {
        // Implementación del movimiento lineal
        naveEnemiga.setX(naveEnemiga.getX() + naveEnemiga.getXSpeed());
    }
}
