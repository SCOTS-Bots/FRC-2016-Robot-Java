package org.scotsbots.stronghold;

import org.scotsbots.robotbase.RobotHardware;
import org.scotsbots.robotbase.RobotOperation;
import org.scotsbots.robotbase.RobotVision;
import org.scotsbots.robotbase.RobotVisionDualUSB;
import org.scotsbots.robotbase.utils.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotHardwarePrototypeBot extends RobotHardware
{
	public Victor leftMotor;
	
	public Victor rightMotor;

	public Victor shooterMotor;
	public Victor intake;
	
	private int autoShootTimer = 0;
	private boolean autoFireMode = false;
	
	public Talon tapeVertical;
	public Talon tapeHorizontal;
	public Talon winch;
	
	public Spark shroud;
	
	//-1 to 1
	public double speed = 1;
	public double scoopSpeed = 1;
	
	boolean camSwitchBump = false;
	int camSwitchBumpTime = 0;
	
	@Override
	public void initialize()
	{
		vision = new RobotVision("10.47.76.20", "cam3");
		dualUSBVision = new RobotVisionDualUSB("cam3", "cam5");
		
		leftMotor = new Victor(0);
		rightMotor = new Victor(1); 
		drivetrain = new RobotDrive(leftMotor, rightMotor);
		
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		
		shooterMotor = new Victor(2);  
		intake = new Victor(3);     
		shroud = new Spark(4);      
		winch = new Talon(5);          
		
		tapeVertical = new Talon(7);
		tapeHorizontal = new Talon(8);
		
		autoFireMode = false;
		
		addAuton(new AutonStrategyDriveRockwallPos5());
		addAuton(new AutonStrategyDriveRockwallPos34());
		addAuton(new AutonStrategyDriveRoughTerrain());
		addAuton(new AutonStrategyLowbarPortcullis());
		addAuton(new AutonStrategyRockwallLowGoal());
		addAuton(new AutonStrategyDriveLowbarLowgoal());
		addAuton(new AutonStrategyDriveCheval());
		addAuton(new AutonStrategyDriveMoat());
		
		speed = 1;
		scoopSpeed = 1;
		SmartDashboard.putNumber("Speed", speed);
		SmartDashboard.putNumber("Scoop Speed", scoopSpeed);
		
		camSwitchBump = false;
		camSwitchBumpTime = 0;
	}

	public void teleopInit()
	{
		SmartDashboard.putNumber("Speed", speed);
		SmartDashboard.putNumber("Scoop Speed", scoopSpeed);
		camSwitchBump = false;
		camSwitchBumpTime = 0;
	}
	
	@Override
	public void teleop()
	{
		RobotOperation.driveTank(1, SmartDashboard.getNumber("Speed"));
		
		if(Gamepad.secondaryAttackJoystick.getRightT())
		{
			intake.set(-1);
		}
		else if(Gamepad.secondaryAttackJoystick.getRB())
		{
			intake.set(1);
		}
		else
		{
			intake.set(0);
		}
		
		if(Gamepad.secondaryAttackJoystick.getLeftT())
		{
			shooterMotor.set(1);
		}
		else if(Gamepad.secondaryAttackJoystick.getLB())
		{
			shooterMotor.set(-1);
		}
		else
		{
			shooterMotor.set(0);
		}	
		
		//auto shoot
		if(Gamepad.secondaryAttackJoystick.getY())
		{
			//if it is in the right location
			autoFireMode = true;
		}
		
		if(autoFireMode)
		{
			if(autoShoot())
			{
				autoFireMode = false;
			}
		}
		
		if(Gamepad.secondaryAttackJoystick.getA())
		{
			winch.set(-1);
		}
		else if(Gamepad.secondaryAttackJoystick.getStart())
		{
			winch.set(1);
		}
		else
		{
			winch.set(0);
		}
								
		tapeVertical.set(Gamepad.secondaryAttackJoystick.getRightY() * -1);
		tapeHorizontal.set(Gamepad.secondaryAttackJoystick.getRightX() * 0.5);
		
		if(camSwitchBump && Gamepad.secondaryAttackJoystick.getSelect())
		{	
			dualUSBVision.switchCameras();
			camSwitchBump = false;
		}
		
		if(!camSwitchBump)
		{
			camSwitchBumpTime++;
			if(camSwitchBumpTime >100)
			{
				camSwitchBump = true;
				camSwitchBumpTime = 0;
			}
		}
		
		shroud.set(Gamepad.secondaryAttackJoystick.getLeftY() * SmartDashboard.getNumber("Scoop Speed"));
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
			intake.set(1);
		}
		else if(autoShootTimer > 50 && autoShootTimer < 150)
		{
			intake.set(-1);
		}
		else
		{
			intake.set(0);
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
	
	public void logSmartDashboard()
	{
		SmartDashboard.putNumber("Speed", speed);
		super.logSmartDashboard();
	}
	
	public boolean usesDualUSBCameras()
	{
		return true;
	}
	
	@Override
	public String getName()
	{
		return "Prototype";
	}
}
