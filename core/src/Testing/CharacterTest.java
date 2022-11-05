package Testing;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.Test;

import com.mygdx.game.Card;
import com.mygdx.game.Character;
import com.mygdx.game.Inventory;

public class CharacterTest {

	
	@Test
	public void TestCharacter1() throws IOException
    {
        //Arrange
		Character sut = new Character(1);
        
		//Act
		int type = sut.getType();
		int hp = sut.getHP();
		int mana = sut.getMana();
				
		//Assert
		assertEquals(1, type);
		assertEquals(100, hp);
		assertEquals(100, mana);
	
    }
	
	@Test
	public void TestInventoryOfCharacter1() throws IOException
	{
		//Arrange
		Character sut = new Character(1);
        Card answer = new Card(1, "Attack", "Attacks", false, 10, 0,0);

		//Act
		Inventory inv = sut.getInventory();
		Card result = inv.getCard(0);


		//Assert
		assertEquals(answer.toString(), result.toString());
	}
	
	@Test
	public void TestCharacterSetMethods() throws IOException
    {
        //Arrange
		Character sut = new Character(1);
        Inventory tempInv = new Inventory();
		
        //Act
		sut.setType(2);
		sut.setHP(99);
		sut.setMana(99);
		sut.setInventory(tempInv);
				
		//Assert
		assertEquals(2, sut.getType());
		assertEquals(99, sut.getHP());
		assertEquals(99, sut.getMana());
		assertEquals(tempInv, sut.getInventory());
	
    }
	

}
