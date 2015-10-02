import java.awt.Color;

/**
 * The Critter class.
 *
 * An implementation of the abstract Critter class is a type of animal in
 * the critter simulation.  Critters have a variety of behavior that a user
 * is free to implement as they choose.  These methods are:
 *
 * getMove() - returns the next move for the Critter
 * getFoodType() - returns the type of food that the Critter eats
 * getColor() - returns the Color used when rendering the Critter
 * getSpeed() - returns the speed of the Critter
 * getName() - returns the full name of the type of these Critters
 * toString() - returns a String representation of the Critter
 *
 * Note that these methods must be overridden by each subclass of Critter.
 *
 * Several of the method's values are dictated by enumerations which are
 * collections of (finite) named values.  For example, getMove returns a
 * Direction of which there are five possible values corresponding to the
 * cardinal directions (NORTH, EAST, SOUTH, and WEST) and "no move"
 * represented by CENTER.  See the example implementations of Critters in
 * the accompanying files to see how to use these enumerations properly.
 */
public abstract class Critter {

	///// MEMBERS YOU NEED FOR THE HOMEWORK /////

	/** Defines the types of food a critter can eat: grass or meat. */
	public static enum FoodType { GRASS, MEAT }
	
	/** 
	 * Defines the possible directions a critter can move: the four cardinal
	 * directions along with "no move" (CENTER).
	 */
	public static enum Direction { NORTH, EAST, SOUTH, WEST, CENTER }
	
	/**
	 * The speed at which a critter moves: fast, medium, or slow.
	 */
	public static enum Speed { FAST, MEDIUM, SLOW }
	
	/**
	 * @return the direction this critter should move for the next step of the
	 * simulation
	 */
	public abstract Direction getMove();
	
	/** @return the type of food this critter eats */
	public abstract FoodType getFoodType();
	
	/** @return the color of this critter */
	public abstract Color getColor();
	
	/** @return the speed of this critter */
	public abstract Speed getSpeed();
	
	/**
	 * @return the String representation of this critter, a single character
	 * which will be rendered onto the simulation board.
	 */
	public String toString() {
		throw new UnsupportedOperationException("Implementors must provide their own toString() methods!");
	}
	
	///// OPTIONAL MEMBERS FOR IMPLEMENTORS /////
	
	/**
	 * A CritterInfo object contains the current state of this particular critter.
	 * Implementors may use this information in their subclasses.  The simulation
	 * automatically updates this information with each time step.  See the
	 * CritterInfo interface (in CritterInfo.java) for the specific methods you can
	 * call on CritterInfo objects.
	 */
	protected CritterInfo info;
	
	// The onX methods are notification methods that are invoked by the simulator
	// under certain conditions.  Implementors may want to override their behavior,
	// e.g., if they want to do something in response to eating food.  By default,
	// these notification methods do nothing.
	
	/** The critter's response to mating, i.e., invoked whenever the critter mates. */
	public void onMate(Critter other) { }
	
	/** The critter's response to eating, i.e., invoked whenever the critter eats. */
	public void onEat() { }
	
	/** 
	 * The critter's response to winning a fight, i.e., invoked whenever the
	 * critter wins a fight.
	 */
	public void onWin() { }
	
	/** The critter's response to dying, i.e., invoked when the critter dies. */
	public void onDeath() { }
	
	///// IMPLEMENTATION DETAILS THAT IMPLEMENTORS DON'T NEED TO WORRY ABOUT /////
	
	/** Convenience array that contains all of the speed in priority order. */
	public static final Speed[] Speeds = {
		Speed.FAST,
		Speed.MEDIUM,
		Speed.SLOW
	};
	
	/** Convenience array that contains all of the directions. */
	public static final Direction[] Directions = {
		Direction.NORTH,
		Direction.EAST,
		Direction.SOUTH,
		Direction.WEST,
		Direction.CENTER
	};
	
	/**
	 * Finalizes equality over Critters so that subclasses cannot override it.
	 * This is necessary because the simulator relies on equality.
	 */
	public final boolean equals(Object other) {
		return other == this;
	}
	
	/**
	 * Sets this critter's CritterInfo to the given info object (called by the simulator).
	 * @param info the CritterInfo object
	 */
	public void setCritterInfo(CritterInfo info) { this.info = info; }
}
