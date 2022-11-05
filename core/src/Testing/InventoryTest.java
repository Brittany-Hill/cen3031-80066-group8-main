package Testing;


import com.mygdx.game.Inventory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mygdx.game.Card;

public class InventoryTest {

	@Test
	public void TestConstructor() 
	{
		//Arrange
		Inventory sut = new Inventory();
		
		//Act
		int answer = 0;
		int result = sut.getHandCount();
		
		//Assert
		assertEquals(answer, result);
	}
	
	@Test
	public void TestAddCard() 
	{
		//Arrange
		Inventory sut = new Inventory();
		Card card = new Card(1, "TestName", "Test Description", false, 5, 0,0);
		
		//Act
		sut.addCard(card);
		int answer = 1;
		int result = sut.getHandCount();
		
		//Assert
		assertEquals(answer, result);
		assertEquals(card, sut.getCard(0));
		assertEquals(card, sut.getCard());

	}
}
