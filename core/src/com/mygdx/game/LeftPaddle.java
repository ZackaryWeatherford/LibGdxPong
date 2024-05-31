package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.extension.*;

public class LeftPaddle extends GameObject {


    public LeftPaddle(Vector2 position, Sprite sprite, ID id) {
        super(position, sprite, id);
        Engine.addInput(this);
    }

    @Override
    public void input(){

        if(Gdx.input.isKeyPressed(Input.Keys.W) && position.y + dimension.height<= (Window.camera.viewportHeight / 2)){
            setPosition(Movement.moveUp(position, 200));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) && position.y >= -1*(Window.camera.viewportHeight / 2)){
            setPosition(Movement.moveDown(position, 200));
        }

    }

    @Override
    public void logic(){

    }

}
