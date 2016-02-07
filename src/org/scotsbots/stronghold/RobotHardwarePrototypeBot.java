package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;
import org.scotsbots.robotbase.RobotVision;
import org.scotsbots.robotbase.utils.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;

public class RobotHardwarePrototypeBot extends RobotHardware
{
	public Victor leftBackMotor;
	public Victor leftFrontMotor;

	public Victor rightBackMotor;
	public Victor rightFrontMotor;

	public Victor shooterMotor;
	public Victor scoopMotor;
	
	private int autoShootTimer = 0;
	private boolean autoFireMode = false;
	
	@Override
	public void initialize()
	{
		vision = new RobotVision("", "cam0");
		leftBackMotor = new Victor(2);
		leftFrontMotor = new Victor(0);
		rightBackMotor = new Victor(1);
		rightFrontMotor = new Victor(3);
		drivetrain = new RobotDrive(leftBackMotor, leftFrontMotor, rightBackMotor, rightFrontMotor);
		
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		
		shooterMotor = new Victor(5);
		scoopMotor = new Victor(4);
		
		autoFireMode = false;
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
			//if it is in the right location //TODO add when sensor is addded
			autoFireMode = true;
		}
		
		if(autoFireMode == true)
		{
			autoShootTimer++;
			if(autoShootTimer < 25)
			{
				scoopMotor.set(-1);
			}
			else if(autoShootTimer > 50 && autoShootTimer < 150)
			{
				scoopMotor.set(1);
			}
			else
			{
				scoopMotor.set(0);
			}
			
			if(autoShootTimer > 25 && autoShootTimer < 150)
			{
				shooterMotor.set(1);
			}
			else	
			{
				shooterMotor.set(0);
			}		
			
			if(autoShootTimer > 225)
			{
				autoFireMode = false;
				autoShootTimer = 0;
			}
		}

	}

	public boolean usesDualUSBCameras()
	{
		return true;
	}
	
	@Override
	public AutonStrategy getSwitchedAuton()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "Prototype";
	}
}
