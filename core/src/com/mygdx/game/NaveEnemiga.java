package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class NaveEnemiga extends Enemigo{

    public NaveEnemiga(int x, int y, int xSpeed, int ySpeed, Texture tx) {
        setSpr(new Sprite(tx));
    	setX(x);
    	
        getSpr().setPosition(getX(), getY());
        setXSpeed(xSpeed);
        setySpeed(ySpeed);
    }

    public void draw(SpriteBatch batch) {
        getSpr().draw(batch);
    }

    public void atacar(Nave4 nave) {
        //implementar ataque

        /*// disparo
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bullet bala = new Bullet(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5, 0, 3, txBala);
            juego.agregarBala(bala);
            soundBala.play();
        }*/
        
    }

}