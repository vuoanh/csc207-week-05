import java.awt.Color;

/**
 * A Lemming is a Critter that always marches east.
 */
public class Lemming extends Critter {
	/** @return the next move of this Lemming: always east */
	public Direction getMove() {
		return Direction.EAST;
	}
	
	/** @return the food type for this Lemming: grass */
	public FoodType getFoodType() {
		return FoodType.GRASS;
	}
	
	/** @returns the color for this Lemming: blue */
	public Color getColor() {
		return Color.BLUE;
	}
	
	/** @returns the speed of this Lemming: medium */
	public Speed getSpeed() {
		return Speed.FAST;
	}
	
	/** @returns the String representation of this Lemming */
	public String toString() {
		return "e";
	}
}