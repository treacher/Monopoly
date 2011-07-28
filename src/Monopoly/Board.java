package Monopoly;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Represents the board and holds all 40 cells
 * 
 * 
 * @author Sean Anderson && Michael Treacher
 * 
 */
public class Board {

	
	public static ArrayList<Cell> cells = new ArrayList<Cell>(40);
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player currentPlayer;
	
	public Board(){
		loadCells();
	}
	
	public ArrayList<Cell> getCells(){
		return cells;
	}
	
	public Cell getCell(int id){
		return cells.get(id);
	}
	
	/**
	 * method that will initialise all the cells in the game
	 */
	private void loadCells(){
		cells.clear();
		
		Color orange = null,lightBlue = null,purple = null,pink = null,red = null,yellow = null,green = null,blue = null,station = null,utility = null;
		
		try {
		 orange = new Color("Orange", 3,ImageIO.read(new File("src/cards/property_card_orange.jpg")));
		 lightBlue = new Color("Light Blue", 3,ImageIO.read(new File("src/cards/property_card_lightblue.jpg")));
		 purple = new Color("Purple", 2,ImageIO.read(new File("src/cards/property_card_purple.jpg")));				
		 pink = new Color("pink", 3,ImageIO.read(new File("src/cards/property_card_pink.jpg")));		
		 red = new Color("Red",3,ImageIO.read(new File("src/cards/property_card_red.jpg")));
		 yellow = new Color("Yellow",3,ImageIO.read(new File("src/cards/property_card_yellow.jpg")));
		 green = new Color("Green",3,ImageIO.read(new File("src/cards/property_card_green.jpg")));
		 blue = new Color("Blue",2,ImageIO.read(new File("src/cards/property_card_blue.jpg")));
		 station = new Color("Station",4,ImageIO.read(new File("src/cards/station_card.jpg")));
		 utility = new Color("Utility",2,ImageIO.read(new File("src/cards/empty_card.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
		cells.add(0,new Go(0, "Go"));
		cells.add(1,new Street(1, "Old Kent Road", 60, purple, 2 , 50, 150,ImageIO.read(new File("src/property_cards/old kent road.jpg"))));
		cells.add(2,new Card(2, "Community Chest"));
		cells.add(3,new Street(3, "Whitechapel Road", 60, purple, 4 , 50, 150,ImageIO.read(new File("src/property_cards/whitechapel road.jpg"))));
		cells.add(4,new Tax(4, "Income Tax", 200));
		cells.add(5,new Railway(5, "Kings Cross Station", 200,station,ImageIO.read(new File("src/property_cards/kings cross station.jpg"))));
		cells.add(6,new Street(6, "The Angel Islington", 100, lightBlue, 6 , 50, 150,ImageIO.read(new File("src/property_cards/the angel islington.jpg"))));
		cells.add(7,new Card(7, "Chance"));
		cells.add(8,new Street(8, "Euston Road", 100, lightBlue, 6 , 50, 150,ImageIO.read(new File("src/property_cards/euston road.jpg"))));
		cells.add(9,new Street(9, "Pentoville Road", 120, lightBlue, 8 , 50, 150,ImageIO.read(new File("src/property_cards/pentoville road.jpg"))));
		cells.add(10,new Jail(10, "Jail"));
		cells.add(11,new Street(11, "Pall Mall", 140, pink, 10 , 100, 200,ImageIO.read(new File("src/property_cards/pall mall.jpg"))));
		cells.add(12,new Utility(12, "Electric Company", 150,utility,ImageIO.read(new File("src/property_cards/electric company.jpg"))));
		cells.add(13,new Street(13, "Whitehall", 140, pink, 10, 100, 200,ImageIO.read(new File("src/property_cards/whitehall.jpg"))));
		cells.add(14,new Street(14, "Northland Ave", 160, pink, 12, 100, 200,ImageIO.read(new File("src/property_cards/northland ave.jpg"))));
		cells.add(15,new Railway(15, "MaryLebone Station", 200,station,ImageIO.read(new File("src/property_cards/marylebone station.jpg"))));
		cells.add(16,new Street(16, "Bow Street", 180, orange, 14 , 100, 200,ImageIO.read(new File("src/property_cards/bow street.jpg"))));
		cells.add(17,new Card(17, "Community Chest"));
		cells.add(18,new Street(18, "Marlborough Street", 180, orange, 14, 100, 200,ImageIO.read(new File("src/property_cards/marlborough street.jpg"))));
		cells.add(19,new Street(19, "Vine Street", 200, orange, 16, 100, 200,ImageIO.read(new File("src/property_cards/vine street.jpg"))));
		cells.add(20,new FreeParking(20,"Free Parking"));
		
		cells.add(21,new Street(21,"The Strand",220,red,18,150,250,ImageIO.read(new File("src/property_cards/the strand.jpg"))));
		cells.add(22,new Card(22, "Chance"));
		cells.add(23,new Street(23,"Fleet Street",220,red,18,150,250,ImageIO.read(new File("src/property_cards/fleet street.jpg"))));
		cells.add(24,new Street(24,"Trafalgar Square",240,red,28,150,250,ImageIO.read(new File("src/property_cards/trafalgar square.jpg"))));
		cells.add(25,new Railway(25,"Fenchurch Street Station",200,station,ImageIO.read(new File("src/property_cards/fenchruch station.jpg"))));
		cells.add(26,new Street(26,"Leciester Square",260,yellow,22,150,250,ImageIO.read(new File("src/property_cards/leciester square.jpg"))));
		cells.add(27,new Street(27,"Conventry Street",260,yellow,22,150,220,ImageIO.read(new File("src/property_cards/conventry street.jpg"))));
		cells.add(28,new Utility(28,"Water Works",150,utility,ImageIO.read(new File("src/property_cards/water works.jpg"))));
		cells.add(29,new Street(29,"Piccadily Circus",280,yellow,24,150,250,ImageIO.read(new File("src/property_cards/piccadily circus.jpg"))));
		cells.add(30,new GoToJail(30,"Go To Jail"));
		
		cells.add(31,new Street(31,"Regent Street",300,green,26,200,300,ImageIO.read(new File("src/property_cards/regent street.jpg"))));
		cells.add(32,new Street(32,"Oxford Street",300,green,26,200,300,ImageIO.read(new File("src/property_cards/oxford street.jpg"))));
		cells.add(33,new Card(33, "Community Chest"));
		cells.add(34,new Street(34,"Bond Street",320,green,28,200,300,ImageIO.read(new File("src/property_cards/bond street.jpg"))));
		cells.add(35,new Railway(35,"Liverpool Street Station",200,station,ImageIO.read(new File("src/property_cards/liverpool st station.jpg"))));
		cells.add(36,new Card(36, "Chance"));	
		cells.add(37,new Street(37,"Park Lane",350,blue,35,200,300,ImageIO.read(new File("src/property_cards/park lane.jpg"))));
		cells.add(38,new Tax(38,"Super Tax",200));
		cells.add(39,new Street(39,"Mayfair",400,blue,50,200,300,ImageIO.read(new File("src/property_cards/mayfair.jpg"))));
		}
		catch(Exception e){System.out.println(e);}
	}
	                   
}
