package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	//private Music gameMusic;
	private int score;
	private int ronda;
	private int velXAsteroides; 
	private int velYAsteroides; 
	private int cantAsteroides;
	
	private Nave4 nave;
	private  ArrayList<Ovni> Ovnis1 = new ArrayList<>();
	private  ArrayList<Ovni> Ovnis2 = new ArrayList<>();
	private  ArrayList<NaveEnemiga> naveEnemigas1 = new ArrayList<>();
	private  ArrayList<NaveEnemiga> naveEnemigas2 = new ArrayList<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
	private  ArrayList<DisparoDoble> pDispDoble = new ArrayList<>();
	private  ArrayList<VidaExtra> pVidExt = new ArrayList<>();

	private Texture mTextureBg = new Texture("Space.png");


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
			int velXAsteroides, int velYAsteroides, int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		//gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		//gameMusic.setLooping(true);
		//gameMusic.setVolume(0.5f);
		//gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    nave = new Nave4(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("MainShip3.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
	    				new Texture(Gdx.files.internal("Rocket2.png")), 
	    				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"))); 
        nave.setVidas(vidas);

        //crear asteroides
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ovni bb = new Ovni(r.nextInt((int)Gdx.graphics.getWidth()),
						50+r.nextInt((int)Gdx.graphics.getHeight()-50),
						velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4),
						new Texture(Gdx.files.internal("ShipOrange.png")));	   
	  	    Ovnis1.add(bb);
	  	    Ovnis2.add(bb);
	  	}

		//crear naves enemigas
        Random j = new Random();
	    for (int i = 0; i < 3; i++) {
	        NaveEnemiga bb = new NaveEnemiga(j.nextInt((int)Gdx.graphics.getWidth()),
						50+j.nextInt((int)Gdx.graphics.getHeight()-50),
						velXAsteroides+r.nextInt(4), velYAsteroides+r.nextInt(4),
						new Texture(Gdx.files.internal("ShipBlue.png")));
	  	    naveEnemigas1.add(bb);
	  	    naveEnemigas2.add(bb);
	  	}

		//crear potenciador DisparoDoble
		Random a = new Random();
	    for (int i = 0; i < 1; i++) {
	        DisparoDoble disp = new DisparoDoble(a.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+a.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+a.nextInt(10), velXAsteroides+a.nextInt(4), 
	  	            new Texture(Gdx.files.internal("powerup.png")));	   
	  	    pDispDoble.add(disp);
	  	}

		//crear potenciador vida Extra
		Random b = new Random();
	    for (int i = 0; i < 1; i++) {
	        VidaExtra vidExt = new VidaExtra(b.nextInt((int)Gdx.graphics.getWidth()),
	  	            50+b.nextInt((int)Gdx.graphics.getHeight()-50),
	  	            20+b.nextInt(10), velXAsteroides+b.nextInt(4), 
	  	            new Texture(Gdx.files.internal("orb.png")));	   
	  	    pVidExt.add(vidExt);
	  	}
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		game.getBatch().draw(mTextureBg, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.getBatch().end();
      	batch.begin();
	  	dibujaEncabezado();
      	if (!nave.estaHerido()) {
		  // colisiones entre balas y asteroides y su destruccion  
	    	  for (int i = 0; i < balas.size(); i++) {
		           	Bullet b = balas.get(i);
		       	    b.update();
		           	for (int j = 0; j < Ovnis1.size(); j++) {    
		             	if (b.checkCollision(Ovnis1.get(j))) {          
		   	        		explosionSound.play();
	        	    		Ovnis1.remove(j);
	            			Ovnis2.remove(j);
	            			j--;
	            			score +=10;
	           		    }   	  
	  	       		}
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }

			  for (int i = 0; i < balas.size(); i++) {
				Bullet b = balas.get(i);
				b.update();
				for (int j = 0; j < naveEnemigas1.size(); j++) {    
				  if (b.checkCollision(naveEnemigas1.get(j))) {          
					 explosionSound.play();
					 naveEnemigas1.remove(j);
					 naveEnemigas2.remove(j);
					 j--;
					 score +=10;
				  }   	  
				  }
					
			 //   b.draw(batch);
				if (b.isDestroyed()) {
					balas.remove(b);
					i--; //para no saltarse 1 tras eliminar del arraylist
				}
		  }
		      //actualizar movimiento de asteroides dentro del area
		      for (Ovni ball : Ovnis1) {
		          ball.update();
		      }
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<Ovnis1.size();i++) {
		    	Ovni ball1 = Ovnis1.get(i);   
		        for (int j=0;j<Ovnis2.size();j++) {
		          Ovni ball2 = Ovnis2.get(j); 
		          if (i<j) {
		        	  ball1.checkCollision(ball2);
		     
		          }
		        }
		      } 

			  //actualizar movimiento de asteroides dentro del area
		      for (NaveEnemiga ball : naveEnemigas1) {
				ball.update();
			}
			//colisiones entre asteroides y sus rebotes  
			for (int i=0;i<naveEnemigas1.size();i++) {
			  NaveEnemiga NaveEnemiga1 = naveEnemigas1.get(i);   
			  for (int j=0;j<naveEnemigas2.size();j++) {
				NaveEnemiga NaveEnemiga2 = naveEnemigas2.get(j); 
				if (i<j) {
					NaveEnemiga1.checkCollision(NaveEnemiga2);
		   
				}
			  }
			}
	      }
	      //dibujar balas
	     for (Bullet b : balas) {       
	          b.draw(batch);
	      }
	      nave.draw(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      for (int i = 0; i < Ovnis1.size(); i++) {
	    	    Ovni b=Ovnis1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 Ovnis1.remove(i);
	            	 Ovnis2.remove(i);
	            	 i--;
              }   	  
  	        }

			//dibujar asteroides y manejar colision con nave
			for (int i = 0; i < naveEnemigas1.size(); i++) {
	    	    NaveEnemiga b=naveEnemigas1.get(i);
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.checkCollision(b)) {
		            //asteroide se destruye con el choque             
	            	 naveEnemigas1.remove(i);
	            	 naveEnemigas2.remove(i);
	            	 i--;
              }   	  
  	        }

			for (DisparoDoble potDisp : pDispDoble) {
				potDisp.update();
			}

			for (int i = 0; i < pDispDoble.size(); i++) {
	    	    DisparoDoble b=pDispDoble.get(i);
	    	    b.draw(batch);
				if(nave.checkCollision(b)){
					pDispDoble.remove(i);
					nave.potenciador(b);
					i--;
				}
			}

			for (VidaExtra potVidaExt : pVidExt) {
				potVidaExt.update();
			}

			for (int i = 0; i < pVidExt.size(); i++) {
	    	    VidaExtra b=pVidExt.get(i);
	    	    b.draw(batch);
				if(nave.checkCollision(b)){
					pVidExt.remove(i);
					i--;
				}
			}
	      
	      if (nave.estaDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      //nivel completado
	      if (Ovnis1.size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+1, velYAsteroides+1, cantAsteroides+3);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }
	    	 
	}
    
    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		//gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		//this.gameMusic.dispose();
	}
   
}
