package org.scotsbots.robotbase.utils;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Replaces WPILib joystick class for easily acessing buttons.
 * <strong>Configured for 'D' switch setting on back of controller!</strong>
 * <br> Originally adapted from Adambots code.
 * @author Nathan Fenner
 * @author Domenic Portuesi
 *
 */
public class Gamepad
{
	public Joystick joystick;

	/**
	 * XBOX 360 West Face Button
	 */
	private static final int BUTTON_X = 1;

	/**
	 * XBOX 360 South Face Button
	 */
	private static final int BUTTON_A = 2;
	
	/**
	 * XBOX 360 East Face Button
	 */
	private static final int BUTTON_B = 3;
	
	/**
	 * XBOX 360 North Face Button
	 */
	private static final int BUTTON_Y = 4;
	/**
	 * XBOX 360 Left Bumper (Top)
	 */
	private static final int BUTTON_LB = 5;

	/**
	 * XBOX 360 Right Bumper (Top)
	 */
	private static final int BUTTON_RB = 6;

	/**
	 * XBOX 360 Back Button
	 */
	private static final int BUTTON_BACK = 9;

	/**
	 * XBOX 360 Start Button
	 */
	private static final int BUTTON_START = 10;
	
	/**
	 * XBOX 360 Left Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_LEFT_X = 0;

	/**
	 * XBOX 360 Left Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_LEFT_Y = 1;
	
	/**
	 * XBOX 360 Right Horizontal Axis (Left=-1, Right=1)
	 */
	private static final int AXIS_RIGHT_X = 2;

	/**
	 * XBOX 360 Right Vertical Axis (Up=-1, Down=1)
	 */
	private static final int AXIS_RIGHT_Y = 3;

	private static final int BUTTON_R3 = 12;
	
	private static final int BUTTON_L3 = 11;
	
	private static final int BUTTON_RT = 8;
	private static final int BUTTON_LT = 7;


	private Gamepad(int port)
	{
		joystick = new Joystick(port);
	}

	//HOW THEY ARE NAMED:
	//Set1 : PrimaryLeftAttack, PrimaryRightAttack, SecondaryAttack
	//set2 : Primary Gamepad, Secondary Gamepad
	//Your set of controllers is set when you called tank drive!
	//-Domenic
	public static Gamepad primaryGamepad = new Gamepad(0);
	public static AttackJoystick primaryLeftAttackJoystick = new AttackJoystick(0);
	public static AttackJoystick primaryRightAttackJoystick = new AttackJoystick(1);
	public static Gamepad secondaryGamepad = new Gamepad(1);
	public static Gamepad secondaryAttackJoystick = new Gamepad(2);

	/**
	 * Returns the value of the trigger with a deadzone.
	 * 
	 * @return
	 */
	public static double createDeadzone(double triggerValue)
	{
		return Math.abs(triggerValue) < 0.15 ? 0 : triggerValue;
	}
	
	public static double createDeadzoneTrigger(double triggerValue)
	{
		return Math.abs(triggerValue) < .5 ? 0 : triggerValue;
	}

	/**
	 * Corresponds to HORIZONTAL input on the LEFT joystick.
	 *
	 * @return The X coordinate of the left joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getLeftX()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_LEFT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the LEFT joystick.
	 *
	 * @return The Y coordinate of the LEFT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getLeftY()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_LEFT_Y));
	}

	/**
	 * Corresponds to HORIZONTAL input on the RIGHT joystick
	 *
	 * @return The X coordinate of the RIGHT joystick (-1 is LEFT, 1 is RIGHT)
	 */
	public double getRightX()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_RIGHT_X));
	}

	/**
	 * Corresponds to VERTICAL input on the RIGHT joystick
	 *
	 * @return The Y coordinate of the RIGHT joystick (-1 is UP, 1 is DOWN)
	 */
	public double getRightY()
	{
		return createDeadzone(joystick.getRawAxis(AXIS_RIGHT_Y));
	}
	
	/*
	public double getLeftT()
	{
		return createDeadzoneTrigger(joystick.getRawAxis(AXIS_LEFT_T));
	}
	*/
	public boolean getLeftT()
	{
		return joystick.getRawButton(BUTTON_LT);
	}
	/*
	public double getRightT()
	{
		return createDeadzoneTrigger(joystick.getRawAxis(AXIS_RIGHT_T));
	}
	*/
	
	public boolean getRightT()
	{
		return joystick.getRawButton(BUTTON_RT);
	}
	
	//TODO Fix these
	@Deprecated
	public boolean getDPadLeft()
	{
		return joystick.getPOV() == 270;
		//return joystick.getRawAxis(AXIS_DPAD_X) < -0.5;
	}

	@Deprecated
	public boolean getDPadRight()
	{
		return joystick.getPOV() == 90;
		//return joystick.getRawAxis(AXIS_DPAD_X) > 0.5;
	}

	@Deprecated
	public boolean getDPadUp()
	{
		return joystick.getPOV() == 0;
		//return joystick.getRawAxis(AXIS_DPAD_Y) < -0.5;
	}
	
	@Deprecated
	public boolean getDPadDown()
	{
		return joystick.getPOV() == 180;
		//return joystick.getRawAxis(AXIS_DPAD_Y) > 0.5;
	}
	
	/**
	 *
	 * @return Is the left bumper pressed? [top one]
	 */
	public boolean getLB()
	{
		return joystick.getRawButton(BUTTON_LB);
	}

	/**
	 *
	 * @return Is the right bumper pressed? [top one]
	 */
	public boolean getRB()
	{
		return joystick.getRawButton(BUTTON_RB);
	}

	public boolean getA()
	{
		return joystick.getRawButton(BUTTON_A);
	}

	public boolean getB()
	{
		return joystick.getRawButton(BUTTON_B);
	}

	public boolean getX()
	{
		return joystick.getRawButton(BUTTON_X);
	}

	public boolean getY()
	{
		return joystick.getRawButton(BUTTON_Y);
	}

	public boolean getStart()
	{
		return joystick.getRawButton(BUTTON_START);
	}

	public boolean getBack()
	{
		return joystick.getRawButton(BUTTON_BACK);
	}
	
	public boolean getR3()
	{
		return joystick.getRawButton(BUTTON_R3);
	}
	
	public boolean getL3()
	{
		return joystick.getRawButton(BUTTON_L3);
	}
}
