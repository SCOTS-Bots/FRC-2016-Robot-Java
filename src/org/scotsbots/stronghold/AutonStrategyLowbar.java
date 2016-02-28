package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyLowbar extends AutonStrategy
{
	
	public int time = 0;
	public boolean autoFireMode = false;
	public int autoShootTimer = 0;
	public boolean stopped = false;

	@Override
	public void intialize()
	{
		stopped = false;
	}

	@Override
	public void update()
	{
		if(!stopped)
		{
			time++;
		}
		
		if(time >= 0 && time <= 100)
		{
			Robot.bot.drivetrain.drive(0.6, 0);
		}
		else if(time >= 100 && time <= 110)
		{
			Robot.bot.drivetrain.drive(0.5, 0.5);
		}
		else if(time >= 111 && time <= 175)
		{
			Robot.bot.drivetrain.drive(0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
		if(time >= 200)
		{
			if(Robot.bot instanceof RobotHardwarePrototypeBot)
			{
				RobotHardwarePrototypeBot protobot = (RobotHardwarePrototypeBot)Robot.bot;
							
				if(protobot.autoShoot())
				{
					time = 0;
					stopped = true;
				}
			}
			
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
							
				if(protobot.autoShoot())
				{
					time = 0;
					stopped = true;
				}
			}
		}
	}

	@Override
	public String getName()
	{
		return "Lowbar Drive Timed";
	}

}
