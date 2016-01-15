package org.scotsbots.robot.Game;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;


import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class CompBot extends RobotHardware 
{
	public Victor LeftMotor;
	public Victor RightMotor;

	public void initialize() 
	{
		LeftMotor = new Victor (0);
		RightMotor = new Victor (1);
		drivetrain = new RobotDrive(LeftMotor,RightMotor);
		
	}

	public void teleop() {

		RobotOperation.driveTank(0);

	}

	public AutonStrategy getSwitchedAuton() {
		
		return null;
	}

	public String getName() {
		
		return "CompBot";
	}

}
