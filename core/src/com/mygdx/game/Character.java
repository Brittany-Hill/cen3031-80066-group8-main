package com.mygdx.game;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Character {
	
	//need enums for type
    private int type;
    private int hp;
    private int mana;


    public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	private boolean block;
    private Inventory inventory;
    private final CardReader cardReader;

    public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	private boolean isTurn;
    
    public Character(int type){
        this.type = type;
        this.inventory = new Inventory();
        this.hp = 100;
        this.mana = 100;
        this.cardReader = new CardReader();
        LogSystem.writeToLog("Created character type " + type);
        cardSelection();
    }
    
    public Character(){
        type = 1;
    	inventory = new Inventory();
    	hp = 100;
    	mana = 100;
    	cardReader = new CardReader();
        cardSelection();
        LogSystem.writeToLog("Created character type " + type);
    }


    public int getType()
    {
        return this.type;
    }

    public int getHP()
    {
        return this.hp;
    }

    public int getMana()
    {
        return this.mana;
    }

    

    private void cardSelection() {

        if(this.type == 1)
            meleeCards();
        else if(this.type == 2)
            rangedCards();
        else if(this.type ==3)
            mageCards();
        else if(this.type >= 4)
            enemyCards();
    }

    private void meleeCards() {
        Card attack = new Card(1, "Attack", "Attacks", false, 15, 0, 0);
        Card block = new Card(2, "Block", "Blocks", true, 10, 0,15);
        Card heal = new Card(3, "Heal", "Heals", false, 0, 10,20);

        this.inventory.addCard(attack);
        this.inventory.addCard(block);
        this.inventory.addCard(heal);

    }

    private void rangedCards() {
        Card attack = new Card(1, "Attack", "Attacks", false, 15, 0,0);
        Card heal = new Card(3, "Heal", "Heals", false, 0, 10,20);

        inventory.addCard(attack);
        inventory.addCard(attack);
        inventory.addCard(heal);
    }

    private void mageCards() {
        Card attack = new Card(1, "Attack", "Attacks", false, 15, 0,0);
        Card heal = new Card(3, "Heal", "Heals", false, 0, 10,20);

        inventory.addCard(attack);
        inventory.addCard(heal);
        inventory.addCard(heal);
    }

    private void enemyCards(){
        Card attack = new Card(1, "Attack", "Attacks", false, 5*(this.type - 3), 0,0);
        Card heal = new Card(3, "Heal", "Heals", false, 0, 10,20);

        inventory.addCard(attack);
        inventory.addCard(attack);
        inventory.addCard(heal);
    }
    public Inventory getInventory(){
        return this.inventory;
    }

    public void setType(int type){
        this.type = type;
    }
    public void setHP(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setInventory(Inventory tempInv) {
        this.inventory = tempInv;
    }
}
