package levelparts;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Utils;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import static main.Utils.world;

public class TestSquare {


    /**** WALL FINAL CHARACTERISTICS ****/
    private static final float RESTITUTION = 0.6f;

    //JavaFX UI for ball
    private Node node;

    //X and Y position of the ball in JBox2D world
    //THIS IS CENTER X
    private float posX;
    private float posY;

    //main.Ball radius in pixels
    private int size;

    private Color color;

    /**
     * There are three types bodies in JBox2D – Static, Kinematic and dynamic
     * In this application static bodies (BodyType.STATIC – non movable bodies)
     * are used for drawing hurdles and dynamic bodies (BodyType.DYNAMIC–movable bodies)
     * are used for falling balls
     */

    public TestSquare(float posX, float posY, int size, Color color){
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.color = color;
        node = create();
    }

    public Node getNode() {
        return node;
    }

    private Node create(){


        /****THIS IS THE SKIN**/
        Rectangle square = new Rectangle();
        square.setWidth(size);
        square.setHeight(size);
        square.setFill(color); //set look and feel

        /**
         * Set ball position on JavaFX scene. We need to convert JBox2D coordinates
         * to JavaFX coordinates which are in pixels.
         */
        //this sets the skins location
        square.setLayoutX(Utils.toPixelPosX(posX));
        square.setLayoutY(Utils.toPixelPosY(posY));

        square.setCache(true); //Cache this object for better performance

        /**
         * Virtual invisible JBox2D body of ball. Bodies have velocity and position.
         * Forces, torques, and impulses can be applied to these bodies.
         */
        Body body = createBody();
        square.setUserData(body);
        return square;
    }

    private Body createBody() {

        float hx = Math.abs(size);
        float hy = Math.abs(size);
        PolygonShape wallshape = new PolygonShape();
        // Don't set the angle here; instead call setTransform on the body below. This allows future
        // calls to setTransform to adjust the rotation as expected.
        wallshape.setAsBox(hx, hy, new Vec2(0f, 0f), 0f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = wallshape;
        fdef.density = 1.0f;
        if (RESTITUTION>0) fdef.restitution = RESTITUTION;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        Body wall = world.createBody(bd);
        wall.createFixture(fdef);
        wall.setType(BodyType.STATIC);
        return wall;
    }

}
