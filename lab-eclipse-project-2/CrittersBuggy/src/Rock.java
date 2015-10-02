import java.awt.Color;

/**
 * A rock is an example Critter that... erodes.
 */
public class Rock extends Critter {
	/** @return the next move for this Rock: hold still! */
	public Direction getMove() {
		return Direction.CENTER;
	}
	
	/** @return the food type for this Rock: meat */
	public FoodType getFoodType() {
		return FoodType.MEAT;
	}
	
	/** @return the color of this Rock: gray */
	public Color getColor() {
		return Color.GRAY;
	}
	
	/** @return the speed of this Rock: slow */
	public Speed getSpeed() {
		return Speed.SLOW;
	}
	
	/** @return the string representation of this Rock */
	public String toString() { return "o"; }
}