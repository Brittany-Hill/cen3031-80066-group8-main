package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.io.IOException;
import java.util.ArrayList;

public class StageBuilderCombat extends StageBuilder {
    MyGdxGame game;
    Music combatMusic;
    public static Character player;
    private static Image enemyImage;
    public static String tempString;

    public StageBuilderCombat(MyGdxGame game, int combatNumber){
        this.game = game;
        this.player = CombatScreen.player;
        this.combatMusic = Gdx.audio.newMusic(Gdx.files.internal("./audio/Goblin-Loop.mp3"));
        this.combatMusic.setLooping(true);
        this.combatMusic.setVolume((float) 0.05);
    }

    @Override
    public Button createButton(Texture inactiveTexture, Texture textureForButton) {
        return null;
    }

    @Override
    public void setButtonPosition(Button buttonTemp) {

    }

    @Override
    public Stage getStage(){
        setBackground();
        cardToShow();
        player.setTurn(true);
        return this.emptyStage;
    }

    @Override
    public void setBackground() {
        int randomBG = (int) ((Math.random() * 7) + 1);
        Texture bgTexture = new Texture(Gdx.files.internal("testbg" + randomBG + ".png"));
        bgTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        TextureRegion bgRegion = new TextureRegion(bgTexture);
        Image background = new Image(bgRegion);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
        emptyStage.addActor(background);
    }

    private void cardToShow(){
        ArrayList<Card> cards = getPlayerInventory();
        CardVisualizer cv;
        for (int i = 0; i < player.getInventory().getHandCount(); i++) {
            cv = new CardVisualizer(cards.get(i));
            cv.card.setPosition(242 * i, 0);
            emptyStage.addActor(cv.getCard());
        }
    }

    public static Image enemyVisualizer(Character enemy) {
        Texture texture;
        if(enemy.getType()==4){
             texture = new Texture(("EnemyPlaceholder.png"));
        }else{
            if(enemy.getType()==5){
                texture = new Texture(("Vassel_enemy2.png"));
            }else{
                texture = new Texture(("Vassal_enemy3.png"));
            }
        }
        TextureRegion textureRegion = new TextureRegion(texture);
        Image enemyTemp = new Image(textureRegion);
        enemyTemp.setScale((float) 0.62);
        enemyTemp.setPosition(220, 220);
        enemyImage = enemyTemp;
        return enemyTemp;
    }

    private ArrayList<Card> getPlayerInventory() {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = 0; i < player.getInventory().getHandCount(); i++) {
            System.out.println(player.getInventory().getCard(i));
            temp.add(player.getInventory().getCard(i));
        }
        return temp;
    }

    public static Label.LabelStyle generateHealthLabel() {
        Label.LabelStyle healthLabelStyle = new Label.LabelStyle();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Hardsign.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        generator.generateFont(parameter);
        BitmapFont font;
        font = generator.generateFont(parameter);
        generator.dispose();
        healthLabelStyle.font = font;
        return healthLabelStyle;
    }

    public static Label generateEnemyHPLabel(Character enemy) {
        Label enemyHealthLabel = new Label("HP: " + enemy.getHP(), generateHealthLabel());
        enemyHealthLabel.setPosition(enemyImage.getX() + 30, enemyImage.getY() - 35);
        return enemyHealthLabel;
    }

    public static Label generatePlayerHPLabel(Character player) {
        Label healthLabel = new Label("HP: " + player.getHP(), generateHealthLabel());
        healthLabel.setPosition(10, 125);
        return healthLabel;
    }

    public static Label generatePlayerManaLabel(Character player) {
        Label manaLabel = new Label("Mana: " + player.getHP(), generateHealthLabel());
        manaLabel.setPosition(510, 125);
        return manaLabel;
    }

    public static Label generateActionLabel() {
        Label actionLabel = new Label("Combat Initiated!", generateHealthLabel());
        actionLabel.setPosition(Gdx.graphics.getWidth() / 2f - actionLabel.getWidth() / 2, Gdx.graphics.getHeight() / 4f + 40);
        return actionLabel;
    }
    
    public Music getCombatMusic(){
        return this.combatMusic;
    }

}