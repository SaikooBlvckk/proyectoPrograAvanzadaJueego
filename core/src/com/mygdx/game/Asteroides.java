package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Asteroides{
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Sprite spr;
    
    public abstract int calculateScore();
    public abstract void resize();

    public void update() {
        x += getXSpeed();
        y += getySpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth()){
            setXSpeed(getXSpeed() * -1);
        }
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight()){
            setySpeed(getySpeed() * -1);
        }
        spr.setPosition(x, y);
    }

    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }

    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }

    public void checkCollision(Ball2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getSpr().getBoundingRectangle())){
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());

            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed());
            // Llamar a resize cuando hay una colisión.
            this.resize();
            b2.resize();
        }
    }

    public void checkCollision(Ball3 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.getSpr().getBoundingRectangle())){
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());

            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed());
            // Llamar a resize cuando hay una colisión.
            this.resize();
            b2.resize();
        }
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

    public Sprite getSpr(){
        return this.spr;
    }

    public void setSpr(Sprite spr){
        this.spr = spr;
    }

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}
