package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.extension.*;

public class RightPaddle extends GameObject {

    public RightPaddle(Vector2 position, Sprite sprite, ID id) {
        super(position, sprite, id);
        Engine.addInput(this);
        Engine.enableCollision(this);
        Engine.enableCollisionUpdates(this);
    }

    @Override
    public void input(){


        if(Gdx.input.isKeyPressed(Input.Keys.UP) && position.y + dimension.height<= (Window.camera.viewportHeight / 2)){
            setPosition(Movement.moveUp(position, 200));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && position.y >= -1*(Window.camera.viewportHeight / 2)){
            setPosition(Movement.moveDown(position, 200));
        }

    }

    @Override
    public void logic() {
    }



}
