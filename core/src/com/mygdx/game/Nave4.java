package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave4 {
    private boolean destruida = false;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private boolean consumioDispDoble;
    private DisparoDoble disparoDoble;

    private String casco;
    private String propulsores;
    private String armas;
    private String escudos;

    public Nave4(){

    }

    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        sonidoHerido = soundChoque;
        this.soundBala = soundBala;
        this.txBala = txBala;
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        spr.setBounds(x, y, 45, 45);

        disparoDoble = new DisparoDoble();
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = spr.getX();
        float y = spr.getY();
        if (!herido) {
            // que se mueva con teclado
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                xVel = -5;
                yVel = 0;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                xVel = 5;
                yVel = 0;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                xVel = 0;
                yVel = -5;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                xVel = 0;
                yVel = 5;
            }


            // que se mantenga dentro de los bordes de la ventana
            if (x + xVel < 0 || x + xVel + spr.getWidth() > Gdx.graphics.getWidth())
                xVel *= -1;
            if (y + yVel < 0 || y + yVel + spr.getHeight() > Gdx.graphics.getHeight())
                yVel *= -1;

            spr.setPosition(x + xVel, y + yVel);

            spr.draw(batch);

            // disparo
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                Bullet bala = new Bullet(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5, 0, 3, txBala);
                juego.agregarBala(bala);
                soundBala.play();
            }

            // Interacción con DisparoDoble
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (this.consumioDispDoble == true) {
                    disparoDoble.disparo();
                    Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-20,spr.getY()+ spr.getHeight()-20,0,3,txBala);
	                juego.agregarBala(bala);
                    desPotenciador(disparoDoble);
                }
            }
        } else {
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            spr.draw(batch);
            spr.setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    public boolean checkCollision(DisparoDoble b) {
        if (b.getArea().overlaps(spr.getBoundingRectangle())){
            consumioDispDoble = true;
            return true;
        }
        consumioDispDoble = false;
        return false;
    }

    public boolean checkCollision(VidaExtra b) {
        if (b.getArea().overlaps(spr.getBoundingRectangle())){
            this.vidas++;
            return true;
        }
        return false;
    }

    public boolean checkCollision(Ovni b) {
        if (!herido && b.getArea().overlaps(spr.getBoundingRectangle())) {
            // rebote
            if (xVel == 0) xVel += b.getXSpeed() / 2;
            if (b.getXSpeed() == 0) b.setXSpeed(b.getXSpeed() + (int) xVel / 2);
            xVel = -xVel;
            b.setXSpeed(-b.getXSpeed());

            if (yVel == 0) yVel += b.getYSpeed() / 2;
            if (b.getYSpeed() == 0) b.setySpeed(b.getYSpeed() + (int) yVel / 2);
            yVel = -yVel;
            b.setySpeed(-b.getYSpeed());

            // despegar sprites
            /* int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont < xVel) {
               spr.setX(spr.getX() + Math.signum(xVel));
            } */

            // actualizar vidas y herir
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            if (vidas <= 0)
                destruida = true;
            return true;
        }
        return false;
    }

    public boolean checkCollision(NaveEnemiga b) {
        if (!herido && b.getArea().overlaps(spr.getBoundingRectangle())) {
            // rebote
            if (xVel == 0) xVel += b.getXSpeed() / 2;
            if (b.getXSpeed() == 0) b.setXSpeed(b.getXSpeed() + (int) xVel / 2);
            xVel = -xVel;
            b.setXSpeed(-b.getXSpeed());

            if (yVel == 0) yVel += b.getYSpeed() / 2;
            if (b.getYSpeed() == 0) b.setySpeed(b.getYSpeed() + (int) yVel / 2);
            yVel = -yVel;
            b.setySpeed(-b.getYSpeed());

            // despegar sprites
            /* int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont < xVel) {
               spr.setX(spr.getX() + Math.signum(xVel));
            } */

            // actualizar vidas y herir
            vidas = vidas - 2;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            if (vidas <= 0)
                destruida = true;
            return true;
        }
        return false;
    }

    public boolean estaDestruido() {
        return !herido && destruida;
    }

    public boolean estaHerido() {
        return herido;
    }

    public void setSprite(Sprite spr){
        this.spr = spr;
    }

    public int getVidas() {
        return vidas;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public int getY() {
        return (int) spr.getY();
    }

    public void setVidas(int vidas2) {
        vidas = vidas2;
    }

    public void potenciador(DisparoDoble b){
        if (this.consumioDispDoble == true){
            b.activa();
        }
    }

    public void desPotenciador(DisparoDoble b){
        if(b.getEstado() == 2){
            this.consumioDispDoble = false;
        }
    }

     public String getCasco() {
        return casco;
    }

    public String getPropulsores() {
        return propulsores;
    }

    public String getArmas() {
        return armas;
    }

    public String getEscudos() {
        return escudos;
    }

    public void setCasco(String casco) {
        this.casco = casco;
    }

    public void setPropulsores(String propulsores) {
        this.propulsores = propulsores;
    }

    public void setArmas(String armas) {
        this.armas = armas;
    }

    public void setEscudos(String escudos) {
        this.escudos = escudos;
    }


}
