package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.ArrayList;

public class StageBuilderMainMenu extends StageBuilder {
    int buttonsTotal = 0;
    MyGdxGame mmGame;
    Music mmMusic;
    Dialog settings;

    Skin UISkin = new Skin(Gdx.files.internal("pixthulhu-ui.json"));
    ProgressBar volumeBar;


    public StageBuilderMainMenu(MyGdxGame game){
        this.mmGame = game;
        this.emptyStage = new Stage();
        this.texturesToLoad = setMMTextures();
        this.mmMusic = Gdx.audio.newMusic(Gdx.files.internal("./audio/Cyber-Dream-Loop.mp3"));
        this.mmMusic.setLooping(true);
        this.mmMusic.setVolume((float) 0.2);

        this.settings = new Dialog("Volume",UISkin);
        setBackground();
        addDialog();
    }

    private void addDialog() {
        Button temp = new TextButton("+",UISkin);
        setVolumeUp(temp);
        Button temp2 = new TextButton("-",UISkin);
        setVolumeDown(temp2);
        this.settings.add(temp);
        this.volumeBar = new ProgressBar(0,1,0.05f,false,UISkin);
        this.volumeBar.setValue(this.mmMusic.getVolume());
        this.settings.add(volumeBar);
        this.settings.add(temp2);
    }

    @Override
    public Button createButton(Texture inactiveTexture, Texture textureForButton ){
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(inactiveTexture);
        buttonStyle.down = new TextureRegionDrawable(textureForButton);
        buttonStyle.over = new TextureRegionDrawable(textureForButton);
        return new Button(buttonStyle);
    }
    @Override
    public void setButtonPosition(Button buttonTemp){
        buttonTemp.setPosition(Gdx.graphics.getWidth() / 2f - buttonTemp.getWidth() / 2, Gdx.graphics.getHeight() / 2f - buttonsTotal * buttonTemp.getHeight());
        buttonsTotal++;
    }

    private  void setStartListener(Button button){
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                LogSystem.writeToLog("Start game clicked");
                try {
                    mmGame.setScreen(new CharacterSelectionScreen(mmGame));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setExitListener(Button button){
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                LogSystem.writeToLog("Exit game clicked");
                emptyStage.dispose();
                Gdx.app.exit();
            }
        });
    }
    @Override
    public Stage getStage(){
        emptyStage.addActor(addStartButton(createButton(texturesToLoad.get(0), texturesToLoad.get(1))));
        emptyStage.addActor(addResumeButton(createButton(texturesToLoad.get(2), texturesToLoad.get(3))));
        emptyStage.addActor(addSettingsButton(createButton(texturesToLoad.get(4), texturesToLoad.get(5))));
        emptyStage.addActor(addExitButton(createButton(texturesToLoad.get(6), texturesToLoad.get(7))));

        return  this.emptyStage;
    }

    @Override
    public void setBackground() {
        Texture texture = new Texture(("testbg5.png"));
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0,0,texture.getWidth(),texture.getWidth());
        Image background = new Image(textureRegion);
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
        background.setPosition(0,0);
        emptyStage.addActor(background);
    }

    private Button addStartButton(Button tempButt){

        Button startButton = tempButt;
        setButtonPosition(startButton);
        setStartListener(startButton);

        return startButton;
    }

    private Button addExitButton(Button tempButt){
        Button exitButton = tempButt;
        setButtonPosition(exitButton);
        setExitListener(exitButton);

        return exitButton;
    }

    private Button addResumeButton(Button tempButt){
        Button resumeButton = tempButt;
        setButtonPosition(resumeButton);
        setResumeListener(resumeButton);

        return resumeButton;
    }

    private Button addSettingsButton(Button tempButt){
        Button settingsButton = tempButt;
        setButtonPosition(settingsButton);
        setSettingsListener(settingsButton);

        return settingsButton;
    }

    private void setResumeListener(Button resumeButton) {
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                LogSystem.writeToLog("Resume game clicked");
            }
        });
    }

    private void setSettingsListener(Button settingsButton){
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                settings.addListener(new InputListener() {
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        if (x < 0 || x > settings.getWidth() || y < 0 || y > settings.getHeight()){
                            settings.hide();
                            return true;
                        }
                        return false;
                    }
                });
                settings.show(emptyStage);

                LogSystem.writeToLog("Settings clicked");
            }
        });
    }

    private void setVolumeUp(Button tempButt){
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                if(mmMusic.getVolume() < 1.0) {
                    mmMusic.setVolume( mmMusic.getVolume() + (float) 0.05);
                    System.out.println( mmMusic.getVolume());
                    volumeBar.setValue(mmMusic.getVolume());
                }

            }
        });
    }
    private void setVolumeDown(Button tempButt){
        tempButt.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                if(mmMusic.getVolume() > 0.1) {
                    mmMusic.setVolume(mmMusic.getVolume() - (float) 0.05);
                    System.out.println(mmMusic.getVolume());
                    volumeBar.setValue(mmMusic.getVolume());
                }
                else {
                    mmMusic.setVolume(0);
                }

            }
        });
    }

    public Music getMmMusic(){
        return this.mmMusic;
    }

    private ArrayList<Texture> setMMTextures(){
        ArrayList<Texture> mainMenuTextures = new ArrayList<>();
        mainMenuTextures.add(new Texture("Vassal_NewGame_Inactive.png"));
        mainMenuTextures.add(new Texture("Vassal_NewGame_Active.png"));
        mainMenuTextures.add(new Texture("Vassal_Resume_Inactive.png"));
        mainMenuTextures.add(new Texture("Vassal_Resume_Active.png"));
        mainMenuTextures.add(new Texture("Vassal_Settings_Active.png"));
        mainMenuTextures.add(new Texture("Vassal_Settings_Inactive.png"));
        mainMenuTextures.add(new Texture("Vassal_Exit_Inactive.png"));
        mainMenuTextures.add(new Texture("Vassal_Exit_Active.png"));

        return mainMenuTextures;
    }
}