/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2015. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/* Howell SCOTS Bots 2015 - Competition Season                                */
/*----------------------------------------------------------------------------*/

package org.scotsbots.robot;

import org.scotsbots.robot.Game.CompBot;
import org.scotsbots.robot.Game.MechBot;
import org.scotsbots.robot.utils.Logger;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main Class for our 2015 Robot
 * @author Domenic
 *
 */
public class Robot extends IterativeRobot 
{
	public static AutonStrategy selectedAuton = null;
	public static RobotHardware bot = null;	
	
    public void robotInit() 
    {
    	Logger.riolog("S.C.O.T.S. Bots 2015 Robot intializing...");
    	bot = new MechBot(); //This changes which bot it loads. TODO Add abstraction way of doing this.
    	bot.initialize();
    	RobotOperation.initialize();
    	if(bot.usesCamera())
    	{
    		RobotVision.initialize();
    	}
		Logger.riolog("S.C.O.T.S. Bots 2015 Robot intialized.");
    }
    
    public void autonomousInit()
    {
    	RobotOperation.reset();
    	//Switches: A1 = true true
    	selectedAuton = bot.getSwitchedAuton();
    	selectedAuton.intialize();
    }
    
    public void autonomousPeriodic() 
    {
    	selectedAuton.update();
    	bot.logSmartDashboard();
    	//tes comment
    }
    
    public void teleopInit()
    {
    	RobotOperation.reset();
    }
    
    public void teleopPeriodic() 
    {
    	if(bot.usesCamera())
    	{
    		RobotVision.stream();
    	}
		bot.teleop();
    	bot.logSmartDashboard();
    }

    public void testInit()
    {
    	RobotOperation.reset();
    }
    
    public void testPeriodic() 
    {
    	LiveWindow.run();
    	bot.logSmartDashboard();
    }
    
    public void disabledInit() 
    {
		RobotOperation.reset();
    }
    
    public void disabledPeriodic()
    {
    	if(bot.getSwitchedAuton() != null)
    	{
    		SmartDashboard.putString("Auton Mode Switched", bot.getSwitchedAuton().getName());
    	}
    }
}
