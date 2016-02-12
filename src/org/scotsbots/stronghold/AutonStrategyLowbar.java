package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;
import org.scotsbots.robotbase.utils.Gamepad;

public class AutonStrategyLowbar extends AutonStrategy
{
	
	public int time = 0;
	public boolean autoFireMode = false;
	public int autoShootTimer = 0;
	public boolean stopped = false;

	@Override
	public void intialize()
	{
		
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
			Robot.bot.drivetrain.drive(-1, 0);
		}
		else if(time >= 100 && time <= 200)
		{
			Robot.bot.drivetrain.drive(-1, 1);
		}
		else if(time >= 200)
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
		}
	}

	@Override
	public String getName()
	{
		return "Lowbar Drive Timed";
	}

}
