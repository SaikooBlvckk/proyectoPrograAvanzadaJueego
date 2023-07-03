package com.mygdx.game;

public class ShipDirector {
    private ShipBuilder shipBuilder;

    public ShipDirector(ShipBuilder shipBuilder) {
        this.shipBuilder = shipBuilder;
    }

    public void constructShip() {
        shipBuilder.createNewShipProduct();
        shipBuilder.buildSprite();
        shipBuilder.buildBody();
        shipBuilder.buildShape();
        // Llama aquí a cualquier otro método "build" que necesites.
    }

    public Nave4 getShip() {
        return shipBuilder.getShip();
    }
}
