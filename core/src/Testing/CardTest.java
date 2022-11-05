package Testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mygdx.game.Card;

public class CardTest {

	
	@Test
	public void TestToString()
	{
		//Arrange
		Card sut = new Card(1, "TestName", "Test Description", false, 5, 0,0);
		
		//Act
		String result = sut.toString();
		String answer = "ID: 1\nName: TestName\nDesc: Test Description\nDamage: 5";
		
		//Assert
		assertEquals(answer, result);
	}
	
	@Test
	public void TestGetMethods()
	{
		//Arrange
		Card sut = new Card(1, "TestName", "Test Description", false, 5, 0,0);
		
		//Act
		int id = sut.getID();
		int damage = sut.getDamage();
		int heal = sut.getHeal();
		String name = sut.getName();
		String desc = sut.getDescription();
		boolean block = sut.isBlock();
		
		//Assert
		assertEquals(1, id);
		assertEquals(5, damage);
		assertEquals(0, heal);
		assertEquals("TestName", name);
		assertEquals("Test Description", desc);
		assertEquals(false, block);

	}
	
	@Test
	public void TestSetMethods()
	{
		//Arrange
		Card sut = new Card(1, "TestName", "Test Description", false, 5, 0,0);
				
		//Act
		sut.setDamage(99);
		sut.setHeal(99);
		sut.setName("Big Boomer");
		sut.setDescription("complete desolation");
		sut.setBlock(true);
				
		//Assert
		assertEquals(99, sut.getDamage());
		assertEquals(99, sut.getHeal());
		assertEquals("Big Boomer", sut.getName());
		assertEquals("complete desolation", sut.getDescription());
		assertEquals(true, sut.isBlock());
	}
	
	
}
