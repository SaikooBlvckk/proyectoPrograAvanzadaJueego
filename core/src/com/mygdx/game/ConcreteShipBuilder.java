package com.mygdx.game;

public class ConcreteShipBuilder extends ShipBuilder {
    public void construirCasco() {
        nave.setCasco("Casco de acero");
    }

    public void construirPropulsores() {
        nave.setPropulsores("Propulsores de plasma");
    }

    public void construirArmas() {
        nave.setArmas("Láseres de alta energía");
    }

    public void construirEscudos() {
        nave.setEscudos("Escudos de energía");
    }

    // Implementa aquí cualquier otro método "build" que necesites.
}

