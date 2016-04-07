package org.scotsbots.stronghold;

import org.scotsbots.robotbase.AutonStrategy;
import org.scotsbots.robotbase.Robot;

public class AutonStrategyLowbarPortcullis extends AutonStrategy
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
		if(time >= 0 && time <= 30)
		{
			Robot.bot.drivetrain.drive(-0.2, 0);
			if(Robot.bot instanceof RobotHardwareCompbot)
			{
				RobotHardwareCompbot protobot = (RobotHardwareCompbot)Robot.bot;
				protobot.shroud.set(0.75);
			}
		}
		else if(time >= 30 && time <= 90)
		{
			Robot.bot.drivetrain.drive(-0.4, 0);
		}
		else if(time >= 91 && time <= 200)
		{
			Robot.bot.drivetrain.drive(-0.6, 0);
		}
		else
		{
			Robot.bot.drivetrain.drive(0, 0);
		}
	}

	@Override
	public String getName()
	{
		return "Lowbar Drive Timed/Portcullis Drive Timed";
	}

}
