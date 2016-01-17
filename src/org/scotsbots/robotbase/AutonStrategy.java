package org.scotsbots.robotbase;

/**
 * An abstract class for creating autonomous strategies. Make sure to add the auton strat to the list.
 * @author Domenic
 */
public abstract class AutonStrategy 
{	
	public abstract void intialize();
	public abstract void update(); 
	public abstract String getName();
}
