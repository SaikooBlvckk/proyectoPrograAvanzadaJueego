package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class SpaceNavigation extends Game {
	private String nombreJuego = "Space Navigation";
	private SpriteBatch batch;
	private BitmapFont font;
	private GameManager gameManager;	

	public void create() {
		gameManager = GameManager.getInstance();
		batch = new SpriteBatch();
		font = new BitmapFont(); // usa Arial font x defecto
		font.getData().setScale(2f);
		Screen ss = new PantallaMenu(this);
		this.setScreen(ss);
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public int getHighScore() {
		return gameManager.getScore();
	}

	public void setHighScore(int highScore) {
		gameManager.increaseScore(highScore - gameManager.getScore());
	}
}
