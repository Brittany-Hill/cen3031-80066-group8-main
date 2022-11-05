package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.IOException;

public class StageController {
    static StageBuilderMainMenu mainMenu;
    static StageBuilderCharacterSelect ccSelect;
    static StageBuilderCombat combat;
    static SGDialog dialog;
    static InbetweenStage loading;


    public static Stage getMainMenuStage(MyGdxGame game){
        mainMenu = new StageBuilderMainMenu(game);
        return mainMenu.getStage();
    }

    public static Stage getCCSelectStage(MyGdxGame game) throws IOException {
        ccSelect = new StageBuilderCharacterSelect(game);
        return ccSelect.getStage();
    }

    public static Stage getCombatStage(MyGdxGame game, int combatNumber){
        combat = new StageBuilderCombat(game,combatNumber);
        return combat.getStage();
    }

    public static Stage getDialog(MyGdxGame gameStage, Stage previousStage, int combatNumber){
        dialog = new SGDialog(gameStage,previousStage,combatNumber);
        return dialog.getStage();
    }

    public static Stage getLoadingScreen(MyGdxGame game) throws IOException {
        loading = new InbetweenStage(game);
        return loading.getStage();
    }

    public static Stage getDialog(MyGdxGame game, Stage stageBefore, String dialogFileName, int combatNumber) {
        dialog = new SGDialog(game,stageBefore,dialogFileName, combatNumber);
        return dialog.getStage();
    }

    public static Music getMMMusic(){
        return mainMenu.getMmMusic();
    }
    public static Music getCombatMusic(){
        return combat.getCombatMusic();
    }
}
