package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOver extends ScreenAdapter {
    MyGdxGame game;
    Stage gameOverStage;
    Dialog gameOver;
    Skin UISkin = new Skin(Gdx.files.internal("pixthulhu-ui.json"));
    public GameOver(MyGdxGame game){
        this.game = game;
        this.gameOverStage = new Stage();
        this.gameOver = new Dialog("Game Over", UISkin);
    }

    @Override
    public void show(){
            Texture bgTexture = new Texture(Gdx.files.internal("ryleh.png"));       // Change with actual game over screen; add retry button and exit
            bgTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
            TextureRegion bgRegion = new TextureRegion(bgTexture);
            Image background = new Image(bgRegion);
            background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth());
            gameOverStage.addActor(background);
            TextButton yes = new TextButton("Yes", UISkin);
            TextButton no = new TextButton("No",UISkin);
            this.gameOver.text("You have died. Start over?");
            this.gameOver.button(yes);
            this.gameOver.button(no);
                 no.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                LogSystem.writeToLog("Game Lost");
                gameOverStage.dispose();
                Gdx.app.exit();
            }
        });
        yes.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y ) {
                game.setScreen(new MainMenu(game));
                LogSystem.writeToLog("Game Reset");
            }
        });
        this.gameOver.show(this.gameOverStage);
        Gdx.input.setInputProcessor(this.gameOverStage);
    }
    @Override
    public void render(float delta) {
        gameOverStage.act();
        gameOverStage.draw();
    }


}
