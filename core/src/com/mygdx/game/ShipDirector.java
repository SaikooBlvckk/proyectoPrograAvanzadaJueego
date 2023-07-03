package com.mygdx.game;

public class ShipDirector {
    private ShipBuilder shipBuilder;

    public void setConstructorNave(ShipBuilder cn) {
        shipBuilder = cn;
    }

    public Nave4 getNave() {
        return shipBuilder.getNave();
    }

    public void construirNave() {
        shipBuilder.crearNuevaNave();
        shipBuilder.construirCasco();
        shipBuilder.construirPropulsores();
        shipBuilder.construirArmas();
        shipBuilder.construirEscudos();
    }

}
