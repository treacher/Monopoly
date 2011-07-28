package GraphicalUserInterface;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import Monopoly.Board;
import Monopoly.Cell;
import Monopoly.GetOutOfJailOption;
import Monopoly.Option;
import Monopoly.Player;
import Monopoly.Property;
import Monopoly.Street;
/**
 * Class to represent Button panel
 * @author Michael Treacher
 *
 */
public class ButtonPanel extends JPanel {
	/**
	 * 
	 * Constructs button panel
	 */
	public ButtonPanel(){
		this.setLayout(new FlowLayout());
	}
	/**
	 * Builds a property card panel
	 * @param property
	 */
	public void buildPropertyCardPanel(final Property property,final JFrame parentFrame){
		removeAll();
		if(property.getOwner()!= null){
			if(property.getOwner().equals(Board.currentPlayer)){
				if(property instanceof Monopoly.Street){
					final Street street = (Street)property;
					if(((Monopoly.Street) property).streetSetOwnedBy(Board.currentPlayer)){
						JButton buyHouses = new JButton("Buy Houses");
						buyHouses.addActionListener(
								new ActionListener(){
									/*
									 * (non-Javadoc)
									 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
									 */
									public void actionPerformed(ActionEvent e) {
										buyHouses(street);
									}

								}
						);
						add(buyHouses);
					}
				}
				if(property.isMortgaged()){
					JButton unmortgageProperty = new JButton("Unmortgage Property");
					unmortgageProperty.addActionListener(
							new ActionListener(){
								/*
								 * (non-Javadoc)
								 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
								 */
								public void actionPerformed(ActionEvent e) {
									int cost = (int) (property.getPropertyValue()*1.1);
									//check player can afford to unmortgage
									if (Board.currentPlayer.getBalance() < cost){
										JOptionPane.showMessageDialog(getParent(), "You don't have enough funds to unmortgage this property.","Unmortgage Property",JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									Board.currentPlayer.addMoney(-cost);
									property.setMortgage(false);
									JOptionPane.showMessageDialog(getParent(), "Your property has been unmortgaged.","Unmortgage Property",JOptionPane.INFORMATION_MESSAGE);
									buildPropertyCardPanel(property,parentFrame);
								}

							}
					);
					add(unmortgageProperty);
				}

				else {
					JButton mortgageProperty = new JButton("Mortgage Property ");
					mortgageProperty.addActionListener(
							new ActionListener(){
								/*
								 * (non-Javadoc)
								 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
								 */
								public void actionPerformed(ActionEvent e) {
									property.setMortgage(true);
									Board.currentPlayer.addMoney((int)(property.getPropertyValue()/2.0));
									JOptionPane.showMessageDialog(getParent(), "Your property has been mortgaged");
									buildPropertyCardPanel(property,parentFrame);
								}
							}
					);
					add(mortgageProperty);
				}

			}
			else if(property.getOwner() != null){
				JButton trade = new JButton("Trade");
				trade.addActionListener(
						new ActionListener(){
							/*
							 * (non-Javadoc)
							 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
							 */
							public void actionPerformed(ActionEvent e) {
								int option = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to buy this property?", "Trade property", JOptionPane.YES_NO_OPTION);
								if(option == JOptionPane.YES_OPTION){
									int tradePrice = getTradePrice(property);
									if(tradePrice > 0){
										JOptionPane.showMessageDialog(parentFrame, "You do not have enough funds to purchase this property");
									}
								}
								buildPropertyCardPanel(property,parentFrame);
							}

						}
				);
				add(trade);
			}
		}
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(
				new ActionListener(){
					/*
					 * (non-Javadoc)
					 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
					 */
					public void actionPerformed(ActionEvent e) {
						parentFrame.dispose();
					}

				}
		);
		add(exitButton);

		revalidate();
		repaint();
	}
	/**
	 * This methods gets the trade price
	 * @param property
	 * @return -1 if something goes goes wrong and a number > 0 if it works
	 */
	public int getTradePrice(Property property){
		
		String string = JOptionPane.showInputDialog(getParent(), "Please enter the agreed price");
		if(string == null){return 0;}
		int price = Integer.parseInt(string);
		if(price > 0){
			if(!Board.currentPlayer.buyProperty(property,price)){
				JOptionPane.showMessageDialog(getParent(), "You do not have enough money to purchase this.");
				return price;
			}
		}
		else{
			JOptionPane.showMessageDialog(getParent(), "Please put in a correct numeric value.");
			return getTradePrice(property);
		}
		
		return 0;
	}
	
	/**
	 * Shows the dialog for getting the number of houses your buying
	 * @param street the street the houses are being built on
	 * @return returns if it was successful
	 */
	public void buyHouses(Street street){
		String houses = JOptionPane.showInputDialog(getParent(), "You currently have " + street.getHouses() + " houses on this property. 6 Houses represents a Hotel");
		int noOfHouses = 0;
		try{
			noOfHouses = Integer.parseInt(houses);
		}catch(Exception e){} // will trigger next conditional
		// makes sure you put a number between 2 - 7
		if(noOfHouses == 0){
			// do nothing user has most likely cancelled
		}
		else if(!street.addHouse(noOfHouses)){
			JOptionPane.showMessageDialog(getParent(), "You can not build more than 6 houses and you have "+ street.getHouses());
		}
		else{
			JOptionPane.showMessageDialog(getParent(), "You now have "+ street.getHouses() + "houses on " + street.toString());
		}

	}
	/**
	 * Method to check if a player is bankrupt
	 * @param p player
	 * @return
	 */
	private boolean bankrupt(Player p){

		if(p.getBalance() < 0 ){
			p.sellAssets();
			Board.players.remove(p);
			MonopolyBoardFrame.appendText(p.toString() + " has gone bankrupt and has been removed from game!");
		}
		return p.getBalance() < 0;
	}
	/**
	 * Rolls the one di
	 * @return
	 */
	private int rollDice(){
		Random dice = new Random();
		return dice.nextInt(6) + 1;
	}

	/**
	 * Method to build the button panel
	 * @param options Array of options to represent as buttons
	 */
	public void buildPanel(final MonopolyBoardFrame parentFrame){
		removeAll();

		if(Board.players.size() == 1){
			MonopolyBoardFrame.appendText(Board.currentPlayer.toString() + " is the winner!");
			removeAll();
			return;
		}

		if(bankrupt(Board.currentPlayer)){
			if((Board.players.size()-1) == Board.players.indexOf(Board.currentPlayer)){
				Board.currentPlayer = Board.players.get(0);
			}
			else{
				Board.currentPlayer = Board.players.get(Board.players.indexOf(Board.currentPlayer) + 1);
			}
			parentFrame.getCanvas().repaint();
			buildPanel(parentFrame);
		}

		if(Board.currentPlayer.isInJail()){
			JButton payToGetOutOfJail = new JButton("Pay to get out of jail");
			payToGetOutOfJail.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							MonopolyBoardFrame.appendText(new GetOutOfJailOption(Board.currentPlayer).activate());
							buildPanel(parentFrame);

						}

					}
			);
			add(payToGetOutOfJail);
		}
		else if(!Board.currentPlayer.hasRolled()){
			JButton roll = new JButton("Roll Dice");
			roll.addActionListener(
					new ActionListener(){

						public void actionPerformed(ActionEvent e) {
							Board.currentPlayer.setRolled(true);
							Option opt = Board.cells.get(Board.currentPlayer.roll(rollDice(), rollDice())).trigger(Board.currentPlayer);
							parentFrame.getCanvas().animatePlayer();
							if(opt == null){
								// do nothing option has been taken card of 
							}
							else {
								MonopolyBoardFrame.appendText(opt.activate());
							}
							buildPanel(parentFrame);
						}

					}
			);
			add(roll);
		}
		else{
			Cell currentCell = Board.cells.get(Board.currentPlayer.getPosition());
			if(currentCell instanceof Property){
				final Property property = (Property)currentCell;
				if(property.getOwner()!= null && property.getOwner().equals(Board.currentPlayer)){
					// dont do anything
				}
				else if(property.getOwner()!= null && !property.getOwner().equals(Board.currentPlayer)){
					if(!property.isMortgaged())
						MonopolyBoardFrame.appendText(Board.currentPlayer.payPlayer(property.rent(Board.currentPlayer), property.getOwner()));
					else{
						MonopolyBoardFrame.appendText(property.toString()+" is mortgaged so no rent due.");
					}
				}
				else {
					JButton buyProperty = new JButton("Buy Property");
					buyProperty.addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									int option = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to buy " + property.toString()+"?","Buy Property",JOptionPane.YES_NO_OPTION);
									if(option == JOptionPane.YES_OPTION){
										if(!Board.currentPlayer.buyProperty(property,property.getPropertyValue())){
											JOptionPane.showMessageDialog(parentFrame, "You do not have enough funds to purchase this property");
										}
										parentFrame.getSidePanel().displayPlayersPropertys();
										buildPanel(parentFrame);
									}
								}
							}
					);
					add(buyProperty);
				}
			}

			JButton endTurn = new JButton("End Turn");
			endTurn.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							int option = JOptionPane.showConfirmDialog(getParent(),"Are you sure you want to end your turn?","End your turn",JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION){
								Board.currentPlayer.setRolled(false);
								if((Board.players.size()-1) == Board.players.indexOf(Board.currentPlayer)){
									Board.currentPlayer = Board.players.get(0);
								}
								else{
									Board.currentPlayer = Board.players.get(Board.players.indexOf(Board.currentPlayer) + 1);
								}
								parentFrame.getSidePanel().nextPlayersTurn();
								buildPanel(parentFrame);
							}
						}

					}
			);
			add(endTurn);


		}
		revalidate();
		repaint();

	}

}

