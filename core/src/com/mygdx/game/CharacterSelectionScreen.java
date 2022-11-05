package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;


import java.io.IOException;


public class CharacterSelectionScreen extends ScreenAdapter {

    MyGdxGame game;
    Stage ccSelectStage;
    Music mmMusic;
    public CharacterSelectionScreen(MyGdxGame game){
        this.game = game;
    }

    @Override
    public void show() {

        try {
            ccSelectStage = StageController.getCCSelectStage(this.game);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        Gdx.input.setInputProcessor(ccSelectStage);
        

        mmMusic = StageController.getMMMusic();

    }

    @Override
    public void render(float delta) {
    	super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ccSelectStage.act();
        ccSelectStage.draw();


    }

    @Override
	public void hide(){
    	mmMusic.dispose();
	}


}