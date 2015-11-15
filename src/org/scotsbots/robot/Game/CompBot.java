package org.scotsbots.robot.Game;

import org.scotsbots.robot.AutonStrategy;
import org.scotsbots.robot.RobotHardware;
import org.scotsbots.robot.RobotOperation;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;



public class CompBot extends RobotHardware {
	DigitalInput limitSwitch;
	Ultrasonic ultra = new Ultrasonic(1,1);	
	public void initialize() {
=======
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
		
>>>>>>> origin/master
		
		limitSwitch = new DigitalInput(1);
		ultra.setAutomaticMode(true);
	}

	public void teleop() {
<<<<<<< HEAD
		
		while (limitSwitch.get()) {
			Timer.delay(10);
		}
=======
		RobotOperation.driveTank(0);
		
>>>>>>> origin/master
	}

	public AutonStrategy getSwitchedAuton() {
		
		return null;
	}

	public String getName() {
		
		return "CompBot";
	}

}
