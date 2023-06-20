package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball3 extends Asteroides{
    public Ball3(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
    	setSpr(new Sprite(tx));
    	setX(x); 
 	
        //validar que borde de esfera no quede fuera
    	if (x-size < 0) setX(x+size);
    	if (x+size > Gdx.graphics.getWidth()) setX(x-size);
         
        setY(y);
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) setY(y+size);
    	if (y+size > Gdx.graphics.getHeight()) setY(y-size);
    	
        getSpr().setPosition(getX(), getY());
        setXSpeed(xSpeed);
        setySpeed(ySpeed);
    }

    @Override
    public int calculateScore() {
        return 3 * (getXSpeed() + getySpeed());
    }
    public void resize() {
        this.getSpr().setSize(this.getSpr().getWidth() * 2, this.getSpr().getHeight() * 2);
}
}