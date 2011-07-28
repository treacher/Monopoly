package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import GraphicalUserInterface.MonopolyBoardFrame;
import Monopoly.Board;
import Monopoly.Cell;
import Monopoly.Piece;
import Monopoly.Player;
import Monopoly.Property;
import Monopoly.Street;

public class Tests {

	private ArrayList<Player> players = new ArrayList<Player>();

	public void build(){
		players.clear();
		Board b = new Board();
	}

	@Test
	public void testBoardSetup(){
		Board board = new Board();

	}

	
	@Test
	public void testHouses(){
		Board b = new Board();

		ArrayList<Street> streets = new ArrayList<Street>();

		//create a list of all streets
		for (Cell c : Board.cells){
			if (c instanceof Street){
				streets.add((Street)c);
			}
		}
		int i = 0;

		Player p = new Player(Piece.Boot, "Test", 5000);

		for (Street s : streets){
			p.buyProperty(s, 10);
			s.setOwner(p);
			s.addHouse(i);
			i++;

			i = i % 6; //make sure number doesn't go over 6
		}

		MonopolyBoardFrame board = new MonopolyBoardFrame();


	}

	 

	@Test
	public void testBuyProperty(){
		build();
		Street s = (Street)Board.cells.get(1);
		Player p = new Player(Piece.Boot, "Test", 5000);

		assertTrue(p.buyProperty(s, 5000));
		assertTrue(p.equals(s.getOwner()));
	}
	@Test
	public void testNotEnoughFundsBuyProperty(){
		build();
		Street s = (Street)Board.cells.get(1);
		Player p = new Player(Piece.Boot, "Test", 0);

		assertFalse(p.buyProperty(s, 100));
		assertFalse(p.equals(s.getOwner()));

	}
	
	@Test 
	public void testBuyHouses(){
		build();
		Street s = (Street)Board.cells.get(1);

		Player p = new Player(Piece.Boot, "Test", s.getHousePrice() * 6);
		s.setOwner(p);

		assertTrue(s.addHouse(6));
	}
	@Test
	public void testBuyTooManyHouses(){
		build();
		Street s = (Street)Board.cells.get(1);

		Player p = new Player(Piece.Boot, "Test", s.getHousePrice() * 7);
		s.setOwner(p);

		assertFalse(s.addHouse(7));
	}
	@Test
	public void testBuyTooManyHouses2(){
		build();
		Street s = (Street)Board.cells.get(1);

		Player p = new Player(Piece.Boot, "Test", s.getHousePrice() * 7);
		s.setOwner(p);

		assertFalse(s.addHouse(7));
	}
	@Test
	public void testNotEnoughFundsBuyHouses(){
		build();
		Street s = (Street)Board.cells.get(1);

		Player p = new Player(Piece.Boot, "Test", s.getHousePrice() * 6 - 1);
		s.setOwner(p);

		assertFalse(s.addHouse(6));
	}

	@Test
	public void testTrade(){
		build();
		Street s = (Street)Board.cells.get(1);
		Player p1 = new Player(Piece.Boot, "Test", 0);
		Player p2 = new Player(Piece.Car, "Test2", 100);

		s.setOwner(p1);

		assertTrue(p2.buyProperty(s, 100));
		assertTrue(p1.getBalance() == 100);
		assertTrue(p2.getBalance() == 0);
	}

	@Test
	public void testNotEnoughFundsTrade(){
		build();
		Property p = (Property)Board.cells.get(1);
		Player p1 = new Player(Piece.Boot, "Test", 0);
		Player p2 = new Player(Piece.Car, "Test", 100);

		p.setOwner(p1);

		assertFalse(p2.buyProperty(p, 101));
		assertTrue(p1.getBalance() == 0);
		assertTrue(p2.getBalance() == 100);
	}

	@Test
	public void testPlayerRoll(){		
		Player p = new Player(Piece.Boot, "Test", 0);	

		p.roll(2, 2);	
		assertTrue(p.getPosition() == 4);

		p.roll(2, 4);
		assertTrue(p.getPosition() == 10);

		//wrap round
		p.roll(29, 0);
		assertTrue(p.getPosition() == 39);

		p.roll(1, 0);
		assertTrue(p.getPosition() == 0);
	}
	@Test
	public void testPlayerRollWrapRound(){
		Player p = new Player(Piece.Boot, "Test", 0);

		p.roll(39, 0);
		assertTrue(p.getPosition() == 39);	

		p.roll(1, 0);
		assertTrue(p.getPosition() == 0);	
	}
}
