package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import java.util.Random;


public class MainMenu extends ScreenAdapter{
	MyGdxGame game;
	Stage stage;
	String[] phrases;
	Label.LabelStyle label1Style;
	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;
	BitmapFont font;
	Label.LabelStyle label2Style;
	Label label1;
	Label label2;
	Music mainMenuMusic;
	

	public MainMenu(MyGdxGame myGdxGame){
		this.game = myGdxGame;
	}

	@Override
	public void show(){
		stage = StageController.getMainMenuStage(this.game);
		Gdx.input.setInputProcessor(stage);
		Gdx.graphics.setTitle("Rise of a Vassal");
		createLabelStyle();
		createLabelOne();
		createLabelTwo();
		
		stage.addActor(label2);

		mainMenuMusic = StageController.getMMMusic();
        


	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

		mainMenuMusic.play();
	}
	@Override
	public void hide(){
		Gdx.input.setInputProcessor(null);

	}

		
	
	private String getPhrase(){
		phrases = new String[3];
		phrases[0] = "This is the start of the end...";
		phrases[1] = "Only the chosen one can save us...";
		phrases[2] = "The future is in your hands...";
		Random rand = new Random();
		int rand_int = rand.nextInt(3);
		return phrases[rand_int];
	}

	private void createLabelStyle(){
		label1Style = new Label.LabelStyle();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("Hardsign.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 80;
		parameter.color = Color.GOLDENROD;
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 3;

		font = generator.generateFont(parameter);
		generator.dispose();

		label1Style.font = font;
	}

	private void createLabelOne(){
		label1 = new Label("Rise of a Vassal",label1Style);
		label1.setPosition(Gdx.graphics.getWidth() / 2f - label1.getWidth() / 2 ,Gdx.graphics.getHeight() - label1.getHeight());
		label1.setAlignment(Align.center);
		stage.addActor(label1);
		label2Style = new Label.LabelStyle();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("unseen.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 30;
		parameter.color = Color.WHITE;
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 1;

		font = generator.generateFont(parameter);
		generator.dispose();
	}

	private void createLabelTwo(){
		label2Style.font = font;
		label2 = new Label(getPhrase(),label2Style);
		label2.setPosition(Gdx.graphics.getWidth() / 2f - label2.getWidth() / 2 ,Gdx.graphics.getHeight() - label1.getHeight() - label2.getHeight());
		label2.setAlignment(Align.center);
	}

	public MyGdxGame getGame(){
		return  this.game;
	}
}