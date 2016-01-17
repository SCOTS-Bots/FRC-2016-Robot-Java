package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

public class RobotHardwareTestbot extends RobotHardware
{
	public Talon right;
	public Talon left;
	
	@Override
	public void initialize()
	{
		right = new Talon(0);
		left = new Talon(1);
		drivetrain = new RobotDrive(left, right);
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1);
	}

	@Override
	public AutonStrategy getSwitchedAuton()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "Test bot";
	}

}
