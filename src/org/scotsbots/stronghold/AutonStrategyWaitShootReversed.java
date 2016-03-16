package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyWaitShootReversed extends AutonStrategy
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
		
		if(time >= 500 && time <= 600)
		{
			Robot.bot.drivetrain.drive(0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
		if(time >= 600)
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
		return "Wait Drive Straight Shoot - Reverse";
	}

}
