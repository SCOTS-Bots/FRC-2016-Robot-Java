package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyDriveLowbar extends AutonStrategy
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

		if(time > 0 && time <= 200)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;			
				protobot.intake.set(1);
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(-0.1);
			}
		}

		else if(time >= 0 && time <= 30)
		{
			Robot.bot.drivetrain.drive(-0.2, 0);
		}
		else if(time >= 0 && time <= 60)
		{
			Robot.bot.drivetrain.drive(-0.4, 0);
		}
		else if(time >= 60 && time <= 170)
		{
			Robot.bot.drivetrain.drive(-0.6, 0);
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
				protobot.shroud.set(0);
			}
		}
	}

	@Override
	public String getName()
	{
		return "Drive Straight - Lowbar";
	}

}
