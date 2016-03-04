/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2016 - Competition Season                                */
/*----------------------------------------------------------------------------*/

package org.scotsbots.robotbase;

import org.scotsbots.robotbase.utils.Logger;
import org.scotsbots.stronghold.RobotHardwareCompbot;
import org.scotsbots.stronghold.RobotHardwarePrototypeBot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main Class for Robot Base code.
 * @author Domenic
 *
 */
public class Robot extends IterativeRobot 
{
	public static AutonStrategy selectedAuton = null;
	public static RobotHardware bot = null;	
	public SendableChooser auton;
	
	public static RobotVisionDualUSB cameraFeeds;
	
    public void robotInit() 
    {
    	Logger.riolog("S.C.O.T.S. Bots Robot Base code Intializing.");
    	bot = new RobotHardwarePrototypeBot(); //This changes which bot it loads. TODO Add abstraction way of doing this.
    	bot.initialize();
    	RobotOperation.initialize();
    	if(bot.usesIPCamera() && bot.vision != null)
    	{
    		bot.vision.initialize();
    	}
    	if(bot.usesSingleUSBCamera() && bot.vision != null)
    	{
    		bot.vision.initUSBCam();
    	}
    	if(bot.usesDualUSBCameras())
    	{
    		cameraFeeds = new RobotVisionDualUSB();
    		cameraFeeds.init();
    	}
    	auton = new SendableChooser();
    	auton.addDefault("Nothing", new AutonStrategyDoNothing());
    	for(int i = 0; i < Robot.bot.autons.size(); i++)
    	{
    		auton.addObject(Robot.bot.autons.get(i).getName(), Robot.bot.autons.get(i));
    	}
		Logger.riolog("S.C.O.T.S. Bots Robot Base code intialized.");

    }
    
    public void autonomousInit()
    {
    	RobotOperation.reset();
    	//selectedAuton = bot.getSwitchedAuton(); old non SD auton switching
    	if(auton.getSelected() instanceof AutonStrategy)
    	{
    		selectedAuton = (AutonStrategy) auton.getSelected();
    	}
    	selectedAuton.intialize();
    }
    
    public void autonomousPeriodic() 
    {
    	selectedAuton.update();
    	bot.logSmartDashboard();
    	if(bot.usesDualUSBCameras())
    	{
    	//	cameraFeeds.run();
    	}
    }
    
    public void teleopInit()
    {
    	RobotOperation.reset();
    }
    
    public void teleopPeriodic() 
    {
    	if(bot.usesIPCamera() && bot.vision != null)
    	{
    		bot.vision.stream();
    	}
		bot.teleop();
    	bot.logSmartDashboard();
    	if(bot.usesDualUSBCameras())
    	{
  //  		cameraFeeds.run();
    	}
    }

    public void testInit()
    {
    	RobotOperation.reset();
    }
    
    public void testPeriodic() 
    {
    	LiveWindow.run();
    	bot.logSmartDashboard();
    	if(bot.usesDualUSBCameras())
    	{
    		cameraFeeds.updateCam();
    	}
    }
    
    public void disabledInit() 
    {
		RobotOperation.reset();
		if(bot.usesDualUSBCameras())
		{
			cameraFeeds.end();
		}
    }
    
    public void disabledPeriodic()
    {
    	/* From old non SD code
    	if(selectedAuton != null)
    	{
    		SmartDashboard.putString("Auton Mode Switched", selectedAuton.getName());
    		
    	}
    	*/
    	SmartDashboard.putData("Auton Modes", auton);
    }
}
