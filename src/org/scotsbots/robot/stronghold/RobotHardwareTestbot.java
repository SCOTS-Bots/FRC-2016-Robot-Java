package org.scotsbots.robot.stronghold;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

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
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(0);
	}

	@Override
	public AutonStrategy getSwitchedAuton()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return null;
	}

}
