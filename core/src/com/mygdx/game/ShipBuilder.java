package com.mygdx.game;

public abstract class ShipBuilder {
    protected Nave4 nave;

    public Nave4 getNave() {
        return nave;
    }

    public void crearNuevaNave() {
        nave = new Nave4();
    }

    public abstract void construirCasco();
    public abstract void construirPropulsores();
    public abstract void construirArmas();
    public abstract void construirEscudos();
    
    // Agrega aquí cualquier otro método abstracto "build" que necesites.
}
