package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;

public class CombatScreen extends ScreenAdapter{

	MyGdxGame game;
	Stage stage;
	public static Character player = SaveFile.LoadFromFile();
	Skin UI = new Skin(Gdx.files.internal("pixthulhu-ui.json"));
	Dialog pauseMenu = new Dialog("Game Paused",UI);
	Label health;
	Label mana;
	Label enemyHealth;
	Label actionLabel;
	ProgressBar enemyHPBar = new ProgressBar(0,100,1,false,UI);
	ProgressBar playerHPBar = new ProgressBar(0,100,1,false,UI);
	ProgressBar playerMana = new ProgressBar(0,100,1,false,UI);
	Image enemyImage;
	int CombatNumber;
	public static Character enemy1;
	CombatScreen tempScreen;
	Button pauseButton;

	Music combatMusic;
	
	public CombatScreen(MyGdxGame myGdxGame, int CombatNumber){
		this.game = myGdxGame;
		this.tempScreen = null;
		this.CombatNumber = CombatNumber;
	}

	@Override
	public void show()
	{
		enemy1 = new Character(this.CombatNumber+3);
		if(tempScreen!=null){
			stage = tempScreen.stage;
		}else {
			if (StageController.getCombatStage(this.game, this.CombatNumber) != null) {
				stage = StageController.getCombatStage(this.game,this.CombatNumber);
			}
			Gdx.input.setInputProcessor(stage);
			enemyImage = StageBuilderCombat.enemyVisualizer(enemy1);
			health = StageBuilderCombat.generatePlayerHPLabel(player);
			mana = StageBuilderCombat.generatePlayerManaLabel(player);
			enemyHealth = StageBuilderCombat.generateEnemyHPLabel(enemy1);
			actionLabel = StageBuilderCombat.generateActionLabel();
			playerHPBar.setPosition(health.getX()-10,health.getY());
			playerMana.setPosition(mana.getX()-10,mana.getY());
			stage.addActor(playerMana);
			stage.addActor(playerHPBar);
			stage.addActor(health);
			stage.addActor(mana);
			enemyHPBar.setPosition(enemyHealth.getX()-25,enemyHealth.getY());
			stage.addActor(enemyHPBar);
			stage.addActor(enemyHealth);
			stage.addActor(actionLabel);
			stage.addActor(enemyImage);

			pauseButton = pauseButton(stage);
			stage.addActor(pauseButton);
			
			combatMusic = StageController.getCombatMusic();
			combatMusic.play();


		}
	}
	

	@Override
    public void render(float delta) {
		playerMana.setValue(player.getMana());
		if(!player.isTurn()/* && timer < System.currentTimeMillis() */)
    {
        enemy1.setBlock(false);
        String action = Combat.randomAttack(player, enemy1);
        actionLabel.setText("Monster uses " + action + "!");
        LogSystem.writeToLog("Monster uses " + action);
        player.setBlock(false);
        player.setTurn(true);
    }
        if(enemy1.getHP() <= 0)
        {
            combatMusic.dispose();
            actionLabel.setText("Monster defeated!");
            LogSystem.writeToLog("Monster defeated!");
            enemyImage.addAction(Actions.fadeOut(1.0f));

            if(player.getHP() < 100) {
				player.setHP((100-player.getHP()+player.getHP()));
			}if(player.getMana() < 100) {
			player.setMana((100-player.getMana()+player.getMana()));
		}
            if(this.CombatNumber < 3) {
            	this.CombatNumber++;
				game.setScreen(new DialogScreen(game, stage, "Dialog/Stage2.txt", CombatNumber));
				enemy1.setHP(100);
				enemy1.setMana(100);
			}else{
            	game.setScreen(new WonGame(game));
			}

        }
        
		health.setText("HP: " + player.getHP());
        playerHPBar.setValue(player.getHP());
        mana.setText("Mana: " + player.getMana());
        if(player.getHP() <= 0){
			this.game.setScreen(new GameOver(game));
	        combatMusic.dispose();
			player.setHP(100);
			player.setMana(100);
		}
		enemyHealth.setText("HP: " + enemy1.getHP());
        enemyHPBar.setValue(enemy1.getHP());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		

    }

	@Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
	public Button pauseButton(final Stage stage){
		TextButton quit = quitButton(stage);
		TextButton resume = resumeButton();
		TextButton tempButt = new TextButton("||", UI);
		tempButt.setSize(75f,75f);
		tempButt.setPosition(5, Gdx.graphics.getHeight() - 80);
		tempButt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					pauseMenu.show(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				LogSystem.writeToLog("Pause button clicked");
			}
		});

		pauseMenu.button(quit);
		pauseMenu.button(resume);

		return tempButt;
	}

	private TextButton resumeButton() {
		TextButton tempButt = new TextButton("Resume",UI);
		tempButt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					pauseMenu.hide();
				} catch (Exception e) {
					e.printStackTrace();
				}
				LogSystem.writeToLog("Pause button clicked");
			}
		});
		return tempButt;
	}

	private TextButton quitButton(final Stage stage) {
		TextButton tempButt = new TextButton("Quit",UI);
		tempButt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					doubleCheck(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				LogSystem.writeToLog("Pause button clicked");
			}
		});
		return tempButt;
	}

	private void doubleCheck(final Stage stage){
		Dialog doubleCheck = new Dialog("Are you sure? Progress will not be saved", UI);
		TextButton yes = new TextButton("Yes",UI);
		yes.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					stage.dispose();
					Gdx.app.exit();
				} catch (Exception e) {
					e.printStackTrace();
				}
				LogSystem.writeToLog("Pause button clicked");
			}
		});
		TextButton no = new TextButton("No", UI);
		no.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					pauseMenu.hide();
				} catch (Exception e) {
					e.printStackTrace();
				}
				LogSystem.writeToLog("Pause button clicked");
			}
		});
		doubleCheck.button(yes);
		doubleCheck.button(no);
		doubleCheck.show(stage);

	}
	

	
}