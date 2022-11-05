package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.IOException;
import java.util.ArrayList;

public class StageBuilderCharacterSelect extends StageBuilder {
    int buttonsTotal = 0;
    MyGdxGame game;
    Character tempCharacter;
    SaveFile saveFile = new SaveFile();
    Skin UISkin = new Skin(Gdx.files.internal("pixthulhu-ui.json"));
    Dialog bgOne = new Dialog("Melee",UISkin);
    Dialog bgTwo = new Dialog("Ranged", UISkin);
    Dialog bgThree = new Dialog("Mage", UISkin);

    public StageBuilderCharacterSelect(MyGdxGame game) throws IOException {
        this.game = game;
        this.emptyStage = new Stage();
        this.texturesToLoad = setCCTextures();

        setBackground();
    }

    private void setClassChoiceListenerandPosition(Button tempButt, int i) {
        if(i == 0) {
                selectMeleeClass(tempButt);
        }else{
            if(i==1) {
                selectRangedClass(tempButt);
            }else {
                if(i==2) {
                    selectMageClass(tempButt);
                }else{
                    selectBackButton(tempButt);
                }
            }
        }

        emptyStage.addActor(tempButt);
    }


    public Button createButton(Texture textureForButton) {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(textureForButton);
        buttonStyle.down = new TextureRegionDrawable(textureForButton);
        buttonStyle.over = new TextureRegionDrawable(textureForButton);
        return new Button(buttonStyle);
    }

    @Override
    public Button createButton(Texture inactiveTexture, Texture textureForButton) {
        //For future use of active/inactive Textures for character choices
        return null;
    }

    @Override
    public void setButtonPosition(Button buttonTemp) {
        buttonsTotal++;
        buttonTemp.setPosition(Gdx.graphics.getWidth()/2f - buttonTemp.getWidth()/2 - (20 * buttonsTotal), Gdx.graphics.getWidth() / 2f - buttonTemp.getHeight());

    }

    @Override
    public Stage getStage() {
        emptyStage.addActor(bgOne);
        emptyStage.addActor(bgTwo);
        emptyStage.addActor(bgThree);
        emptyStage.addActor(createCharacterButtons(0));
        emptyStage.addActor(createCharacterButtons(1));
        emptyStage.addActor(createCharacterButtons(2));
        emptyStage.addActor(createCharacterButtons(3));
        return emptyStage;
    }

    @Override
    public void setBackground() {
        Texture texture = new Texture(("testbg.png"));
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,texture.getWidth(),texture.getWidth());
        Image background = new Image(textureRegion);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        background.setPosition(0,0);
        emptyStage.addActor(background);
    }

    private Button createCharacterButtons(int i) {

        Button tempButt = createButton(texturesToLoad.get(i));
        setClassChoiceListenerandPosition(tempButt,i);
        return tempButt;
    }
    private ArrayList<Texture> setCCTextures(){
        ArrayList<Texture> ccSelectTextures = new ArrayList<>();
        ccSelectTextures.add(new Texture("Sword.png"));
        ccSelectTextures.add(new Texture("Vassal_Bow.png"));
        ccSelectTextures.add(new Texture("Staff.png"));
        ccSelectTextures.add(new Texture("Vassal_Back.png"));

        return ccSelectTextures;
    }
    private void selectMeleeClass(Button tempButt){
        tempButt.setSize(140,300);
        bgOne.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2 - 220, Gdx.graphics.getWidth() / 2f - tempButt.getHeight() + 30);
        tempButt.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2 - 200, Gdx.graphics.getWidth() / 2f - tempButt.getHeight() + 50);

        bgOne.setSize(180,340);
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    tempCharacter = new Character(1);
                    saveFile.SaveToFile(tempCharacter);
                    game.setScreen(new LoadingScreen(game));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogSystem.writeToLog("Character 0 chosen");
            }
        });
    }
    private void selectRangedClass(Button tempButt){
        tempButt.setSize(140,300);
        bgTwo.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2 - 20, (Gdx.graphics.getWidth()/2f - tempButt.getHeight() + 30));
        tempButt.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2, (Gdx.graphics.getWidth()/2f - tempButt.getHeight() + 50));

        bgTwo.setSize(180,340);
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    tempCharacter = new Character(2);
                    saveFile.SaveToFile(tempCharacter);
                    game.setScreen(new LoadingScreen(game));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogSystem.writeToLog("Character 1 chosen");
            }
        });
    }
    private void selectMageClass(Button tempButt){
        tempButt.setSize(140,300);
        bgThree.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2 + 180, (Gdx.graphics.getWidth()/2f - tempButt.getHeight() + 30));
        tempButt.setPosition(Gdx.graphics.getWidth()/2f - tempButt.getWidth()/2 + 200, (Gdx.graphics.getWidth()/2f - tempButt.getHeight() + 50));

        bgThree.setSize(180,340);
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    tempCharacter = new Character(3);
                    saveFile.SaveToFile(tempCharacter);
                    game.setScreen(new LoadingScreen(game));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogSystem.writeToLog("Character 2 chosen");
            }
        });
    }
    private void selectBackButton(Button tempButt){
        tempButt.setPosition(0, 0);
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    game.setScreen(new MainMenu(game));
                } catch (Exception e) {
                    LogSystem.writeToLog("Error switching from CCScreen to Main Menu");
                }
                LogSystem.writeToLog("Character 2 chosen");
            }
        });
    }
}
