package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import java.io.IOException;

public class MyGdxGame extends Game {
	public int paused = 0;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		setScreen(new MainMenu(this));
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			try{
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if(paused == 1) {
				setPauseState(0);
			}else {
				setPauseState(1);
				//setScreen(new PauseMenu(this));

			}
			try{
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}



	}
	public void setPauseState(int state) {
		paused = state;
	}
	public int getState() {
		return paused;
	}



}
