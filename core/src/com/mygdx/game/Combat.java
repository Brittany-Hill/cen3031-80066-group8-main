package com.mygdx.game;

public class Combat {


	
	public static void heal(Character character, int heal)
	{
		if(character.getHP() < 100) {
			character.setHP(character.getHP() + heal);
		}
	}
	
	public static void block(Character character, boolean blocking)
	{
		character.setBlock(blocking);
	}

	
	public static void useMana(Character character, int mana)
	{
		character.setMana(character.getMana() - mana);
	}

	public static void damage(Character character, int damage) {
		if(!character.isBlock()) {
			character.setHP(character.getHP() - damage);
		}
	}


	public static String randomAttack(Character player, Character enemy)
	{
		int num = ((int)(Math.random()*3));
		System.out.println(num);
		Card card = enemy.getInventory().getCard(num);
		damage(player, card.getDamage());
		if(enemy.getMana() >= card.manaCost) {
			heal(enemy, card.getHeal());
		block(enemy, card.isBlock());}
		
		return card.getName();
	}
	
	public static void playerAttack(Character player, Character enemy, Card card)
	{
		damage(enemy, card.getDamage());
		if(player.getMana() >= card.manaCost) {
			heal(player, card.getHeal());
			block(player, card.isBlock());
			useMana(player, card.getManaCost());
		}
		
		
	}
	
}
