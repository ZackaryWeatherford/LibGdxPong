package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.extension.*;

public class Ball extends GameObject {

    double angle = 200;
    float velocity = 100;

    RightPaddle rightPaddle;
    LeftPaddle leftPaddle;


    public Ball(Vector2 position, Sprite sprite, ID id, RightPaddle rightPaddle, LeftPaddle leftPaddle) {
        super(position, sprite, id);
        Engine.addLogic(this);
        Engine.enableCollision(this);
        Engine.enableCollisionUpdates(this);
        this.rightPaddle = rightPaddle;
        this.leftPaddle = leftPaddle;
    }


    @Override
    public void logic(){

        //Get new coordinates based on velocity and angle
        float newX = position.x + (float)(Movement.getDeltaSpeed(velocity) * Math.cos((angle*Math.PI) /180));
        float newY = position.y + (float)(Movement.getDeltaSpeed(velocity) * Math.sin((angle*Math.PI) /180));

        setPosition(new Vector2(newX, newY));

        if(position.x <= -1* (Window.camera.viewportWidth/2) || position.x + dimension.width >= Window.camera.viewportWidth/2
        || position.y <= -1* (Window.camera.viewportHeight/2) || position.y + dimension.height >= Window.camera.viewportHeight/2)
            wallCollision();

        if(!collidingObjects.isEmpty()){

        }


    }

    public void wallCollision(){

        //Bottom Collision
        if(position.y <= -1 * (Window.camera.viewportHeight/2))
            angle = angle > 270 ? 90 - (angle - 270) : 90 + (angle - 180) ;
        
    }

    public void paddleCollision(){

    }

}
