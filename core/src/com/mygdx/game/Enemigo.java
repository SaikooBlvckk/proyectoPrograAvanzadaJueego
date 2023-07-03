package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemigo {
    private int x;
    private int y;
    private int xSpeed = 3;
    private int ySpeed = 3;
    private Sprite spr;
    private Movimiento movimiento;

    public abstract void atacar(Nave4 nave);

    public void update() {
        x += getXSpeed();
        y += getYSpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	setXSpeed(getXSpeed() * -1);
        if (y+getYSpeed() < 0 || y+getYSpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getYSpeed() * -1);
        spr.setPosition(x, y);
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Movimiento getMovimiento() {
        return this.movimiento;
    }

    public void cambiarMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public void setSpr(Sprite spr){
        this.spr = spr;
    }

    public Sprite getSpr(){
        return this.spr;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY() {
        return (int) spr.getY();
    }

    public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }

    public void checkCollision(NaveEnemiga b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getSpr().getBoundingRectangle())){
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());

            if (getYSpeed() ==0) setySpeed(getYSpeed() + b2.getYSpeed()/2);
            if (b2.getYSpeed() ==0) b2.setySpeed(b2.getYSpeed() + getYSpeed()/2);
            setySpeed(- getYSpeed());
            b2.setySpeed(- b2.getYSpeed());
        }
    }

    public void checkCollision(Ovni b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getSpr().getBoundingRectangle())){
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());

            if (getYSpeed() ==0) setySpeed(getYSpeed() + b2.getYSpeed()/2);
            if (b2.getYSpeed() ==0) b2.setySpeed(b2.getYSpeed() + getYSpeed()/2);
            setySpeed(- getYSpeed());
            b2.setySpeed(- b2.getYSpeed());
        }
    }

}
