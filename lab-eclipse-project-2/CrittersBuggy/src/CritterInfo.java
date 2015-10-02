/**
 * An interface that describes the info that a particular critter object
 * knows about during the simulation.  Subclasses of Critter may use
 * the CritterInfo field of their critter (defined in the Critter class)
 * to make more informed decisions.  This field is updated by the
 * simulation at the beginning of each time step.
 */
public interface CritterInfo {
	/** @return the current x-coordinate of the critter in the world. */
	public int getX();
	
	/** @return the current y-coordinate of the critter in the world. */
	public int getY();
	
	/** @return the width of the world. */
	public int getWidth();
	
	/** @return the height of the world. */
	public int getHeight();
	
	/** @return the number of steps currently taken in the world. */
	public int getNumSteps();
	
	/**
	 * @returns the String representation of the space adjacent to the
	 * critter in the given direction, i.e., what your critter "sees".
	 * @param direction the direction in which to look
	 */
	public String getNeighbor(Critter.Direction direction);
	
	/**
	 * @return the hunger level of the critter where 0 means that the
	 * critter is not hungry at all.
	 */
	public int getHungerLevel();
	
	/**
	 * @return true if the critter has mated.
	 * Remember that a critter can only mate once in its lifetime.
	 */
	public boolean hasMated();
	
	/** Tells the critter to kill itself.  Why would do this?  You monster. */
	public void suicide();
}