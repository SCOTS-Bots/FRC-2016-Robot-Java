package org.scotsbots.robot.Game;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;
import org.scotsbots.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class MechBot extends RobotHardware
{
	public Victor leftFront, leftBack, rightFront, rightBack;
	public double speedRatio = 1;

	public void initialize() {
		leftFront = new Victor(0);
		leftBack = new Victor(1);
		rightFront = new Victor(2);
		rightBack = new Victor(3);
		drivetrain = new RobotDrive(leftFront,leftBack,rightFront,rightBack);
	}

	public void teleop() {
		if(Gamepad.primaryRightAttackJoystick.getButton(4))
		{
			speedRatio = 0.5;
		}
		if(Gamepad.primaryRightAttackJoystick.getButton(5))
		{
			speedRatio = 1;
		}
		RobotOperation.driveMecanum(1,speedRatio);
	}

	public AutonStrategy getSwitchedAuton() {
		return null;
	}

	public String getName() {
		return "Mec Bot";
	}

}
