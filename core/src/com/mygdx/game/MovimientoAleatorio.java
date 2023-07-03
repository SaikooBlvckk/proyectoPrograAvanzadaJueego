package com.mygdx.game;

public class MovimientoAleatorio implements Movimiento {
    @Override
    public void mover(NaveEnemiga naveEnemiga) {
        // Implementación del movimiento aleatorio
        int move;
        move = (int) ((int) naveEnemiga.getX() + Math.random() * naveEnemiga.getXSpeed());
        naveEnemiga.setX(move);
    }
}
