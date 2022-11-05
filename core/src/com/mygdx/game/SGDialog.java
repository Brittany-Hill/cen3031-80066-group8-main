package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SGDialog extends StageBuilder{
    Stage sceneBeforeDialog;
    MyGdxGame game;
    Button screenButton;
    Dialog emptyBox;
    int temp = 0;
    Skin UISkin = new Skin(Gdx.files.internal("pixthulhu-ui.json"));
    Label dialog;
    String fileName;
    int combatNumber;
    ArrayList<String> dialogString;


    public SGDialog(MyGdxGame game, Stage currentStage,int combatNumber){

        this.game = game;
        this.emptyStage = currentStage;
        this.sceneBeforeDialog = currentStage;
        this.screenButton = new Button();
        this.screenButton.setSize(currentStage.getWidth(),currentStage.getHeight());
        this.dialog = new Label("Hello.",UISkin);        //33Characters per line
        this.emptyBox = new Dialog("",UISkin);
        this.dialog.setPosition(20,Gdx.graphics.getHeight()/8f + 10);
        this.emptyBox.setWidth(Gdx.graphics.getWidth());
        this.emptyBox.setHeight(Gdx.graphics.getHeight()/4f);

        this.fileName = "Dialog/Intro.txt";
        this.dialogString = DialogReader.dialogScanner("Dialog/Intro.txt");
        this.combatNumber = combatNumber;

    }

    public SGDialog(MyGdxGame game, Stage stageBefore, String dialogFileName,int combatNumber) {
        this.game = game;
        this.emptyStage = stageBefore;
        this.sceneBeforeDialog = stageBefore;
        this.screenButton = new Button();
        this.dialogString = DialogReader.dialogScanner(dialogFileName);
        this.screenButton.setSize(stageBefore.getWidth(),stageBefore.getHeight());
        this.dialog = new Label(dialogString.get(0),UISkin);        //33Characters per line
        this.emptyBox = new Dialog("",UISkin);
        this.dialog.setPosition(20,Gdx.graphics.getHeight()/8f + 10);
        this.emptyBox.setWidth(Gdx.graphics.getWidth());
        this.emptyBox.setHeight(Gdx.graphics.getHeight()/4f);
        this.fileName = dialogFileName;
        this.temp++;
        this.combatNumber = combatNumber;
    }

    @Override
    public Button createButton(Texture inactiveTexture, Texture textureForButton) {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(textureForButton);
        buttonStyle.down = new TextureRegionDrawable(textureForButton);
        buttonStyle.over = new TextureRegionDrawable(textureForButton);
        return new Button(buttonStyle);
    }

    @Override
    public void setButtonPosition(Button buttonTemp) {
        buttonTemp.setPosition(emptyStage.getWidth(),emptyStage.getHeight());
    }



    @Override
    public Stage getStage(){
        Texture buttonTexture = new Texture("test.png");
        setBackground();
        Button tempButt = createButton(buttonTexture,buttonTexture);
        onClick(tempButt);
        emptyStage.addActor(emptyBox);
        emptyStage.addActor(dialog);
        emptyStage.addActor(tempButt);
        return emptyStage;
    }

    @Override
    public void setBackground() {
        Texture bgTexture = new Texture("dialogOverlay.png");
        bgTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        TextureRegion bgRegion = new TextureRegion(bgTexture);
        Image background = new Image(bgRegion);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        emptyStage.addActor(background);
    }

    public Stage getPreviousStage(){
        return this.sceneBeforeDialog;
    }

    private void onClick(Button tempButt){
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                dialog.setText(dialogString.get(temp));

                if(temp == dialogString.size() - 1){
                        if(fileName == "Dialog/Intro.txt") {
                            game.setScreen(new CombatScreen(game,combatNumber));

                        }else{
                            game.setScreen(new CombatScreen(game,combatNumber));
                        }

                }
                LogSystem.writeToLog("Dialog continued");
                temp++;
            }
        });
    }

}
