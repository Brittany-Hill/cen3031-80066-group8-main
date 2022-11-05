package com.mygdx.game;

import java.util.ArrayList;

public class Inventory {


	private final ArrayList<Card> cards;
	
	public Inventory() {
		cards  = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public Card getCard() {
		Card card;
		
		card = cards.get(0);
		
		
		return card;
	}

	public Card getCard(int cardID) {
		Card card;

		card = cards.get(cardID);


		return card;
	}

	public int getHandCount(){
		return cards.size();
	}
	public int getDeckCount(){
		return cards.size();
	}
	
}
