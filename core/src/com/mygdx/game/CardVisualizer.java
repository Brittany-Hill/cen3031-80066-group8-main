package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;

public class CardVisualizer {
    Image cardImage;
    Card card;
    ArrayList<Label> cardLabels = new ArrayList<>();
    public CardVisualizer(Card cardToShow){
        this.card = cardToShow;
        this.cardImage =setCardBG();
    }
    public Image getCardImage(){

        addListener(this.cardImage);
        return this.cardImage;
    }
    public Card getCard()
    {
        card.setCardImage(getCardImage());
        addActors();
        return card;
    }
    private Image setCardBG(){
        Texture texture;
        if(this.card.isBlock() == true){
            texture = new Texture(Gdx.files.internal("shieldBG.png"));
        }else{
            if(this.card.getHeal()==0){
               texture = new Texture(Gdx.files.internal("CardBG.png"));
            }else{
                texture = new Texture(Gdx.files.internal("Vassal_heal.png"));
            }
        }

        TextureRegion textureRegion = new TextureRegion(texture);
        return new Image(textureRegion);
    }
    private void addListener(Image cardBG){
        cardBG.addListener(new ClickListener() {

            //TODO move the attacking to the combat class and add a function for random card usage for enemies
            @Override
            public void clicked(InputEvent event, float a, float b) {
                System.out.println("Card" + card.getID() + " clicked.");
                LogSystem.writeToLog("Card" + card.getID() + " clicked.");
                Combat.playerAttack(CombatScreen.player, CombatScreen.enemy1, card.getCard());
                CombatScreen.player.setTurn(false);
                StageBuilderCombat.tempString = ("Player uses " + card.getName());
                System.out.println("HP: " + CombatScreen.player.getHP());
//				CombatScreen.timer = System.currentTimeMillis() + 2000;

            }
        });
    }
    private Label.LabelStyle generateLabel(){
        Label.LabelStyle cardLabelStyle = new Label.LabelStyle();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Hardsign.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        BitmapFont font;

        font = generator.generateFont(parameter);
        generator.dispose();

        cardLabelStyle.font = font;
        return cardLabelStyle;
    }

    private void generateNameLabel(){
        Label nameLabel = new Label(card.getName(), generateLabel());
        cardLabels.add(nameLabel);
        nameLabel.setPosition(30, 95);
    }
    private void generateDescription(){
        Label descriptionLabel = new Label("Mana cost: " + card.getManaCost(), generateLabel());
        cardLabels.add(descriptionLabel);
    }
    private void generateDamageLabel(){
        Label damageLabel = new Label("DMG: " + card.getDamage(), generateLabel());
        cardLabels.add(damageLabel);
        damageLabel.setPosition(0, 25);
    }
    private void fillLabels(){
        generateDescription();
        generateDamageLabel();
        generateNameLabel();
    }

    private void addActors(){
        fillLabels();
        for (Label cardLabel : cardLabels) {
            card.addActor(cardLabel);
        }
    }
}