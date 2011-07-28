package Monopoly;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Class to represent the street colors on the monopoly board
 * and also used to represent railways and utils
 * @author Michael Treacher
 *
 */
public class Color {
	
	private String color;
	private Property[] propertySet;
	private int size;
	private Image img;
	
	//private helper field for addStreet()
	private int streetCount = 0;
	/**
	 * Constructs a Color object.
	 * @param color 
	 * @param streets - the streets which have the same color
	 */
	public Color(String color, int size,Image img){
		this.color = color;
		propertySet = new Street[size];
		this.img = img;
	}
	/**
	 * adds a street to the colours collection
	 * @param street to be added
	 */
	public void addStreet(Street s){
		propertySet[streetCount] = s;
		streetCount++;
	}
	/**
	 * Gets the color.
	 * @return
	 */
	public String getColor(){
		return color;
	}
	/**
	 * Gets the streets represented by this color.
	 * @return
	 */
	public Property[] getSet(){
		return propertySet.clone();
	}
	/**
	 * Gets the size
	 * @return Size
	 */
	public int size(){
		return size;
	}
	/**
	 * Gets the Image
	 * @return the image
	 */
	public ImageIcon getImage(){
		return new ImageIcon(img.getScaledInstance(30, 50, Image.SCALE_FAST));
	}
	
	

}
