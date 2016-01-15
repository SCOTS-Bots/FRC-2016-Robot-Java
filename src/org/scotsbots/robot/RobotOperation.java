package org.scotsbots.robot;

import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;

/**
 * Static class for basic robot operation calls.
 * @author Domenic
 *
 */
public class RobotOperation 
{	
	public static PIDController encoderControl = new PIDController(.1, .003,0, new PIDSource() 
	{
		public double pidGet() 
		{
			return getDistance();
		}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType()
		{
			// TODO Auto-generated method stub
			return null;
		}
	}, new PIDOutput() 
	{
		public void pidWrite(double d) 
		{
			Robot.bot.drivetrain.tankDrive(-d * 0.66, -d * 0.66);
		}
	});
	
	/**
	 * Sets up Encoders and Gyro.
	 */
	public static void initialize()
	{	
		
		encoderControl.setPercentTolerance(5);
		
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.setDistancePerPulse(0.042);
			Robot.bot.rightDriveEncoder.setDistancePerPulse(0.042);
		}		
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			Robot.bot.forwardDriveEncoder.setDistancePerPulse(0.037);
			Robot.bot.sideDriveEncoder.setDistancePerPulse(0.037);
		}
	}
	
	/**
	 * Drives tank drive.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveTank(int joystickSet)
	{
		driveTank(joystickSet, 1);
	}
	
	/**
	 * Drives in tank, multiplies speed by speed ratio.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad, 2 - only one gamepad
	 * @param speedRatio range of motor power
	 */
	public static void driveTank(int joystickSet, double speedRatio)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.tankDrive(Gamepad.primaryGamepad.getLeftY() * speedRatio, Gamepad.primaryGamepad.getRightY() * speedRatio, true);
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.tankDrive(Gamepad.primaryLeftAttackJoystick.getY()* speedRatio, Gamepad.primaryRightAttackJoystick.getY()* speedRatio, true);
		}
		if(joystickSet == 2)
		{
			Robot.bot.drivetrain.tankDrive(Gamepad.primaryGamepad.getLeftY() * speedRatio, Gamepad.primaryGamepad.getRightY() * speedRatio, true);
		}
		
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and the right up/down
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveDoubleStickArcade(int joystickSet)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryGamepad.getLeftY(), Gamepad.primaryGamepad.getRightX());
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryLeftAttackJoystick.getY(), Gamepad.primaryRightAttackJoystick.getX());
		}
		
        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	/**
	 * Drives the robot in arcade using the left up/down and left left/right
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveSingleStickArcade(int joystickSet)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryGamepad.getLeftY(), Gamepad.primaryGamepad.getLeftX()); 
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.arcadeDrive(Gamepad.primaryLeftAttackJoystick.getY(), Gamepad.primaryLeftAttackJoystick.getX());
		}
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static void driveMecanum(int joystickSet)
	{
		driveMecanum(joystickSet, 1);
	}
	
	/**
	 * Drives mecanum using left left/right, left up/down, and right left/right.
	 * @param joystickSet 0 - Only gamepads, 1 - attacks and gamepad
	 */
	public static void driveMecanum(int joystickSet, double speedRatio)
	{
		if(joystickSet == 0)
		{
			Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primaryGamepad.getLeftX() * speedRatio, Gamepad.primaryGamepad.getLeftY() * speedRatio, Gamepad.primaryGamepad.getRightY() * speedRatio, 0);       
		}
		if(joystickSet == 1)
		{
			Robot.bot.drivetrain.mecanumDrive_Cartesian(Gamepad.primaryLeftAttackJoystick.getX() * speedRatio, Gamepad.primaryLeftAttackJoystick.getY() * speedRatio, Gamepad.primaryRightAttackJoystick.getY() * speedRatio, 0);       
		}
		Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
	}
	
	public static double time = 0;
	public static double timeReverse = 0;

	/**
	 * Drives straight using gyro. Checks if there from accelerometer.
	 * @param distance
	 */
	public static boolean driveTimed(double timeToTravel)
	{
		if(time/100 <= timeToTravel)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
			time++;
			return false;
		}
		return true;
	}
	
	public static boolean driveTimedReverse(double timeToTravel)
	{
		if(timeReverse/100 <= timeToTravel)
		{
			Robot.bot.drivetrain.drive(0.4, 0);
			timeReverse++;
			return false;
		}
		return true;
	}
		
	public static void turn(float angle)
	{
	    //TODO write this
	}
	
	/**
	 * Doesn't work.
	*/
	public static void turnTimedMillis(double timeToTravel)
	{		
		while (time/100 <= timeToTravel)
		{
			Robot.bot.drivetrain.arcadeDrive(0, 0.5);
			Timer.delay(0.5);	// wait .5s to avoid hogging CPU cycles
		}
		time++;
	}
	
	/**
	 * Drives using drive encoders if they exist.
	 * @param distance
	 * @return
	 */
	public static boolean driveEncoded(double distance)
	{		
		//Checks for dual or single drive encoders
		if((Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null) || (Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null))
		{
			encoderControl.enable();
			encoderControl.setSetpoint(distance);
			if(encoderControl.onTarget())
			{
				Robot.bot.drivetrain.stopMotor();
				return true;
			}
			else
			{
		        Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
			}
		}
		return false;
	}
	
	public static void reset()
	{
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			Robot.bot.leftDriveEncoder.reset();
			Robot.bot.rightDriveEncoder.reset();
			
			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}	
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			Robot.bot.forwardDriveEncoder.reset();
			Robot.bot.sideDriveEncoder.reset();

			if(encoderControl != null)
			{
				encoderControl.reset();
			}
		}
		time = 0;
		timeReverse = 0;
	}
	
	/**
	 * Gets distance of drive encoder(s).
	 * @returns negative 1 if there are no encoders initialized.
	 */
	public static double getDistance()
	{
		if(Robot.bot.leftDriveEncoder != null && Robot.bot.rightDriveEncoder != null)
		{
			return (Robot.bot.leftDriveEncoder.getDistance() + Robot.bot.rightDriveEncoder.getDistance())/2;
		}
		if(Robot.bot.forwardDriveEncoder != null && Robot.bot.sideDriveEncoder != null)
		{
			return Robot.bot.forwardDriveEncoder.getDistance();
		}
		return 0;
	}
}
