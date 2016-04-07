package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyRockwallLowGoal extends AutonStrategy
{
	public int time = 0;
	
	@Override
	public void intialize()
	{
		time = 0;
	}

	@Override
	public void update()
	{
		time++;
		if(time >= 0 && time <= 30)
		{
			Robot.bot.drivetrain.drive(-0.25, 0);
		}
		else if(time >= 0 && time <= 60)
		{
			Robot.bot.drivetrain.drive(-0.5, 0);
		}
		else if(time >= 60 && time <= 190)
		{
			Robot.bot.drivetrain.drive(-0.7, 0);
		}
		else if(time >= 191 && time <= 215)
		{
			Robot.bot.drivetrain.tankDrive(0.7, -0.7);
		}
		else if(time >= 216 && time <=280)
		{
			Robot.bot.drivetrain.drive(-0.35, 0);
		}
		else if(time > 280 && time <= 350)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(1);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.intake.set(1);
			}
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
			
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(0);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.intake.set(0);
			}
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight - Rockwall (pos 5) - Lowgoal";
	}

}
