package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.IOException;
import java.util.ArrayList;

public abstract class StageBuilder {
    MyGdxGame game;
    public Stage emptyStage = new Stage();
    public ArrayList<Texture> texturesToLoad = new ArrayList<>();
    public abstract Button createButton(Texture inactiveTexture, Texture textureForButton);
    public abstract void setButtonPosition(Button buttonTemp);
    public abstract Stage getStage() throws IOException;
    public Button createButton(){
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new Texture("sample.png"));
        buttonStyle.down = new TextureRegionDrawable(new Texture("sample.png"));
        buttonStyle.over = new TextureRegionDrawable(new Texture("sample.png"));
        return new Button(buttonStyle);
    }
    public abstract void setBackground();
}
