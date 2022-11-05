package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.IOException;

public class LoadingScreen extends ScreenAdapter {
    MyGdxGame game;
    Stage stage;
    public LoadingScreen(MyGdxGame game){
        this.game = game;
    }
    @Override
    public void show(){
        try {
            stage = StageController.getLoadingScreen(this.game);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gdx.input.setInputProcessor(stage);
        game.setScreen(new DialogScreen(game,stage,1));
    }
    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }
}
