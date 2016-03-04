package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;
import org.scotsbots.robotbase.RobotVision;
import org.scotsbots.robotbase.utils.Gamepad;
import org.scotsbots.robotbase.utils.Logger;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

public class RobotHardwareCompbot extends RobotHardware
{
	public Victor leftMotor;
	public Victor rightMotor;

	public Victor shooterMotor;
	public Victor scoopMotor;
	public Victor scoopGate;
	public Victor winch;
	
	private int autoShootTimer = 0;
	private boolean autoFireMode = false;
	
	public Spark manipulator;
	public Spark tapeVertical;
	public Spark tapeHorizontal;
	
	@Override
	public void initialize()
	{
		
		vision = new RobotVision("10.47.76.20", "cam0");          
		leftMotor = new Victor(0);         //changed to 0, was 1                      
		rightMotor = new Victor(1); 	   //changed to 1, was 0                    
		drivetrain = new RobotDrive(leftMotor, rightMotor);
		
		//drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);


		shooterMotor = new Victor(2);   //changed to 2, WAS 3
		scoopMotor = new Victor(4);     //changed to 4, was 2
		scoopGate = new Victor(3);      //changed to 3, was 5
		winch = new Victor(5);          //changed to 5, was 6
		
		manipulator = new Spark(6);   //changed to 6, was 4
		tapeVertical = new Spark(8);
		tapeHorizontal = new Spark(7);
		
		autoFireMode = false;
		
		addAuton(new AutonStrategyDriveReverse());
		addAuton(new AutonStrategyDumpReverse());
		addAuton(new AutonStrategyLowbarReverse());
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1);

		if(Gamepad.secondaryAttackJoystick.getRightT())
		{
			scoopMotor.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getRB())
		{
			scoopMotor.set(-1);
		}
		else
		{
			scoopMotor.set(0);
		}
		
		if(Gamepad.secondaryAttackJoystick.getLeftT())
		{
			shooterMotor.set(1);
		}
		else
		{
			shooterMotor.set(0);
		}	
		
		//auto shoot
		if(Gamepad.secondaryAttackJoystick.getY())
		{
			autoFireMode = true;
		}
		
		if(autoFireMode)
		{
			if(autoShoot())
			{
				autoFireMode = false;
			}
		}
		
		if(Gamepad.secondaryAttackJoystick.getB())
		{
			manipulator.set(-1);
		}
		else if(Gamepad.secondaryAttackJoystick.getX())
		{
			manipulator.set(1);
		}
		else
		{
			manipulator.set(0);
		}
		
		if(Gamepad.secondaryAttackJoystick.getA())
		{
			winch.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getStart())
		{
			winch.set(-1);
		}
		else
		{
			winch.set(0);
		}
				
		scoopGate.set(Gamepad.secondaryAttackJoystick.getLeftY() * 0.5);
				
		tapeVertical.set(Gamepad.secondaryAttackJoystick.getRightY() * -1);
		tapeHorizontal.set(Gamepad.secondaryAttackJoystick.getRightX() * 0.5);
	}

	/**
	 * 
	 * @return Done shooting?
	 */
	public boolean autoShoot()
	{
		autoShootTimer++;
		if(autoShootTimer < 7)
		{
			scoopMotor.set(1);
		}
		else if(autoShootTimer > 75 && autoShootTimer < 175)
		{
			scoopMotor.set(-1);
		}
		else
		{
			scoopMotor.set(0);
		}
		
		if(autoShootTimer > 5 && autoShootTimer < 150)
		{
			shooterMotor.set(1);
		}
		else	
		{
			shooterMotor.set(0);
		}		
		
		if(autoShootTimer > 150)
		{
			autoShootTimer = 0;
			return true;
		}
		return false;
	}
	
	public boolean usesSingleUSBCamera()
	{
		return true;
	}
	
	@Override
	public String getName()
	{
		return "Compbot";
	}
}
