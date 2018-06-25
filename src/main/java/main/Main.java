package main;

import java.util.Random;

import ScreenControllers.PlatformMode;
import ScreenControllers.ScreenNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import levelparts.TestSquare;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    static ScreenNode root;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Mexican Burritos");
        primaryStage.setResizable(false);

        final Group root = new PlatformMode(); //Create a group for holding all objects on the screen
        final Scene scene = new Scene(root, Utils.WIDTH, Utils.HEIGHT,Color.BLACK);

        //Draw hurdles on mouse event.
        EventHandler<MouseEvent> addHurdle = new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me) {
                //Get mouse's x and y coordinates on the scene
                float dragX = (float)me.getSceneX();
                float dragY = (float)me.getSceneY();

                //Draw ball on this location. Set balls body type to static.
                TestSquare hurdle = new TestSquare(Utils.toPosX(dragX), Utils.toPosY(dragY),2,Color.BLUE);
                //Add ball to the root group
                root.getChildren().add(hurdle.getNode());
            }
        };

        scene.setOnMouseDragged(addHurdle);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}