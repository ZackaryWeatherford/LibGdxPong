package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.extension.Engine;
import com.mygdx.game.extension.GameObject;
import com.mygdx.game.extension.ID;

public class Pong extends ApplicationAdapter {

	Engine engine;

	LeftPaddle leftPaddle;
	RightPaddle rightPaddle;
	Ball ball;


	@Override
	public void create () {
		engine = new Engine(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		leftPaddle = new LeftPaddle(new Vector2(-250f, -50), new Sprite(new Texture("leftPaddle.png")), new ID());
		rightPaddle = new RightPaddle(new Vector2(250f, -50), new Sprite(new Texture("rightPaddle.png")), new ID());
		ball = new Ball(new Vector2(400,0), new Sprite(new Texture("ball.png")), new ID(), rightPaddle, leftPaddle);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		engine.update();

	}
	
	@Override
	public void dispose () {
		Engine.window.batch.dispose();
	}
}
