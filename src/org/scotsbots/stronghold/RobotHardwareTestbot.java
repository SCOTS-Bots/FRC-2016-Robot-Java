package org.scotsbots.stronghold;

import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;
import org.scotsbots.robotbase.RobotVision;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

public class RobotHardwareTestbot extends RobotHardware
{
	public Talon leftMotors;
	public Talon rightMotors;
	
	@Override
	public void initialize()
	{
		vision = new RobotVision("10.47.76.20", "cam1");
		
		leftMotors = new Talon(0);
		rightMotors = new Talon(1);
		drivetrain = new RobotDrive(leftMotors, rightMotors);
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
	}

	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1);
	}

	@Override
	public String getName()
	{
		return "Testbot";
	}
	
	public boolean usesSingleUSBCamera()
	{
		return true;
	}

}
