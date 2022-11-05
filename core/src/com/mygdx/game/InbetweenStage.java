package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.io.IOException;

public class InbetweenStage extends StageBuilder{
    MyGdxGame game;

    public InbetweenStage(MyGdxGame game){
        this.game = game;
        this.emptyStage = new Stage();
    }

    @Override
    public Button createButton(Texture inactiveTexture, Texture textureForButton) {
        return null;
    }

    @Override
    public void setButtonPosition(Button buttonTemp) {

    }

    @Override
    public Stage getStage() throws IOException {
        setBackground();
        return this.emptyStage;
    }

    @Override
    public void setBackground() {
        Texture loadingScreenbg = new Texture("yes.png");
        TextureRegion loadingScreenRegion = new TextureRegion(loadingScreenbg);
        Image background = new Image(loadingScreenRegion);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        background.setPosition(0,0);
        emptyStage.addActor(background);
    }
}
