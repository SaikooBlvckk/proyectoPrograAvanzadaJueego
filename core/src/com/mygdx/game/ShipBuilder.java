package com.mygdx.game;

public abstract class ShipBuilder {
    protected Nave4 ship;

    public Nave4 getShip() {
        return ship;
    }

    public void createNewShipProduct() {
        ship = new Nave4();
    }

    public abstract void buildSprite();
    public abstract void buildBody();
    public abstract void buildShape();
    // Agrega aquí cualquier otro método abstracto "build" que necesites.
}
