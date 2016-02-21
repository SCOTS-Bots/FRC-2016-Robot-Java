package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;
import org.scotsbots.robotbase.RobotVision;
import org.scotsbots.robotbase.utils.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;

public class RobotHardwareCompbot extends RobotHardware
{
	public Victor leftBackMotor;
	public Victor leftFrontMotor;

	public Victor rightBackMotor;
	public Victor rightFrontMotor;

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
		leftBackMotor = new Victor(2);
		leftFrontMotor = new Victor(0);
		rightBackMotor = new Victor(1);
		rightFrontMotor = new Victor(3);
		drivetrain = new RobotDrive(leftBackMotor, leftFrontMotor, rightBackMotor, rightFrontMotor);
		
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		
		shooterMotor = new Victor(5);
		scoopMotor = new Victor(4);
		scoopGate = new Victor(7);
		winch = new Victor(8);
		
		manipulator = new Spark(6);
		tapeVertical = new Spark(9);
		tapeHorizontal = new Spark(10);
		
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
			manipulator.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getX())
		{
			manipulator.set(-1);
		}
		else
		{
			manipulator.set(0);
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadUp())
		{
			tapeVertical.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getDPadDown())
		{
			tapeVertical.set(-1);
		}
		else
		{
			tapeVertical.set(0);
		}
		
		if(Gamepad.secondaryAttackJoystick.getDPadRight())
		{
			tapeHorizontal.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getDPadLeft())
		{
			tapeHorizontal.set(-1);
		}
		else
		{
			tapeHorizontal.set(0);
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
		
		scoopGate.set(Gamepad.secondaryAttackJoystick.getLeftY(
	}

	/**
	 * 
	 * @return Done shooting?
	 */
	public boolean autoShoot()
	{
		autoShootTimer++;
		if(autoShootTimer < 5)
		{
			scoopMotor.set(1);
		}
		else if(autoShootTimer > 50 && autoShootTimer < 150)
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
	public AutonStrategy getSwitchedAuton()
	{
		return new AutonStrategyLowbar();
	}

	@Override
	public String getName()
	{
		return "Compbot";
	}
}
