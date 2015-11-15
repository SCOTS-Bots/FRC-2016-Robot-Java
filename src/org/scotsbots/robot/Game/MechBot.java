package org.scotsbots.robot.Game;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class MechBot extends RobotHardware
{
	public Victor leftFront, leftBack, rightFront, rightBack;

	public void initialize() {
		leftFront = new Victor(0);
		leftBack = new Victor(1);
		rightFront = new Victor(2);
		rightBack = new Victor(3);
		drivetrain = new RobotDrive(leftFront,leftBack,rightFront,rightBack);
	}

	public void teleop() {
		RobotOperation.driveMecanum(1);
	}

	public AutonStrategy getSwitchedAuton() {
		return null;
	}

	public String getName() {
		return "Mec Bot";
	}

}
