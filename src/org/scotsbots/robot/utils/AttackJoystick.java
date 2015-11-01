package org.scotsbots.robot.utils;

import edu.wpi.first.wpilibj.Joystick;

public class AttackJoystick
{
	private Joystick joystick;
	
	private static int AXIS_X = 0;
	private static int AXIS_Y = 1;
	private static int AXIS_Z = 2;
	private static int BUTTON_TRIGGER = 1;
	//TODO add the rest of the buttons
	
	public AttackJoystick(int port)
	{
		joystick = new Joystick(port);
	}
	
	/**
	 * Returns the value of the trigger with a deadzone.
	 * 
	 * @return
	 */
	public static double createDeadzone(double triggerValue)
	{
		return Math.abs(triggerValue) < 0.15 ? 0 : triggerValue;
	}
	
	/**
	 * Corresponds to HORIZONTAL input on the joystick.
	 *
	 * @return The X coordinate of the left joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getX()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_X));
	}
	
	/**
	 * Corresponds to VERTICAL input on the joystick.
	 *
	 * @return The Y coordinate of the left joystick (-1 is DOWN, 1 is UP)
	 */
	public double getY()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_Y));
	}
	
	/**
	 * Corresponds to SPINNY THING input on the joystick.
	 *
	 * @return The Z coordinate of the left joystick (-1 is DOWN, 1 is UP)
	 */
	public double getZ()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_Z));
	}
	
	/**
	 * Corresponds to the TRIGGER button on the joystick.
	 * @return
	 */
	public boolean getTrigger()
	{
		return joystick.getRawButton(BUTTON_TRIGGER);
	}
	
	public boolean getButton(int button)
	{
		return joystick.getRawButton(button);
	}
}
