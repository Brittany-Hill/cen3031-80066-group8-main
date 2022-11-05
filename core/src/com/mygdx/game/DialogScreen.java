package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.IOException;

public class DialogScreen extends ScreenAdapter{
    MyGdxGame game;
    StageController dialog;
    Stage stage;
    Stage stageBefore;
    String dialogFileName;
    int combatNumber;

    public DialogScreen(MyGdxGame game, Stage previousStage, int combatNumber){
        this.game = game;
        this.dialog = new StageController();
        this.stageBefore = previousStage;
        this.dialogFileName = null;
        this.combatNumber = combatNumber;
    }
    public DialogScreen(MyGdxGame game, Stage previousStage, String fileName, int combatNumber){
        this.game = game;
        this.dialog = new StageController();
        this.stageBefore = previousStage;
        this.dialogFileName = fileName;
        this.combatNumber = combatNumber;
    }


    @Override
    public void show() {
        if (this.dialogFileName == null) {
        this.stage = StageController.getDialog(this.game, this.stageBefore,this.combatNumber);
    }else
    {
        this.stage = StageController.getDialog(this.game,this.stageBefore,this.dialogFileName, this.combatNumber);
    }
        Gdx.input.setInputProcessor(this.stage);
    }
    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }






}
