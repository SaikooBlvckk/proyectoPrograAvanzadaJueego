import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Nave4;

public class ConcreteShipBuilder extends ShipBuilder {

    @Override
    public void buildSprite() {
        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("spaceship.png")));
        sprite.setPosition(100, 100);
        ship.setSprite(sprite);
    }

    @Override
    public void buildBody() {
        World world = new World();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(ship.getSprite().getX(), ship.getSprite().getY());
        Body body = world.createBody(bodyDef);
        ship.setBody(body);
    }

    @Override
    public void buildShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ship.getSprite().getWidth()/2, ship.getSprite().getHeight()/2);
        ship.setShape(shape);
    }
    // Implementa aquí cualquier otro método "build" que necesites.
}

