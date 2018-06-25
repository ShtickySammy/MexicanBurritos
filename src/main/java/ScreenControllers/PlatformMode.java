package ScreenControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.util.Duration;
import levelparts.TestSquare;
import main.Ball;
import org.jbox2d.dynamics.Body;

import java.util.Random;

import main.Utils;


public class PlatformMode extends Group implements Runnable, ScreenNode {


    public PlatformMode(){


        //main.Ball array for hold the  balls
        final Ball[] balls = new Ball[Utils.NO_OF_BALLS];

        Random r = new Random(System.currentTimeMillis());

        /**
         * Generate balls and position them on random locations.
         * Random locations between 5 to 95 on x axis and between 100 to 500 on y axis
         */
        for(int i = 0; i< Utils.NO_OF_BALLS; i++) {
            balls[i]=new Ball(r.nextInt(90)+5,r.nextInt(400)+100);
        }

        //Add ground to the application, this is where balls will land
        Utils.addGround(100, 10);

        //Add left and right walls so balls will not move outside the viewing area.
        Utils.addWall(0,100,1,100); //Left wall
        Utils.addWall(99,100,1,100); //Right wall


        final Timeline timeline = new Timeline();
        //clock never stops
        timeline.setCycleCount(Timeline.INDEFINITE);

        //60 fps
        Duration duration = Duration.seconds(1.0/60.0); // Set duration for frame.

        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position
        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                /****MAIN GAME LOOP MAIN GAME LOOP MAIN GAME LOOP MAIN GAME LOOP****/

                //Create time step. Set Iteration count 8 for velocity and 3 for positions
                Utils.world.step(1.0f/60.f, 8, 3);



                //THIS IS FINDING THEIR NEW LOCATION BASED ON ENGINE AND SETTING IT TO THEIR NEW LOCATION
                //Move balls to the new position computed by JBox2D
                for(int i = 0; i< Utils.NO_OF_BALLS; i++) {
                    Body body = (Body)balls[i].node.getUserData();
                    float xpos = Utils.toPixelPosX(body.getPosition().x);
                    float ypos = Utils.toPixelPosY(body.getPosition().y);
                    balls[i].node.setLayoutX(xpos);
                    balls[i].node.setLayoutY(ypos);
                }
            }
        };


        /**
         * Set ActionEvent and duration to the KeyFrame.
         * The ActionEvent is trigged when KeyFrame execution is over.
         */
        KeyFrame frame = new KeyFrame(duration, ae, null,null);
        //refresh/add next frame
        timeline.getKeyFrames().add(frame);


        /** setting up start button in tutorial **/
        //Create button to start simulation.
        final Button btn = new Button();
        btn.setLayoutX((Utils.WIDTH/2));
        btn.setLayoutY((Utils.HEIGHT-30));
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //this starts timeline
                timeline.playFromStart();
                btn.setVisible(false);
            }
        });

        //Add button to the root group
        getChildren().add(btn);

        //Add all balls to the root group
        for(int i = 0; i< Utils.NO_OF_BALLS; i++) {
            getChildren().add(balls[i].node);
        }


    }


    @Override
    public void run() {

    }
}
