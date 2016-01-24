package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class RobotHardwareCompbot extends RobotHardware
{
	public Victor leftBackMotor;
	public Victor rightBackMotor;
	public Victor leftFrontMotor;
	public Victor rightFrontMotor;
	
	@Override
	public void initialize()
	{
		leftBackMotor = new Victor(0);
		rightBackMotor = new Victor(1);
		leftFrontMotor = new Victor(2);
		rightFrontMotor = new Victor(3);
		drivetrain = new RobotDrive(leftBackMotor, leftFrontMotor,rightBackMotor,rightFrontMotor);
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
		return "Compbot";
	}

}
