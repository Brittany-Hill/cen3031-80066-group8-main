package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Card extends Group{

	int id;
	String name;
	String description;
	boolean block;
	int damage;
	int heal;
	int manaCost;

	Image cardImage;


	public Image getCardImage() {
		return cardImage;
	}
	public void setCardImage(Image cardImage) {
		this.cardImage = cardImage;
		addActor(cardImage);
	}

	public Card getCard()
	{
		return this;
	}
	public Card(int id, String name, String description, boolean block,
				int damage, int heal, int manaCost)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.damage = damage;
		this.block = block;
		this.heal = heal;
		this.manaCost = manaCost;
	}

	@Override
	public String toString() {
		String string;

		string = "ID: " + getID() + "\nName: " + getName() + "\nDesc: " + getDescription() +
				"\nDamage: " + getDamage();

		return string;
	}


	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBlock() {
		return block;
	}


	public void setBlock(boolean block) {
		this.block = block;
	}


	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public int getHeal() {
		return heal;
	}


	public void setHeal(int heal) {
		this.heal = heal;
	}

	public int getManaCost(){
		return this.manaCost;
	}




}

